package org.project_kessel.api.common;

import com.fasterxml.jackson.databind.ObjectMapper;

public final class JsonMapper {

    private static final ObjectMapper INSTANCE = new ObjectMapper();

    private JsonMapper() {
    }

    public static ObjectMapper instance() {
        return INSTANCE;
    }
}
