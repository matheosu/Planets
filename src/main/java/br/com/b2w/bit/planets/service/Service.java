package br.com.b2w.bit.planets.service;

import br.com.b2w.bit.planets.model.Entity;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.result.DeleteResult;
import org.bson.types.ObjectId;
import org.jboss.logging.Logger;

import java.util.*;

import static com.mongodb.client.model.Filters.eq;

class Service<T extends Entity> {

    private static final Logger logger = Logger.getLogger(Service.class);

    private static final String ID_PARAM = "_id";
    final MongoCollection<T> collection;

    Service(MongoCollection<T> collection) {
        this.collection = collection;
    }

    public Optional<T> findById(String id) {
        return findById(new ObjectId(id));
    }

    public Optional<T> findById(ObjectId id) {
        try {
            FindIterable<T> result = collection.find(eq(ID_PARAM, id));
            return result != null ? Optional.ofNullable(result.first()) : Optional.empty();
        } catch (Exception e) {
            logger.error("Error to findById.", e);
        }
        return Optional.empty();
    }

    public List<T> list() {
        try {
            FindIterable<T> result = collection.find();
            return result.into(new ArrayList<>());
        } catch (Exception e) {
            logger.error("Error to list.", e);
        }
        return Collections.emptyList();
    }

    public List<T> list(Pagination pagination) {
        try {
            FindIterable<T> result = collection.find().skip(pagination.getOffset()).limit(pagination.getLimit());
            return result.into(new ArrayList<>());
        } catch (Exception e) {
            logger.error("Error to list with Pagination", e);
        }
        return Collections.emptyList();
    }

    /**
     * https://jira.mongodb.org/browse/JAVA-2674
     */
    public void save(T entity) {
        try {
            entity.setId(new ObjectId());
            collection.insertOne(entity);
        } catch (Exception e) {
            logger.error("Error to save", e);
        }
    }

    public void update(T entity) {
        collection.replaceOne(eq(ID_PARAM, entity.getId()), entity);
    }

    public boolean delete(T entity) {
        try {
            Objects.requireNonNull(entity);
            DeleteResult deleteResult = collection.deleteOne(eq(ID_PARAM, entity.getId()));
            return deleteResult.getDeletedCount() > 0;
        } catch (Exception e) {
            logger.error("Error to delete", e);
        }
        return false;
    }

}
