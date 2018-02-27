package br.com.b2w.bit.planets.service;

import br.com.b2w.bit.planets.api.annotation.Collection;
import br.com.b2w.bit.planets.model.Planet;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;

public class PlanetService extends Service {

    @Inject
    public PlanetService(@Collection("planets") MongoCollection<Document> collection) {
        super(collection);
    }

    public List<Planet> listByName(String name, Pagination pagination) {
        try {
            FindIterable<Planet> results = collection.find(eq("nome", name), Planet.class)
                    .skip(pagination.getOffset()).limit(pagination.getLimit());
            return results.into(new ArrayList<>());
        } catch (Exception e) {
            //
        }
        return Collections.emptyList();
    }

    public long updateFilms(String id, Long films) {
        try {
            UpdateResult result = collection.updateOne(eq(ID_PARAM, id),
                    new Document("$set", new Document("films", films)));
            return result.getModifiedCount();
        } catch (Exception e) {
        //
        }
        return DEFAULT_DELETE_RESULT;
    }
}
