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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import it.unimib.disco.sal.bestmovie.adapters.HomeAdapter;
import it.unimib.disco.sal.bestmovie.databinding.FragmentHomeBinding;
import it.unimib.disco.sal.bestmovie.models.Movie;
import it.unimib.disco.sal.bestmovie.models.Resource;
import it.unimib.disco.sal.bestmovie.viewmodels.MovieViewModel;

public class HomeFragment extends Fragment {

    private static final String TAG = "HomeFragment";

    private MovieViewModel movieViewModel;
    private FragmentHomeBinding binding;

    public HomeFragment() {
        // Required empty constructor
    }

    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    /*
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

     */

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        movieViewModel = new ViewModelProvider(requireActivity()).get(MovieViewModel.class);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        binding.moviesRecyclerView.setLayoutManager(layoutManager);

        final Observer<Resource<List<Movie>>> observer = new Observer<Resource<List<Movie>>>() {
            @Override
            public void onChanged(Resource<List<Movie>> moviesResource) {

                if(moviesResource.getData() != null) {
                    for(int i=0; i<moviesResource.getData().size(); i++) {
                        Log.d(TAG, "Info: " + "total results " + moviesResource.getTotalResults() + ", " + "status code " + moviesResource.getStatusCode() + " " + moviesResource.getStatusMessage());
                        Log.d(TAG, "Titolo film: " + i + " " + moviesResource.getData().get(i).getTitle());
                    }
                } else {
                    Log.d(TAG, "Info errore: " + "total results " + moviesResource.getTotalResults() + ", " + "status code " + moviesResource.getStatusCode() + " " + moviesResource.getStatusMessage());
                }


                HomeAdapter homeAdapter = new HomeAdapter(getActivity(), moviesResource.getData(), new HomeAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(Movie movie) {
                        Log.d(TAG, "elemento premuto: " + movie.getTitle());

                        // Permette di spostarci nel MovieDetailsFragment selezionando un film nella Home
                        HomeFragmentDirections.ShowMovieDetailsAction action = HomeFragmentDirections.showMovieDetailsAction(movie);
                        Navigation.findNavController(view).navigate(action);

                    }
                });
                binding.moviesRecyclerView.setAdapter(homeAdapter);

            }
        };

        LiveData<Resource<List<Movie>>> liveData = movieViewModel.getMoviesResource(1);

        liveData.observe(getViewLifecycleOwner(), observer);



    }


}