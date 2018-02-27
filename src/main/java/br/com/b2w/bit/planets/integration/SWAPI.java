package br.com.b2w.bit.planets.integration;

import javax.annotation.PostConstruct;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.InvocationCallback;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

public class SWAPI {

    private static final String SEARCH = "search";

    private final Client client = ClientBuilder.newClient();

    private WebTarget target;

    @PostConstruct
    public void init() {
        target = client.target("https://swapi.co/api");
    }

    public void findPlanet(String name, InvocationCallback<ResultSW<PlanetSW>> callback) {
        target.path("/planets")
                .queryParam(SEARCH, name)
                .request(MediaType.APPLICATION_JSON)
                .async()
                .get(callback);
    }


}

