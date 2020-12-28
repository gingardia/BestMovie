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

    private MutableLiveData<Resource<Movie>> movie;

    public LiveData<Resource<Movie>> getMovieSearch(int id) {
        if (movie == null) {
            movie = new MutableLiveData<>();
            MoviesRepository.getInstance().getSingularMovieDetails(id, Constants.LANGUAGE);
        }
        return movie;
    }
}
