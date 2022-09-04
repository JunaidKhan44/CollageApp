package com.tas.collagemaker.photoeditor.piccollage.photoframe.gradientfragment;

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

public class GradientAdapter  extends  RecyclerView.Adapter<GradientAdapter.ViewHolder>{

    private Context context;
    private ArrayList<GradientModel> myGradient;

    public GradientAdapter(Context context, ArrayList<GradientModel> myGradient) {
        this.context = context;
        this.myGradient = myGradient;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.gradientrow_item,null,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.cardView.setBackground(context.getResources().getDrawable(myGradient.get(position).getGradient()) );
        
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //here condition must
                if(MainCanvas.flagForBack==true) {
                    MainCanvas.stickerView.setBackground(context.getResources().getDrawable(myGradient.get(position).getGradient()));
                }
                if(FreeStyle.flagForTextSticker==true){
                FreeStyle.stickerViewFree.setBackground(context.getResources().getDrawable(myGradient.get(position).getGradient()));
            }
            }
        });

    }

    @Override
    public int getItemCount() {
        return myGradient.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        public CardView cardView;
        public ViewHolder(View itemView) {
            super(itemView);
            cardView=(CardView)itemView.findViewById(R.id.cardviewgradient);
        }
    }
}
