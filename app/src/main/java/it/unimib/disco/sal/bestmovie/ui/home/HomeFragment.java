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

import java.util.List;

import it.unimib.disco.sal.bestmovie.R;
import it.unimib.disco.sal.bestmovie.models.Movie;
import it.unimib.disco.sal.bestmovie.viewmodels.MovieViewModel;

public class HomeFragment extends Fragment {

    private static final String TAG = "HomeFragment";

    private MovieViewModel movieViewModel;

    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        final TextView textView = root.findViewById(R.id.text_home);
        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);

        movieViewModel = new ViewModelProvider(requireActivity()).get(MovieViewModel.class);

        final Observer<List<Movie>> observer = new Observer<List<Movie>>() {
            @Override
            public void onChanged(List<Movie> movies) {

                for(int i=0; i < movies.size(); i++) {
                    Log.d(TAG, "Movie" + i + movies.get(i).getTitle());
                }

            }
        };

        String language = "en"; //prova lingua inglese
        LiveData<List<Movie>> liveData = movieViewModel.getMovies(language);

        liveData.observe(getViewLifecycleOwner(), observer);
    }


}