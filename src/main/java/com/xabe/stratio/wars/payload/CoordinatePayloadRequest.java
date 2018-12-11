package com.xabe.stratio.wars.payload;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class CoordinatePayloadRequest {

    public static final String REGEX = "-";
    private final String galaxy;
    private final String quadrant;
    private final String starSystem1;
    private final String starSystem2;
    private final String planet;

    public CoordinatePayloadRequest(String uuid) {
        final String[] matcher = uuid.split(REGEX);
        this.galaxy = matcher[0];
        this.quadrant = matcher[1];
        this.starSystem1 = matcher[2];
        this.starSystem2 = matcher[3];
        this.planet = matcher[4];
    }

    public String getGalaxy() {
        return galaxy;
    }

    public String getQuadrant() {
        return quadrant;
    }

    public String getStarSystem1() {
        return starSystem1;
    }

    public String getStarSystem2() {
        return starSystem2;
    }

    public String getPlanet() {
        return planet;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof CoordinatePayloadRequest) {
            CoordinatePayloadRequest that = (CoordinatePayloadRequest) o;

            return new EqualsBuilder()
                    .append(galaxy, that.galaxy)
                    .append(quadrant, that.quadrant)
                    .append(starSystem1, that.starSystem1)
                    .append(starSystem2, that.starSystem2)
                    .append(planet, that.planet)
                    .isEquals();
        }
        return false;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(galaxy)
                .append(quadrant)
                .append(starSystem1)
                .append(starSystem2)
                .append(planet)
                .toHashCode();
    }
}
