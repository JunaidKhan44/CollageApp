package com.tas.collagemaker.photoeditor.piccollage.photoframe.stickerfragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
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

public class FragStickerAdapter extends RecyclerView.Adapter<FragStickerAdapter.ViewHolder> {


    private Context context;
    
    private ArrayList<FragStickerModel> myStickerList;

    public FragStickerAdapter(Context context, ArrayList<FragStickerModel> myStickerList) {
        this.context = context;
        this.myStickerList = myStickerList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sticker_rowitem, null, false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.stickerImage.setImageResource(myStickerList.get(position).getStickerImg());

        holder.stickerImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(MainCanvas.flagForBack==true) {
                    Drawable drawable = context.getResources().getDrawable(myStickerList.get(position).getStickerImg());
                    MainCanvas.loadSticker2(drawable);
                }
                if(FreeStyle.flagForTextSticker==true){

                    Drawable drawable = context.getResources().getDrawable(myStickerList.get(position).getStickerImg());
                    FreeStyle.loadSticker2(drawable);
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return myStickerList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView stickerImage;

        public ViewHolder(View itemView) {
            super(itemView);
            stickerImage = itemView.findViewById(R.id.stickery);
        }
    }
}
