package br.com.b2w.bit.planets.integration;

import java.io.Serializable;
import java.util.List;

public class ResultSW<Result> implements Serializable {

    private static final long serialVersionUID = 3018206290692462813L;

    private Long count;
    private String next;
    private String previous;

    private List<Result> results;


    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public String getPrevious() {
        return previous;
    }

    public void setPrevious(String previous) {
        this.previous = previous;
    }

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }
}
