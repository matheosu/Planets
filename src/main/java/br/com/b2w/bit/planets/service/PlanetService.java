package br.com.b2w.bit.planets.service;

import br.com.b2w.bit.planets.model.Planet;
import br.com.b2w.bit.planets.producer.Collection;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import org.jboss.logging.Logger;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;

public class PlanetService extends Service<Planet> {

    private static final Logger logger = Logger.getLogger(PlanetService.class);

    @Inject
    public PlanetService(@Collection(value = "planets", clazz = Planet.class) MongoCollection<Planet> collection) {
        super(collection);
    }

    public List<Planet> listByName(String name, Pagination pagination) {
        try {
            FindIterable<Planet> results = collection.find(eq("nome", name), Planet.class)
                    .skip(pagination.getOffset()).limit(pagination.getLimit());
            return results.into(new ArrayList<>());
        } catch (Exception e) {
            logger.error("Error List Planet By Name", e);
        }
        return Collections.emptyList();
    }

}
