package com.gsbina.android.demo.ta.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gsbina.android.demo.ta.ImageDetailActivity;
import com.gsbina.android.demo.ta.R;
import com.gsbina.android.demo.ta.value.ImageId;
import com.gsbina.android.demo.ta.value.ImageIds;

public class ImageAdapter extends Adapter<ImageViewHolder> {

    private final ImageIds images;

    private final LayoutInflater mInflater;

    public ImageAdapter(Context context, ImageIds images) {
        this.images = images;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.image_item, parent, false);
        return new ImageViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ImageViewHolder holder, int position) {
        final ImageId imageId = images.get(position);
        holder.imageView.setImageResource(imageId.getId());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImageDetailActivity.start(view.getContext(), imageId);
            }
        });
    }

    @Override
    public int getItemCount() {
        return images.size();
    }
}
