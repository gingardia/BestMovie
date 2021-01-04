package it.unimib.disco.sal.bestmovie.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import it.unimib.disco.sal.bestmovie.R;
import it.unimib.disco.sal.bestmovie.adapters.HomePagerAdapter;
import it.unimib.disco.sal.bestmovie.databinding.FragmentHomeBinding;
import it.unimib.disco.sal.bestmovie.ui.search.FiltersFunctionFragment;
import it.unimib.disco.sal.bestmovie.viewmodels.MovieViewModel;

public class HomeFragment extends Fragment {

    private static final String TAG = "HomeFragment";
    TabLayout tabLayout;
    ViewPager viewPager;


    public HomeFragment() {
        // Required empty constructor
    }

    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        addFragment(view);
        return view;
    }

    private void addFragment(View view) {
        tabLayout = view.findViewById(R.id.home_tab_layout);
        viewPager = view.findViewById(R.id.home_view_pager);
        HomePagerAdapter adapter = new HomePagerAdapter(getChildFragmentManager());
        adapter.addFragment(new HomeUpcomingFragment(), "Upcoming");
        adapter.addFragment(new HomePopularFragment(), "Popular");
        adapter.addFragment(new HomeTopRatedFragment(), "Top Rated");
        adapter.addFragment(new HomeNowPlayingFragment(), "Now Playing");
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }

}