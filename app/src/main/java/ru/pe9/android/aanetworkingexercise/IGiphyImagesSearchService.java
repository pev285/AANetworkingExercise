package ru.pe9.android.aanetworkingexercise;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface IGiphyImagesSearchService {

    @GET("/v1/gifs/search")
    Call<GiphySearchResponse> getImages(@Query("q") String query);
}
