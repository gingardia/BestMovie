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

    public Movie(int id, boolean adult, String homepage, String overview, String originalLanguage, String originalTitle, float popularity, String posterPath, String backdropPath, String releaseDate, String title, boolean video, List<Genre> genres, float voteAverage, float voteCount, String status, String imdbId, String tagline, List<ProductionCompany> productionCompanies, List<ProductionCountry> productionCountries, List<SpokenLanguage> spokenLanguages, BelongsToCollection belongsToCollection) {
        this.id = id;
        this.adult = adult;
        this.homepage = homepage;
        this.overview = overview;
        this.originalLanguage = originalLanguage;
        this.originalTitle = originalTitle;
        this.popularity = popularity;
        this.posterPath = posterPath;
        this.backdropPath = backdropPath;
        this.releaseDate = releaseDate;
        this.title = title;
        this.video = video;
        this.genres = genres;
        this.voteAverage = voteAverage;
        this.voteCount = voteCount;
        this.status = status;
        this.imdbId = imdbId;
        this.tagline = tagline;
        this.productionCompanies = productionCompanies;
        this.productionCountries = productionCountries;
        this.spokenLanguages = spokenLanguages;
        this.belongsToCollection = belongsToCollection;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isAdult() {
        return adult;
    }

    public void setAdult(boolean adult) {
        this.adult = adult;
    }

    public String getHomepage() {
        return homepage;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public void setOriginalLanguage(String originalLanguage) {
        this.originalLanguage = originalLanguage;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public float getPopularity() {
        return popularity;
    }

    public void setPopularity(float popularity) {
        this.popularity = popularity;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isVideo() {
        return video;
    }

    public void setVideo(boolean video) {
        this.video = video;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    public float getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(float voteAverage) {
        this.voteAverage = voteAverage;
    }

    public float getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(float voteCount) {
        this.voteCount = voteCount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getImdbId() {
        return imdbId;
    }

    public void setImdbId(String imdbId) {
        this.imdbId = imdbId;
    }

    public String getTagline() {
        return tagline;
    }

    public void setTagline(String tagline) {
        this.tagline = tagline;
    }

    public List<ProductionCompany> getProductionCompanies() {
        return productionCompanies;
    }

    public void setProductionCompanies(List<ProductionCompany> productionCompanies) {
        this.productionCompanies = productionCompanies;
    }

    public List<ProductionCountry> getProductionCountries() {
        return productionCountries;
    }

    public void setProductionCountries(List<ProductionCountry> productionCountries) {
        this.productionCountries = productionCountries;
    }

    public List<SpokenLanguage> getSpokenLanguages() {
        return spokenLanguages;
    }

    public void setSpokenLanguages(List<SpokenLanguage> spokenLanguages) {
        this.spokenLanguages = spokenLanguages;
    }

    public BelongsToCollection getBelongsToCollection() {
        return belongsToCollection;
    }

    public void setBelongsToCollection(BelongsToCollection belongsToCollection) {
        this.belongsToCollection = belongsToCollection;
    }
}
