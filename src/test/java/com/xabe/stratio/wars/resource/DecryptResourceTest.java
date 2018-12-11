package com.xabe.stratio.wars.resource;

import com.xabe.stratio.wars.payload.DecryptPayloadRequest;
import com.xabe.stratio.wars.payload.DecryptPayloadResponse;
import org.junit.jupiter.api.Test;

import javax.ws.rs.WebApplicationException;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DecryptResourceTest {

    @Test
    public void shoudlReturnOk() throws Exception {
        assertThat(new DecryptResource().getStatus(), is(notNullValue()));
    }

    @Test
    public void givenAListUuidsCorrectWhenInvokeDecryptUuidThenReturnList() throws Exception {
        //Given
        final DecryptPayloadRequest decryptPayloadRequest = new DecryptPayloadRequest();
        decryptPayloadRequest.setUuids(List.of("2952410b-0a94-446b-8bcb-448dc6e30b08","6f9c15fa-ef51-4415-afab-36218d76c2d9"));
        final DecryptResource decryptResource = new DecryptResource();

        //When
        final DecryptPayloadResponse result = decryptResource.getDecrypt(decryptPayloadRequest);

        //Then
        assertThat(result, is(notNullValue()));
    }

    @Test
    public void givenAListUuidsIncorrectWhenInvokeDecryptUuidThenReturnList() throws Exception {
        //Given
        final DecryptPayloadRequest decryptPayloadRequest = new DecryptPayloadRequest();
        decryptPayloadRequest.setUuids(List.of("2952410b-0a94"));
        final DecryptResource decryptResource = new DecryptResource();


        //Then
        assertThrows(WebApplicationException.class, () -> decryptResource.getDecrypt(decryptPayloadRequest));
    }
}
