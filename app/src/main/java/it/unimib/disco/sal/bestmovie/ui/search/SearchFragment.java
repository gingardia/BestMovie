package it.unimib.disco.sal.bestmovie.ui.search;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TabHost;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import it.unimib.disco.sal.bestmovie.R;
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
        TabLayout tabLayout;
        ViewPager viewPager;

        tabLayout = getActivity().findViewById(R.id.tab_layout);
        viewPager = getActivity().findViewById(R.id.view_pager);

        ArrayList<String> arrayList = new ArrayList<>();

        arrayList.add("Cerca");
        arrayList.add("Filtri");

        prepareViewPager(viewPager,arrayList);

        tabLayout.setupWithViewPager(viewPager);

        /*TabHost tabHost = getActivity().findViewById(R.id.tabHost);
        tabHost.setup();

        //Tab 1 --> Cerca
        TabHost.TabSpec spec = tabHost.newTabSpec("Tab Cerca");
        spec.setContent(R.id.Cerca);
        spec.setIndicator("Tab cerca");
        tabHost.addTab(spec);

        //Tab 2 --> Filtri
        spec.setContent(R.id.Filtri);
        spec.setIndicator("Tab filtri");
        tabHost.addTab(spec);*/

        //RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        //RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity(), 2);
        //searchBinding.tabHost.setLayoutManager(layoutManager);

        final Observer<Resource<List<Movie>>> observer = new Observer<Resource<List<Movie>>>() {
            @Override
            public void onChanged(Resource<List<Movie>> moviesResource) {
                SearchAdapter searchAdapter = new SearchAdapter(getActivity(), moviesResource.getData(), new SearchAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(Movie movie) {
                        Log.d(TAG, "elemento premuto: " + movie.getTitle());

                        //Permette di spostarci nel MovieDetailsFragment selezionando un film nella Home
                        //SearchFragmentDirections.ActionNavigationSearchToMovieDetailsFragment action = SearchFragmentDirections.actionNavigationSearchToMovieDetailsFragment(movie);
                        //Navigation.findNavController(view).navigate(action);

                    }
                });
                //searchBinding.searchRecyclerView.setAdapter(searchAdapter);

            }
        };

        LiveData<Resource<List<Movie>>> liveData = searchViewModel.getMovieSearch(19, "Harry");

        liveData.observe(getViewLifecycleOwner(), observer);



    }

    private void prepareViewPager(ViewPager viewPager, ArrayList<String> arrayList) {
        MainAdapter adapter = new MainAdapter(getFragmentManager());

        SearchFragment searchFragment = new SearchFragment();

        for (int i=0;i<arrayList.size();i++) {
            Bundle bundle = new Bundle();
            bundle.putString("title",arrayList.get(i));
            searchFragment.setArguments(bundle);
            adapter.addFragment(searchFragment,arrayList.get(i));
            searchFragment = new SearchFragment();
        }
        viewPager.setAdapter(adapter);
    }

    private class MainAdapter extends FragmentPagerAdapter {
        ArrayList<String> arrayList = new ArrayList<>();
        List<Fragment> fragmentList = new ArrayList<>();

        public void addFragment(Fragment fragment, String title) {
            arrayList.add(title);
            fragmentList.add(fragment);
        }

        public MainAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return arrayList.get(position);
        }
    }
}