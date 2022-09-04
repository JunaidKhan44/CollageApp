package com.tas.collagemaker.photoeditor.piccollage.photoframe.stickerwork;

import android.view.MotionEvent;

public class ZoomIconEvent implements StickerIconEvent {
    @Override public void onActionDown(StickerView stickerView, MotionEvent event) {

    }

    @Override public void onActionMove(StickerView stickerView, MotionEvent event) {
        stickerView.zoomAndRotateCurrentSticker(event);
    }

    @Override public void onActionUp(StickerView stickerView, MotionEvent event) {
        if (stickerView.getOnStickerOperationListener() != null) {
            stickerView.getOnStickerOperationListener()
                    .onStickerZoomFinished(stickerView.getCurrentSticker());
        }
    }

}
