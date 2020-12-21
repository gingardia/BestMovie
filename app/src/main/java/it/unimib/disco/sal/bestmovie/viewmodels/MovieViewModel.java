package it.unimib.disco.sal.bestmovie.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import it.unimib.disco.sal.bestmovie.models.Movie;
import it.unimib.disco.sal.bestmovie.repositories.MoviesRepository;

public class MovieViewModel extends ViewModel {

    private MutableLiveData<List<Movie>> movies;


    public LiveData<List<Movie>> getMovies(int page) {
        if (movies == null) {
            movies = new MutableLiveData<>();
            MoviesRepository.getInstance().getPopularMoviesDetails(movies, page);
        }
        return movies;
    }



}
