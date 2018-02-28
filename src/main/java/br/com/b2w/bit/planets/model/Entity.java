package br.com.b2w.bit.planets.model;

import org.bson.types.ObjectId;

import java.io.Serializable;
import java.util.Objects;

public abstract class Entity implements Serializable {

    private static final long serialVersionUID = -8425106839096417309L;

    private ObjectId id;

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Entity)) return false;
        Entity entity = (Entity) o;
        return Objects.equals(getId(), entity.getId());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getId());
    }
}
