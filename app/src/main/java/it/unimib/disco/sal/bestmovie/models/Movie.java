package it.unimib.disco.sal.bestmovie.models;

import java.util.List;

public class Movie {

    public int id;
    public boolean adult;
    public String homepage;
    public String overview;
    public String originalLanguage;
    public String originalTitle;
    public float popularity;
    public String posterPath;
    public String backdropPath;
    public String releaseDate; //format date, pattern = "yyyy-mm-dd"
    public String title;
    public boolean video;
    public List<Genre> genres = null;
    public float voteAverage;
    public float voteCount;
    public String status; // "Released" or "DATROVARE"
    public String imdbId; // minLength = 9, maxLength=9, pattern = ^tt[0-9]{7} (esempio: tt12416066)
    public String tagline;
    public List<ProductionCompany> productionCompanies = null;
    public List<ProductionCountry> productionCountries = null;
    public List<SpokenLanguage> spokenLanguages = null;
    public BelongsToCollection belongsToCollection;

}
