package it.unimib.disco.sal.bestmovie.models;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class MovieCreditsResponse {

    @SerializedName("id")
    private Integer id;
    @SerializedName("cast")
    private List<Cast> cast = null;
    @SerializedName("crew")
    private List<Crew> crew = null;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Cast> getCast() {
        return cast;
    }

    public void setCast(List<Cast> cast) {
        this.cast = cast;
    }

    public List<Crew> getCrew() {
        return crew;
    }

    public void setCrew(List<Crew> crew) {
        this.crew = crew;
    }

}
