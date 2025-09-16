package org.project_kessel.examples.util;

import io.github.cdimascio.dotenv.Dotenv;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class EnvConfig {
    private static final Dotenv dotenv;

    static {
        ensureEnvFileExists();
        dotenv = Dotenv.configure()
                .directory("./")
                .ignoreIfMalformed()
                .ignoreIfMissing()
                .load();
    }

    private static void ensureEnvFileExists() {
        Path envFile = Paths.get(".env");
        Path sampleFile = Paths.get(".env.sample");

        if (!Files.exists(envFile) && Files.exists(sampleFile)) {
            try {
                Files.copy(sampleFile, envFile, StandardCopyOption.REPLACE_EXISTING);
                System.out.println("Created .env file from .env.sample");
                System.out.println("Please update .env with your actual configuration values");
            } catch (IOException e) {
                System.err.println("Warning: Could not create .env file from .env.sample: " + e.getMessage());
            }
        }
    }

    public static String get(String key) {
        return dotenv.get(key);
    }

    public static void validateRequired(String... keys) {
        StringBuilder missing = new StringBuilder();

        for (String key : keys) {
            String value = get(key);
            if (value == null || value.trim().isEmpty()) {
                if (missing.length() > 0) {
                    missing.append("\n");
                }
                missing.append("- ").append(key);
            }
        }

        if (missing.length() > 0) {
            throw new IllegalStateException(
                "Missing required environment variables:\n" + missing +
                "\n\nPlease update your .env file with the required values."
            );
        }
    }
}
