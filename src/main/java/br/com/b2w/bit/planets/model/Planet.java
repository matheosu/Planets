package br.com.b2w.bit.planets.model;

import br.com.b2w.bit.planets.util.Strings;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Planet extends Entity {

    private static final long serialVersionUID = 1936265232662522464L;

    private String nome;
    private String clima;
    private String terreno;

    private Long filmes;

    private List<Integration> integrations;

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

    public Long getFilmes() {
        return filmes;
    }

    public void setFilmes(Long filmes) {
        this.filmes = filmes;
    }

    public List<Integration> getIntegrations() {
        return integrations;
    }

    public void setIntegrations(List<Integration> integrations) {
        this.integrations = integrations;
    }

    @Override
    public Map<String, Object> toMap() {
        HashMap<String, Object> map = new HashMap<>();

        if (Strings.isNotNullAndNotEmptyTrim(nome))
            map.put("nome", nome);
        if (Strings.isNotNullAndNotEmptyTrim(clima))
            map.put("clima", clima);
        if (Strings.isNotNullAndNotEmptyTrim(terreno))
            map.put("terreno", terreno);

        return Collections.unmodifiableMap(map);
    }

}
