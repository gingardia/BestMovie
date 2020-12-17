package it.unimib.disco.sal.bestmovie.repositories;

import androidx.lifecycle.MutableLiveData;

import java.util.List;

import it.unimib.disco.sal.bestmovie.models.Movie;
import it.unimib.disco.sal.bestmovie.models.MovieDetailsApiResponse;
import it.unimib.disco.sal.bestmovie.services.MoviesService;
import it.unimib.disco.sal.bestmovie.utils.Constants;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MoviesRepository {


    private static MoviesRepository instance;
    private MoviesService moviesService;

    private MoviesRepository() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(Constants.MOVIE_API_BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        moviesService = retrofit.create(MoviesService.class);
    }

    public static synchronized MoviesRepository getInstance() {
        if(instance == null){
            instance = new MoviesRepository();
        }
        return instance;
    }


    public void getMovieDetails(MutableLiveData<List<Movie>> movies) {
        Call<MovieDetailsApiResponse> call = moviesService.getMovies("latest", Constants.MOVIE_API_READ_ACCESS_TOKEN);

        call.enqueue(new Callback<MovieDetailsApiResponse>() {

            //Chiamato se la chiamata va a buon fine
            @Override
            public void onResponse(Call<MovieDetailsApiResponse> call, Response<MovieDetailsApiResponse> response) {
                response.body().getResults();
            }

            //Chiamato se si verifica un errore e la chiamata non va a buon fine
            @Override
            public void onFailure(Call<MovieDetailsApiResponse> call, Throwable t) {

                //t.printStackTrace();

            }
        });
    }




}
