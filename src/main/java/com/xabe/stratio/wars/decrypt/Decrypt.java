package com.xabe.stratio.wars.decrypt;

import com.xabe.stratio.wars.payload.CoordinatePayloadRequest;
import com.xabe.stratio.wars.payload.CoordinatePayloadResponse;
import com.xabe.stratio.wars.payload.DecryptPayloadRequest;
import com.xabe.stratio.wars.payload.DecryptPayloadResponse;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Decrypt implements Function<DecryptPayloadRequest, DecryptPayloadResponse> {

    private static final String TEMPLATE = "%s-%s-%s-%s";

    @Override
    public DecryptPayloadResponse apply(DecryptPayloadRequest decryptPayloadRequest) {
        final List<CoordinatePayloadResponse> coordinatePayloadResponses = decryptPayloadRequest.getCoordinates().stream()
                    .map(this::decryptPayload)
                    .collect(Collectors.toList());
        return new DecryptPayloadResponse(coordinatePayloadResponses);
    }

    private CoordinatePayloadResponse decryptPayload(CoordinatePayloadRequest coordinatePayloadRequest) {
        final String galaxyDecrypt = decryptGalaxy(coordinatePayloadRequest.getGalaxy());
        final String quadrantDecrypt = decryptQuadrant(coordinatePayloadRequest.getQuadrant());
        final String starDecrypt = decryptStart(coordinatePayloadRequest.getStarSystem1(), coordinatePayloadRequest.getStarSystem2());
        final String planetDecrypt = decryptPlanet(coordinatePayloadRequest.getPlanet());
        return new CoordinatePayloadResponse(String.format(TEMPLATE, galaxyDecrypt, quadrantDecrypt, starDecrypt, planetDecrypt));
    }

    private String decryptGalaxy(String galaxy) {
        final int sum = galaxy.chars().mapToObj(this::toChar).mapToInt(this::toDecimal).sum();
        return String.valueOf(sum);
    }

    private String decryptQuadrant(String quadrant) {
        final Optional<Integer> max = quadrant.chars().mapToObj(this::toChar).map(this::toDecimal).max(Comparator.naturalOrder());
        return max.orElseThrow().toString();
    }

    private String decryptStart(String starSystem1, String starSystem2) {
        final int sum = IntStream.rangeClosed(0, 3).map(i -> {
            final int start1 = toDecimal(starSystem1.charAt(i));
            final int start2 = toDecimal(starSystem2.charAt(i));
            return start1 >= start2 ? start1 : start2;
        }).sum();
        return String.valueOf(sum);
    }

    private String decryptPlanet(String planet) {
        return planet.chars()
                .mapToObj(this::toChar)
                .distinct()
                .map(this::toDecimal)
                .sorted(Comparator.reverseOrder())
                .map( item -> Integer.toHexString(item))
                .collect(Collectors.joining());
    }

    private Integer toDecimal(Character character) {
        return Integer.parseInt(String.valueOf(character), 16);
    }

    private char toChar(int i) {
        return (char) i;
    }


}
