package com.moeabdel.assignment3;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

public class ArtworkViewHolder extends RecyclerView.ViewHolder{

    TextView recyclerViewTitleText;
    ImageView recyclerViewImage;

    public ArtworkViewHolder(@NonNull View itemview){
        super(itemview);
        recyclerViewTitleText = itemview.findViewById(R.id.recyclerViewText);
        recyclerViewImage = itemview.findViewById(R.id.recyclerViewArtworkImage);
    }
}
