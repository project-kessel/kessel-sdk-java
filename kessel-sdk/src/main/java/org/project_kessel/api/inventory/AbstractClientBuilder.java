package org.project_kessel.api.inventory;

import io.grpc.*;
import io.grpc.stub.AbstractAsyncStub;
import io.grpc.stub.AbstractStub;
import org.project_kessel.api.auth.OAuth2ClientCredentials;
import org.project_kessel.api.grpc.OAuth2CallCredentials;

import java.time.Duration;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public abstract class AbstractClientBuilder<BT extends AbstractStub<BT>, AT extends AbstractAsyncStub<AT>> {

    private final String target;
    private ChannelCredentials channelCredentials;
    private CallCredentials callCredentials;
    private long keepaliveIntervalNanos = TimeUnit.SECONDS.toNanos(45);
    private long keepaliveTimeoutNanos = TimeUnit.SECONDS.toNanos(10);
    private boolean keepalivePermitWithoutCalls = true;


    public AbstractClientBuilder(String target) {
        Objects.requireNonNull(target, "target must not be null");
        this.target = target;
    }

    /**
     * Sets the client's keepalive interval. The value must be non-null, positive, and convertible
     * to nanoseconds without overflow. gRPC Java may clamp intervals below its 10-second minimum.
     *
     * @param interval keepalive interval
     * @return this builder
     * @throws NullPointerException if interval is null
     * @throws IllegalArgumentException if interval is not positive or cannot be represented in nanoseconds
     */
    public AbstractClientBuilder<BT, AT> keepaliveInterval(Duration interval) {
        long intervalNanos = positiveNanos("keepaliveInterval", interval);
        this.keepaliveIntervalNanos = intervalNanos;
        return this;
    }

    /**
     * Sets the client's keepalive timeout. The value must be non-null, positive, and convertible
     * to nanoseconds without overflow. gRPC Java may clamp timeouts below its 10-millisecond minimum.
     *
     * @param timeout keepalive timeout
     * @return this builder
     * @throws NullPointerException if timeout is null
     * @throws IllegalArgumentException if timeout is not positive or cannot be represented in nanoseconds
     */
    public AbstractClientBuilder<BT, AT> keepaliveTimeout(Duration timeout) {
        long timeoutNanos = positiveNanos("keepaliveTimeout", timeout);
        this.keepaliveTimeoutNanos = timeoutNanos;
        return this;
    }

    /**
     * Sets whether keepalive pings may be sent when there are no active calls. The default is true.
     * This option does not change server-side keepalive enforcement policy.
     *
     * @param permitWithoutCalls whether pings are permitted without active calls
     * @return this builder
     */
    public AbstractClientBuilder<BT, AT> keepalivePermitWithoutCalls(boolean permitWithoutCalls) {
        this.keepalivePermitWithoutCalls = permitWithoutCalls;
        return this;
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

    public ClientBuildResult<BT> build() {
        ManagedChannel channel = this.buildChannel();
        return new ClientBuildResult<>(this.newStub(channel), channel);
    }

    public ClientBuildResult<AT> buildAsync() {
        ManagedChannel channel = this.buildChannel();
        return new ClientBuildResult<>(this.newAsyncStub(channel), channel);
    }

    private ManagedChannel buildChannel() {
        ChannelCredentials channelCredentials = this.channelCredentials;
        if (channelCredentials == null) {
            channelCredentials = TlsChannelCredentials.create();
        }

        if (this.callCredentials != null) {
            channelCredentials = CompositeChannelCredentials.create(channelCredentials, this.callCredentials);
        }

        return Grpc.newChannelBuilder(target, channelCredentials)
                .keepAliveTime(keepaliveIntervalNanos, TimeUnit.NANOSECONDS)
                .keepAliveTimeout(keepaliveTimeoutNanos, TimeUnit.NANOSECONDS)
                .keepAliveWithoutCalls(keepalivePermitWithoutCalls)
                .build();
    }

    private static long positiveNanos(String optionName, Duration duration) {
        Objects.requireNonNull(duration, optionName + " must not be null");
        if (duration.isZero() || duration.isNegative()) {
            throw new IllegalArgumentException(optionName + " must be positive");
        }

        try {
            return duration.toNanos();
        } catch (ArithmeticException exception) {
            throw new IllegalArgumentException(optionName + " must be representable in nanoseconds", exception);
        }
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
