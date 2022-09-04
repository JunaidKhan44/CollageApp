package com.tas.collagemaker.photoeditor.piccollage.photoframe.stickerwork;


import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;


/**
 * @author wupanjie
 */
public class DrawableSticker extends Sticker {

    private Drawable drawable;
    private Rect realBounds;

    public DrawableSticker(Drawable drawable) {
        this.drawable = drawable;
        realBounds = new Rect(0, 0, (int) getWidth(), getHeight());
    }

    @NonNull
    @Override public Drawable getDrawable() {
        return drawable;
    }

    @Override public DrawableSticker setDrawable(@NonNull Drawable drawable) {
        this.drawable = drawable;
        return this;
    }

    @Override public void draw(@NonNull Canvas canvas) {
        canvas.save();
        canvas.concat(getMatrix());
        drawable.setBounds(realBounds);
        drawable.draw(canvas);
        canvas.restore();
    }

    @NonNull
    @Override public DrawableSticker setAlpha(@IntRange(from = 0, to = 255) int alpha) {
        drawable.setAlpha(alpha);
        return this;
    }

    @Override public float getWidth() {
        return drawable.getIntrinsicWidth();
    }

    @Override public int getHeight() {
        return drawable.getIntrinsicHeight();
    }

    @Override public void release() {
        super.release();
        if (drawable != null) {
            drawable = null;
        }
    }
}

