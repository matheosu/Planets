package br.com.b2w.bit.planets.api;

import br.com.b2w.bit.planets.converter.PlanetInConverter;
import br.com.b2w.bit.planets.converter.PlanetOutConverter;
import br.com.b2w.bit.planets.dto.PlanetIn;
import br.com.b2w.bit.planets.dto.PlanetOut;
import br.com.b2w.bit.planets.integration.PlanetSWIntegration;
import br.com.b2w.bit.planets.model.Planet;
import br.com.b2w.bit.planets.producer.CacheControlConfig;
import br.com.b2w.bit.planets.service.PlanetService;
import br.com.b2w.bit.planets.util.Strings;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.CacheControl;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/planets")
public class PlanetResource extends Resource {

    @Inject
    @CacheControlConfig(maxAge = 100)
    private CacheControl cache;

    @Inject
    private PlanetService service;

    @Inject
    private PlanetInConverter inConverter;

    @Inject
    private PlanetOutConverter outConverter;

    @Inject
    private PlanetSWIntegration integration;

    @GET
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response get(@PathParam("id") String id) {
        Planet planet = service.findById(id).orElseThrow(this::planetNotFound);
        PlanetOut out = outConverter.convert(planet);
        return okETag(out, cache);
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Response list(@QueryParam("nome") String nome,
                         @BeanParam PaginationBean pagination) {

        List<Planet> planets = Strings.isNotNullAndNotEmptyTrim(nome) ?
                service.listByName(nome, pagination) : service.list(pagination);
        List<PlanetOut> out = outConverter.convert(planets);
        return ok(out, "Nenhum planeta encontrado");
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    public Response create(PlanetIn planetIn) {
        Planet planet = inConverter.convert(planetIn);
        service.save(planet);
        integration.getFilmsFromIntegration(planet);
        return created("/{id}", planet.getId().toString());
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") String id) {
        Planet planet = service.findById(id).orElseThrow(this::planetNotFound);
        return service.delete(planet) ? Response.noContent().build() : Response.serverError().build();
    }

    private NotFoundException planetNotFound() {
        return new NotFoundException("Planeta não encontrado!");
    }

}
