package com.moeabdel.assignment3;

import android.media.Image;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.Serializable;

public class Artwork implements Serializable {
    private String thumbnailImage;
    private  String mediumDisplay;
    private  String artistDisplay;
    private  String title;
    private  String galleryTitle;
    private  String placeOfOrigin;
    private String creditLine;

    //private ImageView thumbnailImage;

    public String getMediumDisplay() {
        return mediumDisplay;
    }

    public String getArtistDisplay() {
        return artistDisplay;
    }

    public String getTitle() {
        return title;
    }

    public String getGalleryTitle() {
        return galleryTitle;
    }

    public String getPlaceOfOrigin() {
        return placeOfOrigin;
    }

    public String getCreditLine() {
        return creditLine;
    }

    public String getArtworkTypeTitle() {
        return artworkTypeTitle;
    }

    public String getDepartmentTitle() {
        return departmentTitle;
    }

    public String getApiLink() {
        return apiLink;
    }

    public String getDateDisplay() {
        return dateDisplay;
    }

    public int getGalleryId() {
        return galleryId;
    }

    public int getId() {
        return id;
    }

    public String getImageId() {
        return imageId;
    }

    public String getDimensions() {
        return dimensions;
    }

    public String getThumbnailImage(){
        return thumbnailImage;
    }

    private String artworkTypeTitle;
    private String departmentTitle;
    private String apiLink;
    private String dateDisplay;
    private int galleryId;



    private int id;
    private String imageId;
    private String dimensions;


    //private final ImageView recyclerViewImage;

    public Artwork(String mediumDisplay, String artistDisplay, String title, String galleryTitle, String placeOfOrigin,
                   String creditLine, String artworkTypeTitle, String departmentTitle, String apiLink, String dateDisplay, int galleryId,
                   int id, String thumbnailImage, String dimensions, String imageId){
        this.mediumDisplay = mediumDisplay;
        this.artistDisplay = artistDisplay;
        this.title = title;
        this.galleryTitle = galleryTitle;
        this.placeOfOrigin = placeOfOrigin;
        this.creditLine = creditLine;
        this.artworkTypeTitle = artworkTypeTitle;
        this.departmentTitle = departmentTitle;
        this.apiLink = apiLink;
        this.dateDisplay = dateDisplay;
        this.galleryId = galleryId;
        this.id = id;
        this.thumbnailImage = thumbnailImage;
        this.imageId = imageId;
        this.dimensions = dimensions;
   // public Artwork(String example){
       // this.recyclerViewText = example;
        //this.recyclerViewImage = recyclerViewImage;
    }
}
