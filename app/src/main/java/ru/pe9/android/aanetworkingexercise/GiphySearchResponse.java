package ru.pe9.android.aanetworkingexercise;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class GiphySearchResponse {
    public List<GiphySingleImageDataResponse> data = null;

    public List<OnlineImageListItem> GetImagesData() {
        List<OnlineImageListItem> imagesData = new ArrayList<OnlineImageListItem>();

//        Log.w("ERROR285", "Want to retreive images, size = " + data.size());

        for(int i = 0; i < data.size(); i++) {
//            Log.w("ERROR285", "Adding item number " + i);
            imagesData.add(new OnlineImageListItem(data.get(i)));
        }

        return imagesData;
    }
}
