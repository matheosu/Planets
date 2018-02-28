package br.com.b2w.bit.planets.dto;

import java.util.Objects;

public class PlanetOut extends PlanetIn {

    private static final long serialVersionUID = -4387426749787466356L;

    private String id;

    private Long quantidadeFilmes;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getQuantidadeFilmes() {
        return quantidadeFilmes;
    }

    public void setQuantidadeFilmes(Long quantidadeFilmes) {
        this.quantidadeFilmes = quantidadeFilmes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PlanetOut)) return false;
        if (!super.equals(o)) return false;
        PlanetOut planetOut = (PlanetOut) o;
        return Objects.equals(getId(), planetOut.getId()) &&
                Objects.equals(getQuantidadeFilmes(), planetOut.getQuantidadeFilmes());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getId(), getQuantidadeFilmes());
    }
}
