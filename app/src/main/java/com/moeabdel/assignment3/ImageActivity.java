package com.moeabdel.assignment3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.github.chrisbanes.photoview.PhotoView;
import com.squareup.picasso.Picasso;

public class ImageActivity extends AppCompatActivity {

    private TextView imageActivityTitle;
    private TextView imageActivityArtistDisplay1;
    private TextView imageActivityArtistDisplay2;
    private PhotoView photoView;
    private String fullImageUrl;

    private Picasso picasso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
        imageActivityTitle = findViewById(R.id.ImageActivityTitle);
        imageActivityArtistDisplay1 = findViewById(R.id.ImageActivityArtistDisplay1);
        imageActivityArtistDisplay2 = findViewById(R.id.ImageActivityArtistDisplay2);
        photoView = findViewById(R.id.ImageActivityFullImage);
        picasso = Picasso.get();



        //photoView.setImageResource();


        Intent intent = getIntent();
        if(intent.hasExtra("artwork") && intent.hasExtra("artistDisplay2")){
            Artwork artwork = (Artwork) intent.getSerializableExtra("artwork");
            imageActivityTitle.setText(artwork.getTitle());
            imageActivityArtistDisplay1.setText(artwork.getArtistDisplay());
            imageActivityArtistDisplay2.setText(intent.getStringExtra("artistDisplay2"));
            fullImageUrl = intent.getStringExtra("url");
        }

        picasso.load(fullImageUrl).error(R.drawable.not_available).into(photoView);

        photoView.setMaximumScale(12.25f);
        photoView.setMediumScale(3.5f);
        photoView.setMinimumScale(1f);
    }

    public void goToMenu(View view){
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
    }
}