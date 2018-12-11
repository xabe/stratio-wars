package com.xabe.stratio.wars.payload;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class CoordinatePayloadResponse {
    private final String decrypt;

    @JsonCreator
    public CoordinatePayloadResponse(@JsonProperty("decrypt") String decrypt) {
        this.decrypt = decrypt;
    }

    public String getDecrypt() {
        return decrypt;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof CoordinatePayloadResponse) {

            CoordinatePayloadResponse that = (CoordinatePayloadResponse) o;

            return new EqualsBuilder()
                    .append(decrypt, that.decrypt)
                    .isEquals();
        }
        return false;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(decrypt)
                .toHashCode();
    }
}
