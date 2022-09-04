package com.tas.collagemaker.photoeditor.piccollage.photoframe.layoutfragment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.tas.collagemaker.photoeditor.piccollage.photoframe.R;
import com.tas.collagemaker.photoeditor.piccollage.photoframe.otherclasses.MainCanvas;

import java.util.ArrayList;

public class LayoutAdapter extends RecyclerView.Adapter<LayoutAdapter.MyHolder> {

    public Context cr;
    ArrayList<LayoutModel> models;


    public LayoutAdapter(Context cr, ArrayList<LayoutModel> models) {
        this.cr = cr;
        this.models = models;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_itemrow, null);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {

        holder.imgView.setImageResource(models.get(position).getImage());
        holder.imgView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layoutChanger(position+1);
//                Toast.makeText(cr, position+1+"", Toast.LENGTH_SHORT).show();
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
            this.imgView = itemView.findViewById(R.id.imageforlayout);
        }
    }


    void layoutChanger(int position) {

        if(MainCanvas.v1.getVisibility()==View.VISIBLE){
            MainCanvas.v1.setVisibility(View.GONE);
        }
        else if(MainCanvas.v2.getVisibility()==View.VISIBLE){
            MainCanvas.v2.setVisibility(View.GONE);
        }
        else if(MainCanvas.v3.getVisibility()==View.VISIBLE){
            MainCanvas.v3.setVisibility(View.GONE);
        }
        else if(MainCanvas.v4.getVisibility()==View.VISIBLE){
            MainCanvas.v4.setVisibility(View.GONE);
        }
        else if(MainCanvas.v5.getVisibility()==View.VISIBLE){
            MainCanvas.v5.setVisibility(View.GONE);
        }
        else if(MainCanvas.v6.getVisibility()==View.VISIBLE){
            MainCanvas.v6.setVisibility(View.GONE);
        }
        else if(MainCanvas.v7.getVisibility()==View.VISIBLE){
            MainCanvas.v7.setVisibility(View.GONE);
        }
        else if(MainCanvas.v8.getVisibility()==View.VISIBLE){
            MainCanvas.v8.setVisibility(View.GONE);
        }

        try {
            switch (position){
                case 1:
                    MainCanvas.v1.setVisibility(View.VISIBLE);
                    if(MainCanvas.forChangeImgList.size()>=1){
                    MainCanvas.singleimg.setImageURI(MainCanvas.forChangeImgList.get(0).uri);}
                    return;
                case 2:
                    MainCanvas.v2.setVisibility(View.VISIBLE);
                    if(MainCanvas.forChangeImgList.size()>=2) {
                        MainCanvas.zimg1.setImageURI(MainCanvas.forChangeImgList.get(0).uri);
                        MainCanvas.zimg2.setImageURI(MainCanvas.forChangeImgList.get(1).uri);
                    }
                    else{

                        int size=MainCanvas.forChangeImgList.size();
                        switch(MainCanvas.forChangeImgList.size()){
                            case 1:
                                MainCanvas.zimg1.setImageURI(MainCanvas.forChangeImgList.get(0).uri);
                                return;
                            case 3:
                                MainCanvas.img31.setImageURI(MainCanvas.forChangeImgList.get(0).uri);
                                return;
                            case 4:
                                MainCanvas.zimg21.setImageURI(MainCanvas.forChangeImgList.get(0).uri);
                                return;
                            case 5:
                                MainCanvas.img51.setImageURI(MainCanvas.forChangeImgList.get(0).uri);
                                return;
                            case 6:
                                MainCanvas.img61.setImageURI(MainCanvas.forChangeImgList.get(0).uri);
                                return;
                            case 7:
                                MainCanvas.img71.setImageURI(MainCanvas.forChangeImgList.get(0).uri);
                                return;
                            case 8:
                                MainCanvas.img81.setImageURI(MainCanvas.forChangeImgList.get(0).uri);
                                return;

                        }
                    }
                    return;
                case 3:
                    MainCanvas.v3.setVisibility(View.VISIBLE);
                    if(MainCanvas.forChangeImgList.size()>=3) {
                        MainCanvas.img31.setImageURI(MainCanvas.forChangeImgList.get(0).uri);
                        MainCanvas.img32.setImageURI(MainCanvas.forChangeImgList.get(1).uri);
                        MainCanvas.img33.setImageURI(MainCanvas.forChangeImgList.get(2).uri);
                    }
                    else{
                       int size=MainCanvas.forChangeImgList.size();
                       switch(MainCanvas.forChangeImgList.size()){
                           case 1:
                               MainCanvas.img31.setImageURI(MainCanvas.forChangeImgList.get(0).uri);
                          return;
                           case 2:
                               MainCanvas.img31.setImageURI(MainCanvas.forChangeImgList.get(0).uri);
                               MainCanvas.img32.setImageURI(MainCanvas.forChangeImgList.get(1).uri);
                               return;

                       }

                    }
                    return;
                case 4:
                    MainCanvas.v4.setVisibility(View.VISIBLE);
                    if(MainCanvas.forChangeImgList.size()>=4) {
                        MainCanvas.zimg21.setImageURI(MainCanvas.forChangeImgList.get(0).uri);
                        MainCanvas.zimg22.setImageURI(MainCanvas.forChangeImgList.get(1).uri);
                        MainCanvas.zimg23.setImageURI(MainCanvas.forChangeImgList.get(2).uri);
                        MainCanvas.zimg24.setImageURI(MainCanvas.forChangeImgList.get(3).uri);
                    }
                    else{
                        int size=MainCanvas.forChangeImgList.size();
                        switch(MainCanvas.forChangeImgList.size()){
                            case 1:
                                MainCanvas.zimg21.setImageURI(MainCanvas.forChangeImgList.get(0).uri);
                                return;
                            case 2:
                                MainCanvas.zimg21.setImageURI(MainCanvas.forChangeImgList.get(0).uri);
                                MainCanvas.zimg22.setImageURI(MainCanvas.forChangeImgList.get(1).uri);
                                return;
                            case 3:
                                MainCanvas.zimg21.setImageURI(MainCanvas.forChangeImgList.get(0).uri);
                                MainCanvas.zimg22.setImageURI(MainCanvas.forChangeImgList.get(1).uri);
                                MainCanvas.zimg23.setImageURI(MainCanvas.forChangeImgList.get(2).uri);
                                return;
                        }

                    }
                    return;
                case 5:
                    MainCanvas.v5.setVisibility(View.VISIBLE);
                    if(MainCanvas.forChangeImgList.size()>=5) {
                        MainCanvas.img51.setImageURI(MainCanvas.forChangeImgList.get(0).uri);
                        MainCanvas.img52.setImageURI(MainCanvas.forChangeImgList.get(1).uri);
                        MainCanvas.img53.setImageURI(MainCanvas.forChangeImgList.get(2).uri);
                        MainCanvas.img54.setImageURI(MainCanvas.forChangeImgList.get(3).uri);
                        MainCanvas.img55.setImageURI(MainCanvas.forChangeImgList.get(4).uri);
                    }
                    else{
                        int size=MainCanvas.forChangeImgList.size();
                        switch(MainCanvas.forChangeImgList.size()){
                            case 1:
                                MainCanvas.img51.setImageURI(MainCanvas.forChangeImgList.get(0).uri);
                                return;
                            case 2:
                                MainCanvas.img51.setImageURI(MainCanvas.forChangeImgList.get(0).uri);
                                MainCanvas.img52.setImageURI(MainCanvas.forChangeImgList.get(1).uri);
                                return;
                            case 3:
                                MainCanvas.img51.setImageURI(MainCanvas.forChangeImgList.get(0).uri);
                                MainCanvas.img52.setImageURI(MainCanvas.forChangeImgList.get(1).uri);
                                MainCanvas.img53.setImageURI(MainCanvas.forChangeImgList.get(2).uri);
                                return;
                            case 4:
                                MainCanvas.img51.setImageURI(MainCanvas.forChangeImgList.get(0).uri);
                                MainCanvas.img52.setImageURI(MainCanvas.forChangeImgList.get(1).uri);
                                MainCanvas.img53.setImageURI(MainCanvas.forChangeImgList.get(2).uri);
                                MainCanvas.img54.setImageURI(MainCanvas.forChangeImgList.get(3).uri);
                                return;

                        }

                    }
                 return;
                case 6:
                    MainCanvas.v6.setVisibility(View.VISIBLE);
                    if(MainCanvas.forChangeImgList.size()>=6) {
                        MainCanvas.img61.setImageURI(MainCanvas.forChangeImgList.get(0).uri);
                        MainCanvas.img62.setImageURI(MainCanvas.forChangeImgList.get(1).uri);
                        MainCanvas.img63.setImageURI(MainCanvas.forChangeImgList.get(2).uri);
                        MainCanvas.img64.setImageURI(MainCanvas.forChangeImgList.get(3).uri);
                        MainCanvas.img65.setImageURI(MainCanvas.forChangeImgList.get(4).uri);
                        MainCanvas.img66.setImageURI(MainCanvas.forChangeImgList.get(5).uri);
                    }
                    else{
                        int size=MainCanvas.forChangeImgList.size();
                        switch(MainCanvas.forChangeImgList.size()){
                            case 1:
                                MainCanvas.img61.setImageURI(MainCanvas.forChangeImgList.get(0).uri);
                                return;
                            case 2:
                                MainCanvas.img61.setImageURI(MainCanvas.forChangeImgList.get(0).uri);
                                MainCanvas.img62.setImageURI(MainCanvas.forChangeImgList.get(1).uri);
                                return;
                            case 3:
                                MainCanvas.img61.setImageURI(MainCanvas.forChangeImgList.get(0).uri);
                                MainCanvas.img62.setImageURI(MainCanvas.forChangeImgList.get(1).uri);
                                MainCanvas.img63.setImageURI(MainCanvas.forChangeImgList.get(2).uri);
                                return;
                            case 4:
                                MainCanvas.img61.setImageURI(MainCanvas.forChangeImgList.get(0).uri);
                                MainCanvas.img62.setImageURI(MainCanvas.forChangeImgList.get(1).uri);
                                MainCanvas.img63.setImageURI(MainCanvas.forChangeImgList.get(2).uri);
                                MainCanvas.img64.setImageURI(MainCanvas.forChangeImgList.get(3).uri);
                                return;
                            case 5:
                                MainCanvas.img61.setImageURI(MainCanvas.forChangeImgList.get(0).uri);
                                MainCanvas.img62.setImageURI(MainCanvas.forChangeImgList.get(1).uri);
                                MainCanvas.img63.setImageURI(MainCanvas.forChangeImgList.get(2).uri);
                                MainCanvas.img64.setImageURI(MainCanvas.forChangeImgList.get(3).uri);
                                MainCanvas.img65.setImageURI(MainCanvas.forChangeImgList.get(3).uri);
                                return;

                        }

                    }
                    return;
                case 7:
                    MainCanvas.v7.setVisibility(View.VISIBLE);
                    if(MainCanvas.forChangeImgList.size()>=7) {
                        MainCanvas.img71.setImageURI(MainCanvas.forChangeImgList.get(0).uri);
                        MainCanvas.img72.setImageURI(MainCanvas.forChangeImgList.get(1).uri);
                        MainCanvas.img73.setImageURI(MainCanvas.forChangeImgList.get(2).uri);
                        MainCanvas.img74.setImageURI(MainCanvas.forChangeImgList.get(3).uri);
                        MainCanvas.img75.setImageURI(MainCanvas.forChangeImgList.get(4).uri);
                        MainCanvas.img76.setImageURI(MainCanvas.forChangeImgList.get(5).uri);
                        MainCanvas.img77.setImageURI(MainCanvas.forChangeImgList.get(6).uri);
                    }
                    else{
                        int size=MainCanvas.forChangeImgList.size();
                        switch(MainCanvas.forChangeImgList.size()){
                            case 1:
                                MainCanvas.img71.setImageURI(MainCanvas.forChangeImgList.get(0).uri);
                                return;
                            case 2:
                                MainCanvas.img71.setImageURI(MainCanvas.forChangeImgList.get(0).uri);
                                MainCanvas.img72.setImageURI(MainCanvas.forChangeImgList.get(1).uri);
                                return;
                            case 3:
                                MainCanvas.img71.setImageURI(MainCanvas.forChangeImgList.get(0).uri);
                                MainCanvas.img72.setImageURI(MainCanvas.forChangeImgList.get(1).uri);
                                MainCanvas.img73.setImageURI(MainCanvas.forChangeImgList.get(2).uri);
                                return;
                            case 4:
                                MainCanvas.img71.setImageURI(MainCanvas.forChangeImgList.get(0).uri);
                                MainCanvas.img72.setImageURI(MainCanvas.forChangeImgList.get(1).uri);
                                MainCanvas.img73.setImageURI(MainCanvas.forChangeImgList.get(2).uri);
                                MainCanvas.img74.setImageURI(MainCanvas.forChangeImgList.get(3).uri);
                                return;
                            case 5:
                                MainCanvas.img71.setImageURI(MainCanvas.forChangeImgList.get(0).uri);
                                MainCanvas.img72.setImageURI(MainCanvas.forChangeImgList.get(1).uri);
                                MainCanvas.img73.setImageURI(MainCanvas.forChangeImgList.get(2).uri);
                                MainCanvas.img74.setImageURI(MainCanvas.forChangeImgList.get(3).uri);
                                MainCanvas.img75.setImageURI(MainCanvas.forChangeImgList.get(4).uri);
                                return;
                            case 6:
                                MainCanvas.img71.setImageURI(MainCanvas.forChangeImgList.get(0).uri);
                                MainCanvas.img72.setImageURI(MainCanvas.forChangeImgList.get(1).uri);
                                MainCanvas.img73.setImageURI(MainCanvas.forChangeImgList.get(2).uri);
                                MainCanvas.img74.setImageURI(MainCanvas.forChangeImgList.get(3).uri);
                                MainCanvas.img75.setImageURI(MainCanvas.forChangeImgList.get(4).uri);
                                MainCanvas.img76.setImageURI(MainCanvas.forChangeImgList.get(5).uri);
                                return;

                        }

                    }
                    return;
                case 8:
                    MainCanvas.v8.setVisibility(View.VISIBLE);
                    if(MainCanvas.forChangeImgList.size()>=8) {
                        MainCanvas.img81.setImageURI(MainCanvas.forChangeImgList.get(0).uri);
                        MainCanvas.img82.setImageURI(MainCanvas.forChangeImgList.get(1).uri);
                        MainCanvas.img83.setImageURI(MainCanvas.forChangeImgList.get(2).uri);
                        MainCanvas.img84.setImageURI(MainCanvas.forChangeImgList.get(3).uri);
                        MainCanvas.img85.setImageURI(MainCanvas.forChangeImgList.get(4).uri);
                        MainCanvas.img86.setImageURI(MainCanvas.forChangeImgList.get(5).uri);
                        MainCanvas.img87.setImageURI(MainCanvas.forChangeImgList.get(6).uri);
                        MainCanvas.img88.setImageURI(MainCanvas.forChangeImgList.get(7).uri);
                    }
                    else{
                        int size=MainCanvas.forChangeImgList.size();
                        switch(MainCanvas.forChangeImgList.size()){
                            case 1:
                                MainCanvas.img81.setImageURI(MainCanvas.forChangeImgList.get(0).uri);
                                return;
                            case 2:
                                MainCanvas.img81.setImageURI(MainCanvas.forChangeImgList.get(0).uri);
                                MainCanvas.img82.setImageURI(MainCanvas.forChangeImgList.get(1).uri);
                                return;
                            case 3:
                                MainCanvas.img81.setImageURI(MainCanvas.forChangeImgList.get(0).uri);
                                MainCanvas.img82.setImageURI(MainCanvas.forChangeImgList.get(1).uri);
                                MainCanvas.img83.setImageURI(MainCanvas.forChangeImgList.get(2).uri);
                                return;
                            case 4:
                                MainCanvas.img81.setImageURI(MainCanvas.forChangeImgList.get(0).uri);
                                MainCanvas.img82.setImageURI(MainCanvas.forChangeImgList.get(1).uri);
                                MainCanvas.img83.setImageURI(MainCanvas.forChangeImgList.get(2).uri);
                                MainCanvas.img84.setImageURI(MainCanvas.forChangeImgList.get(3).uri);
                                return;
                            case 5:
                                MainCanvas.img81.setImageURI(MainCanvas.forChangeImgList.get(0).uri);
                                MainCanvas.img82.setImageURI(MainCanvas.forChangeImgList.get(1).uri);
                                MainCanvas.img83.setImageURI(MainCanvas.forChangeImgList.get(2).uri);
                                MainCanvas.img84.setImageURI(MainCanvas.forChangeImgList.get(3).uri);
                                MainCanvas.img85.setImageURI(MainCanvas.forChangeImgList.get(4).uri);
                                return;
                            case 6:
                                MainCanvas.img81.setImageURI(MainCanvas.forChangeImgList.get(0).uri);
                                MainCanvas.img82.setImageURI(MainCanvas.forChangeImgList.get(1).uri);
                                MainCanvas.img83.setImageURI(MainCanvas.forChangeImgList.get(2).uri);
                                MainCanvas.img84.setImageURI(MainCanvas.forChangeImgList.get(3).uri);
                                MainCanvas.img85.setImageURI(MainCanvas.forChangeImgList.get(4).uri);
                                MainCanvas.img86.setImageURI(MainCanvas.forChangeImgList.get(5).uri);
                                return;
                            case 7:
                                MainCanvas.img81.setImageURI(MainCanvas.forChangeImgList.get(0).uri);
                                MainCanvas.img82.setImageURI(MainCanvas.forChangeImgList.get(1).uri);
                                MainCanvas.img83.setImageURI(MainCanvas.forChangeImgList.get(2).uri);
                                MainCanvas.img84.setImageURI(MainCanvas.forChangeImgList.get(3).uri);
                                MainCanvas.img85.setImageURI(MainCanvas.forChangeImgList.get(4).uri);
                                MainCanvas.img86.setImageURI(MainCanvas.forChangeImgList.get(5).uri);
                                MainCanvas.img87.setImageURI(MainCanvas.forChangeImgList.get(6).uri);
                                return;
                        }

                    }
                    return;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

}
