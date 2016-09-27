package com.gsbina.android.demo.ta.view;

import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.widget.ImageView;

import com.gsbina.android.demo.ta.R;

public class ImageViewHolder extends ViewHolder {

    public final ImageView imageView;

    public ImageViewHolder(View itemView) {
        super(itemView);
        imageView = (ImageView) itemView.findViewById(R.id.image);
    }
}
