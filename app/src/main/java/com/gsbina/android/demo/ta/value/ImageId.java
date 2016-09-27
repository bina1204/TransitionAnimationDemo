package com.gsbina.android.demo.ta.value;

import android.support.annotation.DrawableRes;

public class ImageId {

    public static final int NO_ID = -1;

    @DrawableRes
    private final int id;

    public ImageId(@DrawableRes int id) {
        this.id = id;
    }

    @DrawableRes
    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return String.valueOf(id);
    }
}
