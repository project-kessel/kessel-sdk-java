package org.project_kessel.api.auth;

import java.util.Objects;

/**
 * Configuration for bounded retries on transient OAuth token endpoint failures.
 * Retry behavior is applied only while obtaining a token, not to arbitrary API
 * calls or OIDC discovery.
 */
public record RetryOptions(
    int maxRetries,
    double baseDelay,
    double maxDelay,
    String jitter
) {

    private static final int DEFAULT_MAX_RETRIES = 3;
    private static final double DEFAULT_BASE_DELAY = 0.5;
    private static final double DEFAULT_MAX_DELAY = 2.0;
    private static final String DEFAULT_JITTER = "full";

    public RetryOptions {
        Objects.requireNonNull(jitter, "jitter must not be null");
        if (maxRetries < 0) {
            throw new IllegalArgumentException("maxRetries must be >= 0");
        }
        if (baseDelay <= 0) {
            throw new IllegalArgumentException("baseDelay must be > 0");
        }
        if (maxDelay <= 0) {
            throw new IllegalArgumentException("maxDelay must be > 0");
        }
        if (maxDelay < baseDelay) {
            throw new IllegalArgumentException("maxDelay must be >= baseDelay");
        }
        if (!"full".equals(jitter) && !"none".equals(jitter)) {
            throw new IllegalArgumentException("jitter must be 'full' or 'none'");
        }
    }

    /**
     * Returns default retry options: 3 retries with full-jitter exponential
     * backoff capped at 0.5, 1, and 2 seconds.
     */
    public static RetryOptions defaults() {
        return new RetryOptions(DEFAULT_MAX_RETRIES, DEFAULT_BASE_DELAY, DEFAULT_MAX_DELAY, DEFAULT_JITTER);
    }
}
