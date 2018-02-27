package br.com.b2w.bit.planets.model;

import java.io.Serializable;
import java.util.Map;
import java.util.Objects;

public abstract class Entity implements Serializable {

    private static final long serialVersionUID = -8425106839096417309L;

    private String _id;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public abstract Map<String, Object> toMap();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Entity)) return false;
        Entity entity = (Entity) o;
        return Objects.equals(get_id(), entity.get_id());
    }

    @Override
    public int hashCode() {
        return Objects.hash(get_id());
    }
}
