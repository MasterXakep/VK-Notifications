package org.vanya;

import com.fasterxml.jackson.annotation.*;

import javax.annotation.Generated;
import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
        "key",
        "server",
        "ts"
})
public class LongPollServerResponse {

    @JsonProperty("key")
    private String key;
    @JsonProperty("server")
    private String server;
    @JsonProperty("ts")
    private Integer ts;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     *
     * @return
     * The key
     */
    @JsonProperty("key")
    public String getKey() {
        return key;
    }

    /**
     *
     * @param key
     * The key
     */
    @JsonProperty("key")
    public void setKey(String key) {
        this.key = key;
    }

    /**
     *
     * @return
     * The server
     */
    @JsonProperty("server")
    public String getServer() {
        return server;
    }

    /**
     *
     * @param server
     * The server
     */
    @JsonProperty("server")
    public void setServer(String server) {
        this.server = server;
    }

    /**
     *
     * @return
     * The ts
     */
    @JsonProperty("ts")
    public Integer getTs() {
        return ts;
    }

    /**
     *
     * @param ts
     * The ts
     */
    @JsonProperty("ts")
    public void setTs(Integer ts) {
        this.ts = ts;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}