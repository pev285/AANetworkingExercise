package ru.pe9.android.aanetworkingexercise;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

public class SingleImageActivity extends AppCompatActivity {

    private OnlineImageListItem imageListItem = null;

    private ImageView mainImage = null;
    private TextView titleText = null;
    private TextView ratingText = null;
    private TextView urlText = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_image);

        Intent intent = getIntent();

        imageListItem = new OnlineImageListItem(intent);

        mainImage = findViewById(R.id.photo);
        titleText = findViewById(R.id.imageTitle);
        ratingText = findViewById(R.id.ratingValue);
        urlText = findViewById(R.id.originalLink);

        urlText.setText(imageListItem.getPageUrl());
        urlText.setPaintFlags(urlText.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        urlText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(imageListItem.getPageUrl()));
                startActivity(browserIntent);
            }
        });

        titleText.setText(imageListItem.getTitle());
        ratingText.setText(imageListItem.getRating());
//        Picasso.get().load(imageListItem.getStillImgSrc()).placeholder(R.drawable.import_placeholder).into(mainImage);

        Glide.with(mainImage.getContext()).load(imageListItem.getAnimatedImgSrc())/*.placeholder(R.drawable.import_placeholder)*/.into(mainImage);

    } // onCreate() //



    public static void StartMe(Context context, OnlineImageListItem imageItem) {
        Intent intent = new Intent(context, SingleImageActivity.class);

        imageItem.SaveToIntent(intent);

        context.startActivity(intent);
    }


} // END OF CLASS ///
