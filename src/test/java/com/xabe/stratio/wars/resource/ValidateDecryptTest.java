package com.xabe.stratio.wars.resource;

import com.xabe.stratio.wars.payload.DecryptPayloadRequest;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;

public class ValidateDecryptTest {

    @Test
    public void givenAListCorrectUuidsWhenInvokeValidUuidsThenReturnTrue() throws Exception {
        //Given
        final DecryptPayloadRequest decryptPayloadRequest = new DecryptPayloadRequest();
        decryptPayloadRequest.setUuids(List.of("2952410b-0a94-446b-8bcb-448dc6e30b08","6f9c15fa-ef51-4415-afab-36218d76c2d9"));
        
        //When
        final Boolean result = ValidateDecrypt.validUuids().apply(decryptPayloadRequest);

        //Then
        assertThat(result, is(true));
        assertThat(decryptPayloadRequest.getCoordinates(), is(hasSize(2)));
        assertThat(decryptPayloadRequest.getCoordinates().get(0).getGalaxy(), is("2952410b"));
        assertThat(decryptPayloadRequest.getCoordinates().get(0).getQuadrant(), is("0a94"));
        assertThat(decryptPayloadRequest.getCoordinates().get(0).getStarSystem1(), is("446b"));
        assertThat(decryptPayloadRequest.getCoordinates().get(0).getStarSystem2(), is("8bcb"));
        assertThat(decryptPayloadRequest.getCoordinates().get(0).getPlanet(), is("448dc6e30b08"));

    }

    @Test
    public void givenAListInCorrectUuidsWhenInvokeValidUuidsThenReturnTrue() throws Exception {
        //Given
        final DecryptPayloadRequest decryptPayloadRequest = new DecryptPayloadRequest();
        decryptPayloadRequest.setUuids(List.of("2952410b-0a94-446b-8bcb-448dc6e30b08","36218d76c2d9"));

        //When
        final Boolean result = ValidateDecrypt.validUuids().apply(decryptPayloadRequest);

        //Then
        assertThat(result, is(false));
    }
    
}