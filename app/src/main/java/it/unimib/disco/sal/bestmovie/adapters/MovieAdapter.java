package it.unimib.disco.sal.bestmovie.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import it.unimib.disco.sal.bestmovie.R;
import it.unimib.disco.sal.bestmovie.databinding.FragmentHomeBinding;
import it.unimib.disco.sal.bestmovie.models.Movie;
/*
public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {


    private List<Movie> movieList;
    private LayoutInflater layoutInflater;


    public static class MovieViewHolder extends RecyclerView.ViewHolder {

        TextView textViewMovieTitle;

        public MovieViewHolder(View view) {
            super(view);
            textViewMovieTitle = view.findViewById(R.id.textViewMovieTitle);
        }
    }

    public MovieAdapter(Context context, List<Movie> movieList) {
        this.layoutInflater = LayoutInflater.from(context);
        this.movieList = movieList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = this.layoutInflater.inflate(R.layout.movie_item, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {

        holder.textViewMovieTitle.setText(movieList.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }
}

 */
