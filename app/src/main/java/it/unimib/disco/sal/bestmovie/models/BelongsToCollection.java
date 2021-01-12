package it.unimib.disco.sal.bestmovie.models;

public class BelongsToCollection {

    private int id;
    private String name;
    private String backdrop_path;
    private String poster_path;

    public BelongsToCollection(int id, String name, String backdrop_path, String poster_path) {
        this.id = id;
        this.name = name;
        this.backdrop_path = backdrop_path;
        this.poster_path = poster_path;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public void setBackdrop_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }
}
