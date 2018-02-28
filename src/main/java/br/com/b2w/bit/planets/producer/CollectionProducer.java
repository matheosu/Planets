package br.com.b2w.bit.planets.producer;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.jboss.logging.Logger;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.inject.Inject;

@RequestScoped
public class CollectionProducer {

    private static final Logger logger = Logger.getLogger(CollectionProducer.class);

    @Inject
    @Database
    private MongoDatabase database;

    @Produces
    @Collection
    @SuppressWarnings("unchecked")
    public <T> MongoCollection<T> getCollection(InjectionPoint ip) {
        Collection annotation = ip.getAnnotated().getAnnotation(Collection.class);
        String collectionName = annotation.value();
        Class<?> clazz = annotation.clazz();

        try {
            if (!collectionName.isEmpty()) {
                if (!existCollection(collectionName)) {
                    logger.info("Creating Collection " + collectionName);
                    database.createCollection(collectionName);
                }
                if (!Document.class.equals(clazz)) {
                    return (MongoCollection<T>) database.getCollection(collectionName, clazz);
                }
                return (MongoCollection<T>) database.getCollection(collectionName);
            }
        } catch (Exception e) {
            logger.error(e);
        }
        throw new IllegalArgumentException();
    }

    private boolean existCollection(String name) {
        for (String s : database.listCollectionNames()) {
            if (name.equalsIgnoreCase(s))
                return true;
        }
        return false;
    }

}
