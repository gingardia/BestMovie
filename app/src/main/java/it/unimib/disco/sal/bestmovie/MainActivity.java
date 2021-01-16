package it.unimib.disco.sal.bestmovie;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import java.util.Objects;

import it.unimib.disco.sal.bestmovie.databinding.ActivityMainBinding;
import it.unimib.disco.sal.bestmovie.viewmodels.MovieViewModel;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding activityMainDataBinding = null;
    private MovieViewModel activityMainViewModel = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Mostra un'icona nell'ActionBar
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher_round);


        //requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        //getSupportActionBar().hide(); // hide the title bar
        //this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
        //        WindowManager.LayoutParams.FLAG_FULLSCREEN); //enable full screen

        setUpDataBindingAndViewModel();
        setUpNavigation();
    }

    private void setUpDataBindingAndViewModel() {
        activityMainDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        activityMainDataBinding.setLifecycleOwner(this);
    }

    public void setUpNavigation() {
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
        NavController navController = navHostFragment.getNavController();
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        NavigationUI.setupWithNavController(bottomNavigationView, navController);
    }
}