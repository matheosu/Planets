package br.com.b2w.bit.planets.integration;

import org.jboss.logging.Logger;
import org.wildfly.swarm.spi.runtime.annotations.ConfigurationValue;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.ws.rs.client.*;
import javax.ws.rs.core.MediaType;
import java.io.IOException;

public class SWAPI {

    private static final String SEARCH = "search";

    private Client client;
    private WebTarget target;

    @Inject
    @ConfigurationValue("swarm.integration.sw-api.url")
    private String url;

    @PostConstruct
    public void init() {
        this.client = ClientBuilder.newClient();
        this.client.register(new LoggingFilter());
        this.target = client.target(url);
    }

    public void findPlanets(String name, InvocationCallback<ResultSW<PlanetSW>> callback) {
        WebTarget planets = target.path("planets/");
        Invocation.Builder invocation = planets.queryParam(SEARCH, name).request(MediaType.APPLICATION_JSON);
        invocation.async().get(callback);
    }

    public static class LoggingFilter implements ClientRequestFilter, ClientResponseFilter {
        private static final Logger LOG = Logger.getLogger(LoggingFilter.class.getName());

        @Override
        public void filter(ClientRequestContext requestContext) throws IOException {
            LOG.info("Requesting");
            LOG.info(requestContext.getMethod() + ": " + requestContext.getUri());
            LOG.info("Headers:" + requestContext.getHeaders().toString());
            if (requestContext.getEntity() != null)
                LOG.info(requestContext.getEntity().toString());
        }

        @Override
        public void filter(ClientRequestContext requestContext, ClientResponseContext responseContext) throws IOException {
            LOG.info("Response");
            LOG.info("Status: " + responseContext.getStatusInfo());
            LOG.info("Headers: " + responseContext.getHeaders().toString());
        }
    }


}

