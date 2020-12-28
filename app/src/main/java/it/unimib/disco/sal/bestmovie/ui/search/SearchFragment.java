package it.unimib.disco.sal.bestmovie.ui.search;

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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import it.unimib.disco.sal.bestmovie.adapters.HomeAdapter;
import it.unimib.disco.sal.bestmovie.adapters.SearchAdapter;
import it.unimib.disco.sal.bestmovie.databinding.FragmentSearchBinding;
import it.unimib.disco.sal.bestmovie.models.Movie;
import it.unimib.disco.sal.bestmovie.models.Resource;
import it.unimib.disco.sal.bestmovie.viewmodels.MovieViewModelSearch;

public class SearchFragment extends Fragment {

    private MovieViewModelSearch searchViewModel;
    private static final String TAG = "SearchFragment";
    private FragmentSearchBinding searchBinding;

    public SearchFragment() {
        // Required empty constructor
    }

    public static SearchFragment newInstance() {
        SearchFragment fragment = new SearchFragment();
        return fragment;
    }

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        searchBinding = FragmentSearchBinding.inflate(getLayoutInflater());
        return searchBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);

        searchViewModel = new ViewModelProvider(requireActivity()).get(MovieViewModelSearch.class);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        searchBinding.searchRecyclerView.setLayoutManager(layoutManager);

        final Observer<Resource<Movie>> observer = new Observer<Resource<Movie>>() {
            @Override
            public void onChanged(Resource<Movie> moviesResource) {

                /*if(moviesResource.getData() != null) {
                    for(int i=0; i<moviesResource.getData().size(); i++) {
                        Log.d(TAG, "Info: " + "total results " + moviesResource.getTotalResults() + ", " + "status code " + moviesResource.getStatusCode() + " " + moviesResource.getStatusMessage());
                        Log.d(TAG, "Titolo film: " + i + " " + moviesResource.getData().get(i).getTitle());
                    }
                } else {
                    Log.d(TAG, "Info errore: " + "total results " + moviesResource.getTotalResults() + ", " + "status code " + moviesResource.getStatusCode() + " " + moviesResource.getStatusMessage());
                }*/


                SearchAdapter searchAdapter = new SearchAdapter(getActivity(), moviesResource.getData(), new SearchAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(Movie movie) {
                        Log.d(TAG, "elemento premuto: " + movie.getTitle());

                        //Permette di spostarci nel MovieDetailsFragment selezionando un film nella Home
                        //SearchFragmentDirections.ActionNavigationSearchToMovieDetailsFragment action = SearchFragmentDirections.actionNavigationSearchToMovieDetailsFragment(movie);
                        //Navigation.findNavController(view).navigate(action);

                    }
                });
                searchBinding.searchRecyclerView.setAdapter(searchAdapter);

            }
        };

        LiveData<Resource<Movie>> liveData = searchViewModel.getMovieSearch(19);

        liveData.observe(getViewLifecycleOwner(), observer);



    }
}