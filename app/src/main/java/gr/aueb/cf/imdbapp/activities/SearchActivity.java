package gr.aueb.cf.imdbapp.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import gr.aueb.cf.imdbapp.R;
import gr.aueb.cf.imdbapp.adapters.MovieAdapter;
import gr.aueb.cf.imdbapp.models.Movie;

public class
SearchActivity extends AppCompatActivity {
    private ArrayList<Movie> movies = new ArrayList<Movie>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        Movie movie = new Movie(1, "Hello Darkness", 2000, "01:23:05", "An awesome movie", 8.5, "https://m.media-amazon.com/images/M/MV5BMTUxMzQyNjA5MF5BMl5BanBnXkFtZTYwOTU2NTY3._V1_FMjpg_UX1000_.jpg");

        movies.add(movie);
        movies.add(movie);
        movies.add(movie);
        movies.add(movie);
        movies.add(movie);

        RecyclerView latestRV = findViewById(R.id.latestRV);
        latestRV.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        latestRV.setAdapter(new MovieAdapter(this, movies));

        RecyclerView topRatedRV = findViewById(R.id.topRatedRV);
        topRatedRV.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        topRatedRV.setAdapter(new MovieAdapter(this, movies));
    }
}