package ru.pe9.android.aanetworkingexercise;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GiphyNetworkModule {

    private static final String BASE_URL="http://api.giphy.com/";
    public static final String API_KEY_VALUE = "CdYBZ8tC9yMAe3eA30odWarkYG4r8YwU";
    public static final String API_KEY_PARAM_NAME = "api_key";
    public static final String LIMIT_PARAM_NAME = "limit";
    public static final String LIMIT_VALUE = "100";

    private static HttpLoggingInterceptor logging = new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);

    Interceptor apiKeyInterceptor = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request originalRequest = chain.request();
            HttpUrl originalHttpUrl = originalRequest.url();

            HttpUrl url = originalHttpUrl.newBuilder()
                    .addQueryParameter(API_KEY_PARAM_NAME, API_KEY_VALUE)
                    .build();

            Request.Builder requestBuilder = originalRequest.newBuilder().url(url);

            return chain.proceed(requestBuilder.build());
        }
    }; // apiKeyInterceptor ///

    Interceptor limitResultsNumberInterceptor = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request originalRequest = chain.request();
            HttpUrl originalUrl = originalRequest.url();

            HttpUrl newUrl = originalUrl.newBuilder()
                    .addQueryParameter(LIMIT_PARAM_NAME, LIMIT_VALUE).build();

            Request.Builder newRequestBuilder = originalRequest.newBuilder().url(newUrl);
            return chain.proceed(newRequestBuilder.build());
        }
    };

    private OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder().connectTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS).readTimeout(30,TimeUnit.SECONDS)
            .addInterceptor(apiKeyInterceptor)
            .addInterceptor(limitResultsNumberInterceptor)
            .addInterceptor(logging).addNetworkInterceptor(logging)
            ;

    private Retrofit.Builder retrofitBuilder = new Retrofit.Builder().baseUrl(BASE_URL)
            .client(httpClientBuilder.build()).addConverterFactory(GsonConverterFactory.create());

    private Retrofit retrofit = retrofitBuilder.build();

    public IGiphyImagesSearchService giphySearchService = retrofit.create(IGiphyImagesSearchService.class);
}
