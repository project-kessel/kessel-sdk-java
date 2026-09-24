package org.project_kessel.api.auth;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RetryOptionsTest {

    @Test
    void testDefaults() {
        RetryOptions opts = RetryOptions.defaults();
        assertEquals(3, opts.maxRetries());
        assertEquals(0.5, opts.baseDelay());
        assertEquals(2.0, opts.maxDelay());
        assertEquals("full", opts.jitter());
    }

    @Test
    void testCustomValues() {
        RetryOptions opts = new RetryOptions(5, 1.0, 10.0, "none");
        assertEquals(5, opts.maxRetries());
        assertEquals(1.0, opts.baseDelay());
        assertEquals(10.0, opts.maxDelay());
        assertEquals("none", opts.jitter());
    }

    @Test
    void testZeroRetriesDisablesRetry() {
        RetryOptions opts = new RetryOptions(0, 0.5, 2.0, "full");
        assertEquals(0, opts.maxRetries());
    }

    @Test
    void testNegativeMaxRetriesThrows() {
        assertThrows(IllegalArgumentException.class,
            () -> new RetryOptions(-1, 0.5, 2.0, "full"));
    }

    @Test
    void testZeroBaseDelayThrows() {
        assertThrows(IllegalArgumentException.class,
            () -> new RetryOptions(3, 0, 2.0, "full"));
    }

    @Test
    void testNegativeBaseDelayThrows() {
        assertThrows(IllegalArgumentException.class,
            () -> new RetryOptions(3, -1.0, 2.0, "full"));
    }

    @Test
    void testZeroMaxDelayThrows() {
        assertThrows(IllegalArgumentException.class,
            () -> new RetryOptions(3, 0.5, 0, "full"));
    }

    @Test
    void testNegativeMaxDelayThrows() {
        assertThrows(IllegalArgumentException.class,
            () -> new RetryOptions(3, 0.5, -1.0, "full"));
    }

    @Test
    void testMaxDelayLessThanBaseDelayThrows() {
        assertThrows(IllegalArgumentException.class,
            () -> new RetryOptions(3, 2.0, 1.0, "full"));
    }

    @Test
    void testNullJitterThrows() {
        assertThrows(NullPointerException.class,
            () -> new RetryOptions(3, 0.5, 2.0, null));
    }

    @Test
    void testInvalidJitterThrows() {
        assertThrows(IllegalArgumentException.class,
            () -> new RetryOptions(3, 0.5, 2.0, "half"));
    }

    @Test
    void testValidJitterFull() {
        assertDoesNotThrow(() -> new RetryOptions(3, 0.5, 2.0, "full"));
    }

    @Test
    void testValidJitterNone() {
        assertDoesNotThrow(() -> new RetryOptions(3, 0.5, 2.0, "none"));
    }

    @Test
    void testEqualBaseAndMaxDelay() {
        assertDoesNotThrow(() -> new RetryOptions(3, 1.0, 1.0, "full"));
    }
}
