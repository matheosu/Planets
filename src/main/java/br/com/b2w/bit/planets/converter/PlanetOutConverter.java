package br.com.b2w.bit.planets.converter;

import br.com.b2w.bit.planets.dto.PlanetOut;
import br.com.b2w.bit.planets.model.Planet;

import java.util.function.Function;

public class PlanetOutConverter extends FunctionConverter<Planet, PlanetOut> {

    @Override
    Function<Planet, PlanetOut> convertFunction() {
        return planet -> {
            PlanetOut out = new PlanetOut();
            out.setId(planet.getId().toString());
            out.setNome(planet.getNome());
            out.setClima(planet.getClima());
            if (planet.getQuantidadeFilmes() != null) {
                out.setQuantidadeFilmes(planet.getQuantidadeFilmes());
            } else {
                out.setQuantidadeFilmes(0L);
            }
            out.setTerreno(planet.getTerreno());
            return out;
        };
    }
}
