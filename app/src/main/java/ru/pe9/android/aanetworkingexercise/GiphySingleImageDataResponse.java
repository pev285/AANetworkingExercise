package ru.pe9.android.aanetworkingexercise;

import com.google.gson.annotations.SerializedName;

public class GiphySingleImageDataResponse {

    public class Original {
        public String url;
        public String width;
        public String height;
    }


    public class Images {
        Original original;

        @SerializedName("480w_still")
        public Original image480w_still;
    }




    public String type;
    public String id;
    public String slug;
    public String url;

    public String rating;

    public Images images;

    public String title;

}
