package it.unimib.disco.sal.bestmovie.models;

import com.google.gson.annotations.SerializedName;

//Questa classe rappresenta i generi cinematografici, caratterizzati da un id e da un nome
public class Genre {

    @SerializedName("id")
    private Integer id;
    @SerializedName("name")
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
