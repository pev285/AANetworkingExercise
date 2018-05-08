package ru.pe9.android.aanetworkingexercise;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class GiphySearchResponse {
    public List<GiphySingleImageDataResponse> data = null;

    public List<OnlineImageListItem> GetImagesData() {
        List<OnlineImageListItem> imagesData = new ArrayList<OnlineImageListItem>();

        for(int i = 0; i < data.size(); i++) {
            imagesData.add(new OnlineImageListItem(data.get(i)));
        }

        return imagesData;
    }
}
