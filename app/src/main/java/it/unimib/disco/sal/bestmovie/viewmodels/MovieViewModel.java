package it.unimib.disco.sal.bestmovie.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import it.unimib.disco.sal.bestmovie.models.Movie;

public class MovieViewModel extends ViewModel {

    private MutableLiveData<List<Movie>> movies;
    public LiveData<List<Movie>> getMovies() {
        if (movies == null) {
            movies = new MutableLiveData<List<Movie>>();
            loadMovies();
        }
        return movies;
    }

    private void loadMovies() {
        // Do an asynchronous operation to fetch users.
    }

}
