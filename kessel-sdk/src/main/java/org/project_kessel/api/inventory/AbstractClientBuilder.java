package org.project_kessel.api.inventory;

import com.nimbusds.jose.util.Pair;
import io.grpc.*;
import io.grpc.stub.AbstractAsyncStub;
import io.grpc.stub.AbstractStub;
import org.project_kessel.api.auth.OAuth2ClientCredentials;
import org.project_kessel.api.grpc.OAuth2CallCredentials;

import java.util.Objects;
import java.util.function.BiFunction;

public abstract class AbstractClientBuilder<BT extends AbstractStub<BT>, AT extends AbstractAsyncStub<AT>> {

    private final String target;
    private ChannelCredentials channelCredentials;
    private CallCredentials callCredentials;
    private BiFunction<String, ChannelCredentials, ManagedChannelBuilder<?>> createChannelBuilder;



    public AbstractClientBuilder(String target) {
        Objects.requireNonNull(target, "target must not be null");
        this.target = target;
    }

    public AbstractClientBuilder<BT, AT> oauth2ClientAuthenticated(OAuth2ClientCredentials oAuth2ClientCredentials, ChannelCredentials channelCredentials) {
        Objects.requireNonNull(oAuth2ClientCredentials, "oAuth2ClientCredentials must not be null");
        this.callCredentials = OAuth2CallCredentials.oauth2CallCredentials(oAuth2ClientCredentials);
        this.channelCredentials = channelCredentials;
        this.validateCredentials();
        return this;
    }

    public AbstractClientBuilder<BT, AT> oauth2ClientAuthenticated(OAuth2ClientCredentials oAuth2ClientCredentials) {
        return oauth2ClientAuthenticated(oAuth2ClientCredentials, null);
    }

    public AbstractClientBuilder<BT, AT> authenticated(CallCredentials callCredentials, ChannelCredentials channelCredentials) {
        this.callCredentials = callCredentials;
        this.channelCredentials = channelCredentials;
        this.validateCredentials();
        return this;
    }

    public AbstractClientBuilder<BT, AT> authenticated(CallCredentials callCredentials) {
        return authenticated(callCredentials, null);
    }


    public AbstractClientBuilder<BT, AT> authenticated() {
        return authenticated(null, null);
    }

    public AbstractClientBuilder<BT, AT> unauthenticated(ChannelCredentials channelCredentials) {
        this.callCredentials = null;
        this.channelCredentials = channelCredentials;
        this.validateCredentials();
        return this;
    }

    public AbstractClientBuilder<BT, AT> unauthenticated() {
        return this.unauthenticated(null);
    }

    public AbstractClientBuilder<BT, AT> insecure() {
        this.callCredentials = null;
        this.channelCredentials = InsecureChannelCredentials.create();
        return this;
    }

    public AbstractClientBuilder<BT, AT> createChannelBuilder(BiFunction<String, ChannelCredentials, ManagedChannelBuilder<?>> createChannelBuilder) {
        Objects.requireNonNull(createChannelBuilder, "createChannelBuilder must not be null");
        this.createChannelBuilder = createChannelBuilder;
        return this;
    }

    public Pair<BT, ManagedChannel> build() {
        ManagedChannel channel = this.buildChannel();
        return Pair.of(this.newStub(channel), channel);
    }

    public Pair<AT, ManagedChannel> buildAsync() {
        ManagedChannel channel = this.buildChannel();
        return Pair.of(this.newAsyncStub(channel), channel);
    }

    private ManagedChannel buildChannel() {
        ChannelCredentials channelCredentials = this.channelCredentials;
        if (channelCredentials == null) {
            channelCredentials = TlsChannelCredentials.create();
        }

        if (this.callCredentials != null) {
            channelCredentials = CompositeChannelCredentials.create(channelCredentials, this.callCredentials);
        }

        ManagedChannelBuilder<?> channelBuilder;
        if (this.createChannelBuilder == null) {
            channelBuilder = Grpc.newChannelBuilder(target, channelCredentials);
        } else {
            channelBuilder = this.createChannelBuilder.apply(target, channelCredentials);
        }

        return channelBuilder.build();
    }

    private boolean isChannelCredentialsSecure(ChannelCredentials channelCredentials) {
        if (channelCredentials instanceof TlsChannelCredentials) {
            return true;
        } else if (channelCredentials instanceof CompositeChannelCredentials) {
            return isChannelCredentialsSecure(((CompositeChannelCredentials) channelCredentials).getChannelCredentials());
        } else if (channelCredentials instanceof ChoiceChannelCredentials) {
            return ((ChoiceChannelCredentials) channelCredentials).getCredentialsList().stream().allMatch(this::isChannelCredentialsSecure);
        }

        return false;
    }

    private void validateCredentials() {
        if (this.channelCredentials != null && !isChannelCredentialsSecure(this.channelCredentials) && this.callCredentials != null) {
            throw new IllegalStateException("Invalid credential configuration: can not authenticate with insecure channel");
        }
    }

    protected abstract BT newStub(Channel channel);
    protected abstract AT newAsyncStub(Channel channel);

}
