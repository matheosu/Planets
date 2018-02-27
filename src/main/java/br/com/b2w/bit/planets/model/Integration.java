package br.com.b2w.bit.planets.model;

import java.io.Serializable;

public class Integration implements Serializable {

    private String url;
    private Source source;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Source getSource() {
        return source;
    }

    public void setSource(Source source) {
        this.source = source;
    }
}
