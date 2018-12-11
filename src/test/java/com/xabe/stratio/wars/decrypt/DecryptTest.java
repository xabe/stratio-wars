package com.xabe.stratio.wars.decrypt;

import com.xabe.stratio.wars.payload.CoordinatePayloadRequest;
import com.xabe.stratio.wars.payload.DecryptPayloadRequest;
import com.xabe.stratio.wars.payload.DecryptPayloadResponse;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

public class DecryptTest {

    @Test
    public void givenADecryptPayloadRequestWhenInvokeApplyThenReturnDecryptPayloadResponse() throws Exception {
        //Given
        final DecryptPayloadRequest decryptPayloadRequest = new DecryptPayloadRequest();
        decryptPayloadRequest.setCoordinates(List.of(
                new CoordinatePayloadRequest("2952410b-0a94-446b-8bcb-448dc6e30b08"),
                new CoordinatePayloadRequest("6f9c15fa-ef51-4415-afab-36218d76c2d9"),
                new CoordinatePayloadRequest("2ab81c9b-1719-400c-a676-bdba976150eb")));
        final Decrypt decrypt = new Decrypt();

        //When
        final DecryptPayloadResponse payloadResponse = decrypt.apply(decryptPayloadRequest);


        //Then
        assertThat(payloadResponse, is(notNullValue()));
        assertThat(payloadResponse.getCoordinatePayloadResponses().get(0).getDecrypt(), is("34-10-42-edcb86430"));
        assertThat(payloadResponse.getCoordinatePayloadResponses().get(1).getDecrypt(), is("73-15-46-dc9876321"));
        assertThat(payloadResponse.getCoordinatePayloadResponses().get(2).getDecrypt(), is("64-9-35-edba976510"));
    }

}
