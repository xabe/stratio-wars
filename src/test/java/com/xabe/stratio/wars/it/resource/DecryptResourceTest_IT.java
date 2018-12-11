package com.xabe.stratio.wars.it.resource;

import com.xabe.stratio.wars.payload.DecryptPayloadResponse;
import org.glassfish.grizzly.http.util.HttpStatus;
import org.junit.jupiter.api.Test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.nio.charset.StandardCharsets;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

public class DecryptResourceTest_IT {

    @Test
    public void shouldGetStatus() throws Exception {
        //Given
        final Client client = ClientBuilder.newClient();
        final WebTarget target = client.target( "http://localhost:8008"  ).path( "/v1" ).path( "/status" );

        //When
        final Response response = client.target(target.getUri())
                .request(MediaType.TEXT_PLAIN)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.TEXT_PLAIN + "; charset=" + StandardCharsets.UTF_8.name())
                .get();

        //Then
        final String result = response.readEntity(String.class);
        assertThat(result,is(notNullValue()));
    }

    @Test
    public void givenAUuidsWhenInvokeThenReturnDescryptUuids() throws Exception {
        //Given
        final Client client = ClientBuilder.newClient();
        final WebTarget target = client.target( "http://localhost:8008"  ).path( "/v1" ).path( "/decrypt" ).queryParam("uuids", "2952410b-0a94-446b-8bcb-448dc6e30b08");

        //When
        final Response response = client.target(target.getUri())
                .request(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON + "; charset=" + StandardCharsets.UTF_8.name())
                .get();

        //Then
        final DecryptPayloadResponse decryptPayloadResponse = response.readEntity(DecryptPayloadResponse.class);
        assertThat(decryptPayloadResponse,is(notNullValue()));
        assertThat(decryptPayloadResponse.getCoordinatePayloadResponses().get(0),is(notNullValue()));
    }

    @Test
    public void givenAUuidsIncorrectWhenInvokeThenReturnBadRequest() throws Exception {
        //Given
        final Client client = ClientBuilder.newClient();
        final WebTarget target = client.target( "http://localhost:8008"  ).path( "/v1" ).path( "/decrypt" ).queryParam("uuids", "-sdas-asdasddsa");

        //When
        final Response response = client.target(target.getUri())
                .request(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON + "; charset=" + StandardCharsets.UTF_8.name())
                .get();

        //Then
        assertThat(response.getStatus(),is(HttpStatus.BAD_REQUEST_400.getStatusCode()));
    }
}
