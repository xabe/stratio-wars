package com.xabe.stratio.wars.config;

import com.xabe.stratio.wars.config.jackson.ObjectMapperContextResolver;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.logging.LoggingFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;

import javax.ws.rs.ApplicationPath;

@ApplicationPath("/")
public class CustomResourceConfig extends ResourceConfig {

    public CustomResourceConfig() {
        packages("com.xabe.stratio.wars.resource");
        register(JacksonFeature.class);
        register(new ObjectMapperContextResolver());
        property( ServerProperties.BV_FEATURE_DISABLE, true );
        property( ServerProperties.RESOURCE_VALIDATION_IGNORE_ERRORS, true );
    }
}
