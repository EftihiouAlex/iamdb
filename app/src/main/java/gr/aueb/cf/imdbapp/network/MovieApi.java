package gr.aueb.cf.imdbapp.network;

import java.util.List;

import gr.aueb.cf.imdbapp.models.FullMovie;
import gr.aueb.cf.imdbapp.models.Movie;
import gr.aueb.cf.imdbapp.models.User;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MovieApi {
    @GET("movies")
    Call<FullMovie> getMovie(@Query("id") long movieId);

    @GET("movies/latestreleases")
    Call<List<Movie>> getLatest();

    @GET("movies/toprated")
    Call<List<Movie>> getTopRated();

    @GET("user/login")
    Call<User> getUser(@Query("username") String username, @Query("password") String password);

}
