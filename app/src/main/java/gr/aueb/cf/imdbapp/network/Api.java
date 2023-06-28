package gr.aueb.cf.imdbapp.network;

import java.util.List;

import gr.aueb.cf.imdbapp.models.FavoriteMovie;
import gr.aueb.cf.imdbapp.models.FullMovie;
import gr.aueb.cf.imdbapp.models.Movie;
import gr.aueb.cf.imdbapp.models.User;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface Api {
    @GET("movies")
    Call<FullMovie> getMovie(@Query("id") long movieId);

    @GET("movies/latestreleases")
    Call<List<Movie>> getLatest();

    @GET("movies/toprated")
    Call<List<Movie>> getTopRated();

    @GET("user/login")
    Call<User> login(@Query("username") String username, @Query("password") String password);

    @POST("user/register")
    Call<User> registerUser(@Query("username") String username, @Query("password") String password, @Query("email") String email);

    @GET("movies/search")
    Call<List<Movie>> searchMovie(@Query("title") String title);

    @POST("movies/addfavorites")
    Call<List<Movie>> addFavorite(@Query("movieId") long movieId, @Query("userId")  long userId);

    @GET("movies/favorites")
    Call<List<Movie>> getFavorites(@Query("userId") long userId);

    @GET("movies/getfavorite")
    Call<FavoriteMovie> getFavorite(@Query("userId")long userId, @Query("movieId") long movieId );

}
