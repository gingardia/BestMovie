package it.unimib.disco.sal.bestmovie.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import it.unimib.disco.sal.bestmovie.models.Movie;
import it.unimib.disco.sal.bestmovie.models.Resource;
import it.unimib.disco.sal.bestmovie.repositories.MoviesRepository;
import it.unimib.disco.sal.bestmovie.utils.Constants;

public class MovieViewModelSearch extends ViewModel {

    private MutableLiveData<Resource<List<Movie>>> movies;

    public LiveData<Resource<List<Movie>>> getMovieSearch(int page, String query) {
        if (movies == null) {
            movies = new MutableLiveData<>();
            MoviesRepository.getInstance().getListSearchDetails(movies, page, query);
        }
        return movies;
    }
}
