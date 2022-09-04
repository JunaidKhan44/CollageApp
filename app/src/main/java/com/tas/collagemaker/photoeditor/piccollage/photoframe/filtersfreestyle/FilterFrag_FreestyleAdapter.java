package com.tas.collagemaker.photoeditor.piccollage.photoframe.filtersfreestyle;

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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;


import com.tas.collagemaker.photoeditor.piccollage.photoframe.R;
import com.tas.collagemaker.photoeditor.piccollage.photoframe.otherclasses.FreeStyle;
import com.tas.collagemaker.photoeditor.piccollage.photoframe.stickerwork.Sticker;
import com.tas.collagemaker.photoeditor.piccollage.photoframe.stickerwork.TextSticker;
import com.tas.collagemaker.photoeditor.piccollage.photoframe.yourworks.YourWork;
import com.uvstudio.him.photofilterlibrary.PhotoFilter;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;

public class FilterFrag_FreestyleAdapter extends RecyclerView.Adapter<FilterFrag_FreestyleAdapter.ViewHolder> {


    public Context context;
    public ArrayList<FilterFrag_FreestyleModel>  modelList;
    private Bitmap bitmap1;
    private BitmapDrawable d;
    private PhotoFilter photoFilter;

    public FilterFrag_FreestyleAdapter(Context context, ArrayList<FilterFrag_FreestyleModel> modelList) {
        this.context = context;
        this.modelList = modelList;
        photoFilter=new PhotoFilter();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.filterfor_rowfree,null,false);
        return  new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {


        holder.filterImg.setImageResource(modelList.get(position).getImg());
        holder.number.setText(modelList.get(position).getText());
        
        holder.filterImg.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
            @Override
            public void onClick(View v) {

                switch(position){
                    case 0:
                        FreeStyle.stickerViewFree.removeAllStickers();
                        for (int i = 0; i < YourWork.imagesListfree.size(); i++) {

                            Drawable yourDrawable;
                            try {
                                InputStream inputStream = context.getContentResolver().openInputStream(YourWork.imagesListfree.get(i).uri);
                                yourDrawable = Drawable.createFromStream(inputStream, YourWork.imagesListfree.get(i).uri.toString());
                                FreeStyle.stickerViewFree.addSticker(
                                        new TextSticker(context)
                                                .setDrawable(yourDrawable)
                                                .setText("\n")
                                                .setMaxTextSize(14)
                                                .resizeText()
                                        , Sticker.Position.TOP);
                            } catch (FileNotFoundException e) {
                                yourDrawable =context.getResources().getDrawable(R.drawable.haizewang_23);
                            }
                        }
                        Toast.makeText(context, "Restored..", Toast.LENGTH_SHORT).show();
                        return;
                    case 1:
                        FreeStyle.stickerViewFree.removeAllStickers();
                        for (int i = 0; i < YourWork.imagesListfree.size(); i++) {
                            Drawable yourDrawable;
                            try {
                                InputStream inputStream = context.getContentResolver().openInputStream(YourWork.imagesListfree.get(i).uri);
                                yourDrawable = Drawable.createFromStream(inputStream, YourWork.imagesListfree.get(i).uri.toString());
                                bitmap1 = ((BitmapDrawable) yourDrawable).getBitmap();
                                d = new BitmapDrawable(context.getResources(), photoFilter.one(context.getApplicationContext(), bitmap1));
                                FreeStyle.stickerViewFree.addSticker(
                                        new TextSticker(context)
                                                .setDrawable(d)
                                                .setText("\n")
                                                .setMaxTextSize(14)
                                                .resizeText()
                                        , Sticker.Position.TOP);
                            } catch (FileNotFoundException e) {
                                yourDrawable =context.getResources().getDrawable(R.drawable.haizewang_23);
                            }
                        }
                        return;
                    case 2:
                        FreeStyle.stickerViewFree.removeAllStickers();
                        for (int i = 0; i < YourWork.imagesListfree.size(); i++) {
                            Drawable yourDrawable;
                            try {
                                InputStream inputStream = context.getContentResolver().openInputStream(YourWork.imagesListfree.get(i).uri);
                                yourDrawable = Drawable.createFromStream(inputStream, YourWork.imagesListfree.get(i).uri.toString());
                                bitmap1 = ((BitmapDrawable) yourDrawable).getBitmap();
                                d = new BitmapDrawable(context.getResources(), photoFilter.two(context.getApplicationContext(), bitmap1));
                                FreeStyle.stickerViewFree.addSticker(
                                        new TextSticker(context)
                                                .setDrawable(d)
                                                .setText("\n")
                                                .setMaxTextSize(14)
                                                .resizeText()
                                        , Sticker.Position.TOP);
                            } catch (FileNotFoundException e) {
                                yourDrawable =context.getResources().getDrawable(R.drawable.haizewang_23);
                            }
                        }
                        return;
                    case 3:
                        FreeStyle.stickerViewFree.removeAllStickers();
                        for (int i = 0; i < YourWork.imagesListfree.size(); i++) {
                            Drawable yourDrawable;
                            try {
                                InputStream inputStream = context.getContentResolver().openInputStream(YourWork.imagesListfree.get(i).uri);
                                yourDrawable = Drawable.createFromStream(inputStream, YourWork.imagesListfree.get(i).uri.toString());
                                bitmap1 = ((BitmapDrawable) yourDrawable).getBitmap();
                                d = new BitmapDrawable(context.getResources(), photoFilter.three(context.getApplicationContext(), bitmap1));
                                FreeStyle.stickerViewFree.addSticker(
                                        new TextSticker(context)
                                                .setDrawable(d)
                                                .setText("\n")
                                                .setMaxTextSize(14)
                                                .resizeText()
                                        , Sticker.Position.TOP);
                            } catch (FileNotFoundException e) {
                                yourDrawable =context.getResources().getDrawable(R.drawable.haizewang_23);
                            }
                        }
                        return;
                    case 4:
                        FreeStyle.stickerViewFree.removeAllStickers();
                        for (int i = 0; i < YourWork.imagesListfree.size(); i++) {
                            Drawable yourDrawable;
                            try {
                                InputStream inputStream = context.getContentResolver().openInputStream(YourWork.imagesListfree.get(i).uri);
                                yourDrawable = Drawable.createFromStream(inputStream, YourWork.imagesListfree.get(i).uri.toString());
                                bitmap1 = ((BitmapDrawable) yourDrawable).getBitmap();
                                d = new BitmapDrawable(context.getResources(), photoFilter.four(context.getApplicationContext(), bitmap1));
                                FreeStyle.stickerViewFree.addSticker(
                                        new TextSticker(context)
                                                .setDrawable(d)
                                                .setText("\n")
                                                .setMaxTextSize(14)
                                                .resizeText()
                                        , Sticker.Position.TOP);
                            } catch (FileNotFoundException e) {
                                yourDrawable =context.getResources().getDrawable(R.drawable.haizewang_23);
                            }
                        }
                        return;
                    case 5:
                        FreeStyle.stickerViewFree.removeAllStickers();
                        for (int i = 0; i < YourWork.imagesListfree.size(); i++) {
                            Drawable yourDrawable;
                            try {
                                InputStream inputStream = context.getContentResolver().openInputStream(YourWork.imagesListfree.get(i).uri);
                                yourDrawable = Drawable.createFromStream(inputStream, YourWork.imagesListfree.get(i).uri.toString());
                                bitmap1 = ((BitmapDrawable) yourDrawable).getBitmap();
                                d = new BitmapDrawable(context.getResources(), photoFilter.five(context.getApplicationContext(), bitmap1));
                                FreeStyle.stickerViewFree.addSticker(
                                        new TextSticker(context)
                                                .setDrawable(d)
                                                .setText("\n")
                                                .setMaxTextSize(14)
                                                .resizeText()
                                        , Sticker.Position.TOP);
                            } catch (FileNotFoundException e) {
                                yourDrawable =context.getResources().getDrawable(R.drawable.haizewang_23);
                            }
                        }
                        return;
                    case 6:
                        FreeStyle.stickerViewFree.removeAllStickers();
                        for (int i = 0; i < YourWork.imagesListfree.size(); i++) {
                            Drawable yourDrawable;
                            try {
                                InputStream inputStream = context.getContentResolver().openInputStream(YourWork.imagesListfree.get(i).uri);
                                yourDrawable = Drawable.createFromStream(inputStream, YourWork.imagesListfree.get(i).uri.toString());
                                bitmap1 = ((BitmapDrawable) yourDrawable).getBitmap();
                                d = new BitmapDrawable(context.getResources(), photoFilter.six(context.getApplicationContext(), bitmap1));
                                FreeStyle.stickerViewFree.addSticker(
                                        new TextSticker(context)
                                                .setDrawable(d)
                                                .setText("\n")
                                                .setMaxTextSize(14)
                                                .resizeText()
                                        , Sticker.Position.TOP);
                            } catch (FileNotFoundException e) {
                                yourDrawable =context.getResources().getDrawable(R.drawable.haizewang_23);
                            }
                        }
                        return;
                    case 7:
                        FreeStyle.stickerViewFree.removeAllStickers();
                        for (int i = 0; i < YourWork.imagesListfree.size(); i++) {
                            Drawable yourDrawable;
                            try {
                                InputStream inputStream = context.getContentResolver().openInputStream(YourWork.imagesListfree.get(i).uri);
                                yourDrawable = Drawable.createFromStream(inputStream, YourWork.imagesListfree.get(i).uri.toString());
                                bitmap1 = ((BitmapDrawable) yourDrawable).getBitmap();
                                d = new BitmapDrawable(context.getResources(), photoFilter.seven(context.getApplicationContext(), bitmap1));
                                FreeStyle.stickerViewFree.addSticker(
                                        new TextSticker(context)
                                                .setDrawable(d)
                                                .setText("\n")
                                                .setMaxTextSize(14)
                                                .resizeText()
                                        , Sticker.Position.TOP);
                            } catch (FileNotFoundException e) {
                                yourDrawable =context.getResources().getDrawable(R.drawable.haizewang_23);
                            }
                        }
                        return;
                    case 8:
                        FreeStyle.stickerViewFree.removeAllStickers();
                        for (int i = 0; i < YourWork.imagesListfree.size(); i++) {
                            Drawable yourDrawable;
                            try {
                                InputStream inputStream = context.getContentResolver().openInputStream(YourWork.imagesListfree.get(i).uri);
                                yourDrawable = Drawable.createFromStream(inputStream, YourWork.imagesListfree.get(i).uri.toString());
                                bitmap1 = ((BitmapDrawable) yourDrawable).getBitmap();
                                d = new BitmapDrawable(context.getResources(), photoFilter.eight(context.getApplicationContext(), bitmap1));
                                FreeStyle.stickerViewFree.addSticker(
                                        new TextSticker(context)
                                                .setDrawable(d)
                                                .setText("\n")
                                                .setMaxTextSize(14)
                                                .resizeText()
                                        , Sticker.Position.TOP);
                            } catch (FileNotFoundException e) {
                                yourDrawable =context.getResources().getDrawable(R.drawable.haizewang_23);
                            }
                        }
                        return;

                    default:
                        return;

                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView filterImg;
        public TextView number;

        public ViewHolder(View itemView) {
            super(itemView);
            filterImg = itemView.findViewById(R.id.filterimgfree);
            number = itemView.findViewById(R.id.filternumberfree);
        }
    }
}
