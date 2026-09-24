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
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.*;

class OAuth2ClientCredentialsRetryTest {

    private static final String TOKEN_RESPONSE_BODY =
        "{\"access_token\":\"test-token\",\"token_type\":\"Bearer\",\"expires_in\":3600}";

    // Tiny delays for fast tests
    private static final RetryOptions FAST_RETRY = new RetryOptions(3, 0.01, 0.02, "none");

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
    void testRetryOnConnectionClosedBeforeHeaders() throws Exception {
        // Acceptance criteria: "a controlled connection failure where the token
        // endpoint closes before sending response headers and a subsequent
        // request succeeds; this must demonstrate recovery across the actual
        // HTTP/OAuth library boundary, not only a mocked exception."
        AtomicInteger connectionCount = new AtomicInteger(0);

        try (ServerSocket serverSocket = new ServerSocket(0)) {
            int port = serverSocket.getLocalPort();

            Thread serverThread = new Thread(() -> {
                try {
                    // Connection 1: accept and close immediately — no HTTP response
                    try (Socket conn = serverSocket.accept()) {
                        connectionCount.incrementAndGet();
                        // Close before sending any response headers
                    }

                    // Connection 2: full HTTP exchange with valid token
                    try (Socket conn = serverSocket.accept()) {
                        connectionCount.incrementAndGet();
                        handleRawTokenRequest(conn);
                    }
                } catch (IOException e) {
                    // Expected during cleanup
                }
            });
            serverThread.setDaemon(true);
            serverThread.start();

            var config = new ClientConfigAuth("test-client", "test-secret",
                "http://localhost:" + port + "/token");
            var creds = new OAuth2ClientCredentials(config, FAST_RETRY);

            RefreshTokenResponse token = creds.getToken();
            assertEquals("test-token", token.accessToken());
            assertEquals(2, connectionCount.get());

            serverThread.join(5000);
        }
    }

    @Test
    void testNoRetryWithoutRetryOptions() throws Exception {
        AtomicInteger requestCount = new AtomicInteger(0);
        int port = startMockServer(exchange -> {
            int count = requestCount.incrementAndGet();
            if (count == 1) {
                sendResponse(exchange, 503, "Service Unavailable");
            } else {
                sendResponse(exchange, 200, TOKEN_RESPONSE_BODY);
            }
        });

        // No retry options — original single-attempt behavior
        var config = new ClientConfigAuth("test-client", "test-secret",
            "http://localhost:" + port + "/token");
        var creds = new OAuth2ClientCredentials(config);

        assertThrows(OAuth2Exception.class, () -> creds.getToken());
        assertEquals(1, requestCount.get());
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

        var retry = new RetryOptions(0, 0.5, 2.0, "full");
        var creds = createCredentials(port, retry);

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
    void testRetryDelayWithoutRetryOptions() {
        var config = new ClientConfigAuth("test", "test", "https://example.com/token");
        var creds = new OAuth2ClientCredentials(config);

        assertEquals(0, creds.retryDelayMs(0));
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

    @Test
    void testIoExceptionRetryThenSuccess() throws Exception {
        // Verify IOException path with multiple connection failures followed
        // by successful recovery using a raw ServerSocket.
        AtomicInteger connectionCount = new AtomicInteger(0);

        try (ServerSocket serverSocket = new ServerSocket(0)) {
            int port = serverSocket.getLocalPort();

            Thread serverThread = new Thread(() -> {
                try {
                    // First two connections: drop immediately
                    for (int i = 0; i < 2; i++) {
                        try (Socket conn = serverSocket.accept()) {
                            connectionCount.incrementAndGet();
                        }
                    }

                    // Third connection: respond normally
                    try (Socket conn = serverSocket.accept()) {
                        connectionCount.incrementAndGet();
                        handleRawTokenRequest(conn);
                    }
                } catch (IOException e) {
                    // Expected during cleanup
                }
            });
            serverThread.setDaemon(true);
            serverThread.start();

            var config = new ClientConfigAuth("test-client", "test-secret",
                "http://localhost:" + port + "/token");
            var creds = new OAuth2ClientCredentials(config, FAST_RETRY);

            RefreshTokenResponse token = creds.getToken();
            assertEquals("test-token", token.accessToken());
            assertEquals(3, connectionCount.get());

            serverThread.join(5000);
        }
    }

    @Test
    void testIoExceptionRetryExhaustion() throws Exception {
        // All connection attempts fail — should exhaust retries and throw
        AtomicInteger connectionCount = new AtomicInteger(0);

        try (ServerSocket serverSocket = new ServerSocket(0)) {
            int port = serverSocket.getLocalPort();

            Thread serverThread = new Thread(() -> {
                try {
                    // Drop all 4 connections (1 initial + 3 retries)
                    for (int i = 0; i < 4; i++) {
                        try (Socket conn = serverSocket.accept()) {
                            connectionCount.incrementAndGet();
                        }
                    }
                } catch (IOException e) {
                    // Expected during cleanup
                }
            });
            serverThread.setDaemon(true);
            serverThread.start();

            var config = new ClientConfigAuth("test-client", "test-secret",
                "http://localhost:" + port + "/token");
            var creds = new OAuth2ClientCredentials(config, FAST_RETRY);

            OAuth2Exception ex = assertThrows(OAuth2Exception.class, () -> creds.getToken());
            assertTrue(ex.getCause() instanceof IOException);
            assertTrue(ex.getMessage().contains("after 4 attempts"));
            assertEquals(4, connectionCount.get());

            serverThread.join(5000);
        }
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
