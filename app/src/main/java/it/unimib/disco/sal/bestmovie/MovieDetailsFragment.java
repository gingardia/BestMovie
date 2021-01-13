package it.unimib.disco.sal.bestmovie;

import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;

import java.util.List;

import it.unimib.disco.sal.bestmovie.databinding.FragmentHomeBinding;
import it.unimib.disco.sal.bestmovie.databinding.FragmentMovieDetailBinding;
import it.unimib.disco.sal.bestmovie.models.Movie;
import it.unimib.disco.sal.bestmovie.models.Result;
import it.unimib.disco.sal.bestmovie.utils.Constants;
import it.unimib.disco.sal.bestmovie.viewmodels.MovieViewModel;


public class MovieDetailsFragment extends Fragment {
    private final String TAG = "MovieDetailFragment";
    private FragmentMovieDetailBinding fragmentMovieDetailBinding;
    private MovieViewModel activityMainViewModel;
    public MovieDetailsFragment() {
        super(R.layout.fragment_movie_detail);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragmentMovieDetailBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_movie_detail, container, false);
        activityMainViewModel = new ViewModelProvider(this).get(MovieViewModel.class);
        fragmentMovieDetailBinding.setLifecycleOwner(this);
        return fragmentMovieDetailBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled by default */) {
            @Override
            public void handleOnBackPressed() {
                if(getArguments().getBoolean(Constants.IS_FROM_HOME))
                    Navigation.findNavController(view).navigate(R.id.action_movieDetailFragment_to_homeFragment);
                else
                    Navigation.findNavController(view).navigate(R.id.action_movieDetailFragment_to_searchFragment);
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(requireActivity(), callback);

        fragmentMovieDetailBinding.clMovieDetail.setVisibility(View.GONE);
        fragmentMovieDetailBinding.pbMovieDetailFragment.setVisibility(View.VISIBLE);

        int movieID = getArguments().getInt(Constants.BUNDLE_ID_MOVIE_ID);
        Log.d(TAG, "onViewCreated: " + movieID);

        if (movieID == 0) {
            Toast.makeText(requireContext(), R.string.something_went_wrong, Toast.LENGTH_SHORT).show();
            requireActivity().finish();
        } else {
            activityMainViewModel.getMovieDescription(movieID).observe(getViewLifecycleOwner(), movieDescriptionResponse -> {
                if (movieDescriptionResponse == null) {
                    Log.d(TAG, "onViewCreated: null");
                    Toast.makeText(requireContext(), R.string.something_went_wrong, Toast.LENGTH_SHORT).show();
                    requireActivity().finish();
                } else {
                    fragmentMovieDetailBinding.setMovieDetail(movieDescriptionResponse);
                    fragmentMovieDetailBinding.pbMovieDetailFragment.setVisibility(View.GONE);
                    fragmentMovieDetailBinding.clMovieDetail.setVisibility(View.VISIBLE);

                    //Make Movie Long Description TextView Scrollable
                    fragmentMovieDetailBinding.tvMovieDescription.setMovementMethod(new ScrollingMovementMethod());

                    //Set video URL to YouTube player
                    fragmentMovieDetailBinding.youTubePlayerView2.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
                        @Override
                        public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                            List<Result> videoResults = movieDescriptionResponse.getVideos().getResults();
                            for(Result result : videoResults){
                                if(result.getSite().equalsIgnoreCase("YouTube")){
                                    if(!result.getId().isEmpty()) {
                                        youTubePlayer.loadVideo(result.getKey(), 0);
                                        youTubePlayer.pause();
                                        break;
                                    }
                                }
                            }
                        }
                    });
                }
            });
        }
    }
}