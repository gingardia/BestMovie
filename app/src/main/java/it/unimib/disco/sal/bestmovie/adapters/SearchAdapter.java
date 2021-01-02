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
import it.unimib.disco.sal.bestmovie.models.Movie;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.SearchViewHolder> {

    public interface OnItemClickListener {
        void onItemClick(Movie movie);
    }

    private List<Movie> searchList;
    private LayoutInflater layoutInflater;
    private OnItemClickListener onItemClickListener;


    public static class SearchViewHolder extends RecyclerView.ViewHolder {

        TextView textViewMovieTitle;

        public SearchViewHolder(View view) {
            super(view);
            textViewMovieTitle = view.findViewById(R.id.textViewMovieTitle);
        }

        public void bind(Movie movie, OnItemClickListener onItemClickListener) {
            textViewMovieTitle.setText(movie.getTitle());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClickListener.onItemClick(movie);
                }
            });
        }
    }

    public SearchAdapter(Context context, List<Movie> searchList, OnItemClickListener onItemClickListener) {
        this.layoutInflater = LayoutInflater.from(context);
        this.searchList = searchList;
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public SearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = this.layoutInflater.inflate(R.layout.movie_item, parent, false);
        return new SearchViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchViewHolder holder, int position) {
        holder.textViewMovieTitle.setText(searchList.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        if(searchList != null) {
            return searchList.size();
        }
        return 0;
    }
}


