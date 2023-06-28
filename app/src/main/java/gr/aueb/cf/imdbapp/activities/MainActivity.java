package gr.aueb.cf.imdbapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import gr.aueb.cf.imdbapp.Parameters;
import gr.aueb.cf.imdbapp.R;
import gr.aueb.cf.imdbapp.adapters.MovieAdapter;
import gr.aueb.cf.imdbapp.adapters.MovieClickListener;
import gr.aueb.cf.imdbapp.models.Movie;
import gr.aueb.cf.imdbapp.models.User;
import gr.aueb.cf.imdbapp.network.ApiService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private EditText searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        searchView = findViewById(R.id.searchTV);
        ApiService.getInstance().getMovieService().getLatest().enqueue(new Callback<List<Movie>>() {
            @Override
            public void onResponse(Call<List<Movie>> call, Response<List<Movie>> response) {
                RecyclerView latestRV = findViewById(R.id.latestRV);
                latestRV.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false));
                latestRV.setAdapter(new MovieAdapter(MainActivity.this, response.body(), new MovieClickListener() {
                    @Override
                    public void onMovieClick(long id) {
                        Intent intent = new Intent(MainActivity.this, MovieActivity.class);
                        intent.putExtra("movieId", id);
                        startActivity(intent);
                    }
                }));
            }

            @Override
            public void onFailure(Call<List<Movie>> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        ApiService.getInstance().getMovieService().getTopRated().enqueue(new Callback<List<Movie>>() {
            @Override
            public void onResponse(Call<List<Movie>> call, Response<List<Movie>> response) {
                RecyclerView topRatedRV = findViewById(R.id.topRatedRV);
                topRatedRV.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false));
                topRatedRV.setAdapter(new MovieAdapter(MainActivity.this, response.body(), new MovieClickListener() {
                    @Override
                    public void onMovieClick(long id) {
                        Intent intent = new Intent(MainActivity.this, MovieActivity.class);
                        intent.putExtra("movieId", id);
                        startActivity(intent);
                    }
                }));
            }

            @Override
            public void onFailure(Call<List<Movie>> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        long userId = Parameters.getInstance().user.getId();
        ApiService.getInstance().getMovieService().getFavorites(userId).enqueue(new Callback<List<Movie>>() {
            @Override
            public void onResponse(Call<List<Movie>> call, Response<List<Movie>> response) {
                RecyclerView favoritesRV = findViewById(R.id.favoritesRV);
                favoritesRV.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false));
                favoritesRV.setAdapter(new MovieAdapter(MainActivity.this, response.body(), new MovieClickListener() {
                    @Override
                    public void onMovieClick(long id) {
                        Intent intent = new Intent(MainActivity.this, MovieActivity.class);
                        intent.putExtra("movieId", id);
                        startActivity(intent);
                    }
                }));
            }

            @Override
            public void onFailure(Call<List<Movie>> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        findViewById(R.id.searchBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String query = searchView.getText().toString();
                if (query.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please insert a movie title", Toast.LENGTH_SHORT).show();
                } else {
                    if (isSearchValid(query)) {
                        Intent intent = new Intent(MainActivity.this, SearchActivity.class);
                        intent.putExtra("title", query);
                        startActivity(intent);
                    }
                }
            }
        });

    }

    private Boolean isSearchValid(String search) {
        Boolean result = false;
        if (search == "") {
            Toast.makeText(this, "Please write something!", Toast.LENGTH_SHORT).show();
        } else {
            result = true;
        }

        return result;
    }


}