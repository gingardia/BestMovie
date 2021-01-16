package it.unimib.disco.sal.bestmovie.ui.search;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import com.google.android.material.tabs.TabLayoutMediator;
import it.unimib.disco.sal.bestmovie.R;
import it.unimib.disco.sal.bestmovie.adapters.ViewPagerFragmentAdapter;
import it.unimib.disco.sal.bestmovie.databinding.FragmentSearchBinding;
import it.unimib.disco.sal.bestmovie.viewmodels.MovieViewModel;

public class SearchFragment extends Fragment {

    private MovieViewModel activityMainViewModel;
    private static final String TAG = "SearchFragment";
    private FragmentSearchBinding fragmentSearchBinding;
    private final String[] titles = {"Search", "Filters"};

    public SearchFragment() {
        super(R.layout.fragment_search);
    }

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        fragmentSearchBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_search, container, false);
        fragmentSearchBinding.setLifecycleOwner(this);
        activityMainViewModel = new ViewModelProvider(this).get(MovieViewModel.class);
        return fragmentSearchBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);

        fragmentSearchBinding.viewPagerTabLayout.setAdapter(new ViewPagerFragmentAdapter(requireActivity(), titles));
        new TabLayoutMediator(fragmentSearchBinding.tabLayout, fragmentSearchBinding.viewPagerTabLayout,  (tab, position) -> tab.setText(titles[position])).attach();
    }

}