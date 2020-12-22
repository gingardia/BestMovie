package it.unimib.disco.sal.bestmovie.repositories;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import java.io.IOException;
import java.util.List;

import it.unimib.disco.sal.bestmovie.models.Movie;
import it.unimib.disco.sal.bestmovie.models.MovieDetailsApiResponse;
import it.unimib.disco.sal.bestmovie.models.Resource;
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

    public void getPopularMoviesDetails(MutableLiveData<Resource<List<Movie>>> moviesResource, int page) {
        Call<MovieDetailsApiResponse> call = moviesService.getPopularMovies(Constants.MOVIE_API_KEY, Constants.LANGUAGE, page);

        call.enqueue(new Callback<MovieDetailsApiResponse>() {

            @Override
            public void onResponse(Call<MovieDetailsApiResponse> call, Response<MovieDetailsApiResponse> response) {

                if(response.isSuccessful() && response.body() != null) {

                    Resource<List<Movie>> resource = new Resource<>();
                    resource.setData(response.body().getResults());
                    resource.setTotalResults(response.body().getTotalResults());
                    resource.setStatusCode(response.code());
                    resource.setStatusMessage(response.message());
                    moviesResource.postValue(resource);

                    Log.d(TAG, "onResponse: " + response.body().getTotalResults());


                } else if(response.errorBody() != null) {

                    Resource<List<Movie>> resource = new Resource<>();
                    //resource.setData(null);
                    //resource.setTotalResults(null);
                    resource.setStatusCode(response.code());
                    try {
                        resource.setStatusMessage(response.errorBody().string() + "- " + response.message());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    moviesResource.postValue(resource);


                    /*try {
                        new Throwable(response.errorBody().string()).printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }*/
                }
            }

            @Override
            public void onFailure(Call<MovieDetailsApiResponse> call, Throwable t) {

                Resource<List<Movie>> resource = new Resource<>();
                //resource.setData(null);
                //resource.setTotalResults(null);

                resource.setStatusMessage(t.getMessage());
                moviesResource.postValue(resource);

                Log.d(TAG, "Errore " + t.getMessage());

            }
        });
    }




}
