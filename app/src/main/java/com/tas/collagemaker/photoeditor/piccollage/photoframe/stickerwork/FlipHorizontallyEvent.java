package com.tas.collagemaker.photoeditor.piccollage.photoframe.stickerwork;


public class FlipHorizontallyEvent extends AbstractFlipEvent {

    @Override @StickerView.Flip protected int getFlipDirection() {
        return StickerView.FLIP_HORIZONTALLY;
    }
}
