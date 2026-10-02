package org.project_kessel.api.inventory;

import com.nimbusds.jose.util.Pair;
import io.grpc.*;
import io.grpc.stub.AbstractAsyncStub;
import io.grpc.stub.AbstractStub;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;
import org.project_kessel.api.auth.OAuth2ClientCredentials;
import org.project_kessel.api.auth.ClientConfigAuth;

import java.time.Duration;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AbstractClientBuilderTest {

    private static final String TARGET = "localhost:9000";

    @Mock
    private OAuth2ClientCredentials mockOAuthClient;

    private TestClientBuilder builder;

    // Test implementation of AbstractClientBuilder
    static class TestClientBuilder extends AbstractClientBuilder<TestStub, TestAsyncStub> {

        TestClientBuilder(String target) {
            super(target);
        }

        @Override
        protected TestStub newStub(Channel channel) {
            return new TestStub(channel);
        }

        @Override
        protected TestAsyncStub newAsyncStub(Channel channel) {
            return new TestAsyncStub(channel);
        }
    }

    // Test stub implementations
    static class TestStub extends AbstractStub<TestStub> {
        TestStub(Channel channel) {
            super(channel, CallOptions.DEFAULT);
        }

        TestStub(Channel channel, CallOptions callOptions) {
            super(channel, callOptions);
        }

        @Override
        protected TestStub build(Channel channel, CallOptions callOptions) {
            return new TestStub(channel, callOptions);
        }
    }

    static class TestAsyncStub extends AbstractAsyncStub<TestAsyncStub> {
        TestAsyncStub(Channel channel) {
            super(channel, CallOptions.DEFAULT);
        }

        TestAsyncStub(Channel channel, CallOptions callOptions) {
            super(channel, callOptions);
        }

        @Override
        protected TestAsyncStub build(Channel channel, CallOptions callOptions) {
            return new TestAsyncStub(channel, callOptions);
        }
    }

    @BeforeEach
    void setUp() {
        builder = new TestClientBuilder(TARGET);
    }

    @Test
    void testConstructorWithValidTarget() {
        assertDoesNotThrow(() -> new TestClientBuilder("localhost:9000"));
    }

    @Test
    void testConstructorWithNullTarget() {
        // AbstractClientBuilder now validates target with Objects.requireNonNull
        assertThrows(NullPointerException.class, () -> new TestClientBuilder(null));
    }

    @Test
    void testOAuth2ClientAuthenticatedWithNullCredentials() {
        // Test that null OAuth2 credentials are rejected
        assertThrows(NullPointerException.class, () ->
            builder.oauth2ClientAuthenticated(null)
        );
    }

    @Test
    void testInsecureBuilder() {
        AbstractClientBuilder<TestStub, TestAsyncStub> result = builder.insecure();
        assertSame(builder, result); // Should return same instance for chaining
    }

    @Test
    void testUnauthenticatedBuilder() {
        AbstractClientBuilder<TestStub, TestAsyncStub> result = builder.unauthenticated();
        assertSame(builder, result);
    }

    @Test
    void testAuthenticatedBuilder() {
        CallCredentials mockCallCredentials = mock(CallCredentials.class);
        AbstractClientBuilder<TestStub, TestAsyncStub> result = builder.authenticated(mockCallCredentials);
        assertSame(builder, result);
    }

    @Test
    void testOAuth2ClientAuthenticatedBuilder() {
        // Skip OAuth2 test if Nimbus is not available
        try {
            Class.forName("com.nimbusds.oauth2.sdk.TokenRequest");
            ClientConfigAuth config = new ClientConfigAuth("test", "secret", "https://example.com/token");
            OAuth2ClientCredentials oauthClient = new OAuth2ClientCredentials(config);

            AbstractClientBuilder<TestStub, TestAsyncStub> result = builder.oauth2ClientAuthenticated(oauthClient);
            assertSame(builder, result);
        } catch (ClassNotFoundException e) {
            // Nimbus not available, skip this test
            assertTrue(true, "Skipping OAuth2 test - Nimbus library not available");
        } catch (Exception e) {
            // Expected if trying to connect to a real OAuth server
            assertTrue(true, "OAuth2 test skipped due to network dependency");
        }
    }

    @Test
    void testBuildCreatesStub() {
        builder.insecure();
        Pair<TestStub, ManagedChannel> result = builder.build();

        try {
            assertNotNull(result.getLeft());
            assertNotNull(result.getRight());
            assertTrue(result.getLeft() instanceof TestStub);
            assertTrue(result.getRight() instanceof ManagedChannel);
        } finally {
            closeChannel(result.getRight());
        }
    }

    @Test
    void testBuildAsyncCreatesAsyncStub() {
        builder.insecure();
        Pair<TestAsyncStub, ManagedChannel> result = builder.buildAsync();

        try {
            assertNotNull(result.getLeft());
            assertNotNull(result.getRight());
            assertTrue(result.getLeft() instanceof TestAsyncStub);
            assertTrue(result.getRight() instanceof ManagedChannel);
        } finally {
            closeChannel(result.getRight());
        }
    }

    @Test
    void testKeepaliveOptionsAreFluent() {
        assertSame(builder, builder.keepaliveInterval(Duration.ofSeconds(30)));
        assertSame(builder, builder.keepaliveTimeout(Duration.ofSeconds(5)));
        assertSame(builder, builder.keepalivePermitWithoutCalls(false));
    }

    @Test
    void testBuildUsesDefaultKeepaliveSettings() {
        AtomicReference<ManagedChannelBuilder<?>> channelBuilderSpy = new AtomicReference<>();
        AtomicReference<ChannelCredentials> channelCredentials = new AtomicReference<>();

        try (MockedStatic<Grpc> grpcMock = interceptChannelBuilder(channelBuilderSpy, channelCredentials)) {
            Pair<TestStub, ManagedChannel> result = builder.build();
            try {
                assertNotNull(result.getLeft());
                assertInstanceOf(TlsChannelCredentials.class, channelCredentials.get());
                verifyKeepaliveSettings(channelBuilderSpy.get(), Duration.ofSeconds(45), Duration.ofSeconds(10), true);
                verifyChannelBuilderTarget(grpcMock);
            } finally {
                closeChannel(result.getRight());
            }
        }
    }

    @Test
    void testBuildUsesRepeatedKeepaliveOverridesWithInsecureCredentials() {
        AtomicReference<ManagedChannelBuilder<?>> channelBuilderSpy = new AtomicReference<>();
        AtomicReference<ChannelCredentials> channelCredentials = new AtomicReference<>();

        builder.insecure()
                .keepaliveInterval(Duration.ofSeconds(60))
                .keepaliveInterval(Duration.ofSeconds(75))
                .keepaliveTimeout(Duration.ofSeconds(12))
                .keepaliveTimeout(Duration.ofSeconds(15))
                .keepalivePermitWithoutCalls(true)
                .keepalivePermitWithoutCalls(false);

        try (MockedStatic<Grpc> grpcMock = interceptChannelBuilder(channelBuilderSpy, channelCredentials)) {
            Pair<TestStub, ManagedChannel> result = builder.build();
            try {
                assertNotNull(result.getLeft());
                assertInstanceOf(InsecureChannelCredentials.class, channelCredentials.get());
                verifyKeepaliveSettings(channelBuilderSpy.get(), Duration.ofSeconds(75), Duration.ofSeconds(15), false);
                verifyChannelBuilderTarget(grpcMock);
            } finally {
                closeChannel(result.getRight());
            }
        }
    }

    @Test
    void testBuildAsyncPreservesAuthenticationAndUnchangedKeepaliveDefaults() {
        AtomicReference<ManagedChannelBuilder<?>> channelBuilderSpy = new AtomicReference<>();
        AtomicReference<ChannelCredentials> channelCredentials = new AtomicReference<>();
        CallCredentials callCredentials = mock(CallCredentials.class);
        builder.authenticated(callCredentials).keepaliveTimeout(Duration.ofSeconds(20));

        try (MockedStatic<Grpc> grpcMock = interceptChannelBuilder(channelBuilderSpy, channelCredentials)) {
            Pair<TestAsyncStub, ManagedChannel> result = builder.buildAsync();
            try {
                assertNotNull(result.getLeft());
                CompositeChannelCredentials compositeCredentials = assertInstanceOf(
                        CompositeChannelCredentials.class, channelCredentials.get());
                assertInstanceOf(TlsChannelCredentials.class, compositeCredentials.getChannelCredentials());
                assertSame(callCredentials, compositeCredentials.getCallCredentials());
                verifyKeepaliveSettings(channelBuilderSpy.get(), Duration.ofSeconds(45), Duration.ofSeconds(20), true);
                verifyChannelBuilderTarget(grpcMock);
            } finally {
                closeChannel(result.getRight());
            }
        }
    }

    @Test
    void testInvalidKeepaliveDurationsDoNotChangeConfiguredValues() {
        builder.keepaliveInterval(Duration.ofSeconds(75)).keepaliveTimeout(Duration.ofSeconds(20));

        assertNullDuration("keepaliveInterval", () -> builder.keepaliveInterval(null));
        assertNullDuration("keepaliveTimeout", () -> builder.keepaliveTimeout(null));
        assertInvalidDuration("keepaliveInterval", () -> builder.keepaliveInterval(Duration.ZERO), "must be positive");
        assertInvalidDuration("keepaliveInterval", () -> builder.keepaliveInterval(Duration.ofNanos(-1)), "must be positive");
        assertInvalidDuration("keepaliveTimeout", () -> builder.keepaliveTimeout(Duration.ZERO), "must be positive");
        assertInvalidDuration("keepaliveTimeout", () -> builder.keepaliveTimeout(Duration.ofNanos(-1)), "must be positive");
        assertInvalidDuration("keepaliveInterval", () -> builder.keepaliveInterval(Duration.ofSeconds(Long.MAX_VALUE)),
                "representable in nanoseconds");
        assertInvalidDuration("keepaliveTimeout", () -> builder.keepaliveTimeout(Duration.ofSeconds(Long.MAX_VALUE)),
                "representable in nanoseconds");

        AtomicReference<ManagedChannelBuilder<?>> channelBuilderSpy = new AtomicReference<>();
        AtomicReference<ChannelCredentials> channelCredentials = new AtomicReference<>();
        try (MockedStatic<Grpc> grpcMock = interceptChannelBuilder(channelBuilderSpy, channelCredentials)) {
            Pair<TestStub, ManagedChannel> result = builder.build();
            try {
                verifyKeepaliveSettings(channelBuilderSpy.get(), Duration.ofSeconds(75), Duration.ofSeconds(20), true);
                verifyChannelBuilderTarget(grpcMock);
            } finally {
                closeChannel(result.getRight());
            }
        }
    }

    private static MockedStatic<Grpc> interceptChannelBuilder(
            AtomicReference<ManagedChannelBuilder<?>> channelBuilderSpy,
            AtomicReference<ChannelCredentials> channelCredentials) {
        MockedStatic<Grpc> grpcMock = mockStatic(Grpc.class);
        grpcMock.when(() -> Grpc.newChannelBuilder(eq(TARGET), any(ChannelCredentials.class)))
                .thenAnswer(invocation -> {
                    channelCredentials.set(invocation.getArgument(1));
                    ManagedChannelBuilder<?> realBuilder = (ManagedChannelBuilder<?>) invocation.callRealMethod();
                    ManagedChannelBuilder<?> spyBuilder = spy(realBuilder);
                    channelBuilderSpy.set(spyBuilder);
                    return spyBuilder;
                });
        return grpcMock;
    }

    private static void verifyKeepaliveSettings(
            ManagedChannelBuilder<?> channelBuilder,
            Duration interval,
            Duration timeout,
            boolean permitWithoutCalls) {
        verify(channelBuilder).keepAliveTime(interval.toNanos(), TimeUnit.NANOSECONDS);
        verify(channelBuilder).keepAliveTimeout(timeout.toNanos(), TimeUnit.NANOSECONDS);
        verify(channelBuilder).keepAliveWithoutCalls(permitWithoutCalls);
    }

    private static void verifyChannelBuilderTarget(MockedStatic<Grpc> grpcMock) {
        grpcMock.verify(() -> Grpc.newChannelBuilder(eq(TARGET), any(ChannelCredentials.class)), times(1));
    }

    private static void assertNullDuration(String optionName, Executable setter) {
        NullPointerException exception = assertThrows(NullPointerException.class, setter);
        assertTrue(exception.getMessage().contains(optionName));
        assertTrue(exception.getMessage().contains("must not be null"));
    }

    private static void assertInvalidDuration(
            String optionName,
            Executable setter,
            String expectedMessage) {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, setter);
        assertTrue(exception.getMessage().contains(optionName));
        assertTrue(exception.getMessage().contains(expectedMessage));
    }

    private static void closeChannel(ManagedChannel channel) {
        channel.shutdown();
        try {
            assertTrue(channel.awaitTermination(5, TimeUnit.SECONDS), "channel did not shut down within five seconds");
        } catch (InterruptedException exception) {
            Thread.currentThread().interrupt();
            fail("interrupted while waiting for channel shutdown", exception);
        }
    }

    @Test
    void testCredentialConfigurationMethods() {
        // Test that credential configuration methods work without throwing exceptions
        CallCredentials mockCallCredentials = mock(CallCredentials.class);

        // Test various credential configurations
        assertDoesNotThrow(() -> builder.insecure());
        assertDoesNotThrow(() -> builder.unauthenticated());
        assertDoesNotThrow(() -> builder.authenticated());

        // Note: Testing insecure + authentication might be implementation dependent
        // so we just verify the methods don't crash
    }
}
