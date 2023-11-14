package com.moeabdel.assignment3;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ArtworkAdapter extends RecyclerView.Adapter<ArtworkViewHolder> {

    private ArrayList<Artwork> artworkArrayList;
    private MainActivity mainActivity;

    private Picasso picasso;



    public ArtworkAdapter(ArrayList<Artwork> artworkArrayList, MainActivity mainActivity){
        this.artworkArrayList = artworkArrayList;
        this.mainActivity = mainActivity;
    }

    @NonNull
    @Override
    public ArtworkViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemview = LayoutInflater.from(parent.getContext()).inflate(R.layout.artwork_recyclerview, parent,false);
        itemview.setOnClickListener(mainActivity);
        //itemview.setOnLongClickListener(mainActivity);
        picasso = Picasso.get();
        return new ArtworkViewHolder(itemview);
    }

    @Override
    public void onBindViewHolder(@NonNull ArtworkViewHolder holder, int position) {
        Artwork artwork = artworkArrayList.get(position);
        String title = artwork.getTitle();

        if(title.length() >= 30){
            title = title.substring(0, 30 ) + "...";
        }
        String image = artwork.getThumbnailImage();

        picasso.load(artwork.getThumbnailImage()).error(R.drawable.not_available).into(holder.recyclerViewImage);

        holder.recyclerViewTitleText.setText(title);

    }

    @Override
    public int getItemCount() {
        return artworkArrayList.size();
    }
}
