package it.unimib.disco.sal.bestmovie.ui.search;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.material.chip.Chip;

import java.util.List;

import it.unimib.disco.sal.bestmovie.R;
import it.unimib.disco.sal.bestmovie.adapters.MovieCardRecyclerViewAdapter;
import it.unimib.disco.sal.bestmovie.databinding.FragmentFiltersFunctionBinding;
import it.unimib.disco.sal.bestmovie.models.Genre;
import it.unimib.disco.sal.bestmovie.models.Movie;
import it.unimib.disco.sal.bestmovie.utils.Constants;
import it.unimib.disco.sal.bestmovie.viewmodels.MovieViewModel;

public class FiltersFunctionFragment extends Fragment implements MovieCardRecyclerViewAdapter.OnMovieCardClicked {

    private FragmentFiltersFunctionBinding fragmentFiltersFunctionBinding;
    private MovieViewModel activityMainViewModel;
    private final String TAG = "FiltersFunctionFragment";
    private View view;

    public FiltersFunctionFragment() {
    // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentFiltersFunctionBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_filters_function, container, false);
        fragmentFiltersFunctionBinding.setLifecycleOwner(this);
        activityMainViewModel = new ViewModelProvider(this).get(MovieViewModel.class);
        return fragmentFiltersFunctionBinding.getRoot();
    }


    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getGenres();

        this.view = view;

        fragmentFiltersFunctionBinding.btnApplyFilters.setOnClickListener(view1 -> {
            List<Integer> selectedGenres = fragmentFiltersFunctionBinding.cgGenres.getCheckedChipIds();
            String filter = "";
            Integer otherFilter = fragmentFiltersFunctionBinding.cgOtherFilters.getCheckedChipId();
            Log.d(TAG, "onViewCreated: id" + otherFilter);
            if(otherFilter != -1){      //-1 rappresenta nessun chip selezionato
                Chip chip = fragmentFiltersFunctionBinding.cgOtherFilters.findViewById(otherFilter);
                String selectedChipName = chip.getText().toString();
                if(selectedChipName.equals(getString(R.string.high_popularity))){
                    filter = Constants.QUERY_PARAM_POPULARITY_SORT_BY_DESCENDING;
                }
                if(selectedChipName.equals(getString(R.string.low_popularity))){
                    filter = Constants.QUERY_PARAM_POPULARITY_SORT_BY_ASCENDING;
                }
                if(selectedChipName.equals(getString(R.string.high_votes))){
                    filter = Constants.QUERY_PARAM_VOTE_COUNT_BY_DESCENDING;
                }
                if(selectedChipName.equals(getString(R.string.low_votes))){
                    filter = Constants.QUERY_PARAM_VOTE_COUNT_BY_ASCENDING;
                }
            }

            StringBuilder genreIDs = new StringBuilder("");
            if(!selectedGenres.isEmpty()){
                int i = 0;
                for(Integer id: selectedGenres){
                    if(i == 0){
                        genreIDs.append(id.toString());
                    }else{
                        //Concatenazione filtri selezionati
                        genreIDs.append("," + id.toString());
                    }
                    i++;
                }
            }
            makeRequest(genreIDs.toString(), filter);
        });
    }

    private void makeRequest(String selectedGenres, String filter){
        fragmentFiltersFunctionBinding.pbSearchMoviesWithFilters.setVisibility(View.VISIBLE);
        activityMainViewModel.filterMoviesBy(filter, selectedGenres).observe(getViewLifecycleOwner(), moviesResponse -> {
            if(moviesResponse == null || moviesResponse.getResults() == null){
                fragmentFiltersFunctionBinding.pbSearchMoviesWithFilters.setVisibility(View.GONE);
                Toast.makeText(requireContext(), R.string.something_went_wrong, Toast.LENGTH_SHORT).show();
            }else{
                fragmentFiltersFunctionBinding.pbSearchMoviesWithFilters.setVisibility(View.GONE);
                List<Movie> movies = moviesResponse.getResults();
                if(movies.isEmpty()){
                    Toast.makeText(requireContext(), R.string.no_search_results_found, Toast.LENGTH_SHORT).show();
                }else{
                    Context context;
                    GridLayoutManager gridLayoutManager = new GridLayoutManager(requireContext(), 2);
                    MovieCardRecyclerViewAdapter movieCardRecyclerViewAdapter = new MovieCardRecyclerViewAdapter(this, movies, true);
                    fragmentFiltersFunctionBinding.rvMovieAfterFilters.setHasFixedSize(true);
                    fragmentFiltersFunctionBinding.rvMovieAfterFilters.setLayoutManager(gridLayoutManager);
                    fragmentFiltersFunctionBinding.rvMovieAfterFilters.setAdapter(movieCardRecyclerViewAdapter);
                }
            }
        });
    }

    private void getGenres() {
        fragmentFiltersFunctionBinding.pbSearchMoviesWithFilters.setVisibility(View.VISIBLE);
        activityMainViewModel.getAllMovieGenres().observe(getViewLifecycleOwner(), allGenreResponse -> {
            if(allGenreResponse == null){
                fragmentFiltersFunctionBinding.pbSearchMoviesWithFilters.setVisibility(View.GONE);
                Toast.makeText(requireContext(), R.string.something_went_wrong, Toast.LENGTH_SHORT).show();
            }else{
                fragmentFiltersFunctionBinding.pbSearchMoviesWithFilters.setVisibility(View.GONE);
                //Log.d(TAG, "getGenres: Qui");
                for(Genre genre: allGenreResponse.genres){
                    Chip chip = (Chip)requireActivity().getLayoutInflater().inflate(R.layout.filter_chip, fragmentFiltersFunctionBinding.cgGenres, false);
                    chip.setText(genre.getName());
                    chip.setId(genre.getId());
                    fragmentFiltersFunctionBinding.cgGenres.addView(chip);
                }
                fragmentFiltersFunctionBinding.cgGenres.setSelectionRequired(true);
            }
        });
    }

    @Override
    public void onMovieCardClicked(Movie movie) {
        Bundle bundle = new Bundle();
        bundle.putInt(Constants.BUNDLE_ID_MOVIE_ID, movie.getId());
        bundle.putBoolean(Constants.IS_FROM_HOME, false);
        Navigation.findNavController(view).navigate(R.id.action_searchFragment_to_movieDetailFragment, bundle);
    }

}
