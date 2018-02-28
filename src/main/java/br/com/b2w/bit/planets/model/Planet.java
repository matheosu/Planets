package br.com.b2w.bit.planets.model;

public final class Planet extends Entity {

    private static final long serialVersionUID = 1936265232662522464L;

    private String nome;
    private String clima;
    private String terreno;

    private Long quantidadeFilmes;

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

    public Long getQuantidadeFilmes() {
        return quantidadeFilmes;
    }

    public void setQuantidadeFilmes(Long quantidadeFilmes) {
        this.quantidadeFilmes = quantidadeFilmes;
    }


}
