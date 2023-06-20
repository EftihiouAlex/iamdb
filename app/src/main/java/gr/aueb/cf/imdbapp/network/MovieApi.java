package gr.aueb.cf.imdbapp.network;

import java.util.List;

import gr.aueb.cf.imdbapp.models.FullMovie;
import gr.aueb.cf.imdbapp.models.Movie;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface MovieApi {
    @GET("movies?id={moveId}")
    Call<FullMovie> getMovie(@Path("movieId") long movieId);

    @GET("movies/latestreleases")
    Call<List<Movie>> getLatest();

    @GET("movies/toprated")
    Call<List<Movie>> getTopRated();

}
