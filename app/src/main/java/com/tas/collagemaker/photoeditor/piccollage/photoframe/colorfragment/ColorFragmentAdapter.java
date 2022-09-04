package com.tas.collagemaker.photoeditor.piccollage.photoframe.colorfragment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.tas.collagemaker.photoeditor.piccollage.photoframe.R;
import com.tas.collagemaker.photoeditor.piccollage.photoframe.otherclasses.FreeStyle;
import com.tas.collagemaker.photoeditor.piccollage.photoframe.otherclasses.MainCanvas;


import java.util.ArrayList;

public class ColorFragmentAdapter extends RecyclerView.Adapter<ColorFragmentAdapter.ViewHolder> {

    private Context context;
    private ArrayList<ColorModel> mycolorlist;

    public ColorFragmentAdapter(Context applicationContext, ArrayList<ColorModel> mycolor) {
        this.context = applicationContext;
        this.mycolorlist = mycolor;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.colorrow_item,null,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.cardView.setBackground(context.getResources().getDrawable(mycolorlist.get(position).getColor()) );
        
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //here condition is must
                if(FreeStyle.flagForTextSticker==true) {
                    FreeStyle.stickerViewFree.setBackground(context.getResources().getDrawable(mycolorlist.get(position).getColor()));

                }
                if(MainCanvas.flagForBack==true) {
                    MainCanvas.stickerView.setBackground(context.getResources().getDrawable(mycolorlist.get(position).getColor()));
                }

            }
        });
    }


    @Override
    public int getItemCount() {
        return mycolorlist.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
       public CardView  cardView;
        public ViewHolder(View itemView) {
            super(itemView);
            cardView=(CardView)itemView.findViewById(R.id.cardview);
        }
    }
}
