package it.unimib.disco.sal.bestmovie.models;

import java.util.List;

public class Videos {

    List<Result> results;

    public Videos(List<Result> results) {
        this.results = results;
    }

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }
}
