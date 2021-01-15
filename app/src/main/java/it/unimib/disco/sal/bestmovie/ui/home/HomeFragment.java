package it.unimib.disco.sal.bestmovie.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

import it.unimib.disco.sal.bestmovie.R;
import it.unimib.disco.sal.bestmovie.adapters.HomeAdapter;
import it.unimib.disco.sal.bestmovie.adapters.MovieCardRecyclerViewAdapter;
import it.unimib.disco.sal.bestmovie.databinding.FragmentHomeBinding;
import it.unimib.disco.sal.bestmovie.databinding.FragmentHomeNewBinding;
import it.unimib.disco.sal.bestmovie.models.Movie;
import it.unimib.disco.sal.bestmovie.models.Resource;
import it.unimib.disco.sal.bestmovie.utils.Constants;
import it.unimib.disco.sal.bestmovie.viewmodels.MovieViewModel;

public class HomeFragment extends Fragment implements MovieCardRecyclerViewAdapter.OnMovieCardClicked {

    private final String TAG = "HomeFragment";
    private FragmentHomeBinding fragmentHomeBinding;
    private MovieViewModel activityMainViewModel;
    private View view;

    public HomeFragment() {
        super(R.layout.fragment_home);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragmentHomeBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);
        activityMainViewModel = new ViewModelProvider(this).get(MovieViewModel.class);
        fragmentHomeBinding.setLifecycleOwner(this);
        return fragmentHomeBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.view = view;
        fragmentHomeBinding.pbHomeFragment.setVisibility(View.VISIBLE);
        getUpcomingMovies();
    }

    private void setUpList(String title, List<Movie> moviesList) {
        View group = getActivity().getLayoutInflater().inflate(R.layout.layout_movies_recycler_view, fragmentHomeBinding.llHome, false);
        TextView tvGroupTitle = group.findViewById(R.id.tv_group_title);
        RecyclerView recyclerView = group.findViewById(R.id.rv_group);
        MovieCardRecyclerViewAdapter movieCardRecyclerViewAdapter = new MovieCardRecyclerViewAdapter(this, moviesList, false);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false);

        tvGroupTitle.setText(title);

        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(movieCardRecyclerViewAdapter);
        recyclerView.setLayoutManager(linearLayoutManager);

        fragmentHomeBinding.llHome.addView(group);
    }

    private void getNowPlayingMovies() {
        activityMainViewModel.getNowPlayingMovies().observe(getViewLifecycleOwner(), moviesResponse -> {
            if (moviesResponse != null) {
                List<Movie> movies = moviesResponse;
                if (movies.isEmpty()) {
                    Log.d(TAG, "getNowPlayingMovies: Response is empty");
                } else {
                    setUpList(getString(R.string.now_playing), movies);
                    fragmentHomeBinding.pbHomeFragment.setVisibility(View.GONE);
                }
            } else {
                Log.d(TAG, "getNowPlayingMovies: Field");
            }
        });
    }

    private void getTopRatedMovies() {
        activityMainViewModel.getTopRatedMovies().observe(getViewLifecycleOwner(), moviesResponse -> {
            if (moviesResponse != null) {
                List<Movie> movies = moviesResponse;
                if (movies.isEmpty()) {
                    Log.d(TAG, "getTopRatedMovies: Response is empty");
                } else {
                    setUpList(getString(R.string.top_rated), movies);
                    getNowPlayingMovies();

                }
            } else {
                Log.d(TAG, "getTopRatedMovies: Field");
            }
        });
    }

    private void getPopularMovies() {
        activityMainViewModel.getPopularMovies().observe(getViewLifecycleOwner(), moviesResponse -> {
            if (moviesResponse != null) {
                List<Movie> movies = moviesResponse;
                if (movies.isEmpty()) {
                    Log.d(TAG, "getPopularMovies: Response is empty");
                } else {
                    setUpList(getString(R.string.popular), movies);
                    getTopRatedMovies();
                }
            } else {
                Log.d(TAG, "getPopularMovies: Field");
            }
        });
    }

    private void getUpcomingMovies() {
        activityMainViewModel.getUpcomingMovies().observe(getViewLifecycleOwner(), moviesResponse -> {
            if (moviesResponse != null) {
                List<Movie> movies = moviesResponse;
                if (movies.isEmpty()) {
                    Log.d(TAG, "getUpcomingMovies: Response is empty");
                } else {
                    setUpList(getString(R.string.upcoming), movies);
                    getPopularMovies();
                }
            } else {
                Log.d(TAG, "getUpcomingMovies: Field");
            }
        });
    }

    @Override
    public void onMovieCardClicked(Movie movie) {
        Bundle bundle = new Bundle();
        bundle.putInt(Constants.BUNDLE_ID_MOVIE_ID, movie.getId());
        bundle.putBoolean(Constants.IS_FROM_HOME, true);
        Navigation.findNavController(view).navigate(R.id.action_homeFragment_to_movieDetailFragment, bundle);
    }
}