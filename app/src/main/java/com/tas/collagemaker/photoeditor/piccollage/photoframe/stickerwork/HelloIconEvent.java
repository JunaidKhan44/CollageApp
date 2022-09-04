package com.tas.collagemaker.photoeditor.piccollage.photoframe.stickerwork;

import android.view.MotionEvent;
import android.widget.Toast;

public class HelloIconEvent implements StickerIconEvent{
    @Override public void onActionDown(StickerView stickerView, MotionEvent event) {

    }

    @Override public void onActionMove(StickerView stickerView, MotionEvent event) {

    }

    @Override public void onActionUp(StickerView stickerView, MotionEvent event) {
        Toast.makeText(stickerView.getContext(), "Sticker Locked", Toast.LENGTH_SHORT).show();
        stickerView.setLocked(true);

    }
}