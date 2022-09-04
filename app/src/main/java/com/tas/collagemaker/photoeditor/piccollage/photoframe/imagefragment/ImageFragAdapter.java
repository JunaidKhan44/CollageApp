package com.tas.collagemaker.photoeditor.piccollage.photoframe.imagefragment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tas.collagemaker.photoeditor.piccollage.photoframe.R;
import com.tas.collagemaker.photoeditor.piccollage.photoframe.otherclasses.FreeStyle;
import com.tas.collagemaker.photoeditor.piccollage.photoframe.otherclasses.MainCanvas;


import java.util.ArrayList;

public class ImageFragAdapter extends  RecyclerView.Adapter<ImageFragAdapter.MyHolder> {


    public Context cr;
    ArrayList<ImageFragmentModel> models;

    public ImageFragAdapter(Context cr, ArrayList<ImageFragmentModel> models) {
        this.cr = cr;
        this.models = models;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.imagefrag_item, null);
        return  new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {

        holder.imgView.setImageResource(models.get(position).getImg());

        holder.imgView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(MainCanvas.flagForBack) {
                    MainCanvas.imageBackGrounder.setImageResource(models.get(position).getImg());
                }
                if(FreeStyle.flagForTextSticker){
                FreeStyle.freeBackGroundChange.setImageResource(models.get(position).getImg());
            }
            }
        });
    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    //inner class view-holder
    public class MyHolder extends RecyclerView.ViewHolder {

        ImageView imgView;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            this.imgView = itemView.findViewById(R.id.image);
        }
    }

}
