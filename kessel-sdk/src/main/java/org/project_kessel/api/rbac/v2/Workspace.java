package org.project_kessel.api.rbac.v2;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Workspace {
    private String id;
    private String name;
    private String type;
    private String description;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }

    @JsonProperty("id")
    void setId(String id) {
        this.id = id;
    }

    @JsonProperty("name")
    void setName(String name) {
        this.name = name;
    }

    @JsonProperty("type")
    void setType(String type) {
        this.type = type;
    }

    @JsonProperty("description")
    void setDescription(String description) {
        this.description = description;
    }
}
