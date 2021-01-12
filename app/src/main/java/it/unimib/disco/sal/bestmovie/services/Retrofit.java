package it.unimib.disco.sal.bestmovie.services;

// import okhttp3.OkHttpClient;
import it.unimib.disco.sal.bestmovie.utils.Constants;
import retrofit2.converter.gson.GsonConverterFactory;

public class Retrofit {

    private static retrofit2.Retrofit retrofit;

    public static MoviesService getRetrofitInstance() {
        if (retrofit == null) {
            // HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            // logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            // OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
            // httpClient.addInterceptor(logging);

            /*
            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(Constants.MOVIE_API_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(httpClient.build())
                    .build();

             */
            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(Constants.MOVIE_API_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit.create(MoviesService.class);
    }
}

