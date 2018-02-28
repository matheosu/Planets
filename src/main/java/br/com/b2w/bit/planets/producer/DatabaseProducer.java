package br.com.b2w.bit.planets.producer;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.wildfly.swarm.spi.runtime.annotations.ConfigurationValue;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Produces;
import javax.faces.bean.ApplicationScoped;
import javax.inject.Inject;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

@ApplicationScoped
public class DatabaseProducer {

    @Inject
    @ConfigurationValue("swarm.mongodb.host")
    private String host;

    @Inject
    @ConfigurationValue("swarm.mongodb.port")
    private Integer port;

    @Inject
    @ConfigurationValue("swarm.mongodb.database")
    private String databaseName;

    private MongoDatabase database;
    private MongoClient mongoClient;

    @PostConstruct
    private void init() {
        CodecRegistry pojoCodecRegistry = fromRegistries(MongoClient.getDefaultCodecRegistry(),
                fromProviders(PojoCodecProvider.builder().automatic(true).build()));
        this.mongoClient = new MongoClient(host, port);
        this.database = mongoClient.getDatabase(databaseName);
        this.database = database.withCodecRegistry(pojoCodecRegistry);
    }

    @Produces
    @Database
    public MongoDatabase getDatabase() {
        return database;
    }

}
