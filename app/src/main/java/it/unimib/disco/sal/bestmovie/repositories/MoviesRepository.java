package it.unimib.disco.sal.bestmovie.repositories;

import it.unimib.disco.sal.bestmovie.services.MoviesService;
import it.unimib.disco.sal.bestmovie.utils.Constants;
import retrofit2.Retrofit;

public class MoviesRepository {

    private static MoviesRepository instance;
    private MoviesService moviesService;

    private MoviesRepository() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(Constants.MOVIE_API_BASE_URL).build();

        moviesService = retrofit.create(MoviesService.class);
    }

    public static synchronized MoviesRepository getInstance() {
        if(instance == null){
            instance = new MoviesRepository();
        }
        return instance;
    }
}
