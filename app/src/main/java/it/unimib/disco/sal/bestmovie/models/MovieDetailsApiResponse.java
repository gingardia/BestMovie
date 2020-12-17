package it.unimib.disco.sal.bestmovie.models;

import java.util.List;

public class MovieDetailsApiResponse {
    private int page;
    //Prima: private List<Movie> movies;
    private List<Movie> results;
    private int totalPages;
    private int totalResults;

    public MovieDetailsApiResponse(int page, List<Movie> movies, int totalPages, int totalResults) {
        this.page = page;
        this.results = movies;
        this.totalPages = totalPages;
        this.totalResults = totalResults;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public List<Movie> getResults() {
        return results;
    }

    public void setResults(List<Movie> results) {
        this.results = results;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }
}
