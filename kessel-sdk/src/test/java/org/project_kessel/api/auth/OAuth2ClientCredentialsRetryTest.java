package org.project_kessel.api.auth;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

import static org.junit.jupiter.api.Assertions.*;

class OAuth2ClientCredentialsRetryTest {

    private static final String TOKEN_RESPONSE_BODY =
        "{\"access_token\":\"test-token\",\"token_type\":\"Bearer\",\"expires_in\":3600}";

    // Tiny delays for fast tests
    private static final RetryOptions FAST_RETRY = new RetryOptions(3, 0.01, 0.02, "none");
    private static final RetryOptions NO_RETRY = new RetryOptions(0, 0.5, 2.0, "full");

    private HttpServer httpServer;
    private ExecutorService serverExecutor;

    @AfterEach
    void tearDown() throws InterruptedException {
        if (httpServer != null) {
            httpServer.stop(0);
        }
        if (serverExecutor != null) {
            serverExecutor.shutdownNow();
            serverExecutor.awaitTermination(5, TimeUnit.SECONDS);
        }
    }

    @Test
    void testSuccessOnFirstAttempt() throws Exception {
        AtomicInteger requestCount = new AtomicInteger(0);
        int port = startMockServer(exchange -> {
            requestCount.incrementAndGet();
            sendResponse(exchange, 200, TOKEN_RESPONSE_BODY);
        });

        var creds = createCredentials(port, FAST_RETRY);
        RefreshTokenResponse token = creds.getToken();

        assertEquals("test-token", token.accessToken());
        assertEquals(1, requestCount.get());
    }

    @Test
    void testDefaultConstructorRetriesOnTransientError() throws Exception {
        // One-arg constructor uses RetryOptions.defaults() (3 retries).
        // Server returns 503 on first request, 200 on second.
        AtomicInteger requestCount = new AtomicInteger(0);
        int port = startMockServer(exchange -> {
            int count = requestCount.incrementAndGet();
            if (count == 1) {
                sendResponse(exchange, 503, "Service Unavailable");
            } else {
                sendResponse(exchange, 200, TOKEN_RESPONSE_BODY);
            }
        });

        var config = new ClientConfigAuth("test-client", "test-secret",
            "http://localhost:" + port + "/token");
        var creds = new OAuth2ClientCredentials(config);

        RefreshTokenResponse token = creds.getToken();
        assertEquals("test-token", token.accessToken());
        assertEquals(2, requestCount.get());
    }

    @Test
    void testRetryOnHttp500ThenSuccess() throws Exception {
        AtomicInteger requestCount = new AtomicInteger(0);
        int port = startMockServer(exchange -> {
            int count = requestCount.incrementAndGet();
            if (count == 1) {
                sendResponse(exchange, 500, "Internal Server Error");
            } else {
                sendResponse(exchange, 200, TOKEN_RESPONSE_BODY);
            }
        });

        var creds = createCredentials(port, FAST_RETRY);
        RefreshTokenResponse token = creds.getToken();

        assertEquals("test-token", token.accessToken());
        assertEquals(2, requestCount.get());
    }

    @Test
    void testRetryOnHttp502ThenSuccess() throws Exception {
        AtomicInteger requestCount = new AtomicInteger(0);
        int port = startMockServer(exchange -> {
            int count = requestCount.incrementAndGet();
            if (count == 1) {
                sendResponse(exchange, 502, "Bad Gateway");
            } else {
                sendResponse(exchange, 200, TOKEN_RESPONSE_BODY);
            }
        });

        var creds = createCredentials(port, FAST_RETRY);
        RefreshTokenResponse token = creds.getToken();

        assertEquals("test-token", token.accessToken());
        assertEquals(2, requestCount.get());
    }

    @Test
    void testRetryOnHttp503ThenSuccess() throws Exception {
        AtomicInteger requestCount = new AtomicInteger(0);
        int port = startMockServer(exchange -> {
            int count = requestCount.incrementAndGet();
            if (count == 1) {
                sendResponse(exchange, 503, "Service Unavailable");
            } else {
                sendResponse(exchange, 200, TOKEN_RESPONSE_BODY);
            }
        });

        var creds = createCredentials(port, FAST_RETRY);
        RefreshTokenResponse token = creds.getToken();

        assertEquals("test-token", token.accessToken());
        assertEquals(2, requestCount.get());
    }

    @Test
    void testRetryOnHttp429ThenSuccess() throws Exception {
        AtomicInteger requestCount = new AtomicInteger(0);
        int port = startMockServer(exchange -> {
            int count = requestCount.incrementAndGet();
            if (count == 1) {
                sendResponse(exchange, 429, "Too Many Requests");
            } else {
                sendResponse(exchange, 200, TOKEN_RESPONSE_BODY);
            }
        });

        var creds = createCredentials(port, FAST_RETRY);
        RefreshTokenResponse token = creds.getToken();

        assertEquals("test-token", token.accessToken());
        assertEquals(2, requestCount.get());
    }

    @Test
    void testNoRetryOnHttp400() throws Exception {
        AtomicInteger requestCount = new AtomicInteger(0);
        int port = startMockServer(exchange -> {
            requestCount.incrementAndGet();
            String errorBody = "{\"error\":\"invalid_client\",\"error_description\":\"Bad credentials\"}";
            sendResponse(exchange, 400, errorBody);
        });

        var creds = createCredentials(port, FAST_RETRY);

        OAuth2Exception ex = assertThrows(OAuth2Exception.class, () -> creds.getToken());
        assertTrue(ex.getMessage().contains("Bad credentials"));
        assertEquals(1, requestCount.get());
    }

    @Test
    void testNoRetryOnHttp401() throws Exception {
        AtomicInteger requestCount = new AtomicInteger(0);
        int port = startMockServer(exchange -> {
            requestCount.incrementAndGet();
            String errorBody = "{\"error\":\"invalid_client\",\"error_description\":\"Unauthorized\"}";
            sendResponse(exchange, 401, errorBody);
        });

        var creds = createCredentials(port, FAST_RETRY);

        assertThrows(OAuth2Exception.class, () -> creds.getToken());
        assertEquals(1, requestCount.get());
    }

    @Test
    void testNoRetryOnHttp403() throws Exception {
        AtomicInteger requestCount = new AtomicInteger(0);
        int port = startMockServer(exchange -> {
            requestCount.incrementAndGet();
            String errorBody = "{\"error\":\"access_denied\",\"error_description\":\"Forbidden\"}";
            sendResponse(exchange, 403, errorBody);
        });

        var creds = createCredentials(port, FAST_RETRY);

        assertThrows(OAuth2Exception.class, () -> creds.getToken());
        assertEquals(1, requestCount.get());
    }

    @Test
    void testRetryExhaustion() throws Exception {
        AtomicInteger requestCount = new AtomicInteger(0);
        int port = startMockServer(exchange -> {
            requestCount.incrementAndGet();
            sendResponse(exchange, 503, "Service Unavailable");
        });

        var creds = createCredentials(port, FAST_RETRY);

        assertThrows(OAuth2Exception.class, () -> creds.getToken());
        assertEquals(4, requestCount.get()); // 1 initial + 3 retries
    }

    @Test
    void testNoRetryWhenMaxRetriesZero() throws Exception {
        AtomicInteger requestCount = new AtomicInteger(0);
        int port = startMockServer(exchange -> {
            int count = requestCount.incrementAndGet();
            if (count == 1) {
                sendResponse(exchange, 503, "Service Unavailable");
            } else {
                sendResponse(exchange, 200, TOKEN_RESPONSE_BODY);
            }
        });

        var creds = createCredentials(port, NO_RETRY);

        assertThrows(OAuth2Exception.class, () -> creds.getToken());
        assertEquals(1, requestCount.get());
    }

    @Test
    void testMultipleRetriesThenSuccess() throws Exception {
        AtomicInteger requestCount = new AtomicInteger(0);
        int port = startMockServer(exchange -> {
            int count = requestCount.incrementAndGet();
            if (count <= 3) {
                sendResponse(exchange, 503, "Service Unavailable");
            } else {
                sendResponse(exchange, 200, TOKEN_RESPONSE_BODY);
            }
        });

        var creds = createCredentials(port, FAST_RETRY);
        RefreshTokenResponse token = creds.getToken();

        assertEquals("test-token", token.accessToken());
        assertEquals(4, requestCount.get()); // 3 failures + 1 success on last attempt
    }

    // -- SDK-level recovery proof (paired tests) --
    //
    // Both tests use the same raw-socket fixture: drop the first 3 TCP
    // connections, then respond with a valid token on all subsequent ones.
    // Dropping 3 connections exceeds what HttpURLConnection transport-level
    // replay can recover (at most 1 replay per SDK attempt), so recovery
    // requires SDK-level retry.

    @Test
    void testConnectionCloseRecoveryRequiresSdkRetry() throws Exception {
        // Retries enabled (3). SDK retries across multiple connection
        // failures until it reaches a connection that responds.
        AtomicReference<Throwable> serverError = new AtomicReference<>();
        int dropCount = 3;

        ServerSocket serverSocket = new ServerSocket(0);
        int port = serverSocket.getLocalPort();
        AtomicInteger connectionCount = new AtomicInteger(0);

        Thread serverThread = new Thread(() -> {
            try {
                while (!serverSocket.isClosed()) {
                    Socket conn = serverSocket.accept();
                    try {
                        int count = connectionCount.incrementAndGet();
                        if (count <= dropCount) {
                            conn.close();
                        } else {
                            handleRawTokenRequest(conn);
                            conn.close();
                        }
                    } catch (IOException e) {
                        try { conn.close(); } catch (IOException ignored) {}
                    }
                }
            } catch (IOException e) {
                if (!serverSocket.isClosed()) {
                    serverError.set(e);
                }
            }
        });
        serverThread.setDaemon(true);
        serverThread.start();

        try {
            var config = new ClientConfigAuth("test-client", "test-secret",
                "http://localhost:" + port + "/token");
            var creds = new OAuth2ClientCredentials(config, FAST_RETRY);

            RefreshTokenResponse token = creds.getToken();
            assertEquals("test-token", token.accessToken());
        } finally {
            serverSocket.close();
            serverThread.join(5000);
        }

        assertFalse(serverThread.isAlive(), "Server thread should have stopped");
        assertNull(serverError.get(),
            () -> "Server thread error: " + serverError.get());
    }

    @Test
    void testConnectionCloseWithoutSdkRetryFails() throws Exception {
        // Same fixture as above (drop first 3, respond on 4+), but retries
        // disabled. A single SDK attempt plus at most one transport-level
        // replay cannot get past 3 dropped connections, so the request fails.
        AtomicReference<Throwable> serverError = new AtomicReference<>();
        int dropCount = 3;

        ServerSocket serverSocket = new ServerSocket(0);
        int port = serverSocket.getLocalPort();
        AtomicInteger connectionCount = new AtomicInteger(0);

        Thread serverThread = new Thread(() -> {
            try {
                while (!serverSocket.isClosed()) {
                    Socket conn = serverSocket.accept();
                    try {
                        int count = connectionCount.incrementAndGet();
                        if (count <= dropCount) {
                            conn.close();
                        } else {
                            handleRawTokenRequest(conn);
                            conn.close();
                        }
                    } catch (IOException e) {
                        try { conn.close(); } catch (IOException ignored) {}
                    }
                }
            } catch (IOException e) {
                if (!serverSocket.isClosed()) {
                    serverError.set(e);
                }
            }
        });
        serverThread.setDaemon(true);
        serverThread.start();

        try {
            var config = new ClientConfigAuth("test-client", "test-secret",
                "http://localhost:" + port + "/token");
            var creds = new OAuth2ClientCredentials(config, NO_RETRY);

            OAuth2Exception ex = assertThrows(OAuth2Exception.class, () -> creds.getToken());
            assertTrue(ex.getCause() instanceof IOException,
                "Expected IOException cause, got " + ex.getCause());
        } finally {
            serverSocket.close();
            serverThread.join(5000);
        }

        assertFalse(serverThread.isAlive(), "Server thread should have stopped");
        assertNull(serverError.get(),
            () -> "Server thread error: " + serverError.get());
    }

    @Test
    void testIoExceptionRetryExhaustion() throws Exception {
        // All connections dropped. SDK exhausts retries and surfaces IOException.
        AtomicReference<Throwable> serverError = new AtomicReference<>();

        ServerSocket serverSocket = new ServerSocket(0);
        int port = serverSocket.getLocalPort();

        Thread serverThread = new Thread(() -> {
            try {
                while (!serverSocket.isClosed()) {
                    try (Socket conn = serverSocket.accept()) {
                        // Drop — close immediately, no response
                    }
                }
            } catch (IOException e) {
                if (!serverSocket.isClosed()) {
                    serverError.set(e);
                }
            }
        });
        serverThread.setDaemon(true);
        serverThread.start();

        try {
            var config = new ClientConfigAuth("test-client", "test-secret",
                "http://localhost:" + port + "/token");
            var creds = new OAuth2ClientCredentials(config, FAST_RETRY);

            OAuth2Exception ex = assertThrows(OAuth2Exception.class, () -> creds.getToken());
            assertTrue(ex.getCause() instanceof IOException);
            assertTrue(ex.getMessage().contains("after 4 attempts"));
        } finally {
            serverSocket.close();
            serverThread.join(5000);
        }

        assertFalse(serverThread.isAlive(), "Server thread should have stopped");
        assertNull(serverError.get(),
            () -> "Server thread error: " + serverError.get());
    }

    // -- HTTP timeout tests --

    @Test
    void testReadTimeoutTriggersRetryAndFailure() throws Exception {
        // Server accepts connections concurrently but never sends a
        // response, causing a read timeout on each attempt.  Each
        // accepted connection is handled in its own thread so the
        // accept loop stays responsive while earlier connections sleep.
        // Uses the package-private constructor with a short timeout
        // (200 ms) so the test completes quickly.
        AtomicReference<Throwable> serverError = new AtomicReference<>();
        AtomicInteger connectionCount = new AtomicInteger(0);

        ServerSocket serverSocket = new ServerSocket(0);
        int port = serverSocket.getLocalPort();
        ExecutorService workers = Executors.newCachedThreadPool();

        Thread serverThread = new Thread(() -> {
            try {
                while (!serverSocket.isClosed()) {
                    Socket conn = serverSocket.accept();
                    connectionCount.incrementAndGet();
                    workers.submit(() -> {
                        try {
                            // Read request headers but never respond
                            conn.setSoTimeout(2000);
                            InputStream in = conn.getInputStream();
                            StringBuilder headers = new StringBuilder();
                            int b;
                            try {
                                while ((b = in.read()) != -1) {
                                    headers.append((char) b);
                                    if (headers.toString().endsWith("\r\n\r\n")) break;
                                }
                            } catch (SocketTimeoutException ignored) {
                                // Client may time out before sending full request
                            }
                            // Intentionally never send a response — client times out
                            Thread.sleep(5000);
                        } catch (InterruptedException ignored) {
                        } catch (IOException e) {
                            // Client disconnected after timeout — expected
                        } finally {
                            try { conn.close(); } catch (IOException ignored) {}
                        }
                    });
                }
            } catch (IOException e) {
                if (!serverSocket.isClosed()) {
                    serverError.set(e);
                }
            }
        });
        serverThread.setDaemon(true);
        serverThread.start();

        try {
            var config = new ClientConfigAuth("test-client", "test-secret",
                "http://localhost:" + port + "/token");
            // Short timeouts: 200 ms connect, 200 ms read
            var retry = new RetryOptions(2, 0.01, 0.02, "none");
            var creds = new OAuth2ClientCredentials(config, retry, 200, 200);

            OAuth2Exception ex = assertThrows(OAuth2Exception.class, () -> creds.getToken());
            // Verify failure is actually a read timeout (SocketTimeoutException)
            assertTrue(ex.getCause() instanceof SocketTimeoutException,
                "Expected SocketTimeoutException cause (read timeout), got " + ex.getCause());
            assertTrue(ex.getMessage().contains("after 3 attempts"));
        } finally {
            serverSocket.close();
            workers.shutdownNow();
            workers.awaitTermination(5, TimeUnit.SECONDS);
            serverThread.join(5000);
        }

        assertFalse(serverThread.isAlive(), "Server thread should have stopped");
        assertNull(serverError.get(),
            () -> "Server thread error: " + serverError.get());
        // Each retry attempt must reach the server as a new TCP connection
        assertTrue(connectionCount.get() >= 3,
            "Expected at least 3 connections (1 + 2 retries), got " + connectionCount.get());
    }

    @Test
    void testRetryDelayCalculationNoJitter() {
        var retry = new RetryOptions(3, 0.5, 2.0, "none");
        var config = new ClientConfigAuth("test", "test", "https://example.com/token");
        var creds = new OAuth2ClientCredentials(config, retry);

        // Attempt 0: min(2.0, 0.5 * 2^0) = 0.5s = 500ms
        assertEquals(500, creds.retryDelayMs(0));
        // Attempt 1: min(2.0, 0.5 * 2^1) = 1.0s = 1000ms
        assertEquals(1000, creds.retryDelayMs(1));
        // Attempt 2: min(2.0, 0.5 * 2^2) = 2.0s = 2000ms
        assertEquals(2000, creds.retryDelayMs(2));
        // Attempt 3: min(2.0, 0.5 * 2^3) = 2.0s = 2000ms (capped at maxDelay)
        assertEquals(2000, creds.retryDelayMs(3));
    }

    @Test
    void testRetryDelayWithFullJitter() {
        var retry = new RetryOptions(3, 0.5, 2.0, "full");
        var config = new ClientConfigAuth("test", "test", "https://example.com/token");
        var creds = new OAuth2ClientCredentials(config, retry);

        // Full jitter produces values in [0, cap * 1000) ms
        for (int i = 0; i < 100; i++) {
            long delay = creds.retryDelayMs(0);
            assertTrue(delay >= 0, "Delay should be >= 0, got " + delay);
            assertTrue(delay < 500, "Delay should be < 500ms for attempt 0, got " + delay);
        }
    }

    @Test
    void testIsRetryableStatusCode() {
        assertTrue(OAuth2ClientCredentials.isRetryableStatusCode(429));
        assertTrue(OAuth2ClientCredentials.isRetryableStatusCode(500));
        assertTrue(OAuth2ClientCredentials.isRetryableStatusCode(502));
        assertTrue(OAuth2ClientCredentials.isRetryableStatusCode(503));
        assertTrue(OAuth2ClientCredentials.isRetryableStatusCode(504));
        assertTrue(OAuth2ClientCredentials.isRetryableStatusCode(599));

        assertFalse(OAuth2ClientCredentials.isRetryableStatusCode(200));
        assertFalse(OAuth2ClientCredentials.isRetryableStatusCode(400));
        assertFalse(OAuth2ClientCredentials.isRetryableStatusCode(401));
        assertFalse(OAuth2ClientCredentials.isRetryableStatusCode(403));
        assertFalse(OAuth2ClientCredentials.isRetryableStatusCode(404));
        assertFalse(OAuth2ClientCredentials.isRetryableStatusCode(428));
        assertFalse(OAuth2ClientCredentials.isRetryableStatusCode(430));
    }

    @Test
    void testConstructorWithRetryOptionsAndNullAuth() {
        assertThrows(NullPointerException.class,
            () -> new OAuth2ClientCredentials(null, RetryOptions.defaults()));
    }

    @Test
    void testTokenCachingPreservedWithRetry() throws Exception {
        AtomicInteger requestCount = new AtomicInteger(0);
        int port = startMockServer(exchange -> {
            requestCount.incrementAndGet();
            sendResponse(exchange, 200, TOKEN_RESPONSE_BODY);
        });

        var creds = createCredentials(port, FAST_RETRY);

        // First call fetches token
        RefreshTokenResponse token1 = creds.getToken();
        assertEquals("test-token", token1.accessToken());
        assertEquals(1, requestCount.get());

        // Second call uses cached token — no additional request
        RefreshTokenResponse token2 = creds.getToken();
        assertEquals("test-token", token2.accessToken());
        assertEquals(1, requestCount.get());
    }

    // -- Helpers --

    private int startMockServer(com.sun.net.httpserver.HttpHandler handler) throws IOException {
        httpServer = HttpServer.create(new InetSocketAddress(0), 0);
        httpServer.createContext("/token", handler);
        serverExecutor = Executors.newCachedThreadPool();
        httpServer.setExecutor(serverExecutor);
        httpServer.start();
        return httpServer.getAddress().getPort();
    }

    private OAuth2ClientCredentials createCredentials(int port, RetryOptions retry) {
        var config = new ClientConfigAuth("test-client", "test-secret",
            "http://localhost:" + port + "/token");
        return new OAuth2ClientCredentials(config, retry);
    }

    private static void sendResponse(HttpExchange exchange, int statusCode, String body)
            throws IOException {
        byte[] bytes = body.getBytes();
        exchange.getResponseHeaders().set("Content-Type", "application/json");
        exchange.sendResponseHeaders(statusCode, bytes.length);
        try (OutputStream os = exchange.getResponseBody()) {
            os.write(bytes);
        }
    }

    private static void handleRawTokenRequest(Socket conn) throws IOException {
        conn.setSoTimeout(5000); // Prevent indefinite blocking on read
        InputStream in = conn.getInputStream();
        OutputStream out = conn.getOutputStream();

        // Read HTTP request headers
        StringBuilder headers = new StringBuilder();
        int b;
        while ((b = in.read()) != -1) {
            headers.append((char) b);
            if (headers.toString().endsWith("\r\n\r\n")) break;
        }

        // Parse Content-Length and read request body
        int contentLength = 0;
        for (String line : headers.toString().split("\r\n")) {
            if (line.toLowerCase().startsWith("content-length:")) {
                contentLength = Integer.parseInt(line.substring(15).trim());
            }
        }
        if (contentLength > 0) {
            in.readNBytes(contentLength);
        }

        // Send HTTP response with valid token
        String body = TOKEN_RESPONSE_BODY;
        String response = "HTTP/1.1 200 OK\r\n"
            + "Content-Type: application/json\r\n"
            + "Content-Length: " + body.length() + "\r\n"
            + "Connection: close\r\n"
            + "\r\n" + body;
        out.write(response.getBytes());
        out.flush();
    }
}
