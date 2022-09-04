package com.tas.collagemaker.photoeditor.piccollage.photoframe.yourworks;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.tas.collagemaker.photoeditor.piccollage.photoframe.R;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Random;

public class YourWorkAdapter extends RecyclerView.Adapter<YourWorkAdapter.MyHolder> {

    public Context context;
    ArrayList<YourWorkModel> models;

    public YourWorkAdapter(Context context, ArrayList<YourWorkModel> models) {
        this.context = context;
        this.models = models;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.your_workitem, null);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {


        holder.imgview.setImageBitmap(models.get(position).getimgWork());
        holder.imgview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri myuri;

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    myuri= Uri.parse(models.get(position).getPath());
                    Log.d("mytag",myuri.toString());

                } else {
                    myuri = getImageUri(context, models.get(position).getimgWork());
                    Log.d("mytag",myuri.toString());

                }
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.setDataAndType(myuri, "image/jpeg*");
                try {
                    context.startActivity(intent);
                } catch (ActivityNotFoundException e) {
                    Toast.makeText(context, "No application found to open this file.", Toast.LENGTH_LONG).show();
                }
            }
        });


    }

    @Override
    public int getItemCount() {
        return models.size();
    }


    public class MyHolder extends RecyclerView.ViewHolder {

        ImageView imgview;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            this.imgview = itemView.findViewById(R.id.imagework);
        }
    }

    public Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
    }


    public Uri bitmapToUriConverter(Bitmap mBitmap) {
        Uri uri = null;
        try {
            final BitmapFactory.Options options = new BitmapFactory.Options();
            // Calculate inSampleSize
            options.inSampleSize = calculateInSampleSize(options, 100, 100);

            // Decode bitmap with inSampleSize set
            options.inJustDecodeBounds = false;
            Bitmap newBitmap = Bitmap.createScaledBitmap(mBitmap, 200, 200,
                    true);
            File file = new File(context.getApplicationContext().getFilesDir(), "Image"
                    + new Random().nextInt() + ".jpeg");
            FileOutputStream out = context.getApplicationContext().openFileOutput(file.getName(),
                    Context.MODE_WORLD_READABLE);
            newBitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
            out.flush();
            out.close();
            //get absolute path
            String realPath = file.getAbsolutePath();
            File f = new File(realPath);
            uri = Uri.fromFile(f);

        } catch (Exception e) {
            Log.e("Your Error Message", e.getMessage());
        }
        return uri;
    }

    public static int calculateInSampleSize(
            BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) >= reqHeight
                    && (halfWidth / inSampleSize) >= reqWidth) {
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }

}
