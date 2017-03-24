package com.vpaliy.mvp.view.wrapper;


import android.os.Bundle;
import android.widget.ImageView;

public class TransitionWrapper {

    private final Bundle extras;
    private final ImageView image;

    private TransitionWrapper(Bundle extras, ImageView image) {
        this.image=image;
        this.extras=extras;
    }

    public static TransitionWrapper build(Bundle extras, ImageView image) {
        return new TransitionWrapper(extras,image);
    }

    public Bundle getExtras() {
        return extras;
    }

    public ImageView getImage() {
        return image;
    }
}
