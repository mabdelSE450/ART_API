package com.moeabdel.assignment3;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.hardware.input.InputManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.moeabdel.assignment3.databinding.ActivityMainBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static String title;
    private ActivityMainBinding binding;
    private RequestQueue queue;
    private ImageView lionImage;
    private  ProgressBar progressBar;
    private   TextView searchBar;

    private RecyclerView recyclerView;
    private static ArtworkViewHolder artworkViewHolder;
    private EditText editText;
    private String field = "title, date_display, artist_display, medium_display,artwork_type_title, image_id, dimensions, department_title,credit_line, place_of_origin, gallery_title, gallery_id, id, api_link";
    private static String imageURL = "https://www.artic.edu/iiif/2/";
    private static String thumbnailSpec = "/full/200,/0/default.jpg";
    private String fullImageSpec = "/full/843,/0/default.jpg ";


    private String pageLimit;

    private  static ArrayList<Artwork> artworkArrayList = new ArrayList<>();

    private static ArtworkAdapter artworkAdapter;

    private static final String urlString = "https://api.artic.edu/api/v1/artworks/search";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        artworkAdapter = new ArtworkAdapter(artworkArrayList, this);
        binding.artworkRecyclerView.setAdapter(artworkAdapter);
        binding.artworkRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        queue = Volley.newRequestQueue(this);
        recyclerView = binding.artworkRecyclerView;
        artworkAdapter = new ArtworkAdapter(artworkArrayList, this);
        recyclerView.setAdapter(artworkAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        editText = binding.searchBar;
         lionImage = binding.lionImage;
         progressBar = binding.progressBar;
         searchBar = binding.searchBar;
         //progressBar.setVisibility(View.VISIBLE);
        //for (int i = 0; i < 20; i++) {
          //  artworkArrayList.add(new Artwork("This is the text"));
           // artworkAdapter.notifyItemInserted(i);
            //editText = binding.searchBar;

       // }
    }


    public void getArtData(View v) {
        if(!hasConnection()){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("NoConnectionError");
            builder.setMessage("No internet connection present - cannot contact Art Institute API");
            builder.setCancelable(false);
            builder.setIcon(R.drawable.logo);
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                   // finish();

                }
            });
            AlertDialog dialog = builder.create();
            dialog.show();
        }
        else if(searchBar.length() == 0){
            Toast.makeText(this, "Please actually enter a search string", Toast.LENGTH_SHORT).show();
        }
         else if (searchBar.length() < 3) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Search string too short");
            builder.setMessage("Please try a longer search string");
            builder.setCancelable(false);
            builder.setIcon(R.drawable.logo);
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    //finish();

                }
            });
            AlertDialog dialog = builder.create();
            dialog.show();
        } else {
             int size = artworkArrayList.size();
            progressBar.setVisibility(View.VISIBLE);
            // lionImage.setVisibility(View.INVISIBLE);
             for (int i = size - 1; i >= 0; i--) {
              artworkArrayList.remove(i);
             artworkAdapter.notifyItemRemoved(i);
               }

           // artworkArrayList.clear();
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
            String input = editText.getText().toString();
            doDownload(input);
            lionImage.setVisibility(View.INVISIBLE);


        }
    }

    public void mainActivityLink(View v) {

        Intent intent = new Intent(this, CopyrightActivity.class);
        startActivity(intent);

    }

    public void clearSearchBar(View v) {
        //binding.searchBar.setText("");
       searchBar.setText("");
        int size = artworkArrayList.size();

       // for (int i = size - 1; i >= 0; i--) {
          //  artworkArrayList.remove(i);
           // artworkAdapter.notifyItemRemoved(i);
     //   }
        //for(int i = 0; i<size; i++){

           // artworkArrayList.remove(i);
           // artworkAdapter.notifyItemRemoved(i);
       // }

    }

    @Override
    public void onClick(View view) {

        int pos = recyclerView.getChildLayoutPosition(view);
        Artwork artwork = artworkArrayList.get(pos);
        Intent intent = new Intent(this, ArtworkActivity.class);
        String title = artwork.getTitle();
        String date = artwork.getDateDisplay();
        int galleryId = artwork.getGalleryId();
        String galleryTitle = artwork.getGalleryTitle();
        String artistDisplay1 = artwork.getArtistDisplay();
        String artistDisplay2 = artwork.getArtistDisplay();
        String placeOfOrigin = artwork.getPlaceOfOrigin();
        String dimensions = artwork.getDimensions();
        String creditLine = artwork.getCreditLine();
        String mediumDisplay = artwork.getMediumDisplay();
        String artworkTypeTitle = artwork.getArtworkTypeTitle();
        String apiLink = artwork.getApiLink();
        int id = artwork.getId();
        String imageId = artwork.getImageId();
        String thumbnailImage = artwork.getThumbnailImage();
        String department = artwork.getDepartmentTitle();
        String dateDisplay = artwork.getDateDisplay();

        Artwork clickedArtwork = new Artwork(mediumDisplay, artistDisplay1, title, galleryTitle, placeOfOrigin,creditLine,
                artworkTypeTitle, department, apiLink, dateDisplay, galleryId, id, thumbnailImage, dimensions, imageId );
        intent.putExtra("artwork", clickedArtwork);
        //intent.putExtra("title", title);
       // intent.putExtra("data", date);
       // intent.putExtra("galleyId", galleyId);
       // intent.putExtra("galleryTitle", galleryTitle);
       // intent.putExtra("artistDisplay1", artistDisplay1);
       // intent.putExtra("artistDisplay2", artistDisplay2);
       // intent.putExtra("palceOfOrigin", placeOfOrigin);
      //  intent.putExtra("dimensions", dimensions);
       // intent.putExtra("creditLine", creditLine);
       // intent.putExtra("mediumDisplay", mediumDisplay);
      //  intent.putExtra("artworkTypeTtitle", artworkTypeTitle);
        startActivity(intent);




    }

    private void doDownload(String input) {
        Uri.Builder urlBuilder = Uri.parse(urlString).buildUpon();

        urlBuilder.appendQueryParameter("q", input);
        urlBuilder.appendQueryParameter("limit", getResources().getString(R.string.pageLimit));
        urlBuilder.appendQueryParameter("page", "1");
        urlBuilder.appendQueryParameter("fields", getResources().getString(R.string.field));

        String urlToUse = urlBuilder.build().toString();
        Response.Listener<JSONObject> listener =
                response -> parseJSON(response.toString());
        Response.ErrorListener error = error1 -> Toast.makeText(this, "Error getting data", Toast.LENGTH_SHORT).show();

        JsonObjectRequest jsonObjectRequest =
                new JsonObjectRequest(Request.Method.GET, urlToUse,
                        null, listener, error);

       // if(jsonObjectRequest.){
          //  Toast.makeText(this, "NULL JSON", Toast.LENGTH_SHORT).show();
       // }
        queue.add(jsonObjectRequest);


    }

    private void parseJSON(String s) {

        try {
            JSONObject jsonData = new JSONObject(s);
            JSONArray data = jsonData.getJSONArray("data");
            if(data.length() == 0){
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("No search results found");
                builder.setMessage("No search results found for '" + searchBar.getText() + "'. Please try another search string");
                builder.setCancelable(false);
                builder.setIcon(R.drawable.logo);
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //finish();

                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
            else {
                for (int i = 0; i < data.length(); i++) {
                    JSONObject artworkObject = data.getJSONObject(i);


                    String mediumDisplay = artworkObject.optString("medium_display");
                    String artistDisplay = artworkObject.optString("artist_display");
                    String title = artworkObject.optString("title");
                    String galleryTitle = artworkObject.optString("gallery_title");
                    String placeOfOrigin = artworkObject.optString("place_of_origin");
                    String creditLine = artworkObject.optString("credit_line");
                    String artworkTypeTitle = artworkObject.optString("artwork_type_title");
                    String departmentTitle = artworkObject.optString("department_title");
                    String apiLink = artworkObject.optString("api_link");
                    String dateDisplay = artworkObject.optString("date_display");
                    int galleryId = artworkObject.optInt("gallery_id");
                    int id = artworkObject.optInt("id");
                    String imageId = artworkObject.optString("image_id");
                    String dimensions = artworkObject.optString("dimensions");

                    Uri.Builder imageBuilder = Uri.parse(imageURL).buildUpon();
                    imageBuilder.appendEncodedPath(imageId + thumbnailSpec);
                    //imageBuilder.appendPath(imageId );
                    //imageBuilder.appendPath(thumbnailSpec);
                    // imageBuilder.appendEncodedPath(thumbnailSpec);
                    String image = imageBuilder.build().toString();
                    //ImageView thumbnail = (imageURL + imageId + thumbnailSpec);

                    Artwork artwork = new Artwork(mediumDisplay, artistDisplay, title, galleryTitle, placeOfOrigin, creditLine,
                            artworkTypeTitle, departmentTitle, apiLink, dateDisplay, galleryId, id, image, dimensions, imageId);
                    artworkArrayList.add(artwork);

                    artworkAdapter.notifyItemInserted(i);


                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        progressBar.setVisibility(View.INVISIBLE);
    }

    private boolean hasConnection(){
        ConnectivityManager connectivityManager = getSystemService(ConnectivityManager.class);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return(networkInfo != null && networkInfo.isConnectedOrConnecting());
    }

    public String getTitleV(){
        return title;
    }

}