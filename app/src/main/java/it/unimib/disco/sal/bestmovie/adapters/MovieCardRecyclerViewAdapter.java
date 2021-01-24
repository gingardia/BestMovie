package it.unimib.disco.sal.bestmovie.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import it.unimib.disco.sal.bestmovie.databinding.MovieCardBinding;
import it.unimib.disco.sal.bestmovie.models.Movie;

public class MovieCardRecyclerViewAdapter extends RecyclerView.Adapter<MovieCardRecyclerViewAdapter.MovieCardViewHolder> {
    private final OnMovieCardClicked onMovieCardClicked;
    private final List<Movie> movieList;
    private Boolean isShowingInSearch;

    public MovieCardRecyclerViewAdapter(OnMovieCardClicked onMovieCardClicked, List<Movie> movieList, Boolean isShowingInSearch) {
        this.onMovieCardClicked = onMovieCardClicked;
        this.movieList = movieList;
        this.isShowingInSearch = isShowingInSearch;
    }

    @NonNull
    @Override
    public MovieCardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        MovieCardBinding movieCardBinding = MovieCardBinding.inflate(layoutInflater, parent, false);
        return new MovieCardViewHolder(movieCardBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieCardViewHolder holder, int position) {
        Movie movie = movieList.get(position);
        holder.movieCardBinding.setMovie(movie);
        holder.movieCardBinding.setOnMovieClicked(onMovieCardClicked);

        //Set Card Size according to where it is being shown
        if(isShowingInSearch){
            ConstraintLayout constraintLayout = (ConstraintLayout) holder.itemView;
            GridLayoutManager.LayoutParams newLayoutParams = (GridLayoutManager.LayoutParams) holder.itemView.getLayoutParams();
            newLayoutParams.width = RecyclerView.LayoutParams.MATCH_PARENT;
            newLayoutParams.topMargin = 0;
            newLayoutParams.leftMargin = 4;
            newLayoutParams.rightMargin = 0;
            newLayoutParams.bottomMargin = 4;
            constraintLayout.setLayoutParams(newLayoutParams);
        }
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    public interface OnMovieCardClicked {
        void onMovieCardClicked(Movie movie);
    }

    static class MovieCardViewHolder extends RecyclerView.ViewHolder {
        MovieCardBinding movieCardBinding;

        public MovieCardViewHolder(@NonNull MovieCardBinding movieCardBinding) {
            super(movieCardBinding.getRoot());
            this.movieCardBinding = movieCardBinding;
        }
    }
}
