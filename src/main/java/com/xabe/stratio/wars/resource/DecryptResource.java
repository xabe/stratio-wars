package com.xabe.stratio.wars.resource;

import com.xabe.stratio.wars.decrypt.Decrypt;
import com.xabe.stratio.wars.payload.DecryptPayloadRequest;
import com.xabe.stratio.wars.payload.DecryptPayloadResponse;

import javax.inject.Singleton;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Singleton
@Path("/v1")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class DecryptResource {


    @Path("/status")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.TEXT_PLAIN)
    public String getStatus() {
        return "OK";
    }

    @GET
    @Path("/decrypt")
    public DecryptPayloadResponse getDecrypt(@BeanParam DecryptPayloadRequest decryptPayloadRequest) {
        if(!ValidateDecrypt.validUuids().apply(decryptPayloadRequest)){
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }
        return new Decrypt().apply(decryptPayloadRequest);
    }
}
