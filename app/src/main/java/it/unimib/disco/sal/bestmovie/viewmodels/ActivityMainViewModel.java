package it.unimib.disco.sal.bestmovie.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import it.unimib.disco.sal.bestmovie.models.MoviesResponse;
import it.unimib.disco.sal.bestmovie.repositories.MoviesRepository;

public class ActivityMainViewModel extends ViewModel {

    private MoviesRepository moviesRepository;
    private LiveData<MoviesResponse> searchMovieByTitleResponse;

    public ActivityMainViewModel() {
        moviesRepository = new MoviesRepository();
    }

}
