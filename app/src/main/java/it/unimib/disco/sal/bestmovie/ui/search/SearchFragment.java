package it.unimib.disco.sal.bestmovie.ui.search;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import it.unimib.disco.sal.bestmovie.R;
import it.unimib.disco.sal.bestmovie.databinding.FragmentHomeBinding;
import it.unimib.disco.sal.bestmovie.ui.home.HomeFragment;
import it.unimib.disco.sal.bestmovie.ui.home.HomeViewModel;

public class SearchFragment extends Fragment {

    private SearchViewModel searchViewModel;

    private static final String TAG = "SearchFragment";
    private HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;

    public SearchFragment() {
        // Required empty constructor
    }

    public static SearchFragment newInstance() {
        SearchFragment fragment = new SearchFragment();
        return fragment;
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        searchViewModel = new ViewModelProvider(this).get(SearchViewModel.class);
        View root = inflater.inflate(R.layout.fragment_search, container, false);
        //final TextView textView = root.findViewById(R.id.text_search);
        searchViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                //textView.setText(s);
            }
        });
        return root;
    }
}