package gr.aueb.cf.imdbapp.network;

import gr.aueb.cf.imdbapp.models.FullMovie;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MovieService {
    private static MovieService instance = null;
    private Retrofit retrofit;
    private OkHttpClient client;

    private MovieApi movieApi;

    public MovieService() {

        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder okHttpBuilder = new OkHttpClient.Builder();
        okHttpBuilder.addInterceptor(loggingInterceptor);

        client = okHttpBuilder.build();

        retrofit = new Retrofit.Builder().baseUrl("http://localhost:8080/FinalProject_war/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        movieApi = retrofit.create(MovieApi.class);
    }

    public static MovieService getInstance() {
        if (instance == null) {
            instance = new MovieService();
        }

        return instance;
    }

    public MovieApi getMovieService() {
        return movieApi;
    }
}

