package br.com.b2w.bit.planets.integration;

import br.com.b2w.bit.planets.model.Planet;
import br.com.b2w.bit.planets.service.PlanetService;
import org.jboss.logging.Logger;

import javax.ejb.Asynchronous;
import javax.inject.Inject;
import javax.ws.rs.client.InvocationCallback;
import java.util.List;

public class PlanetSWIntegration {

    private static final Logger logger = Logger.getLogger(PlanetSWIntegration.class);

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
        swapi.findPlanets(planet.getNome(), new InvocationCallback<ResultSW<PlanetSW>>() {
            @Override
            public void completed(ResultSW<PlanetSW> planetSWResultSW) {
                Long count = planetSWResultSW.getCount();
                Long films = 0L;
                if (count < 1) {
                    logger.warn("Planet not found in SW API");
                } else if (count > 1) {
                    logger.warn("Too many Planets found in SW API");
                } else {
                    List<PlanetSW> results = planetSWResultSW.getResults();
                    films = results.stream().map(PlanetSW::getFilms).count();
                }
                planet.setQuantidadeFilmes(films);
                planetService.update(planet);
            }

            @Override
            public void failed(Throwable throwable) {
                logger.error("Error in integration. ", throwable);
            }
        });
    }

}
