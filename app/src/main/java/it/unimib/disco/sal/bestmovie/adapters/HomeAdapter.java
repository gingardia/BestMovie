package it.unimib.disco.sal.bestmovie.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;
import it.unimib.disco.sal.bestmovie.R;
import it.unimib.disco.sal.bestmovie.models.Movie;
import it.unimib.disco.sal.bestmovie.utils.Constants;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.MovieViewHolder> {

    public interface OnItemClickListener {
        void onItemClick(Movie movie);
    }

    private List<Movie> movieList;
    private LayoutInflater layoutInflater;
    private OnItemClickListener onItemClickListener;


    public static class MovieViewHolder extends RecyclerView.ViewHolder {

        TextView textViewMovieTitle;
        ImageView imageViewMovieItem;

        public MovieViewHolder(View view) {
            super(view);
            textViewMovieTitle = view.findViewById(R.id.textViewMovieTitle);
            imageViewMovieItem = view.findViewById(R.id.imageViewMovieItem);
        }

        public void bind(Movie movie, OnItemClickListener onItemClickListener) {

            String imageUrl = Constants.IMAGE_BASE_URL + "w500" + movie.getPosterPath();

            Picasso.get().load(imageUrl).into(imageViewMovieItem);

            textViewMovieTitle.setText(movie.getTitle());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClickListener.onItemClick(movie);
                }
            });
        }
    }

    public HomeAdapter(Context context, List<Movie> movieList, OnItemClickListener onItemClickListener) {
        this.layoutInflater = LayoutInflater.from(context);
        this.movieList = movieList;
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = this.layoutInflater.inflate(R.layout.movie_item, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        holder.bind(movieList.get(position), this.onItemClickListener);
        //holder.textViewMovieTitle.setText(movieList.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        if(movieList != null) {
            return movieList.size();
        }
        return 0;
    }
}


