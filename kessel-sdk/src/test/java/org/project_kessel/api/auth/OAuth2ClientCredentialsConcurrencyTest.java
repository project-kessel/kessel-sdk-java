package org.project_kessel.api.auth;

import com.sun.net.httpserver.HttpServer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.net.InetSocketAddress;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.*;

class OAuth2ClientCredentialsConcurrencyTest {

    private static final int NUM_THREADS = 20;
    private static final int SSO_LATENCY_MS = 50;

    private HttpServer mockSsoServer;
    private AtomicInteger ssoCallCount;
    private OAuth2ClientCredentials credentials;

    @BeforeEach
    void setUp() throws IOException {
        ssoCallCount = new AtomicInteger(0);
        mockSsoServer = HttpServer.create(new InetSocketAddress(0), 0);
        mockSsoServer.createContext("/token", exchange -> {
            ssoCallCount.incrementAndGet();
            try {
                Thread.sleep(SSO_LATENCY_MS);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            String responseBody = """
                {"access_token":"refreshed-token","token_type":"Bearer","expires_in":3600}""";
            byte[] bytes = responseBody.getBytes();
            exchange.getResponseHeaders().set("Content-Type", "application/json");
            exchange.sendResponseHeaders(200, bytes.length);
            try (OutputStream os = exchange.getResponseBody()) {
                os.write(bytes);
            }
        });
        mockSsoServer.setExecutor(Executors.newFixedThreadPool(NUM_THREADS));
        mockSsoServer.start();

        int port = mockSsoServer.getAddress().getPort();
        var config = new ClientConfigAuth("test-client", "test-secret",
                "http://localhost:" + port + "/token");
        credentials = new OAuth2ClientCredentials(config);
    }

    @AfterEach
    void tearDown() {
        if (mockSsoServer != null) {
            mockSsoServer.stop(0);
        }
    }

    @Test
    void testConcurrentStaleTokenRefreshCallsSsoOnce() throws Exception {
        seedTokenCache(credentials,
                new RefreshTokenResponse("stale-token", LocalDateTime.now().plusSeconds(60)));

        assertFalse(credentials.isCacheValid(),
                "Pre-seeded token should be inside the 5-min early-refresh window");

        List<RefreshTokenResponse> results = fireConcurrentGetToken(false);

        assertEquals(1, ssoCallCount.get(),
                "Thundering herd: SSO was called " + ssoCallCount.get()
                        + " times, expected exactly 1");
        for (RefreshTokenResponse r : results) {
            assertEquals("refreshed-token", r.accessToken());
        }
    }

    @Test
    void testConcurrentColdStartCallsSsoOnce() throws Exception {
        assertFalse(credentials.isCacheValid(), "Cold start should have no cached token");

        List<RefreshTokenResponse> results = fireConcurrentGetToken(false);

        assertEquals(1, ssoCallCount.get(),
                "Thundering herd: SSO was called " + ssoCallCount.get()
                        + " times, expected exactly 1");
        for (RefreshTokenResponse r : results) {
            assertEquals("refreshed-token", r.accessToken());
        }
    }

    @Test
    void testConcurrentForceRefreshCallsSsoOnce() throws Exception {
        seedTokenCache(credentials,
                new RefreshTokenResponse("valid-token", LocalDateTime.now().plusHours(1)));

        assertTrue(credentials.isCacheValid(),
                "Pre-seeded token should be valid (far from expiry)");

        List<RefreshTokenResponse> results = fireConcurrentGetToken(true);

        assertEquals(1, ssoCallCount.get(),
                "Thundering herd: SSO was called " + ssoCallCount.get()
                        + " times, expected exactly 1");
        for (RefreshTokenResponse r : results) {
            assertEquals("refreshed-token", r.accessToken());
        }
    }

    private List<RefreshTokenResponse> fireConcurrentGetToken(boolean forceRefresh)
            throws InterruptedException {
        CountDownLatch barrier = new CountDownLatch(NUM_THREADS);
        CountDownLatch startGun = new CountDownLatch(1);

        List<Future<RefreshTokenResponse>> futures = new ArrayList<>();
        ExecutorService executor = Executors.newFixedThreadPool(NUM_THREADS);

        try {
            for (int i = 0; i < NUM_THREADS; i++) {
                futures.add(executor.submit(() -> {
                    barrier.countDown();
                    startGun.await();
                    return credentials.getToken(forceRefresh);
                }));
            }

            barrier.await(5, TimeUnit.SECONDS);
            startGun.countDown();

            List<RefreshTokenResponse> results = new ArrayList<>();
            for (Future<RefreshTokenResponse> f : futures) {
                results.add(f.get(10, TimeUnit.SECONDS));
            }
            return results;
        } catch (ExecutionException | TimeoutException e) {
            throw new AssertionError("Concurrent getToken() failed", e);
        } finally {
            executor.shutdownNow();
        }
    }

    private static void seedTokenCache(OAuth2ClientCredentials creds, RefreshTokenResponse token)
            throws Exception {
        Field cacheField = OAuth2ClientCredentials.class.getDeclaredField("tokenCache");
        cacheField.setAccessible(true);
        cacheField.set(creds, token);
    }
}
