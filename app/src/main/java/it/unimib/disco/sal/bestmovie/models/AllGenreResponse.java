package it.unimib.disco.sal.bestmovie.models;

import java.util.List;

public class AllGenreResponse {

    private List<Genre> genres;

    public AllGenreResponse(List<Genre> genres) {
        this.genres = genres;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }
}
