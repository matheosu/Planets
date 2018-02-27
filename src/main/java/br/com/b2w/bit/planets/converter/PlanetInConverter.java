package br.com.b2w.bit.planets.converter;

import br.com.b2w.bit.planets.dto.PlanetIn;
import br.com.b2w.bit.planets.model.Planet;

import java.util.function.Function;

public class PlanetInConverter extends FunctionConverter<PlanetIn, Planet> {

    @Override
    Function<PlanetIn, Planet> convertFunction() {
        return in -> {
            Planet planet = new Planet();
            planet.setNome(in.getNome());
            planet.setClima(in.getClima());
            planet.setTerreno(in.getTerreno());
            return planet;
        };
    }
}
