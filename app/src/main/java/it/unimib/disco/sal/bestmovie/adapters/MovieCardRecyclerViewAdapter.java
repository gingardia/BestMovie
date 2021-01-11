package it.unimib.disco.sal.bestmovie.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import java.util.List;

import it.unimib.disco.sal.bestmovie.R;
import it.unimib.disco.sal.bestmovie.models.Movie;
import it.unimib.disco.sal.bestmovie.utils.BindingUtils;

public class MovieCardRecyclerViewAdapter extends RecyclerView.Adapter<MovieCardRecyclerViewAdapter.MovieCardViewHolder> {

    public interface OnItemClickListener {
        void onItemClick(Movie movie);
    }

    private final List<Movie> movieList;
    private  final String TAG = "66";
    private Boolean isShowingInSearch;
    private LayoutInflater layoutInflater;
    private OnItemClickListener onItemClickListener;

    public MovieCardRecyclerViewAdapter(Context context, List<Movie> movieList, Boolean isShowingInSearch, OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
        this.movieList = movieList;
        this.layoutInflater = LayoutInflater.from(context);
        this.isShowingInSearch = isShowingInSearch;
    }

    @NonNull
    @Override
    public MovieCardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = this.layoutInflater.inflate(R.layout.movie_card, parent, false);
        return new MovieCardRecyclerViewAdapter.MovieCardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieCardViewHolder holder, int position) {
        holder.bind(movieList.get(position), this.onItemClickListener);
        holder.textViewMovieTitle.setText(movieList.get(position).getTitle());

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
        TextView textViewMovieTitle;
        ImageView imageViewMovieItem;

        public MovieCardViewHolder(View view) {
            super(view);
            textViewMovieTitle = view.findViewById(R.id.textView2);
            imageViewMovieItem = view.findViewById(R.id.imageViewMoviePoster);
        }

        public void bind(Movie movie, MovieCardRecyclerViewAdapter.OnItemClickListener onItemClickListener) {

            BindingUtils.loadImage(imageViewMovieItem, movie.getPosterPath());

            textViewMovieTitle.setText(movie.getTitle());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClickListener.onItemClick(movie);
                }
            });
        }
    }


}

