package com.xabe.stratio.wars.payload;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.ws.rs.QueryParam;
import java.util.List;

public class DecryptPayloadRequest {

    @QueryParam("uuids")
    private List<String> uuids;

    private List<CoordinatePayloadRequest> coordinates;

    public List<String> getUuids() {
        return uuids;
    }

    public void setUuids(List<String> uuids) {
        this.uuids = uuids;
    }

    public List<CoordinatePayloadRequest> getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(List<CoordinatePayloadRequest> coordinates) {
        this.coordinates = coordinates;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof DecryptPayloadRequest) {
            DecryptPayloadRequest that = (DecryptPayloadRequest) o;

            return new EqualsBuilder()
                    .append(uuids, that.uuids)
                    .append(coordinates, that.coordinates)
                    .isEquals();
        }
        return false;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(uuids)
                .append(coordinates)
                .toHashCode();
    }
}
