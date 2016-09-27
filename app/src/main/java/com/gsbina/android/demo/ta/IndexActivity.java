package com.gsbina.android.demo.ta;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.gsbina.android.demo.ta.value.ImageIds;
import com.gsbina.android.demo.ta.view.ImageAdapter;

public class IndexActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setAdapter(new ImageAdapter(this, ImageIds.buildImageIds()));
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
    }
}
