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

import it.unimib.disco.sal.bestmovie.R;
import it.unimib.disco.sal.bestmovie.adapters.HomeAdapter;
import it.unimib.disco.sal.bestmovie.databinding.FragmentHomeBinding;
import it.unimib.disco.sal.bestmovie.databinding.FragmentHomeShowMoviesBinding;
import it.unimib.disco.sal.bestmovie.models.Movie;
import it.unimib.disco.sal.bestmovie.models.Resource;
import it.unimib.disco.sal.bestmovie.viewmodels.MovieViewModel;

public class HomeUpcomingFragment extends Fragment {
    /*
    private static final String TAG = "HomeUpcomingFragment";
    private MovieViewModel movieViewModel;
    private FragmentHomeShowMoviesBinding binding;

    public HomeUpcomingFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home_show_movies, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        movieViewModel = new ViewModelProvider(requireActivity()).get(MovieViewModel.class);

        //RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity(), 2);
        binding.showMoviesHomeRecyclerView.setLayoutManager(layoutManager);


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
                binding.showMoviesHomeRecyclerView.setAdapter(homeAdapter);

            }
        };

        LiveData<Resource<List<Movie>>> liveData = movieViewModel.getUpcomingMoviesResource(1);

        liveData.observe(getViewLifecycleOwner(), observer);






    }

     */
}
