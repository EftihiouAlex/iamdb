package gr.aueb.cf.imdbapp.activities;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

import java.util.List;

import gr.aueb.cf.imdbapp.R;
import gr.aueb.cf.imdbapp.models.Actor;
import gr.aueb.cf.imdbapp.models.Director;
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
//    private TextView summaryTV;
//    private TextView actorsTV;
//    private TextView directorTV;
    private TextView plotTV;
    private TextView ratingTV;

    private TextView genreTV;

    private TextView directorTextTV;
    private TextView actorsTextTv;
    private RatingBar ratingBar;
    private ImageView imageView;


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

        long movieId = (long) getIntent().getExtras().get("movieId");

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
                for (Actor actor : actors) {
                    actorsBuilder.append(actor.getFirstname() + " " + actor.getLastname()).append(", ");

                }
                String actorsText = actorsBuilder.toString();
                actorsTextTv.setText(actorsText);

                StringBuilder directorsBuilder = new StringBuilder();
                for (Director director : directors) {
                    directorsBuilder.append(director.getFirstname() + " " + director.getLastname()).append(", ");

                }
                String directorsText = directorsBuilder.toString();
                directorTextTV.setText(directorsText);

                StringBuilder genresBuilder = new StringBuilder();
                for(Genre genre : genres){
                    genresBuilder.append(genre.getType()).append(", ");
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

    }
}