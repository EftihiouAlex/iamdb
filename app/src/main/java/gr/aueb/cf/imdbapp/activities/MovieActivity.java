package gr.aueb.cf.imdbapp.activities;

import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.ImageView;

import gr.aueb.cf.imdbapp.R;
import gr.aueb.cf.imdbapp.databinding.ActivityMovieBinding;
import gr.aueb.cf.imdbapp.models.FullMovie;
import gr.aueb.cf.imdbapp.network.MovieService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

public class MovieActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);


//        MovieService.getInstance().getMovieService().getMovie().enqueue(new Callback<FullMovie>() {
//            @Override
//            public void onResponse(Call<FullMovie> call, Response<FullMovie> response) {
//
//            }
//
//            @Override
//            public void onFailure(Call<FullMovie> call, Throwable t) {
//
//            }
//        });




//        Toolbar toolbar = binding.toolbar;
//        setSupportActionBar(toolbar);
//        CollapsingToolbarLayout toolBarLayout = binding.toolbarLayout;
//        toolBarLayout.setTitle(getTitle());
//
//        FloatingActionButton fab = binding.fab;
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
    }
}