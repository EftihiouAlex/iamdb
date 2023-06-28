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
import gr.aueb.cf.imdbapp.network.ApiService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private EditText searchView;
    private RecyclerView topRatedRV;
    private RecyclerView latestRV;
    private RecyclerView favoritesRV;

    private MovieAdapter favoritesAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchView = findViewById(R.id.searchTV);
        latestRV = findViewById(R.id.latestRV);
        favoritesRV = findViewById(R.id.favoritesRV);
        topRatedRV = findViewById(R.id.topRatedRV);
        latestRV.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false));
        favoritesRV.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false));
        topRatedRV.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false));



        getLatest();
        getTopRated();


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

    @Override
    protected void onResume() {
        super.onResume();
        getFavorites();
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

    private void getTopRated() {
        ApiService.getInstance().getMovieService().getTopRated().enqueue(new Callback<List<Movie>>() {
            @Override
            public void onResponse(Call<List<Movie>> call, Response<List<Movie>> response) {
                topRatedAdapter(response.body());
            }

            @Override
            public void onFailure(Call<List<Movie>> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getLatest() {
        ApiService.getInstance().getMovieService().getLatest().enqueue(new Callback<List<Movie>>() {
            @Override
            public void onResponse(Call<List<Movie>> call, Response<List<Movie>> response) {
                    latestAdapter(response.body());
            }

            @Override
            public void onFailure(Call<List<Movie>> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getFavorites() {
        long userId = Parameters.getInstance().user.getId();
        ApiService.getInstance().getMovieService().getFavorites(userId).enqueue(new Callback<List<Movie>>() {
            @Override
            public void onResponse(Call<List<Movie>> call, Response<List<Movie>> response) {
                favoritesAdapter(response.body());

            }

            @Override
            public void onFailure(Call<List<Movie>> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void movieClicked(long id) {
        Intent intent = new Intent(MainActivity.this, MovieActivity.class);
        intent.putExtra("movieId", id);
        startActivity(intent);
    }

    private void latestAdapter(List<Movie> movies){

        latestRV.setAdapter(new MovieAdapter(MainActivity.this, movies, new MovieClickListener() {
            @Override
            public void onMovieClick(long id) {
                movieClicked(id);
            }
        }));
    }
    private void topRatedAdapter(List<Movie> movies){

        topRatedRV.setAdapter(new MovieAdapter(MainActivity.this, movies, new MovieClickListener() {
            @Override
            public void onMovieClick(long id) {
                movieClicked(id);
            }
        }));
    }
    private void favoritesAdapter(List<Movie> movies){
            favoritesAdapter = new MovieAdapter(MainActivity.this, movies, new MovieClickListener() {
                @Override
                public void onMovieClick(long id) {
                    movieClicked(id);
                }
            });
        favoritesRV.setAdapter(favoritesAdapter);
        favoritesAdapter.notifyDataSetChanged();
    }


}