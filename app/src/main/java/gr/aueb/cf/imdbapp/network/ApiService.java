package gr.aueb.cf.imdbapp.network;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiService {
    private static ApiService instance = null;
    private Retrofit retrofit;
    private OkHttpClient client;

    private Api api;

    public ApiService() {

        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder okHttpBuilder = new OkHttpClient.Builder();
        okHttpBuilder.addInterceptor(loggingInterceptor);

        client = okHttpBuilder.build();

        retrofit = new Retrofit.Builder().baseUrl("http://192.168.2.5:8080/FinalProject_war/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        api = retrofit.create(Api.class);
    }

    public static ApiService getInstance() {
        if (instance == null) {
            instance = new ApiService();
        }

        return instance;
    }

    public Api getMovieService() {
        return api;
    }
}

