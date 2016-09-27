package com.gsbina.android.demo.ta;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.SharedElementCallback;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.gsbina.android.demo.ta.value.ImageId;
import com.gsbina.android.demo.ta.value.ImageIds;
import com.gsbina.android.demo.ta.view.ImageAdapter;

import java.util.List;
import java.util.Map;

public class IndexActivity extends AppCompatActivity {

    public static final int REQUEST_IMAGE_DETAIL = 1;

    private int sharedImageId = ImageId.NO_ID;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ActivityCompat.setExitSharedElementCallback(this, exitTransitionCallback);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setAdapter(new ImageAdapter(this, ImageIds.buildImageIds()));
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
    }

    @Override
    public void onActivityReenter(int resultCode, Intent data) {
        if (resultCode != RESULT_OK) {
            return;
        }
        sharedImageId = ImageDetailActivity.getResultOfImageId(data);
    }


    private final SharedElementCallback exitTransitionCallback = new SharedElementCallback() {
        @Override
        public void onMapSharedElements(List<String> names, Map<String, View> sharedElements) {
            if (sharedImageId != ImageId.NO_ID) {
                ImageAdapter adapter = (ImageAdapter) recyclerView.getAdapter();
                int index = adapter.indexOf(sharedImageId);
                RecyclerView.ViewHolder viewHolder = recyclerView.findViewHolderForAdapterPosition(index);
                sharedElements.put(names.get(0), viewHolder.itemView);
                sharedImageId = ImageId.NO_ID;
            }
        }
    };
}
