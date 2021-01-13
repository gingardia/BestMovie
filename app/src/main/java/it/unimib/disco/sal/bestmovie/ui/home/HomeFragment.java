package it.unimib.disco.sal.bestmovie.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import it.unimib.disco.sal.bestmovie.adapters.HomeAdapter;
import it.unimib.disco.sal.bestmovie.databinding.FragmentHomeBinding;
import it.unimib.disco.sal.bestmovie.databinding.FragmentHomeNewBinding;
import it.unimib.disco.sal.bestmovie.models.Movie;
import it.unimib.disco.sal.bestmovie.models.Resource;
import it.unimib.disco.sal.bestmovie.viewmodels.MovieViewModel;

public class HomeFragment extends Fragment {

    private static final String TAG = "HomeFragment";

    private MovieViewModel movieViewModel;
    private FragmentHomeNewBinding binding;

    public HomeFragment() {
        // Required empty constructor
    }

    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeNewBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }



    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        movieViewModel = new ViewModelProvider(requireActivity()).get(MovieViewModel.class);

        //RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity(), 2);
        binding.homeUpcomingRecyclerView.setLayoutManager(layoutManager);

        final Observer<List<Movie>> observer = new Observer<List<Movie>>() {
            @Override
            public void onChanged(List<Movie> movies) {


                /*HomeAdapter homeAdapter = new HomeAdapter(getActivity(), movies, new HomeAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(Movie movie) {
                        Log.d(TAG, "elemento premuto: " + movie.getTitle());

                        // Permette di spostarci nel MovieDetailsFragment selezionando un film nella Home
                        HomeFragmentDirections.ShowMovieDetailsAction action = HomeFragmentDirections.showMovieDetailsAction(movie);
                        Navigation.findNavController(view).navigate(action);

                    }
                });
                binding.homeUpcomingRecyclerView.setAdapter(homeAdapter);*/

            }
        };
        LiveData<List<Movie>> liveData = movieViewModel.getUpcomingMovie();

        liveData.observe(getViewLifecycleOwner(), observer);
    }
}