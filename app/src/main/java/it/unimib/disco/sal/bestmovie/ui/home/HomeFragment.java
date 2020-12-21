package it.unimib.disco.sal.bestmovie.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import it.unimib.disco.sal.bestmovie.R;
//import it.unimib.disco.sal.bestmovie.adapters.MovieAdapter;
import it.unimib.disco.sal.bestmovie.databinding.FragmentHomeBinding;
import it.unimib.disco.sal.bestmovie.models.Movie;
import it.unimib.disco.sal.bestmovie.repositories.MoviesRepository;
import it.unimib.disco.sal.bestmovie.viewmodels.MovieViewModel;

public class HomeFragment extends Fragment {

    private static final String TAG = "HomeFragment";

    private MovieViewModel movieViewModel;
    private HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;

    public HomeFragment() {
        // Required empty constructor
    }

    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        movieViewModel = new ViewModelProvider(requireActivity()).get(MovieViewModel.class);

        //RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        //binding.moviesRecyclerView.setLayoutManager(layoutManager);

        final Observer<List<Movie>> observer = new Observer<List<Movie>>() {
            @Override
            public void onChanged(List<Movie> results) {


                for(int i=0; i<results.size(); i++) {
                    Log.d(TAG, "Titolo film: " + i + " " + results.get(i).getTitle());
                }

                //MovieAdapter movieAdapter = new MovieAdapter(getActivity(), results);
                //binding.moviesRecyclerView.setAdapter(movieAdapter);

            }
        };

        //movieViewModel.getMovies(1).observe(this, observer);

        LiveData<List<Movie>> liveData = movieViewModel.getMovies(1);

        liveData.observe(getViewLifecycleOwner(), observer);



    }


}