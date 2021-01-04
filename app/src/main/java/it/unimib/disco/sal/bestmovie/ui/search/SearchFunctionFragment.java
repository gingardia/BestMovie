package it.unimib.disco.sal.bestmovie.ui.search;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import it.unimib.disco.sal.bestmovie.R;
import it.unimib.disco.sal.bestmovie.databinding.FragmentSearchBinding;
import it.unimib.disco.sal.bestmovie.databinding.FragmentSearchFunctionBinding;

public class SearchFunctionFragment extends Fragment {

    private FragmentSearchFunctionBinding searchFunctionBinding;

    public SearchFunctionFragment() {
    // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        searchFunctionBinding = FragmentSearchFunctionBinding.inflate(getLayoutInflater());
        return searchFunctionBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

}
