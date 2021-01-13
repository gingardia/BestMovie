package it.unimib.disco.sal.bestmovie.ui.search;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;

import it.unimib.disco.sal.bestmovie.R;
import it.unimib.disco.sal.bestmovie.adapters.MovieCardRecyclerViewAdapter;
import it.unimib.disco.sal.bestmovie.databinding.FragmentSearchFunctionBinding;
import it.unimib.disco.sal.bestmovie.models.Movie;
import it.unimib.disco.sal.bestmovie.utils.Constants;
import it.unimib.disco.sal.bestmovie.viewmodels.ActivityMainViewModel;
import it.unimib.disco.sal.bestmovie.viewmodels.MovieViewModel;

public class SearchFunctionFragment extends Fragment implements MovieCardRecyclerViewAdapter.OnMovieCardClicked {

    private final String TAG = "SearchByTitleFragment";
    private final SearchView searchView = null;
    private SearchView.OnQueryTextListener queryTextListener;
    private FragmentSearchFunctionBinding fragmentSearchByTitleBinding;
    private MovieViewModel activityMainViewModel;
    private MovieCardRecyclerViewAdapter movieCardRecyclerViewAdapter = null;
    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentSearchByTitleBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_search_function, container, false);
        fragmentSearchByTitleBinding.setLifecycleOwner(this);
        activityMainViewModel = new ViewModelProvider(this).get(MovieViewModel.class);
        return fragmentSearchByTitleBinding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.view = view;
        initRecyclerView();
        initSearchView();
    }

    private void initSearchView() {
        queryTextListener = new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextChange(String newText) {
                Log.d(TAG, newText);
                //Search will begin only after 3 characters to reduce N/W calls
                if (newText != null && !newText.isEmpty() && newText.length() > 3)
                    searchMovieByTitle(newText.trim());
                return true;
            }

            @Override
            public boolean onQueryTextSubmit(String query) {
                Log.d(TAG, "onQueryTextSubmit: " + query);
                return true;
            }
        };
        fragmentSearchByTitleBinding.searchView.setOnQueryTextListener(queryTextListener);
    }

    private void initRecyclerView() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(requireContext(), 2);
        fragmentSearchByTitleBinding.rvSearchByTitle.setHasFixedSize(true);
        fragmentSearchByTitleBinding.rvSearchByTitle.setLayoutManager(gridLayoutManager);
    }


    private void searchMovieByTitle(String query) {
        fragmentSearchByTitleBinding.pbSearchByTitle.setVisibility(View.VISIBLE);
        fragmentSearchByTitleBinding.tvSearchStatus.setText("Searching...");
        activityMainViewModel.searchMoviesByTitle(query).observe(getViewLifecycleOwner(), moviesResponse -> {
            if (moviesResponse == null) {
                fragmentSearchByTitleBinding.pbSearchByTitle.setVisibility(View.GONE);
                Toast.makeText(requireContext(), R.string.something_went_wrong, Toast.LENGTH_SHORT).show();
            } else {
                fragmentSearchByTitleBinding.pbSearchByTitle.setVisibility(View.GONE);
                if (moviesResponse.getResults() == null || moviesResponse.getResults().isEmpty()) {
                    fragmentSearchByTitleBinding.tvSearchStatus.setText(R.string.no_search_results_found);
                } else {
                    fragmentSearchByTitleBinding.tvSearchStatus.setText(R.string.search_results);
                    movieCardRecyclerViewAdapter = new MovieCardRecyclerViewAdapter(this, moviesResponse.getResults(), true);
                    fragmentSearchByTitleBinding.rvSearchByTitle.setAdapter(movieCardRecyclerViewAdapter);
                    movieCardRecyclerViewAdapter.notifyDataSetChanged();
                }
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
