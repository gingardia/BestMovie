package it.unimib.disco.sal.bestmovie.utils;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.databinding.BindingAdapter;

import com.squareup.picasso.Picasso;

import java.util.List;

import it.unimib.disco.sal.bestmovie.R;
import it.unimib.disco.sal.bestmovie.models.Genre;

public class BindingUtils {

    @BindingAdapter("posterurl")
    public static void loadImage(ImageView view, String imageUrl) {
        final String URL = Constants.IMAGE_API_URL + imageUrl;
        Picasso.get().load(URL).placeholder(R.drawable.ic_launcher_foreground).into(view);
    }

    @BindingAdapter("generes")
    public static void setGeneres(TextView textView, List<Genre> genres) {
        textView.setText(R.string.genre);
        if( genres != null && !genres.isEmpty()){
            for (int i = 0; i < genres.size(); i++) {
                if (i == 0) {
                    textView.append(genres.get(i).getName());
                } else {
                    textView.append(", " + genres.get(i).getName());
                }
            }
        }
    }


    @BindingAdapter("android:visibility")
    public static void setVisibility(View view, String value) {
        if(value == null || value.isEmpty())
            view.setVisibility(View.GONE);
        else
            view.setVisibility(View.VISIBLE);
    }

}
