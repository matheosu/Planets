package br.com.b2w.bit.planets.integration;

import br.com.b2w.bit.planets.model.Planet;
import br.com.b2w.bit.planets.service.PlanetService;

import javax.ejb.Asynchronous;
import javax.inject.Inject;
import javax.ws.rs.client.InvocationCallback;
import java.util.List;

public class PlanetSWIntegration {

    private final SWAPI swapi;
    private final PlanetService planetService;

    @Inject
    public PlanetSWIntegration(SWAPI swapi,
                               PlanetService planetService) {
        this.swapi = swapi;
        this.planetService = planetService;
    }

    @Asynchronous
    public void getFilmsFromIntegration(final Planet planet) {
        swapi.findPlanet(planet.getNome(), new InvocationCallback<ResultSW<PlanetSW>>() {
            @Override
            public void completed(ResultSW<PlanetSW> planetSWResultSW) {
                Long count = planetSWResultSW.getCount();
                if (count < 0) {
                    // TODO Logger No Planet Found
                } else if (count > 1) {
                    // TODO Logger Too Many Planet Found
                } else {
                    List<PlanetSW> results = planetSWResultSW.getResults();
                    planetService.updateFilms(planet.get_id(), results.stream().map(PlanetSW::getFilms).count());
                }
            }

            @Override
            public void failed(Throwable throwable) {
                // TODO Logger Throwable
            }
        });
    }

}
