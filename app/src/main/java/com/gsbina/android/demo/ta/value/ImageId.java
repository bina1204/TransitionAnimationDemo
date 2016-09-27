package com.gsbina.android.demo.ta.value;

import android.support.annotation.DrawableRes;

public class ImageId {

    @DrawableRes
    private final int id;

    public ImageId(@DrawableRes int id) {
        this.id = id;
    }

    @DrawableRes
    public int getId() {
        return id;
    }
}
