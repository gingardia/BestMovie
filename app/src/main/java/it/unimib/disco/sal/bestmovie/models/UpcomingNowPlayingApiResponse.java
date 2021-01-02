package it.unimib.disco.sal.bestmovie.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UpcomingNowPlayingApiResponse {

    @SerializedName("page")
    private int page;
    @SerializedName("results")
    private List<Movie> results;
    @SerializedName("dates")
    private Dates dates;
    @SerializedName("total_pages")
    private int totalPages;
    @SerializedName("total_results")
    private int totalResults;

    public UpcomingNowPlayingApiResponse(int page, List<Movie> results, Dates dates, int totalPages, int totalResults) {
        this.page = page;
        this.results = results;
        this.dates = dates;
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

    public Dates getDates() {
        return dates;
    }

    public void setDates(Dates dates) {
        this.dates = dates;
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