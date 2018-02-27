package br.com.b2w.bit.planets.producer;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.faces.bean.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

@ApplicationScoped
public class CollectionProducer {

    private final MongoDatabase database;

    @Inject
    public CollectionProducer(@Named("planetsDB") MongoDatabase database) {
        this.database = database;
    }
    
    
    @Produces
    @Collection
    public MongoCollection<Document> getCollection(InjectionPoint ip) {
        Collection annotation = ip.getAnnotated().getAnnotation(Collection.class);
        String collectionName = annotation.value();
        if (!collectionName.isEmpty())
            return database.getCollection(collectionName);

        throw new IllegalArgumentException();
    }
}
