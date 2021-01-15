package it.unimib.disco.sal.bestmovie.models;

import com.google.gson.annotations.SerializedName;

public class Crew {

    @SerializedName("adult")
    private Boolean adult;
    @SerializedName("gender")
    private Integer gender;
    @SerializedName("id")
    private Integer id;
    @SerializedName("known_for_department")
    private String knownForDepartment;
    @SerializedName("name")
    private String name;
    @SerializedName("original_name")
    private String originalName;
    @SerializedName("popularity")
    private Double popularity;
    @SerializedName("profile_path")
    private Object profilePath;
    @SerializedName("credit_id")
    private String creditId;
    @SerializedName("department")
    private String department;
    @SerializedName("job")
    private String job;

    public Boolean getAdult() {
        return adult;
    }

    public void setAdult(Boolean adult) {
        this.adult = adult;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getKnownForDepartment() {
        return knownForDepartment;
    }

    public void setKnownForDepartment(String knownForDepartment) {
        this.knownForDepartment = knownForDepartment;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOriginalName() {
        return originalName;
    }

    public void setOriginalName(String originalName) {
        this.originalName = originalName;
    }

    public Double getPopularity() {
        return popularity;
    }

    public void setPopularity(Double popularity) {
        this.popularity = popularity;
    }

    public Object getProfilePath() {
        return profilePath;
    }

    public void setProfilePath(Object profilePath) {
        this.profilePath = profilePath;
    }

    public String getCreditId() {
        return creditId;
    }

    public void setCreditId(String creditId) {
        this.creditId = creditId;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

}
