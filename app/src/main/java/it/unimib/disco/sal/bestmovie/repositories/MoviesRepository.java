package it.unimib.disco.sal.bestmovie.repositories;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.List;

import it.unimib.disco.sal.bestmovie.models.AllGenreResponse;
import it.unimib.disco.sal.bestmovie.models.Movie;
import it.unimib.disco.sal.bestmovie.models.MovieCreditsResponse;
import it.unimib.disco.sal.bestmovie.models.MovieDescription;
import it.unimib.disco.sal.bestmovie.models.MoviesResponse;
import it.unimib.disco.sal.bestmovie.models.PopularTopRatedApiResponse;
import it.unimib.disco.sal.bestmovie.models.Resource;
import it.unimib.disco.sal.bestmovie.models.UpcomingNowPlayingApiResponse;
import it.unimib.disco.sal.bestmovie.services.MoviesService;
import it.unimib.disco.sal.bestmovie.utils.Constants;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MoviesRepository {

    private static final String TAG = "MoviesRepository";
    private static MoviesRepository instance;
    private MoviesService moviesService;
    private MutableLiveData<MovieDescription> movieDescriptionLiveDataResponse;
    private MutableLiveData<MoviesResponse> nowPlayingMoviesResponse, topRatedMoviesResponse,
                                            popularMoviesResponse, upcomingMoviesResponse,
                                            searchMovieByTitleResponse, getMoviesSortedByResponse;
    private MutableLiveData<AllGenreResponse> getAllMovieGenresResponse;
    private MutableLiveData<MovieCreditsResponse> movieCreditsLiveDataResponse;

    public MoviesRepository() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(Constants.MOVIE_API_BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        moviesService = retrofit.create(MoviesService.class);
    }

    public static synchronized MoviesRepository getInstance() {
        if(instance == null){
            instance = new MoviesRepository();
        }
        return instance;
    }

    public void getTopRatedMovies(MutableLiveData<List<Movie>> movies) {
        Call<MoviesResponse> call = moviesService.getTopRatedMovies(Constants.MOVIE_API_KEY, Constants.LANGUAGE);

        call.enqueue(new Callback<MoviesResponse>() {

            @Override
            public void onResponse(Call<MoviesResponse> call, Response<MoviesResponse> response) {

                if(response.isSuccessful() && response.body() != null) {
                    movies.postValue(response.body().getResults());
                } else {
                    movies.postValue(null);
                }
            }

            @Override
            public void onFailure(Call<MoviesResponse> call, Throwable t) {
                movies.postValue(null);
            }
        });
    }


    public void getPopularMovies(MutableLiveData<List<Movie>> movies) {
        Call<MoviesResponse> call = moviesService.getPopularMovies(Constants.MOVIE_API_KEY, Constants.LANGUAGE);

        call.enqueue(new Callback<MoviesResponse>() {

            @Override
            public void onResponse(Call<MoviesResponse> call, Response<MoviesResponse> response) {

                if(response.isSuccessful() && response.body() != null) {
                    movies.postValue(response.body().getResults());
                } else {
                    movies.postValue(null);
                }
            }

            @Override
            public void onFailure(Call<MoviesResponse> call, Throwable t) {
                movies.postValue(null);
            }
        });
    }

    public void getUpcomingMovies(MutableLiveData<List<Movie>> movies) {
        Call<MoviesResponse> call = moviesService.getUpcomingMovies(Constants.MOVIE_API_KEY, Constants.LANGUAGE);

        call.enqueue(new Callback<MoviesResponse>() {

            @Override
            public void onResponse(Call<MoviesResponse> call, Response<MoviesResponse> response) {

                if(response.isSuccessful() && response.body() != null) {
                    movies.postValue(response.body().getResults());
                } else {
                    movies.postValue(null);
                }
            }

            @Override
            public void onFailure(Call<MoviesResponse> call, Throwable t) {
                movies.postValue(null);
            }
        });
    }

    public void getNowPlayingMovies(MutableLiveData<List<Movie>> movies) {
        Call<MoviesResponse> call = moviesService.getNowPlayingMovies(Constants.MOVIE_API_KEY, Constants.LANGUAGE, Constants.region);

        call.enqueue(new Callback<MoviesResponse>() {

            @Override
            public void onResponse(Call<MoviesResponse> call, Response<MoviesResponse> response) {

                if(response.isSuccessful() && response.body() != null) {
                    movies.postValue(response.body().getResults());
                } else {
                    movies.postValue(null);
                }
            }

            @Override
            public void onFailure(Call<MoviesResponse> call, Throwable t) {
                movies.postValue(null);
            }
        });
    }

    public void getMovieDescription(int movieID) {
        movieDescriptionLiveDataResponse = new MutableLiveData<>();
        Call<MovieDescription> call = moviesService.getMovieDescription(movieID, Constants.MOVIE_API_KEY, Constants.LANGUAGE, Constants.VIDEOS);

        call.enqueue(new Callback<MovieDescription>() {
            @Override
            public void onResponse(Call<MovieDescription> call, Response<MovieDescription> response) {
                if (response.body() != null) {
                    movieDescriptionLiveDataResponse.postValue(response.body());
                } else {
                    movieDescriptionLiveDataResponse.postValue(null);
                }
            }

            @Override
            public void onFailure(Call<MovieDescription> call, Throwable t) {
                movieDescriptionLiveDataResponse.postValue(null);
            }
        });
    }

    public void getMovieCredits(int movieID) {
        movieCreditsLiveDataResponse = new MutableLiveData<>();
        Call<MovieCreditsResponse> call = moviesService.getMovieCredits(movieID, Constants.MOVIE_API_KEY, Constants.LANGUAGE);

        call.enqueue(new Callback<MovieCreditsResponse>() {
            @Override
            public void onResponse(Call<MovieCreditsResponse> call, Response<MovieCreditsResponse> response) {
                if(response.body() != null) {
                    movieCreditsLiveDataResponse.postValue(response.body());
                } else {
                    movieCreditsLiveDataResponse.postValue(null);
                }
            }

            @Override
            public void onFailure(Call<MovieCreditsResponse> call, Throwable t) {
                movieCreditsLiveDataResponse.postValue(null);
            }
        });
    }

    public void searchMovieByTitle(String searchQuery) {
        Call<MoviesResponse> call = moviesService.searchMovieByTitle(Constants.MOVIE_API_KEY, Constants.LANGUAGE, searchQuery);

        call.enqueue(new Callback<MoviesResponse>() {
            @Override
            public void onResponse(Call<MoviesResponse> call, Response<MoviesResponse> response) {
                if(response.body() == null){
                    searchMovieByTitleResponse.postValue(null);
                }else{
                    searchMovieByTitleResponse.postValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<MoviesResponse> call, Throwable t) {
                searchMovieByTitleResponse.postValue(null);
            }
        });
    }

    public void getAllMovieGenres(){
        Call<AllGenreResponse> call = moviesService.getAllMovieGenres(Constants.MOVIE_API_KEY, Constants.LANGUAGE);

        call.enqueue(new Callback<AllGenreResponse>() {
            @Override
            public void onResponse(Call<AllGenreResponse> call, @NotNull Response<AllGenreResponse> response) {
                if(response == null || response.body() == null){
                    getAllMovieGenresResponse.postValue(null);
                }else{
                    Log.d(TAG, "onResponse: posttt");
                    getAllMovieGenresResponse.postValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<AllGenreResponse> call, Throwable t) {
                getAllMovieGenresResponse.postValue(null);
            }
        });
    }

    public void getMoviesSortedBy(String filter, String genres){
        Call<MoviesResponse> call = moviesService.getMoviesSortedBy(Constants.MOVIE_API_KEY, Constants.LANGUAGE, genres, filter);

        call.enqueue(new Callback<MoviesResponse>() {
            @Override
            public void onResponse(Call<MoviesResponse> call, Response<MoviesResponse> response) {
                if(response == null || response.body() == null){
                    getMoviesSortedByResponse.postValue(null);
                }else{
                    getMoviesSortedByResponse.postValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<MoviesResponse> call, Throwable t) {
                getMoviesSortedByResponse.postValue(null);
            }
        });
    }
        
    public MutableLiveData<MovieDescription> getMovieDescriptionLiveData() {
        return movieDescriptionLiveDataResponse;
    }

    public MutableLiveData<MovieCreditsResponse> getMovieCreditsLiveData() {
        return movieCreditsLiveDataResponse;
    }

    public MutableLiveData<MoviesResponse> getSearchMovieByTitleResponseLiveData(){
        return searchMovieByTitleResponse;
    }

    public MutableLiveData<AllGenreResponse> getAllMovieGenresLiveData(){
        return getAllMovieGenresResponse;
    }

    public MutableLiveData<MoviesResponse> getMoviesSortedByResponseLiveData(){
        return getMoviesSortedByResponse;
    }

}
