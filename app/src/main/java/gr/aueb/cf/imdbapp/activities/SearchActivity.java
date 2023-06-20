package gr.aueb.cf.imdbapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import gr.aueb.cf.imdbapp.R;
import gr.aueb.cf.imdbapp.adapters.MovieAdapter;
import gr.aueb.cf.imdbapp.adapters.MovieClickListener;
import gr.aueb.cf.imdbapp.models.Movie;
import gr.aueb.cf.imdbapp.network.MovieService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class
SearchActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        MovieService.getInstance().getMovieService().getLatest().enqueue(new Callback<List<Movie>>() {
            @Override
            public void onResponse(Call<List<Movie>> call, Response<List<Movie>> response) {
                RecyclerView latestRV = findViewById(R.id.latestRV);
                latestRV.setLayoutManager(new LinearLayoutManager(SearchActivity.this, LinearLayoutManager.HORIZONTAL, false));
                latestRV.setAdapter(new MovieAdapter(SearchActivity.this, response.body(), new MovieClickListener() {
                    @Override
                    public void onMovieClick(long id) {
                        Intent intent = new Intent(SearchActivity.this, MovieActivity.class);
                        intent.putExtra("movieId", id);
                        startActivity(intent);
                    }
                }));
            }

            @Override
            public void onFailure(Call<List<Movie>> call, Throwable t) {
                Toast.makeText(SearchActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        MovieService.getInstance().getMovieService().getTopRated().enqueue(new Callback<List<Movie>>() {
            @Override
            public void onResponse(Call<List<Movie>> call, Response<List<Movie>> response) {
                RecyclerView topRatedRV = findViewById(R.id.topRatedRV);
                topRatedRV.setLayoutManager(new LinearLayoutManager(SearchActivity.this, LinearLayoutManager.HORIZONTAL, false));
                topRatedRV.setAdapter(new MovieAdapter(SearchActivity.this, response.body(), new MovieClickListener() {
                    @Override
                    public void onMovieClick(long id) {
                        Intent intent = new Intent(SearchActivity.this, MovieActivity.class);
                        intent.putExtra("movieId", id);
                        startActivity(intent);
                    }
                }));
            }

            @Override
            public void onFailure(Call<List<Movie>> call, Throwable t) {
                Toast.makeText(SearchActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });



    }
}