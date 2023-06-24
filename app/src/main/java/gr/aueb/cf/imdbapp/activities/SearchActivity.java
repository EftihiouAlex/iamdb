package gr.aueb.cf.imdbapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import java.util.List;

import gr.aueb.cf.imdbapp.R;
import gr.aueb.cf.imdbapp.adapters.MovieAdapter;
import gr.aueb.cf.imdbapp.adapters.MovieClickListener;
import gr.aueb.cf.imdbapp.models.Movie;
import gr.aueb.cf.imdbapp.network.ApiService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        String title = (String) getIntent().getExtras().get("title");
        if (title == null) {
            Toast.makeText(this, "Search not found.", Toast.LENGTH_SHORT).show();
            finish();
        }

        ApiService.getInstance().getMovieService().searchMovie(title).enqueue(new Callback<List<Movie>>() {
            @Override
            public void onResponse(Call<List<Movie>> call, Response<List<Movie>> response) {
                List<Movie> movies = response.body();
                if (movies.isEmpty()) {
                    Toast.makeText(SearchActivity.this, "No movies found", Toast.LENGTH_SHORT).show();
                } else {
                    RecyclerView recycler =  findViewById(R.id.searchRV);
                    recycler.setLayoutManager(new LinearLayoutManager(SearchActivity.this));
                    recycler.setAdapter(new MovieAdapter(SearchActivity.this, response.body(), new MovieClickListener() {
                        @Override
                        public void onMovieClick(long id) {
                            Intent intent = new Intent(SearchActivity.this, MovieActivity.class);
                            intent.putExtra("movieId", id);
                            startActivity(intent);
                        }
                    }));
                }
            }

            @Override
            public void onFailure(Call<List<Movie>> call, Throwable t) {

            }
        });
    }
}