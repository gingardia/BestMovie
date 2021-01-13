package it.unimib.disco.sal.bestmovie.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import it.unimib.disco.sal.bestmovie.models.AllGenreResponse;
import it.unimib.disco.sal.bestmovie.models.Movie;
import it.unimib.disco.sal.bestmovie.models.MovieDescription;
import it.unimib.disco.sal.bestmovie.models.MoviesResponse;
import it.unimib.disco.sal.bestmovie.models.Resource;
import it.unimib.disco.sal.bestmovie.repositories.MoviesRepository;

public class MovieViewModel extends ViewModel {

    private MutableLiveData<List<Movie>> popularMovies, topRatedMovies, upcomingMovies, nowPlayingMovies;
    private MutableLiveData<MovieDescription> movieDescriptionResponse;
    private MutableLiveData<MoviesResponse> searchMovieByTitleResponse;
    private MutableLiveData<AllGenreResponse> getAllMovieGenresResponse;
    private LiveData<MoviesResponse> getMoviesSortedByResponse;

    public LiveData<List<Movie>> getPopularMovie() {
        if (popularMovies == null) {
            popularMovies = new MutableLiveData<>();
            MoviesRepository.getInstance().getPopularMovies(popularMovies);
        }
        return popularMovies;
    }

    public LiveData<List<Movie>> getTopRatedMovie() {
        if (topRatedMovies == null) {
            topRatedMovies = new MutableLiveData<>();
            MoviesRepository.getInstance().getTopRatedMovies(topRatedMovies);
        }
        return topRatedMovies;
    }

    public LiveData<List<Movie>> getUpcomingMovie() {
        if (upcomingMovies == null) {
            upcomingMovies = new MutableLiveData<>();
            MoviesRepository.getInstance().getUpcomingMovies(upcomingMovies);
        }
        return upcomingMovies;
    }

    public LiveData<List<Movie>> getNowPlayingMovie() {
        if (nowPlayingMovies == null) {
            nowPlayingMovies = new MutableLiveData<>();
            MoviesRepository.getInstance().getNowPlayingMovies(nowPlayingMovies);
        }
        return nowPlayingMovies;
    }

    // VERIFICARE CHE SIA CORRETTO
    public LiveData<MovieDescription> getMovieDescription(int movieID) {
        if (movieDescriptionResponse == null) {
            movieDescriptionResponse = new MutableLiveData<>();
            MoviesRepository.getInstance().getMovieDescription(movieID);
        }
        return movieDescriptionResponse;
    }

    // VERIFICARE CHE SIA CORRETTO
    public LiveData<MoviesResponse> searchMoviesByTitle(String searchTerm){
        if(searchTerm != null && !searchTerm.isEmpty()){
            MoviesRepository.getInstance().searchMovieByTitle(searchTerm);
            searchMovieByTitleResponse = MoviesRepository.getInstance().getSearchMovieByTitleResponseLiveData();
            return searchMovieByTitleResponse;
        }else{
            return null;
        }
    }

    // VERIFICARE CHE SIA CORRETTO
    public LiveData<AllGenreResponse> getAllMovieGenres(){
        MoviesRepository.getInstance().getAllMovieGenres();
        getAllMovieGenresResponse = MoviesRepository.getInstance().getAllMovieGenresLiveData();
        return getAllMovieGenresResponse;
    }

    // VERIFICARE CHE SIA CORRETTO
    public LiveData<MoviesResponse> filterMoviesBy(String filter, String genres){
        MoviesRepository.getInstance().getMoviesSortedBy(filter, genres);
        getMoviesSortedByResponse = MoviesRepository.getInstance().getMoviesSortedByResponseLiveData();
        return getMoviesSortedByResponse;
    }

}
