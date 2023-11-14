package com.moeabdel.assignment3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.moeabdel.assignment3.databinding.ActivityArtworkBinding;
import com.moeabdel.assignment3.databinding.ActivityMainBinding;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

public class ArtworkActivity extends AppCompatActivity {
    private ActivityArtworkBinding binding;

    private TextView title;
    private TextView date;
    private TextView artistDisplay1;
    private TextView artistDisplay2;
    private TextView departmentTitle;
    private int galleryId;
    private TextView galleryTitle;
    private TextView placeOfOrigin;
    private TextView artworkTypeTitle;
    private String mediumDisplay;
    private TextView dimensions;
    private TextView creditLine;
    private ImageView externalLinkLogo;
    private String imageId;
    private int id;
    private String apiLink;
    private String thumbnailImage;
    private ImageView fullImageView;
    private String dateDisplay;
    private String urlToSend;
    public boolean displayOrNo;
    String completedFullImageUrl;


    private Picasso picasso;

    private String baseImagesUrl = "https://www.artic.edu/iiif/2/";
    private String baseGalleriesUrl = "https://www.artic.edu/galleries/";
    private String fullImageUrl = "/full/843,/0/default.jpg";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_artwork);
        binding = ActivityArtworkBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        title = binding.artworkActivityTitle;
        date = binding.artworkActivityDate;
        artistDisplay1 = binding.artworkActivityArtistDisplay1;
        artistDisplay2 = binding.artworkActivityArtistDisplay2;
        departmentTitle = binding.artworkActivityDepartmentTitle;
        galleryTitle = binding.artworkActivityGalleryId;
        placeOfOrigin = binding.artworkActivityPlaceOfOrigin;
        artworkTypeTitle = binding.artworkActivityArtworkTypeTitle;
        //title = findViewById(R.id.artworkActivityTitle);
       // date = findViewById(R.id.artworkActivityDate);
       // artistDisplay1 = findViewById(R.id.artworkActivityArtistDisplay1);
       // artistDisplay2 = findViewById(R.id.artworkActivityArtistDisplay2);
       // departmentTitle = findViewById(R.id.artworkActivityDepartmentTitle);
        //galleryTitle = findViewById(R.id.artworkActivityGalleryId);
        //placeOfOrigin = findViewById(R.id.artworkActivityPlaceOfOrigin);
       // artworkTypeTitle = findViewById(R.id.artworkActivityArtworkTypeTitle);
        //mediumDisplay = findViewById(R.id.artworkActivityMediumDisplay);
        dimensions = binding.artworkActivityDimensions;
        creditLine = binding.artworkActivityCreditLine;
        externalLinkLogo = binding.externalLinkLogo;
        //fullImageView = binding.artworkActivityImageView;
        //dimensions = findViewById(R.id.artworkActivityDimensions);
       // creditLine = findViewById(R.id.artworkActivityCreditLine);
        //externalLinkLogo = findViewById(R.id.externalLinkLogo);
        //fullImageView = findViewById(R.id.artworkActivityImageView);
        picasso = Picasso.get();

        Intent intent = getIntent();
        if(intent.hasExtra("artwork")){

       // Uri.Builder fullImageBuilder = Uri.parse(baseGalleryIdUrl).buildUpon();
        //fullImageBuilder.appendEncodedPath(imageId + fullImageUrl);
           // String completedFullImageUrl = fullImageBuilder.toString();

            Artwork artwork = (Artwork) intent.getSerializableExtra("artwork");
            String combined = artwork.getArtworkTypeTitle() + " - " + artwork.getMediumDisplay();
            String[] temp = artwork.getArtistDisplay().split("\\n|\\(");
            imageId = artwork.getImageId();
            title.setText(artwork.getTitle());
            date.setText(artwork.getDateDisplay());
            galleryTitle.setText(artwork.getGalleryTitle());
            //artistDisplay1.setText(artwork.getArtistDisplay());
            artistDisplay1.setText(temp[0]);
            artistDisplay2.setText(temp[1]);
            departmentTitle.setText(artwork.getDepartmentTitle());
            //galleryId.setText(artwork.getGalleryId());
            placeOfOrigin.setText(artwork.getPlaceOfOrigin());
           // mediumDisplay.setText(artwork.getMediumDisplay());
            artworkTypeTitle.setText(combined);
            mediumDisplay = artwork.getMediumDisplay();
            dimensions.setText(artwork.getDimensions());
            creditLine.setText(artwork.getCreditLine());
            galleryId = artwork.getGalleryId();
            id = artwork.getId();
            dateDisplay = artwork.getDateDisplay();
            apiLink = artwork.getApiLink();
            thumbnailImage = artwork.getThumbnailImage();
            Uri.Builder fullImageBuilder = Uri.parse(baseImagesUrl).buildUpon();
            fullImageBuilder.appendEncodedPath(imageId + fullImageUrl);
             completedFullImageUrl = fullImageBuilder.toString();
             urlToSend = completedFullImageUrl;
            //picasso.load(completedFullImageUrl).error(R.drawable.not_available).into(binding.artworkActivityImageView);
            picasso.load(completedFullImageUrl).error(R.drawable.not_available).into(binding.artworkActivityImageView, new Callback() {
                @Override
                public void onSuccess() {
                    displayOrNo = true;

                }

                @Override
                public void onError(Exception e) {
                    displayOrNo = false;
                }
            });

        }
        if(galleryId == 0){
            galleryTitle.setText("     Not On Display     ");
            externalLinkLogo.setVisibility(View.INVISIBLE);
        }
    }
    public void openBrowser(View view){
        if(galleryTitle.getText().toString().equals("     Not On Display     ")){
            return;
        }
        String galleyIdUrlString = "" + galleryId;
        Uri.Builder galleryIdUrl = Uri.parse(baseGalleriesUrl).buildUpon();
        galleryIdUrl.appendEncodedPath(galleyIdUrlString);
        String galleryUrlString = galleryIdUrl.toString();
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(galleryUrlString));

        if(intent.resolveActivity(getPackageManager()) != null){
            startActivity(intent);
        }
    }

    public String getTitleMe(){
        return title.getText().toString();
    }

    public String getArtistDisplay1() {
        return artistDisplay1.getText().toString();
    }

    public String getDepartmentTitle() {
        return departmentTitle.getText().toString();
    }

    public String getGalleryTitle() {
        return galleryTitle.getText().toString();
    }

    public String getPlaceOfOrigin() {
        return placeOfOrigin.getText().toString();
    }

    public String getArtworkTypeTitle() {
        return artworkTypeTitle.getText().toString();
    }

    public String getDimensions() {
        return dimensions.getText().toString();
    }

    public String getCreditLine() {
        return creditLine.getText().toString();
    }

    public void openImageActivity(View view) {
       // Drawable desiredDrawable = ContextCompat.getDrawable(this, R.drawable.not_available);
        //Drawable checkedImage = binding.artworkActivityImageView.getDrawable();


        // if (imageId.equals("null")) {
        //Toast.makeText(this, "Image is Null", Toast.LENGTH_SHORT).show();
        //if(desiredDrawable.getConstantState().equals(checkedImage.getConstantState())){
        // Toast.makeText(this,"No Available Image to Zoom", Toast.LENGTH_SHORT).show();


        // }
        //else {
        if (displayOrNo) {
            Intent intent = new Intent(this, ImageActivity.class);
            //Artwork artwork = new Artwork()
            //String imageActivityTitle = title.toString();
            // String imageActivityArtistDisplay1 = artistDisplay1.toString();
            // String imageActivityArtistDisplay2 = artistDisplay2.toString();
            //intent.putExtra("title", imageActivityTitle);
            // intent.putExtra("artistDisplay1", imageActivityArtistDisplay1);
            //intent.putExtra("artistDisplay2", imageActivityArtistDisplay2);
            Artwork artwork = new Artwork(mediumDisplay, getArtistDisplay1(), getTitleMe(), getGalleryTitle(), getPlaceOfOrigin(),
                    getCreditLine(), getArtworkTypeTitle(), getDepartmentTitle(), apiLink, dateDisplay,
                    galleryId, id, thumbnailImage, getDimensions(), imageId);

            intent.putExtra("artwork", artwork);
            intent.putExtra("artistDisplay2", artistDisplay2.getText().toString());
            intent.putExtra("url", urlToSend);

            startActivity(intent);

        }
        else{
            Toast.makeText(this,"No Available Image to Zoom", Toast.LENGTH_SHORT).show();
        }
    }


    public void goToMenu(View view){
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
    }
}
