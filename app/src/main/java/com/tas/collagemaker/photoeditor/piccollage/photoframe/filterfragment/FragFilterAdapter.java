package com.tas.collagemaker.photoeditor.piccollage.photoframe.filterfragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tas.collagemaker.photoeditor.piccollage.photoframe.R;
import com.tas.collagemaker.photoeditor.piccollage.photoframe.common_util.ValidationUtility;
import com.tas.collagemaker.photoeditor.piccollage.photoframe.otherclasses.MainCanvas;

import com.uvstudio.him.photofilterlibrary.PhotoFilter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FragFilterAdapter extends RecyclerView.Adapter<FragFilterAdapter.ViewHolder> {

    public Context context;
    public ArrayList<FragFilterModel> fragFilterModels;
    PhotoFilter photoFilter;
    public Bitmap bitmap1,bitmap2,bitmap3,bitmap4,bitmap5,bitmap6,bitmap7,bitmap8;
    public Map<String,Drawable> drawableMap;



    public FragFilterAdapter(Context context, ArrayList<FragFilterModel> fragFilterModels) {
        this.context = context;
        this.fragFilterModels = fragFilterModels;
        this.photoFilter = new PhotoFilter();
        getMapList();

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.filterfor_row, null, false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.filterImg.setImageResource(fragFilterModels.get(position).getfilterImg());
        holder.number.setText(fragFilterModels.get(position).getName());

        holder.filterImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setfilterAuto(position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return fragFilterModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView filterImg;
        public TextView number;

        public ViewHolder(View itemView) {
            super(itemView);
            filterImg = itemView.findViewById(R.id.filterimg);
            number = itemView.findViewById(R.id.filternumber);
        }
    }

    //auto filter changer function
    void setfilterAuto(int position) {
        getMapList();
        resetBitmap();
        if (MainCanvas.v1.getVisibility() == View.VISIBLE && ValidationUtility.isExists(drawableMap)
                && Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1 ) {

            bitmap1 = ((BitmapDrawable) drawableMap.get("single")).getBitmap();
            switch (position) {
                case 0:

                    MainCanvas.singleimg.setImageBitmap(bitmap1);
                    return;
                case 1:

                      MainCanvas.singleimg.setImageBitmap(photoFilter.one(context.getApplicationContext(), bitmap1));
                    return;
                case 2:

                    MainCanvas.singleimg.setImageBitmap(photoFilter.two(context.getApplicationContext(), bitmap1));
                    return;
                case 3:

                    MainCanvas.singleimg.setImageBitmap(photoFilter.three(context.getApplicationContext(), bitmap1));
                    return;
                case 4:

                    MainCanvas.singleimg.setImageBitmap(photoFilter.four(context.getApplicationContext(), bitmap1));
                    return;
                case 5:

                     MainCanvas.singleimg.setImageBitmap(photoFilter.five(context.getApplicationContext(), bitmap1));
                    return;
                case 6:

                     MainCanvas.singleimg.setImageBitmap(photoFilter.six(context.getApplicationContext(), bitmap1));
                    return;
                case 7:

                    MainCanvas.singleimg.setImageBitmap(photoFilter.seven(context.getApplicationContext(), bitmap1));
                    return;
                case 8:

                    MainCanvas.singleimg.setImageBitmap(photoFilter.eight(context.getApplicationContext(), bitmap1));
                    return;
            }
        }
        else if (MainCanvas.v2.getVisibility() == View.VISIBLE && ValidationUtility.isExists(drawableMap)
                && Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1 ) {

            bitmap1 = ((BitmapDrawable)  drawableMap.get("double1")).getBitmap();
            bitmap2 = ((BitmapDrawable)  drawableMap.get("double2")).getBitmap();
            switch (position) {
                case 0:

                    MainCanvas.zimg1.setImageBitmap(bitmap1);
                    MainCanvas.zimg2.setImageBitmap(bitmap2);

                    return;
                case 1:

                    MainCanvas.zimg1.setImageBitmap(photoFilter.one(context.getApplicationContext(), bitmap1));
                    MainCanvas.zimg2.setImageBitmap(photoFilter.one(context.getApplicationContext(), bitmap2));

                    return;
                case 2:

                    MainCanvas.zimg1.setImageBitmap(photoFilter.two(context.getApplicationContext(), bitmap1));
                    MainCanvas.zimg2.setImageBitmap(photoFilter.two(context.getApplicationContext(), bitmap2));

                    return;
                case 3:

                    MainCanvas.zimg1.setImageBitmap(photoFilter.three(context.getApplicationContext(), bitmap1));
                    MainCanvas.zimg2.setImageBitmap(photoFilter.three(context.getApplicationContext(), bitmap2));

                    return;
                case 4:

                    MainCanvas.zimg1.setImageBitmap(photoFilter.four(context.getApplicationContext(), bitmap1));
                    MainCanvas.zimg2.setImageBitmap(photoFilter.four(context.getApplicationContext(), bitmap2));

                    return;
                case 5:

                    MainCanvas.zimg1.setImageBitmap(photoFilter.five(context.getApplicationContext(), bitmap1));
                    MainCanvas.zimg2.setImageBitmap(photoFilter.five(context.getApplicationContext(), bitmap2));

                    return;
                case 6:

                    MainCanvas.zimg1.setImageBitmap(photoFilter.six(context.getApplicationContext(), bitmap1));
                    MainCanvas.zimg2.setImageBitmap(photoFilter.six(context.getApplicationContext(), bitmap2));

                    return;
                case 7:

                    MainCanvas.zimg1.setImageBitmap(photoFilter.seven(context.getApplicationContext(), bitmap1));
                    MainCanvas.zimg2.setImageBitmap(photoFilter.seven(context.getApplicationContext(), bitmap2));

                    return;
                case 8:

                    MainCanvas.zimg1.setImageBitmap(photoFilter.eight(context.getApplicationContext(), bitmap1));
                    MainCanvas.zimg2.setImageBitmap(photoFilter.eight(context.getApplicationContext(), bitmap2));

                    return;


            }
        } else if (MainCanvas.v3.getVisibility() == View.VISIBLE && ValidationUtility.isExists(drawableMap)
                && Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1 ) {

            bitmap1 = ((BitmapDrawable) drawableMap.get("double31")).getBitmap();
            bitmap2 = ((BitmapDrawable) drawableMap.get("double32")).getBitmap();
            bitmap3 = ((BitmapDrawable)  drawableMap.get("double33")).getBitmap();
            switch (position) {
                case 0:

                     MainCanvas.img31.setImageBitmap(bitmap1);
                     MainCanvas.img32.setImageBitmap(bitmap2);
                     MainCanvas.img33.setImageBitmap(bitmap3);

                    return;
                case 1:

                    MainCanvas.img31.setImageBitmap(photoFilter.one(context.getApplicationContext(), bitmap1));
                    MainCanvas.img32.setImageBitmap(photoFilter.one(context.getApplicationContext(), bitmap2));
                    MainCanvas.img33.setImageBitmap(photoFilter.one(context.getApplicationContext(), bitmap3));

                    return;
                case 2:

                    MainCanvas.img31.setImageBitmap(photoFilter.two(context.getApplicationContext(), bitmap1));
                    MainCanvas.img32.setImageBitmap(photoFilter.two(context.getApplicationContext(), bitmap2));
                    MainCanvas.img33.setImageBitmap(photoFilter.two(context.getApplicationContext(), bitmap3));

                    return;
                case 3:

                    MainCanvas.img31.setImageBitmap(photoFilter.three(context.getApplicationContext(), bitmap1));
                    MainCanvas.img32.setImageBitmap(photoFilter.three(context.getApplicationContext(), bitmap2));
                    MainCanvas.img33.setImageBitmap(photoFilter.three(context.getApplicationContext(), bitmap3));

                    return;
                case 4:

                    MainCanvas.img31.setImageBitmap(photoFilter.four(context.getApplicationContext(), bitmap1));
                    MainCanvas.img32.setImageBitmap(photoFilter.four(context.getApplicationContext(), bitmap2));
                    MainCanvas.img33.setImageBitmap(photoFilter.four(context.getApplicationContext(), bitmap3));

                    return;
                case  5:

                    MainCanvas.img31.setImageBitmap(photoFilter.five(context.getApplicationContext(), bitmap1));
                    MainCanvas.img32.setImageBitmap(photoFilter.five(context.getApplicationContext(), bitmap2));
                    MainCanvas.img33.setImageBitmap(photoFilter.five(context.getApplicationContext(), bitmap3));

                    return;
                case 6:

                    MainCanvas.img31.setImageBitmap(photoFilter.six(context.getApplicationContext(), bitmap1));
                    MainCanvas.img32.setImageBitmap(photoFilter.six(context.getApplicationContext(), bitmap2));
                    MainCanvas.img33.setImageBitmap(photoFilter.six(context.getApplicationContext(), bitmap3));

                    return;
                case 7:

                    MainCanvas.img31.setImageBitmap(photoFilter.seven(context.getApplicationContext(), bitmap1));
                    MainCanvas.img32.setImageBitmap(photoFilter.seven(context.getApplicationContext(), bitmap2));
                    MainCanvas.img33.setImageBitmap(photoFilter.seven(context.getApplicationContext(), bitmap3));

                    return;
                case 8:

                    MainCanvas.img31.setImageBitmap(photoFilter.eight(context.getApplicationContext(), bitmap1));
                    MainCanvas.img32.setImageBitmap(photoFilter.eight(context.getApplicationContext(), bitmap2));
                    MainCanvas.img33.setImageBitmap(photoFilter.eight(context.getApplicationContext(), bitmap3));

                    return;


            }
        } else if (MainCanvas.v4.getVisibility() == View.VISIBLE && ValidationUtility.isExists(drawableMap)
                && Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1 ) {

            bitmap1 = ((BitmapDrawable) drawableMap.get("double41")).getBitmap();
            bitmap2 = ((BitmapDrawable)  drawableMap.get("double42")).getBitmap();
            bitmap3 = ((BitmapDrawable)  drawableMap.get("double43")).getBitmap();
            bitmap4 = ((BitmapDrawable)  drawableMap.get("double44")).getBitmap();
            switch (position) {
                case 0:

                    MainCanvas.zimg21.setImageBitmap(bitmap1);
                    MainCanvas.zimg22.setImageBitmap(bitmap2);
                    MainCanvas.zimg23.setImageBitmap(bitmap3);
                    MainCanvas.zimg24.setImageBitmap(bitmap4);

                    return;
                case 1:

                    MainCanvas.zimg21.setImageBitmap(photoFilter.one(context.getApplicationContext(), bitmap1));
                    MainCanvas.zimg22.setImageBitmap(photoFilter.one(context.getApplicationContext(), bitmap2));
                    MainCanvas.zimg23.setImageBitmap(photoFilter.one(context.getApplicationContext(), bitmap3));
                    MainCanvas.zimg24.setImageBitmap(photoFilter.one(context.getApplicationContext(), bitmap4));

                    return;
                case 2:

                    MainCanvas.zimg21.setImageBitmap(photoFilter.two(context.getApplicationContext(), bitmap1));
                    MainCanvas.zimg22.setImageBitmap(photoFilter.two(context.getApplicationContext(), bitmap2));
                    MainCanvas.zimg23.setImageBitmap(photoFilter.two(context.getApplicationContext(), bitmap3));
                    MainCanvas.zimg24.setImageBitmap(photoFilter.two(context.getApplicationContext(), bitmap4));

                    return;
                case 3:

                    MainCanvas.zimg21.setImageBitmap(photoFilter.three(context.getApplicationContext(), bitmap1));
                    MainCanvas.zimg22.setImageBitmap(photoFilter.three(context.getApplicationContext(), bitmap2));
                    MainCanvas.zimg23.setImageBitmap(photoFilter.three(context.getApplicationContext(), bitmap3));
                    MainCanvas.zimg24.setImageBitmap(photoFilter.three(context.getApplicationContext(), bitmap4));

                    return;
                case 4:

                    MainCanvas.zimg21.setImageBitmap(photoFilter.four(context.getApplicationContext(), bitmap1));
                    MainCanvas.zimg22.setImageBitmap(photoFilter.four(context.getApplicationContext(), bitmap2));
                    MainCanvas.zimg23.setImageBitmap(photoFilter.four(context.getApplicationContext(), bitmap3));
                    MainCanvas.zimg24.setImageBitmap(photoFilter.four(context.getApplicationContext(), bitmap4));

                    return;
                case 5:

                    MainCanvas.zimg21.setImageBitmap(photoFilter.five(context.getApplicationContext(), bitmap1));
                    MainCanvas.zimg22.setImageBitmap(photoFilter.five(context.getApplicationContext(), bitmap2));
                    MainCanvas.zimg23.setImageBitmap(photoFilter.five(context.getApplicationContext(), bitmap3));
                    MainCanvas.zimg24.setImageBitmap(photoFilter.five(context.getApplicationContext(), bitmap4));

                    return;
                case 6:

                    MainCanvas.zimg21.setImageBitmap(photoFilter.six(context.getApplicationContext(), bitmap1));
                    MainCanvas.zimg22.setImageBitmap(photoFilter.six(context.getApplicationContext(), bitmap2));
                    MainCanvas.zimg23.setImageBitmap(photoFilter.six(context.getApplicationContext(), bitmap3));
                    MainCanvas.zimg24.setImageBitmap(photoFilter.six(context.getApplicationContext(), bitmap4));

                    return;

                case 7:

                    MainCanvas.zimg21.setImageBitmap(photoFilter.seven(context.getApplicationContext(), bitmap1));
                    MainCanvas.zimg22.setImageBitmap(photoFilter.seven(context.getApplicationContext(), bitmap2));
                    MainCanvas.zimg23.setImageBitmap(photoFilter.seven(context.getApplicationContext(), bitmap3));
                    MainCanvas.zimg24.setImageBitmap(photoFilter.seven(context.getApplicationContext(), bitmap4));

                    return;

                case 8:

                    MainCanvas.zimg21.setImageBitmap(photoFilter.eight(context.getApplicationContext(), bitmap1));
                    MainCanvas.zimg22.setImageBitmap(photoFilter.eight(context.getApplicationContext(), bitmap2));
                    MainCanvas.zimg23.setImageBitmap(photoFilter.eight(context.getApplicationContext(), bitmap3));
                    MainCanvas.zimg24.setImageBitmap(photoFilter.eight(context.getApplicationContext(), bitmap4));

                    return;


            }
        } else if (MainCanvas.v5.getVisibility() == View.VISIBLE && ValidationUtility.isExists(drawableMap)
                && Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1 ) {

            bitmap1 = ((BitmapDrawable)  drawableMap.get("double51")).getBitmap();
            bitmap2 = ((BitmapDrawable) drawableMap.get("double52")).getBitmap();
            bitmap3 = ((BitmapDrawable) drawableMap.get("double53")).getBitmap();
            bitmap4 = ((BitmapDrawable)  drawableMap.get("double54")).getBitmap();
            bitmap5 = ((BitmapDrawable)  drawableMap.get("double55")).getBitmap();
            switch (position) {
                case 0:

                     MainCanvas.img51.setImageBitmap(bitmap1);
                     MainCanvas.img52.setImageBitmap(bitmap2);
                     MainCanvas.img53.setImageBitmap(bitmap3);
                     MainCanvas.img54.setImageBitmap(bitmap4);
                     MainCanvas.img55.setImageBitmap(bitmap5);

                    return;
                case 1:

                    MainCanvas.img51.setImageBitmap(photoFilter.one(context.getApplicationContext(), bitmap1));
                    MainCanvas.img52.setImageBitmap(photoFilter.one(context.getApplicationContext(), bitmap2));
                    MainCanvas.img53.setImageBitmap(photoFilter.one(context.getApplicationContext(), bitmap3));
                    MainCanvas.img54.setImageBitmap(photoFilter.one(context.getApplicationContext(), bitmap4));
                    MainCanvas.img55.setImageBitmap(photoFilter.one(context.getApplicationContext(), bitmap5));
                    return;
                case 2:

                    MainCanvas.img51.setImageBitmap(photoFilter.two(context.getApplicationContext(), bitmap1));
                    MainCanvas.img52.setImageBitmap(photoFilter.two(context.getApplicationContext(), bitmap2));
                    MainCanvas.img53.setImageBitmap(photoFilter.two(context.getApplicationContext(), bitmap3));
                    MainCanvas.img54.setImageBitmap(photoFilter.two(context.getApplicationContext(), bitmap4));
                    MainCanvas.img55.setImageBitmap(photoFilter.two(context.getApplicationContext(), bitmap5));

                    return;
                case 3:

                    MainCanvas.img51.setImageBitmap(photoFilter.three(context.getApplicationContext(), bitmap1));
                    MainCanvas.img52.setImageBitmap(photoFilter.three(context.getApplicationContext(), bitmap2));
                    MainCanvas.img53.setImageBitmap(photoFilter.three(context.getApplicationContext(), bitmap3));
                    MainCanvas.img54.setImageBitmap(photoFilter.three(context.getApplicationContext(), bitmap4));
                    MainCanvas.img55.setImageBitmap(photoFilter.three(context.getApplicationContext(), bitmap5));


                    return;
                case 4:

                    MainCanvas.img51.setImageBitmap(photoFilter.four(context.getApplicationContext(), bitmap1));
                    MainCanvas.img52.setImageBitmap(photoFilter.four(context.getApplicationContext(), bitmap2));
                    MainCanvas.img53.setImageBitmap(photoFilter.four(context.getApplicationContext(), bitmap3));
                    MainCanvas.img54.setImageBitmap(photoFilter.four(context.getApplicationContext(), bitmap4));
                    MainCanvas.img55.setImageBitmap(photoFilter.four(context.getApplicationContext(), bitmap5));

                    return;
                case 5:

                    MainCanvas.img51.setImageBitmap(photoFilter.five(context.getApplicationContext(), bitmap1));
                    MainCanvas.img52.setImageBitmap(photoFilter.five(context.getApplicationContext(), bitmap2));
                    MainCanvas.img53.setImageBitmap(photoFilter.five(context.getApplicationContext(), bitmap3));
                    MainCanvas.img54.setImageBitmap(photoFilter.five(context.getApplicationContext(), bitmap4));
                    MainCanvas.img55.setImageBitmap(photoFilter.five(context.getApplicationContext(), bitmap5));


                    return;
                case  6:

                    MainCanvas.img51.setImageBitmap(photoFilter.six(context.getApplicationContext(), bitmap1));
                    MainCanvas.img52.setImageBitmap(photoFilter.six(context.getApplicationContext(), bitmap2));
                    MainCanvas.img53.setImageBitmap(photoFilter.six(context.getApplicationContext(), bitmap3));
                    MainCanvas.img54.setImageBitmap(photoFilter.six(context.getApplicationContext(), bitmap4));
                    MainCanvas.img55.setImageBitmap(photoFilter.six(context.getApplicationContext(), bitmap5));


                    return;
                case 7:

                    MainCanvas.img51.setImageBitmap(photoFilter.seven(context.getApplicationContext(), bitmap1));
                    MainCanvas.img52.setImageBitmap(photoFilter.seven(context.getApplicationContext(), bitmap2));
                    MainCanvas.img53.setImageBitmap(photoFilter.seven(context.getApplicationContext(), bitmap3));
                    MainCanvas.img54.setImageBitmap(photoFilter.seven(context.getApplicationContext(), bitmap4));
                    MainCanvas.img55.setImageBitmap(photoFilter.seven(context.getApplicationContext(), bitmap5));

                    return;
                case 8:

                    MainCanvas.img51.setImageBitmap(photoFilter.eight(context.getApplicationContext(), bitmap1));
                    MainCanvas.img52.setImageBitmap(photoFilter.eight(context.getApplicationContext(), bitmap2));
                    MainCanvas.img53.setImageBitmap(photoFilter.eight(context.getApplicationContext(), bitmap3));
                    MainCanvas.img54.setImageBitmap(photoFilter.eight(context.getApplicationContext(), bitmap4));
                    MainCanvas.img55.setImageBitmap(photoFilter.eight(context.getApplicationContext(), bitmap5));

                    return;


            }
        } else if (MainCanvas.v6.getVisibility() == View.VISIBLE  && ValidationUtility.isExists(drawableMap)
                && Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1 ) {

            bitmap1 =((BitmapDrawable)  drawableMap.get("double61")).getBitmap();
            bitmap2 =((BitmapDrawable)  drawableMap.get("double62")).getBitmap();
            bitmap3 =((BitmapDrawable)  drawableMap.get("double63")).getBitmap();
            bitmap4 = ((BitmapDrawable)  drawableMap.get("double64")).getBitmap();
            bitmap5 = ((BitmapDrawable)  drawableMap.get("double65")).getBitmap();
            bitmap6 = ((BitmapDrawable)  drawableMap.get("double66")).getBitmap();

            switch (position) {
                case 0:

                    MainCanvas.img61.setImageBitmap(bitmap1);
                    MainCanvas.img62.setImageBitmap(bitmap2);
                    MainCanvas.img63.setImageBitmap(bitmap3);
                    MainCanvas.img64.setImageBitmap(bitmap4);
                    MainCanvas.img65.setImageBitmap(bitmap5);
                    MainCanvas.img66.setImageBitmap(bitmap6);


                    return;
                case 1:

                    MainCanvas.img61.setImageBitmap(photoFilter.one(context.getApplicationContext(), bitmap1));
                    MainCanvas.img62.setImageBitmap(photoFilter.one(context.getApplicationContext(), bitmap2));
                    MainCanvas.img63.setImageBitmap(photoFilter.one(context.getApplicationContext(), bitmap3));
                    MainCanvas.img64.setImageBitmap(photoFilter.one(context.getApplicationContext(), bitmap4));
                    MainCanvas.img65.setImageBitmap(photoFilter.one(context.getApplicationContext(), bitmap5));
                    MainCanvas.img66.setImageBitmap(photoFilter.one(context.getApplicationContext(), bitmap6));

                    return;
                case 2:
                    MainCanvas.img61.setImageBitmap(photoFilter.two(context.getApplicationContext(), bitmap1));
                    MainCanvas.img62.setImageBitmap(photoFilter.two(context.getApplicationContext(), bitmap2));
                    MainCanvas.img63.setImageBitmap(photoFilter.two(context.getApplicationContext(), bitmap3));
                    MainCanvas.img64.setImageBitmap(photoFilter.two(context.getApplicationContext(), bitmap4));
                    MainCanvas.img65.setImageBitmap(photoFilter.two(context.getApplicationContext(), bitmap5));
                    MainCanvas.img66.setImageBitmap(photoFilter.two(context.getApplicationContext(), bitmap6));

                    return;
                case 3:
                    MainCanvas.img61.setImageBitmap(photoFilter.three(context.getApplicationContext(), bitmap1));
                    MainCanvas.img62.setImageBitmap(photoFilter.three(context.getApplicationContext(), bitmap2));
                    MainCanvas.img63.setImageBitmap(photoFilter.three(context.getApplicationContext(), bitmap3));
                    MainCanvas.img64.setImageBitmap(photoFilter.three(context.getApplicationContext(), bitmap4));
                    MainCanvas.img65.setImageBitmap(photoFilter.three(context.getApplicationContext(), bitmap5));
                    MainCanvas.img66.setImageBitmap(photoFilter.three(context.getApplicationContext(), bitmap6));

                    return;
                case 4:
                    MainCanvas.img61.setImageBitmap(photoFilter.four(context.getApplicationContext(), bitmap1));
                    MainCanvas.img62.setImageBitmap(photoFilter.four(context.getApplicationContext(), bitmap2));
                    MainCanvas.img63.setImageBitmap(photoFilter.four(context.getApplicationContext(), bitmap3));
                    MainCanvas.img64.setImageBitmap(photoFilter.four(context.getApplicationContext(), bitmap4));
                    MainCanvas.img65.setImageBitmap(photoFilter.four(context.getApplicationContext(), bitmap5));
                    MainCanvas.img66.setImageBitmap(photoFilter.four(context.getApplicationContext(), bitmap6));

                    return;
                case 5:
                    MainCanvas.img61.setImageBitmap(photoFilter.five(context.getApplicationContext(), bitmap1));
                    MainCanvas.img62.setImageBitmap(photoFilter.five(context.getApplicationContext(), bitmap2));
                    MainCanvas.img63.setImageBitmap(photoFilter.five(context.getApplicationContext(), bitmap3));
                    MainCanvas.img64.setImageBitmap(photoFilter.five(context.getApplicationContext(), bitmap4));
                    MainCanvas.img65.setImageBitmap(photoFilter.five(context.getApplicationContext(), bitmap5));
                    MainCanvas.img66.setImageBitmap(photoFilter.five(context.getApplicationContext(), bitmap6));

                    return;
                case 6:
                    MainCanvas.img61.setImageBitmap(photoFilter.six(context.getApplicationContext(), bitmap1));
                    MainCanvas.img62.setImageBitmap(photoFilter.six(context.getApplicationContext(), bitmap2));
                    MainCanvas.img63.setImageBitmap(photoFilter.six(context.getApplicationContext(), bitmap3));
                    MainCanvas.img64.setImageBitmap(photoFilter.six(context.getApplicationContext(), bitmap4));
                    MainCanvas.img65.setImageBitmap(photoFilter.six(context.getApplicationContext(), bitmap5));
                    MainCanvas.img66.setImageBitmap(photoFilter.six(context.getApplicationContext(), bitmap6));

                    return;
                case 7:
                    MainCanvas.img61.setImageBitmap(photoFilter.seven(context.getApplicationContext(), bitmap1));
                    MainCanvas.img62.setImageBitmap(photoFilter.seven(context.getApplicationContext(), bitmap2));
                    MainCanvas.img63.setImageBitmap(photoFilter.seven(context.getApplicationContext(), bitmap3));
                    MainCanvas.img64.setImageBitmap(photoFilter.seven(context.getApplicationContext(), bitmap4));
                    MainCanvas.img65.setImageBitmap(photoFilter.seven(context.getApplicationContext(), bitmap5));
                    MainCanvas.img66.setImageBitmap(photoFilter.seven(context.getApplicationContext(), bitmap6));

                    return;
                case 8:

                    MainCanvas.img61.setImageBitmap(photoFilter.eight(context.getApplicationContext(), bitmap1));
                    MainCanvas.img62.setImageBitmap(photoFilter.eight(context.getApplicationContext(), bitmap2));
                    MainCanvas.img63.setImageBitmap(photoFilter.eight(context.getApplicationContext(), bitmap3));
                    MainCanvas.img64.setImageBitmap(photoFilter.eight(context.getApplicationContext(), bitmap4));
                    MainCanvas.img65.setImageBitmap(photoFilter.eight(context.getApplicationContext(), bitmap5));
                    MainCanvas.img66.setImageBitmap(photoFilter.eight(context.getApplicationContext(), bitmap6));

                    return;


            }
        } else if (MainCanvas.v7.getVisibility() == View.VISIBLE && ValidationUtility.isExists(drawableMap)
                && Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1 ) {

            bitmap1 = ((BitmapDrawable)  drawableMap.get("double71")).getBitmap();
            bitmap2 =  ((BitmapDrawable)  drawableMap.get("double72")).getBitmap();
            bitmap3 =  ((BitmapDrawable)  drawableMap.get("double73")).getBitmap();
            bitmap4 = ((BitmapDrawable)  drawableMap.get("double74")).getBitmap();
            bitmap5 =  ((BitmapDrawable)  drawableMap.get("double75")).getBitmap();
            bitmap6 =  ((BitmapDrawable)  drawableMap.get("double76")).getBitmap();
            bitmap7 =  ((BitmapDrawable)  drawableMap.get("double77")).getBitmap();

            switch (position) {
                case 0:

                    MainCanvas.img71.setImageBitmap(bitmap1);
                    MainCanvas.img72.setImageBitmap(bitmap2);
                    MainCanvas.img73.setImageBitmap(bitmap3);
                    MainCanvas.img74.setImageBitmap(bitmap4);
                    MainCanvas.img75.setImageBitmap(bitmap5);
                    MainCanvas.img76.setImageBitmap(bitmap6);
                    MainCanvas.img77.setImageBitmap(bitmap7);


                    return;
                case 1:

                        MainCanvas.img71.setImageBitmap(photoFilter.one(context.getApplicationContext(), bitmap1));
                        MainCanvas.img72.setImageBitmap(photoFilter.one(context.getApplicationContext(), bitmap2));
                        MainCanvas.img73.setImageBitmap(photoFilter.one(context.getApplicationContext(), bitmap3));
                        MainCanvas.img74.setImageBitmap(photoFilter.one(context.getApplicationContext(), bitmap4));
                        MainCanvas.img75.setImageBitmap(photoFilter.one(context.getApplicationContext(), bitmap5));
                        MainCanvas.img76.setImageBitmap(photoFilter.one(context.getApplicationContext(), bitmap6));
                        MainCanvas.img77.setImageBitmap(photoFilter.one(context.getApplicationContext(), bitmap7));

                    return;
                case 2:

                        MainCanvas.img71.setImageBitmap(photoFilter.two(context.getApplicationContext(), bitmap1));
                        MainCanvas.img72.setImageBitmap(photoFilter.two(context.getApplicationContext(), bitmap2));
                        MainCanvas.img73.setImageBitmap(photoFilter.two(context.getApplicationContext(), bitmap3));
                        MainCanvas.img74.setImageBitmap(photoFilter.two(context.getApplicationContext(), bitmap4));
                        MainCanvas.img75.setImageBitmap(photoFilter.two(context.getApplicationContext(), bitmap5));
                        MainCanvas.img76.setImageBitmap(photoFilter.two(context.getApplicationContext(), bitmap6));
                        MainCanvas.img77.setImageBitmap(photoFilter.two(context.getApplicationContext(), bitmap7));

                    return;
                case 3:

                        MainCanvas.img71.setImageBitmap(photoFilter.three(context.getApplicationContext(), bitmap1));
                        MainCanvas.img72.setImageBitmap(photoFilter.three(context.getApplicationContext(), bitmap2));
                        MainCanvas.img73.setImageBitmap(photoFilter.three(context.getApplicationContext(), bitmap3));
                        MainCanvas.img74.setImageBitmap(photoFilter.three(context.getApplicationContext(), bitmap4));
                        MainCanvas.img75.setImageBitmap(photoFilter.three(context.getApplicationContext(), bitmap5));
                        MainCanvas.img76.setImageBitmap(photoFilter.three(context.getApplicationContext(), bitmap6));
                        MainCanvas.img77.setImageBitmap(photoFilter.three(context.getApplicationContext(), bitmap7));

                    return;
                case 4:

                        MainCanvas.img71.setImageBitmap(photoFilter.four(context.getApplicationContext(), bitmap1));
                        MainCanvas.img72.setImageBitmap(photoFilter.four(context.getApplicationContext(), bitmap2));
                        MainCanvas.img73.setImageBitmap(photoFilter.four(context.getApplicationContext(), bitmap3));
                        MainCanvas.img74.setImageBitmap(photoFilter.four(context.getApplicationContext(), bitmap4));
                        MainCanvas.img75.setImageBitmap(photoFilter.four(context.getApplicationContext(), bitmap5));
                        MainCanvas.img76.setImageBitmap(photoFilter.four(context.getApplicationContext(), bitmap6));
                        MainCanvas.img77.setImageBitmap(photoFilter.four(context.getApplicationContext(), bitmap7));

                    return;
                case 5:

                        MainCanvas.img71.setImageBitmap(photoFilter.five(context.getApplicationContext(), bitmap1));
                        MainCanvas.img72.setImageBitmap(photoFilter.five(context.getApplicationContext(), bitmap2));
                        MainCanvas.img73.setImageBitmap(photoFilter.five(context.getApplicationContext(), bitmap3));
                        MainCanvas.img74.setImageBitmap(photoFilter.five(context.getApplicationContext(), bitmap4));
                        MainCanvas.img75.setImageBitmap(photoFilter.five(context.getApplicationContext(), bitmap5));
                        MainCanvas.img76.setImageBitmap(photoFilter.five(context.getApplicationContext(), bitmap6));
                        MainCanvas.img77.setImageBitmap(photoFilter.five(context.getApplicationContext(), bitmap7));

                    return;
                case 6:

                        MainCanvas.img71.setImageBitmap(photoFilter.six(context.getApplicationContext(), bitmap1));
                        MainCanvas.img72.setImageBitmap(photoFilter.six(context.getApplicationContext(), bitmap2));
                        MainCanvas.img73.setImageBitmap(photoFilter.six(context.getApplicationContext(), bitmap3));
                        MainCanvas.img74.setImageBitmap(photoFilter.six(context.getApplicationContext(), bitmap4));
                        MainCanvas.img75.setImageBitmap(photoFilter.six(context.getApplicationContext(), bitmap5));
                        MainCanvas.img76.setImageBitmap(photoFilter.six(context.getApplicationContext(), bitmap6));
                        MainCanvas.img77.setImageBitmap(photoFilter.six(context.getApplicationContext(), bitmap7));

                    return;
                case 7:
                         MainCanvas.img71.setImageBitmap(photoFilter.seven(context.getApplicationContext(), bitmap1));
                        MainCanvas.img72.setImageBitmap(photoFilter.seven(context.getApplicationContext(), bitmap2));
                        MainCanvas.img73.setImageBitmap(photoFilter.seven(context.getApplicationContext(), bitmap3));
                        MainCanvas.img74.setImageBitmap(photoFilter.seven(context.getApplicationContext(), bitmap4));
                        MainCanvas.img75.setImageBitmap(photoFilter.seven(context.getApplicationContext(), bitmap5));
                        MainCanvas.img76.setImageBitmap(photoFilter.seven(context.getApplicationContext(), bitmap6));
                        MainCanvas.img77.setImageBitmap(photoFilter.seven(context.getApplicationContext(), bitmap7));

                    return;
                case 8:

                        MainCanvas.img71.setImageBitmap(photoFilter.eight(context.getApplicationContext(), bitmap1));
                        MainCanvas.img72.setImageBitmap(photoFilter.eight(context.getApplicationContext(), bitmap2));
                        MainCanvas.img73.setImageBitmap(photoFilter.eight(context.getApplicationContext(), bitmap3));
                        MainCanvas.img74.setImageBitmap(photoFilter.eight(context.getApplicationContext(), bitmap4));
                        MainCanvas.img75.setImageBitmap(photoFilter.eight(context.getApplicationContext(), bitmap5));
                        MainCanvas.img76.setImageBitmap(photoFilter.eight(context.getApplicationContext(), bitmap6));
                        MainCanvas.img77.setImageBitmap(photoFilter.eight(context.getApplicationContext(), bitmap7));

                    return;


            }
        } else if (MainCanvas.v8.getVisibility() == View.VISIBLE  && ValidationUtility.isExists(drawableMap)
                && Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1 ) {

            bitmap1 = ((BitmapDrawable)  drawableMap.get("double81")).getBitmap();
            bitmap2 = ((BitmapDrawable)  drawableMap.get("double82")).getBitmap();
            bitmap3 =((BitmapDrawable)  drawableMap.get("double83")).getBitmap();
            bitmap4 = ((BitmapDrawable)  drawableMap.get("double84")).getBitmap();
            bitmap5 = ((BitmapDrawable)  drawableMap.get("double85")).getBitmap();
            bitmap6 = ((BitmapDrawable)  drawableMap.get("double86")).getBitmap();
            bitmap7 =((BitmapDrawable)  drawableMap.get("double87")).getBitmap();
            bitmap8 = ((BitmapDrawable)  drawableMap.get("double88")).getBitmap();

            switch (position) {
                case 0:

                        MainCanvas.img81.setImageBitmap(bitmap1);
                        MainCanvas.img82.setImageBitmap(bitmap2);
                        MainCanvas.img83.setImageBitmap(bitmap3);
                        MainCanvas.img84.setImageBitmap(bitmap4);
                        MainCanvas.img85.setImageBitmap(bitmap5);
                        MainCanvas.img86.setImageBitmap(bitmap6);
                        MainCanvas.img87.setImageBitmap(bitmap7);
                        MainCanvas.img88.setImageBitmap(bitmap8);


                    return;
                case 1:

                        MainCanvas.img81.setImageBitmap(photoFilter.one(context.getApplicationContext(), bitmap1));
                        MainCanvas.img82.setImageBitmap(photoFilter.one(context.getApplicationContext(), bitmap2));
                        MainCanvas.img83.setImageBitmap(photoFilter.one(context.getApplicationContext(), bitmap3));
                        MainCanvas.img84.setImageBitmap(photoFilter.one(context.getApplicationContext(), bitmap4));
                        MainCanvas.img85.setImageBitmap(photoFilter.one(context.getApplicationContext(), bitmap5));
                        MainCanvas.img86.setImageBitmap(photoFilter.one(context.getApplicationContext(), bitmap6));
                        MainCanvas.img87.setImageBitmap(photoFilter.one(context.getApplicationContext(), bitmap7));
                        MainCanvas.img88.setImageBitmap(photoFilter.one(context.getApplicationContext(), bitmap8));

                    return;
                case 2:

                        MainCanvas.img81.setImageBitmap(photoFilter.two(context.getApplicationContext(), bitmap1));
                        MainCanvas.img82.setImageBitmap(photoFilter.two(context.getApplicationContext(), bitmap2));
                        MainCanvas.img83.setImageBitmap(photoFilter.two(context.getApplicationContext(), bitmap3));
                        MainCanvas.img84.setImageBitmap(photoFilter.two(context.getApplicationContext(), bitmap4));
                        MainCanvas.img85.setImageBitmap(photoFilter.two(context.getApplicationContext(), bitmap5));
                        MainCanvas.img86.setImageBitmap(photoFilter.two(context.getApplicationContext(), bitmap6));
                        MainCanvas.img87.setImageBitmap(photoFilter.two(context.getApplicationContext(), bitmap7));
                        MainCanvas.img88.setImageBitmap(photoFilter.two(context.getApplicationContext(), bitmap8));

                    return;
                case 3:

                        MainCanvas.img81.setImageBitmap(photoFilter.three(context.getApplicationContext(), bitmap1));
                        MainCanvas.img82.setImageBitmap(photoFilter.three(context.getApplicationContext(), bitmap2));
                        MainCanvas.img83.setImageBitmap(photoFilter.three(context.getApplicationContext(), bitmap3));
                        MainCanvas.img84.setImageBitmap(photoFilter.three(context.getApplicationContext(), bitmap4));
                        MainCanvas.img85.setImageBitmap(photoFilter.three(context.getApplicationContext(), bitmap5));
                        MainCanvas.img86.setImageBitmap(photoFilter.three(context.getApplicationContext(), bitmap6));
                        MainCanvas.img87.setImageBitmap(photoFilter.three(context.getApplicationContext(), bitmap7));
                        MainCanvas.img88.setImageBitmap(photoFilter.three(context.getApplicationContext(), bitmap8));

                    return;
                case 4:

                        MainCanvas.img81.setImageBitmap(photoFilter.four(context.getApplicationContext(), bitmap1));
                        MainCanvas.img82.setImageBitmap(photoFilter.four(context.getApplicationContext(), bitmap2));
                        MainCanvas.img83.setImageBitmap(photoFilter.four(context.getApplicationContext(), bitmap3));
                        MainCanvas.img84.setImageBitmap(photoFilter.four(context.getApplicationContext(), bitmap4));
                        MainCanvas.img85.setImageBitmap(photoFilter.four(context.getApplicationContext(), bitmap5));
                        MainCanvas.img86.setImageBitmap(photoFilter.four(context.getApplicationContext(), bitmap6));
                        MainCanvas.img87.setImageBitmap(photoFilter.four(context.getApplicationContext(), bitmap7));
                        MainCanvas.img88.setImageBitmap(photoFilter.four(context.getApplicationContext(), bitmap8));

                    return;
                case 5:

                        MainCanvas.img81.setImageBitmap(photoFilter.five(context.getApplicationContext(), bitmap1));
                        MainCanvas.img82.setImageBitmap(photoFilter.five(context.getApplicationContext(), bitmap2));
                        MainCanvas.img83.setImageBitmap(photoFilter.five(context.getApplicationContext(), bitmap3));
                        MainCanvas.img84.setImageBitmap(photoFilter.five(context.getApplicationContext(), bitmap4));
                        MainCanvas.img85.setImageBitmap(photoFilter.five(context.getApplicationContext(), bitmap5));
                        MainCanvas.img86.setImageBitmap(photoFilter.five(context.getApplicationContext(), bitmap6));
                        MainCanvas.img87.setImageBitmap(photoFilter.five(context.getApplicationContext(), bitmap7));
                        MainCanvas.img88.setImageBitmap(photoFilter.five(context.getApplicationContext(), bitmap8));

                    return;
                case 6:

                        MainCanvas.img81.setImageBitmap(photoFilter.six(context.getApplicationContext(), bitmap1));
                        MainCanvas.img82.setImageBitmap(photoFilter.six(context.getApplicationContext(), bitmap2));
                        MainCanvas.img83.setImageBitmap(photoFilter.six(context.getApplicationContext(), bitmap3));
                        MainCanvas.img84.setImageBitmap(photoFilter.six(context.getApplicationContext(), bitmap4));
                        MainCanvas.img85.setImageBitmap(photoFilter.six(context.getApplicationContext(), bitmap5));
                        MainCanvas.img86.setImageBitmap(photoFilter.six(context.getApplicationContext(), bitmap6));
                        MainCanvas.img87.setImageBitmap(photoFilter.six(context.getApplicationContext(), bitmap7));
                        MainCanvas.img88.setImageBitmap(photoFilter.six(context.getApplicationContext(), bitmap8));

                    return;
                case 7:

                        MainCanvas.img81.setImageBitmap(photoFilter.seven(context.getApplicationContext(), bitmap1));
                        MainCanvas.img82.setImageBitmap(photoFilter.seven(context.getApplicationContext(), bitmap2));
                        MainCanvas.img83.setImageBitmap(photoFilter.seven(context.getApplicationContext(), bitmap3));
                        MainCanvas.img84.setImageBitmap(photoFilter.seven(context.getApplicationContext(), bitmap4));
                        MainCanvas.img85.setImageBitmap(photoFilter.seven(context.getApplicationContext(), bitmap5));
                        MainCanvas.img86.setImageBitmap(photoFilter.seven(context.getApplicationContext(), bitmap6));
                        MainCanvas.img87.setImageBitmap(photoFilter.seven(context.getApplicationContext(), bitmap7));
                        MainCanvas.img88.setImageBitmap(photoFilter.seven(context.getApplicationContext(), bitmap8));

                    return;
                case 8:

                        MainCanvas.img81.setImageBitmap(photoFilter.eight(context.getApplicationContext(), bitmap1));
                        MainCanvas.img82.setImageBitmap(photoFilter.eight(context.getApplicationContext(), bitmap2));
                        MainCanvas.img83.setImageBitmap(photoFilter.eight(context.getApplicationContext(), bitmap3));
                        MainCanvas.img84.setImageBitmap(photoFilter.eight(context.getApplicationContext(), bitmap4));
                        MainCanvas.img85.setImageBitmap(photoFilter.eight(context.getApplicationContext(), bitmap5));
                        MainCanvas.img86.setImageBitmap(photoFilter.eight(context.getApplicationContext(), bitmap6));
                        MainCanvas.img87.setImageBitmap(photoFilter.eight(context.getApplicationContext(), bitmap7));
                        MainCanvas.img88.setImageBitmap(photoFilter.eight(context.getApplicationContext(), bitmap8));

                    return;
            }
        }
    }

    private void resetBitmap() {
        bitmap1=null;
        bitmap2=null;
        bitmap3=null;
        bitmap4=null;
        bitmap5=null;
        bitmap6=null;
        bitmap7=null;
        bitmap8=null;
    }

    void getMapList(){
        drawableMap=new HashMap<String,Drawable>();
        drawableMap.put("single",MainCanvas.singleimg.getDrawable());
        drawableMap.put("double1",MainCanvas.zimg1.getDrawable());
        drawableMap.put("double2",MainCanvas.zimg2.getDrawable());
        drawableMap.put("double31",MainCanvas.img31.getDrawable());
        drawableMap.put("double32",MainCanvas.img32.getDrawable());
        drawableMap.put("double33",MainCanvas.img33.getDrawable());

        drawableMap.put("double41",MainCanvas.zimg21.getDrawable());
        drawableMap.put("double42",MainCanvas.zimg22.getDrawable());
        drawableMap.put("double43",MainCanvas.zimg23.getDrawable());
        drawableMap.put("double44",MainCanvas.zimg24.getDrawable());

        drawableMap.put("double51",MainCanvas.img51.getDrawable());
        drawableMap.put("double52",MainCanvas.img52.getDrawable());
        drawableMap.put("double53",MainCanvas.img53.getDrawable());
        drawableMap.put("double54",MainCanvas.img54.getDrawable());
        drawableMap.put("double55",MainCanvas.img55.getDrawable());

        drawableMap.put("double61",MainCanvas.img61.getDrawable());
        drawableMap.put("double62",MainCanvas.img62.getDrawable());
        drawableMap.put("double63",MainCanvas.img63.getDrawable());
        drawableMap.put("double64",MainCanvas.img64.getDrawable());
        drawableMap.put("double65",MainCanvas.img65.getDrawable());
        drawableMap.put("double66",MainCanvas.img66.getDrawable());

        drawableMap.put("double71",MainCanvas.img71.getDrawable());
        drawableMap.put("double72",MainCanvas.img72.getDrawable());
        drawableMap.put("double73",MainCanvas.img73.getDrawable());
        drawableMap.put("double74",MainCanvas.img74.getDrawable());
        drawableMap.put("double75",MainCanvas.img75.getDrawable());
        drawableMap.put("double76",MainCanvas.img76.getDrawable());
        drawableMap.put("double77",MainCanvas.img77.getDrawable());


        drawableMap.put( "double81" , MainCanvas.img81.getDrawable());
        drawableMap.put( "double82" , MainCanvas.img82.getDrawable());
        drawableMap.put( "double83" , MainCanvas.img83.getDrawable());
        drawableMap.put( "double84" , MainCanvas.img84.getDrawable());
        drawableMap.put( "double85" , MainCanvas.img85.getDrawable());
        drawableMap.put( "double86" , MainCanvas.img86.getDrawable());
        drawableMap.put( "double87" , MainCanvas.img87.getDrawable());
        drawableMap.put( "double88" , MainCanvas.img88.getDrawable());
    }
}
