package com.xabe.stratio.wars.payload;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.List;

public class DecryptPayloadResponse {
    private final List<CoordinatePayloadResponse> coordinatePayloadResponses;

    @JsonCreator
    public DecryptPayloadResponse(@JsonProperty("coordinatePayloadResponses") List<CoordinatePayloadResponse> coordinatePayloadResponses) {
        this.coordinatePayloadResponses = coordinatePayloadResponses;
    }

    public List<CoordinatePayloadResponse> getCoordinatePayloadResponses() {
        return coordinatePayloadResponses;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof DecryptPayloadResponse) {

            DecryptPayloadResponse that = (DecryptPayloadResponse) o;

            return new EqualsBuilder()
                    .append(coordinatePayloadResponses, that.coordinatePayloadResponses)
                    .isEquals();
        }
        return false;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(coordinatePayloadResponses)
                .toHashCode();
    }
}
