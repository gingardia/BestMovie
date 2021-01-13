package it.unimib.disco.sal.bestmovie.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import it.unimib.disco.sal.bestmovie.ui.search.FiltersFunctionFragment;
import it.unimib.disco.sal.bestmovie.ui.search.SearchFunctionFragment;

public class ViewPagerFragmentAdapter extends FragmentStateAdapter {

    private  String titles[];

    public ViewPagerFragmentAdapter(FragmentActivity fragmentActivity, String[] tabTitles) {
        super(fragmentActivity);
        titles = tabTitles;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new SearchFunctionFragment();
            case 1:
                return new FiltersFunctionFragment();

        }
        return new SearchFunctionFragment();
    }

    @Override
    public int getItemCount() {
        return titles.length;
    }
}
