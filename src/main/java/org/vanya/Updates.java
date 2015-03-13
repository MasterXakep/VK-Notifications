package org.vanya;
import com.fasterxml.jackson.annotation.*;

import javax.annotation.Generated;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
        "ts",
        "updates"
})
public class Updates {

    @JsonProperty("ts")
    private Integer ts;
    @JsonProperty("updates")
    private List<List<Object>> updates = new ArrayList<List<Object>>();
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

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

    /**
     *
     * @return
     * The updates
     */
    @JsonProperty("updates")
    public List<List<Object>> getUpdates() {
        return updates;
    }

    /**
     *
     * @param updates
     * The updates
     */
    @JsonProperty("updates")
    public void setUpdates(List<List<Object>> updates) {
        this.updates = updates;
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