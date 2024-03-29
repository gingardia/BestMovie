package it.unimib.disco.sal.bestmovie.services;

import it.unimib.disco.sal.bestmovie.models.AllGenreResponse;
import it.unimib.disco.sal.bestmovie.models.MovieCreditsResponse;
import it.unimib.disco.sal.bestmovie.models.MovieDescription;
import it.unimib.disco.sal.bestmovie.models.MoviesResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface MoviesService {

    @GET("movie/popular")
    Call<MoviesResponse> getPopularMovies(@Query("api_key") String apiKey,
                                          @Query("language") String language);

    @GET("movie/top_rated")
    Call<MoviesResponse> getTopRatedMovies(@Query("api_key") String apiKey,
                                           @Query("language") String language);

    @GET("movie/upcoming")
    Call<MoviesResponse> getUpcomingMovies(@Query("api_key") String apiKey,
                                           @Query("language") String language);

    @GET("movie/now_playing")
    Call<MoviesResponse> getNowPlayingMovies(@Query("api_key") String apiKey,
                                             @Query("language") String language,
                                             @Query("region") String region);

    @GET("movie/{id}")
    Call<MovieDescription> getMovieDescription(@Path("id") int movieID,
                                               @Query("api_key") String apiKey,
                                               @Query("language") String language,
                                               @Query("append_to_response") String videos);

    @GET("movie/{id}/credits")
    Call<MovieCreditsResponse> getMovieCredits(@Path("id") int movieID,
                                               @Query("api_key") String apiKey,
                                               @Query("language") String language);

    @GET("search/movie")
    Call<MoviesResponse> searchMovieByTitle(@Query("api_key") String apiKey,
                                            @Query("language") String language,
                                            @Query("query") String videos);

    @GET("genre/movie/list")
    Call<AllGenreResponse> getAllMovieGenres(@Query("api_key") String apiKey,
                                             @Query("language") String language);

    @GET("discover/movie")
    Call<MoviesResponse> getMoviesSortedBy(@Query("api_key") String apiKey,
                                           @Query("language") String language,
                                           @Query("with_genres") String genreIDS,
                                           @Query("sort_by") String sortBy);
}