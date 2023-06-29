package gr.aueb.cf.imdbapp.activities;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import gr.aueb.cf.imdbapp.Parameters;
import gr.aueb.cf.imdbapp.R;
import gr.aueb.cf.imdbapp.models.Actor;
import gr.aueb.cf.imdbapp.models.Director;
import gr.aueb.cf.imdbapp.models.FavoriteMovie;
import gr.aueb.cf.imdbapp.models.FullMovie;
import gr.aueb.cf.imdbapp.models.Genre;
import gr.aueb.cf.imdbapp.models.Movie;
import gr.aueb.cf.imdbapp.network.ApiService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieActivity extends AppCompatActivity {

    private TextView titleTv;
    private TextView durationTV;
    private TextView releaseYearTV;
    private TextView plotTV;
    private TextView ratingTV;

    private TextView genreTV;

    private TextView directorTextTV;
    private TextView actorsTextTv;
    private RatingBar ratingBar;
    private ImageView imageView;

    private FloatingActionButton favoriteBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);

        imageView = findViewById(R.id.movieIV);
        titleTv = findViewById(R.id.titleTV);
        durationTV = findViewById(R.id.durationTV);
        releaseYearTV = findViewById(R.id.releaseYearTV);
        plotTV = findViewById(R.id.plotTV);
        ratingTV = findViewById(R.id.ratingTV);
        genreTV =findViewById(R.id.genreTV);
        ratingBar = findViewById(R.id.ratingBar);
        directorTextTV = findViewById(R.id.directorTextTV);
        actorsTextTv = findViewById(R.id.actorsTextTV);
        favoriteBtn = findViewById(R.id.favoriteBtn);

        long movieId = getIntent().getLongExtra("movieId", -1);
        ApiService.getInstance().getMovieService().getMovie(movieId).enqueue(new Callback<FullMovie>() {
            @Override
            public void onResponse(Call<FullMovie> call, Response<FullMovie> response) {
                Movie movie = response.body().getMovie();

                List<Actor> actors = response.body().getActors();
                List<Director> directors = response.body().getDirector();
                List<Genre> genres = response.body().getGenres();
                String imageUrl = response.body().getMovie().getImageUrl();
                Glide.with(MovieActivity.this).load(imageUrl).into(imageView);

                titleTv.setText(movie.getTitle());
                releaseYearTV.setText(String.valueOf(movie.getReleaseYear()));
                durationTV.setText(movie.getDuration());
                ratingTV.setText(String.valueOf(movie.getRating()));
                ratingBar.setRating((float) movie.getRating());
                plotTV.setText(movie.getPlotSummary());

                StringBuilder actorsBuilder = new StringBuilder();
                boolean hasNextActor = true;
                for (Actor actor : actors) {
                    if (!hasNextActor) {
                        actorsBuilder.append(", ");
                    } else {
                        hasNextActor = false;
                    }
                    actorsBuilder.append(actor.getFirstname()).append(" ").append(actor.getLastname());
                }

                String actorsText = actorsBuilder.toString();
                actorsTextTv.setText(actorsText);

                StringBuilder directorsBuilder = new StringBuilder();
                boolean hasNextDirector = true;
                for (Director director : directors) {
                    if (!hasNextDirector) {
                        directorsBuilder.append(", ");
                    }else{
                        hasNextDirector = false;
                    }
                    directorsBuilder.append(director.getFirstname()).append(" ").append(director.getLastname());
                }
                String directorsText = directorsBuilder.toString();
                directorTextTV.setText(directorsText);

                StringBuilder genresBuilder = new StringBuilder();
                boolean hasNextGenre = true;
                for(Genre genre : genres){
                    if(!hasNextGenre){
                        genresBuilder.append(", ");
                    }else{
                        hasNextGenre = false;
                    }
                    genresBuilder.append(genre.getType());
                }
                String genresText = genresBuilder.toString();
                genreTV.setText(genresText);

            }

            @Override
            public void onFailure(Call<FullMovie> call, Throwable t) {
                System.out.println(t.getMessage());
                Toast.makeText(MovieActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        long userId = Parameters.getInstance().user.getId();
        ApiService.getInstance().getMovieService().getFavorite(userId, movieId).enqueue(new Callback<FavoriteMovie>() {
            @Override
            public void onResponse(Call<FavoriteMovie> call, Response<FavoriteMovie> response) {
                FavoriteMovie favoriteMovie = response.body();
                if (favoriteMovie.getUserId() == -1L) {
                    favoriteBtn.setImageDrawable(ResourcesCompat.getDrawable(MovieActivity.this.getResources(), R.drawable.ic_favorite_empty, MovieActivity.this.getTheme()));
                    favoriteBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            addToFavorite(movieId, userId);
                            favoriteBtn.setImageDrawable(ResourcesCompat.getDrawable(MovieActivity.this.getResources(), R.drawable.ic_favorite, MovieActivity.this.getTheme()));
                            favoriteBtn.setOnClickListener(null);
                            Toast.makeText(MovieActivity.this, "Movie inserted into favorites", Toast.LENGTH_SHORT).show();
                        }
                    });
                } else {
                    favoriteBtn.setImageDrawable(ResourcesCompat.getDrawable(MovieActivity.this.getResources(), R.drawable.ic_favorite, MovieActivity.this.getTheme()));
                }
                favoriteBtn.setVisibility(View.VISIBLE);
            }

            @Override
            public void onFailure(Call<FavoriteMovie> call, Throwable t) {
                t.printStackTrace();
            }
        });


    }

    //check
    private void addToFavorite(long movieId, long userId){
        ApiService.getInstance().getMovieService().addFavorite(movieId, userId).enqueue(new Callback<List<Movie>>() {
            @Override
            public void onResponse(Call<List<Movie>> call, Response<List<Movie>> response) {

            }

            @Override
            public void onFailure(Call<List<Movie>> call, Throwable t) {

            }
        });
    }
}