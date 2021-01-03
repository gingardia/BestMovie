package it.unimib.disco.sal.bestmovie.repositories;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import java.io.IOException;
import java.util.List;

import it.unimib.disco.sal.bestmovie.models.Movie;
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


    public void getListSearchDetails(MutableLiveData<Resource<List<Movie>>> moviesResource, int page, String query) {
        Call<PopularTopRatedApiResponse> call = moviesService.getListSearch(Constants.MOVIE_API_KEY, Constants.LANGUAGE, page, query);

        call.enqueue(new Callback<PopularTopRatedApiResponse>() {

            @Override
            public void onResponse(Call<PopularTopRatedApiResponse> call, Response<PopularTopRatedApiResponse> response) {

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
            public void onFailure(Call<PopularTopRatedApiResponse> call, Throwable t) {

                Resource<List<Movie>> resource = new Resource<>();
                //resource.setData(null);
                //resource.setTotalResults(null);

                resource.setStatusMessage(t.getMessage());
                moviesResource.postValue(resource);

                Log.d(TAG, "Errore " + t.getMessage());

            }
        });
    }

    public void getTopRatedMoviesDetails(MutableLiveData<Resource<List<Movie>>> moviesResource, int page) {
        Call<PopularTopRatedApiResponse> call = moviesService.getTopRatedMovies(Constants.MOVIE_API_KEY, Constants.LANGUAGE, page);

        call.enqueue(new Callback<PopularTopRatedApiResponse>() {

            @Override
            public void onResponse(Call<PopularTopRatedApiResponse> call, Response<PopularTopRatedApiResponse> response) {

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

                }
            }

            @Override
            public void onFailure(Call<PopularTopRatedApiResponse> call, Throwable t) {

                Resource<List<Movie>> resource = new Resource<>();
                resource.setStatusMessage(t.getMessage());
                moviesResource.postValue(resource);

                Log.d(TAG, "Errore " + t.getMessage());

            }
        });
    }


    public void getPopularMoviesDetails(MutableLiveData<Resource<List<Movie>>> moviesResource, int page) {
        Call<PopularTopRatedApiResponse> call = moviesService.getPopularMovies(Constants.MOVIE_API_KEY, Constants.LANGUAGE, page);

        call.enqueue(new Callback<PopularTopRatedApiResponse>() {

            @Override
            public void onResponse(Call<PopularTopRatedApiResponse> call, Response<PopularTopRatedApiResponse> response) {

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
            public void onFailure(Call<PopularTopRatedApiResponse> call, Throwable t) {

                Resource<List<Movie>> resource = new Resource<>();
                //resource.setData(null);
                //resource.setTotalResults(null);

                resource.setStatusMessage(t.getMessage());
                moviesResource.postValue(resource);

                Log.d(TAG, "Errore " + t.getMessage());

            }
        });
    }

    public void getUpcomingMoviesDetails(MutableLiveData<Resource<List<Movie>>> moviesResource, int page) {
        Call<UpcomingNowPlayingApiResponse> call = moviesService.getUpcomingMovies(Constants.MOVIE_API_KEY, Constants.LANGUAGE, page);

        call.enqueue(new Callback<UpcomingNowPlayingApiResponse>() {

            @Override
            public void onResponse(Call<UpcomingNowPlayingApiResponse> call, Response<UpcomingNowPlayingApiResponse> response) {

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
                    resource.setStatusCode(response.code());
                    try {
                        resource.setStatusMessage(response.errorBody().string() + "- " + response.message());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    moviesResource.postValue(resource);
                }
            }

            @Override
            public void onFailure(Call<UpcomingNowPlayingApiResponse> call, Throwable t) {

                Resource<List<Movie>> resource = new Resource<>();
                resource.setStatusMessage(t.getMessage());
                moviesResource.postValue(resource);

                Log.d(TAG, "Errore " + t.getMessage());

            }
        });
    }

    public void getNowPlayingMoviesDetails(MutableLiveData<Resource<List<Movie>>> moviesResource, int page) {
        Call<UpcomingNowPlayingApiResponse> call = moviesService.getNowPlayingMovies(Constants.MOVIE_API_KEY, Constants.LANGUAGE, page, Constants.region);

        call.enqueue(new Callback<UpcomingNowPlayingApiResponse>() {

            @Override
            public void onResponse(Call<UpcomingNowPlayingApiResponse> call, Response<UpcomingNowPlayingApiResponse> response) {

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
                    resource.setStatusCode(response.code());
                    try {
                        resource.setStatusMessage(response.errorBody().string() + "- " + response.message());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    moviesResource.postValue(resource);
                }
            }

            @Override
            public void onFailure(Call<UpcomingNowPlayingApiResponse> call, Throwable t) {

                Resource<List<Movie>> resource = new Resource<>();
                resource.setStatusMessage(t.getMessage());
                moviesResource.postValue(resource);

                Log.d(TAG, "Errore " + t.getMessage());

            }
        });
    }









}
