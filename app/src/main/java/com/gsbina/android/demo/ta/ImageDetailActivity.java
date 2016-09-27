package com.gsbina.android.demo.ta;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.gsbina.android.demo.ta.value.ImageId;
import com.gsbina.android.demo.ta.value.ImageIds;

public class ImageDetailActivity extends AppCompatActivity {

    private static final String EXTRA_FIRST_IMAGE_ID = "first_image_id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_detail);

        ImagePagerAdapter adapter = new ImagePagerAdapter(getSupportFragmentManager(), ImageIds.buildImageIds());

        ViewPager viewPager = (ViewPager) findViewById(R.id.container);
        viewPager.setAdapter(adapter);

        int firstImageId = getIntent().getIntExtra(EXTRA_FIRST_IMAGE_ID, 0);
        int index = adapter.indexOf(firstImageId);
        if (index >= 0) {
            viewPager.setCurrentItem(index);
        }
    }

    public static class ImageDetailFragment extends Fragment {

        private static final String ARG_IMAGE_ID = "image_id";

        public ImageDetailFragment() {
        }

        public static ImageDetailFragment newInstance(ImageId imageId) {
            ImageDetailFragment fragment = new ImageDetailFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_IMAGE_ID, imageId.getId());
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_detail, container, false);
            int imageId = getArguments().getInt(ARG_IMAGE_ID);
            ((ImageView) rootView.findViewById(R.id.image)).setImageResource(imageId);
            return rootView;
        }
    }

    public class ImagePagerAdapter extends FragmentPagerAdapter {

        private final ImageIds images;

        public ImagePagerAdapter(FragmentManager fm, ImageIds images) {
            super(fm);
            this.images = images;
        }

        @Override
        public Fragment getItem(int position) {
            return ImageDetailFragment.newInstance(images.get(position));
        }

        @Override
        public int getCount() {
            return images.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return String.valueOf(images.get(position).getId());
        }

        public int indexOf(int imageId) {
            return images.indexOf(imageId);
        }
    }

    public static void start(Context context, ImageId imageId) {
        Intent intent = new Intent(context, ImageDetailActivity.class);
        intent.putExtra(EXTRA_FIRST_IMAGE_ID, imageId.getId());
        context.startActivity(intent);
    }
}
