package it.unimib.disco.sal.bestmovie.services;

import it.unimib.disco.sal.bestmovie.models.MovieDetailsApiResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface MoviesService {


    @GET("movie/{category}")
    Call<MovieDetailsApiResponse> getMovies(@Path ("category") String category,
                                            @Header("Authorization") String apiKey);





}