package br.com.b2w.bit.planets.dto;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

public class PlanetIn implements Serializable {

    private static final long serialVersionUID = -8211699269672116864L;

    @NotNull
    private String nome;
    private String clima;
    private String terreno;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getClima() {
        return clima;
    }

    public void setClima(String clima) {
        this.clima = clima;
    }

    public String getTerreno() {
        return terreno;
    }

    public void setTerreno(String terreno) {
        this.terreno = terreno;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PlanetIn)) return false;
        PlanetIn planetIn = (PlanetIn) o;
        return Objects.equals(getNome(), planetIn.getNome()) &&
                Objects.equals(getClima(), planetIn.getClima()) &&
                Objects.equals(getTerreno(), planetIn.getTerreno());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNome(), getClima(), getTerreno());
    }
}
