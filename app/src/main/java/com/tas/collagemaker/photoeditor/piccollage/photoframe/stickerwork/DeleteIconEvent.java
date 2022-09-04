package com.tas.collagemaker.photoeditor.piccollage.photoframe.stickerwork;


import android.view.MotionEvent;

/**
 * @author wupanjie
 */

public class DeleteIconEvent implements StickerIconEvent {
    public  static boolean flagdelete=false;
    public  static boolean flagdeletefree=false;
    @Override public void onActionDown(StickerView stickerView, MotionEvent event) {

    }

    @Override public void onActionMove(StickerView stickerView, MotionEvent event) {

    }

    @Override public void onActionUp(StickerView stickerView, MotionEvent event) {
        stickerView.removeCurrentSticker();
        flagdelete=true;
        flagdeletefree=true;

    }
}

