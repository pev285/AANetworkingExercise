package ru.pe9.android.aanetworkingexercise;

import android.content.Intent;

public class OnlineImageListItem {

    private String stillImgSrc = null;
    private String animatedImgSrc = null;

    private String type;
    private String id;
    private String pageUrl;
    private String rating;
    private String title;


    private static final String STILL_IMG_KEY = "STILL_IMG_KEY";
    private static final String ANIMATED_IMG_KEY = "ANIMATED_IMG_KEY";
    private static final String TYPE_KEY = "TYPE_KEY";
    private static final String ID_KEY = "ID_KEY";
    private static final String PAGE_URL_KEY = "PAGE_URL_KEY";
    private static final String RATING_KEY = "RATING_KEY";
    private static final String TITLE_KEY = "TITLE_KEY";


    public void SaveToIntent(Intent intent) {
        intent.putExtra(STILL_IMG_KEY, stillImgSrc);
        intent.putExtra(ANIMATED_IMG_KEY, animatedImgSrc);
        intent.putExtra(TYPE_KEY, type);
        intent.putExtra(ID_KEY, id);
        intent.putExtra(PAGE_URL_KEY, pageUrl);
        intent.putExtra(RATING_KEY, rating);
        intent.putExtra(TITLE_KEY, title);
    }

    public void LoadFromIntent(Intent intent) {
        stillImgSrc = intent.getStringExtra(STILL_IMG_KEY);
        animatedImgSrc = intent.getStringExtra(ANIMATED_IMG_KEY);
        type = intent.getStringExtra(TYPE_KEY);
        id = intent.getStringExtra(ID_KEY);
        pageUrl = intent.getStringExtra(PAGE_URL_KEY);
        rating = intent.getStringExtra(RATING_KEY);
        title = intent.getStringExtra(TITLE_KEY);
    }

    public OnlineImageListItem(Intent intent) {
        LoadFromIntent(intent);
    }


    public OnlineImageListItem(GiphySingleImageDataResponse item) {
        this.type = item.type;
        this.id = item.id;
        this.pageUrl = item.url;
        this.rating = item.rating;
        this.title = item.title;

        this.animatedImgSrc = item.images.original.url;
        this.stillImgSrc = item.images.image480w_still.url;
    }

    public OnlineImageListItem(String imgSrc) {
        this.stillImgSrc = imgSrc;
        this.animatedImgSrc = imgSrc;
    }

    public String getStillImgSrc() {
        return stillImgSrc;
    }

    public String getAnimatedImgSrc() {
        return animatedImgSrc;
    }


    public String getPageUrl() {
        return pageUrl;
    }

    public String getRating() {
        return rating;
    }

    public String getTitle() {
        return title;
    }


}
