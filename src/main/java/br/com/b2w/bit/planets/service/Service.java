package br.com.b2w.bit.planets.service;

import br.com.b2w.bit.planets.model.Entity;
import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.result.DeleteResult;
import org.bson.Document;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class Service {

    static final String ID_PARAM = "_id";
    static final int DEFAULT_DELETE_RESULT = 0;

    final MongoCollection<Document> collection;
    Service(MongoCollection<Document> collection) {
        this.collection = collection;
    }

    public <T extends Entity> Optional<T> findById(String id, Class<T> clazz) {
        try {
            FindIterable<T> result = collection.find(new BasicDBObject(ID_PARAM, id), clazz);
            return result != null ? Optional.ofNullable(result.first()) : Optional.empty();
        } catch (Exception e) {
            //
        }
        return Optional.empty();
    }

    public <T extends Entity> List<T> list(Class<T> clazz) {
        try {
            FindIterable<T> result = collection.find(clazz);
            return result.into(new ArrayList<>());
        } catch (Exception e) {
            //
        }
        return Collections.emptyList();
    }


    public <T extends Entity> void save(T entity) {
        try {
            Document document = new Document(entity.toMap());
            collection.insertOne(document);
            String id = document.getString(ID_PARAM);
            entity.set_id(id);
        } catch (Exception e) {
            //
        }
    }

    public boolean deleted(String id) {
        return delete(id) > DEFAULT_DELETE_RESULT;
    }

    public long delete(String id) {
        try {
            DeleteResult deleteResult = collection.deleteOne(new BasicDBObject(ID_PARAM, id));
            return deleteResult.getDeletedCount();
        } catch (Exception e) {
            //
        }
        return DEFAULT_DELETE_RESULT;
    }

}
