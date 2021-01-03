package it.unimib.disco.sal.bestmovie.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import it.unimib.disco.sal.bestmovie.models.Movie;
import it.unimib.disco.sal.bestmovie.models.Resource;
import it.unimib.disco.sal.bestmovie.repositories.MoviesRepository;

public class MovieViewModel extends ViewModel {

    private MutableLiveData<Resource<List<Movie>>> popularMovies, topRatedMovies, upcomingMovies, nowPlayingMovies;

    public LiveData<Resource<List<Movie>>> getPopularMoviesResource(int page) {
        if (popularMovies == null) {
            popularMovies = new MutableLiveData<>();
            MoviesRepository.getInstance().getPopularMoviesDetails(popularMovies, page);
        }
        return popularMovies;
    }

    public LiveData<Resource<List<Movie>>> getTopRatedMoviesResource(int page) {
        if (topRatedMovies == null) {
            topRatedMovies = new MutableLiveData<>();
            MoviesRepository.getInstance().getPopularMoviesDetails(topRatedMovies, page);
        }
        return topRatedMovies;
    }

    public LiveData<Resource<List<Movie>>> getUpcomingMoviesResource(int page) {
        if (upcomingMovies == null) {
            upcomingMovies = new MutableLiveData<>();
            MoviesRepository.getInstance().getUpcomingMoviesDetails(upcomingMovies, page);
        }
        return upcomingMovies;
    }

    public LiveData<Resource<List<Movie>>> getNowPlayingMoviesResource(int page) {
        if (nowPlayingMovies == null) {
            nowPlayingMovies = new MutableLiveData<>();
            MoviesRepository.getInstance().getNowPlayingMoviesDetails(nowPlayingMovies, page);
        }
        return nowPlayingMovies;
    }

}
