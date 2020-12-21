package it.unimib.disco.sal.bestmovie.services;

import it.unimib.disco.sal.bestmovie.models.Movie;
import it.unimib.disco.sal.bestmovie.models.MovieDetailsApiResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface MoviesService {

    @GET("movie/popular")
    Call<MovieDetailsApiResponse> getPopularMovies(@Query("api_key") String apiKey,
                                                   @Query("language") String language,
                                                   @Query("page") int page);

    @GET("movie/top_rated")
    Call<MovieDetailsApiResponse> getTopRatedMovies(@Query("api_key") String apiKey,
                                                   @Query("language") String language,
                                                   @Query("page") int page);

    @GET("movie/upcoming")
    Call<MovieDetailsApiResponse> getUpcomingMovies(@Query("api_key") String apiKey,
                                                    @Query("language") String language,
                                                    @Query("page") int page);

    @GET("movie/latest")
    Call<Movie> getLatestMovie(@Query("api_key") String apiKey,
                                                 @Query("language") String language,
                                                 @Query("page") int page);

    @GET("movie/{movie_id}")
    Call<Movie> getMovie(@Path("movie_id") int id,
                         @Query("api_key") String apiKey,
                         @Query("language") String language);

}