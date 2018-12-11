package com.xabe.stratio.wars.resource;

import com.xabe.stratio.wars.payload.CoordinatePayloadRequest;
import com.xabe.stratio.wars.payload.DecryptPayloadRequest;

import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public interface ValidateDecrypt extends Function<DecryptPayloadRequest, Boolean> {

    Pattern PATTERN = Pattern.compile("([a-zA-Z0-9]{8})\\-([a-zA-Z0-9]{4})\\-([a-zA-Z0-9]{4})\\-([a-zA-Z0-9]{4})\\-([a-zA-Z0-9]{12})");

    static ValidateDecrypt validUuids(){
        return payload -> {
            payload.setCoordinates(payload.getUuids().stream().filter(item -> PATTERN.matcher(item).matches()).map(CoordinatePayloadRequest::new).collect(Collectors.toList()));
            return payload.getUuids().size() == payload.getCoordinates().size();
        };
    }

}
