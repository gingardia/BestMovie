package it.unimib.disco.sal.bestmovie.services;

import it.unimib.disco.sal.bestmovie.models.Movie;
import it.unimib.disco.sal.bestmovie.models.PopularTopRatedApiResponse;
import it.unimib.disco.sal.bestmovie.models.UpcomingNowPlayingApiResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface MoviesService {

    @GET("movie/popular")
    Call<PopularTopRatedApiResponse> getPopularMovies(@Query("api_key") String apiKey,
                                                      @Query("language") String language,
                                                      @Query("page") int page);

    @GET("movie/top_rated")
    Call<PopularTopRatedApiResponse> getTopRatedMovies(@Query("api_key") String apiKey,
                                                       @Query("language") String language,
                                                       @Query("page") int page);

    @GET("movie/upcoming")
    Call<UpcomingNowPlayingApiResponse> getUpcomingMovies(@Query("api_key") String apiKey,
                                                          @Query("language") String language,
                                                          @Query("page") int page);

    @GET("movie/now_playing")
    Call<UpcomingNowPlayingApiResponse> getNowPlayingMovies(@Query("api_key") String apiKey,
                                                       @Query("language") String language,
                                                       @Query("page") int page,
                                                       @Query("region") String region);

    @GET("movie/searchList")
    Call<PopularTopRatedApiResponse> getListSearch(@Query("api_key") String apiKey,
                                                   @Query("language") String language,
                                                   @Query("page") int page,
                                                   @Query("page") String query);

    @GET("movie/latest")
    Call<Movie> getLatestMovie(@Query("api_key") String apiKey,
                               @Query("language") String language);

    @GET("movie/{movie_id}")
    Call<Movie> getDetails(@Path("movie_id") int id,
                           @Query("api_key") String apiKey,
                           @Query("language") String language);

}