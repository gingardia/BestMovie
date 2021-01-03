package it.unimib.disco.sal.bestmovie.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import it.unimib.disco.sal.bestmovie.MovieDetailsFragment;
import it.unimib.disco.sal.bestmovie.R;
import it.unimib.disco.sal.bestmovie.models.Movie;
import it.unimib.disco.sal.bestmovie.utils.Constants;

public class HomeLargeAdapter extends RecyclerView.Adapter<HomeLargeAdapter.HomeViewHolder> {

    private Context context;
    private List<Movie> movieList;

    public HomeLargeAdapter(Context context, List<Movie> movieList) {
        this.context = context;
        this.movieList = movieList;
    }

    @NonNull
    @Override
    public HomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.movie_item_large, parent, false);
        return new HomeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeViewHolder holder, int position) {
        holder.bind(movieList.get(position));
        holder.movieTitleTextView.setText(movieList.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    public class HomeViewHolder extends RecyclerView.ViewHolder {

        public CardView movieCardView;
        public RelativeLayout imageLayout;
        public ImageView moviePosterImageView;
        public TextView movieTitleTextView;

        public HomeViewHolder(View view) {
            super(view);
            movieCardView = (CardView) itemView.findViewById(R.id.cardView_showLarge_card);
            imageLayout = (RelativeLayout) itemView.findViewById(R.id.image_layout_showLarge_card);
            moviePosterImageView = (ImageView) itemView.findViewById(R.id.imageView_showLarge_card);
            movieTitleTextView = (TextView) itemView.findViewById(R.id.textView_movieTitle_showLarge_card);

            imageLayout.getLayoutParams().width = (int) (context.getResources().getDisplayMetrics().widthPixels * 0.9);
            imageLayout.getLayoutParams().height = (int) ((context.getResources().getDisplayMetrics().widthPixels * 0.9) / 1.77);
        }

        public void bind(Movie movie) {

            String imageUrl = Constants.IMAGE_BASE_URL + "w500" + movie.getPosterPath();

            Picasso.get().load(imageUrl).placeholder(R.drawable.ic_launcher_foreground).into(moviePosterImageView);

        }
    }
}