package com.gsbina.android.demo.ta;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.SharedElementCallback;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;

import com.gsbina.android.demo.ta.value.ImageId;
import com.gsbina.android.demo.ta.value.ImageIds;

import java.util.List;
import java.util.Map;

public class ImageDetailActivity extends AppCompatActivity {

    private static final String EXTRA_FIRST_IMAGE_ID = "first_image_id";

    private ViewPager viewPager;
    private int firstImageId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ActivityCompat.postponeEnterTransition(this);
        ActivityCompat.setEnterSharedElementCallback(this, enterTransitionCallback);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_detail);

        ImagePagerAdapter adapter = new ImagePagerAdapter(getSupportFragmentManager(), ImageIds.buildImageIds());

        viewPager = (ViewPager) findViewById(R.id.container);
        viewPager.setAdapter(adapter);

        firstImageId = getIntent().getIntExtra(EXTRA_FIRST_IMAGE_ID, 0);
        int index = adapter.indexOf(firstImageId);
        if (index >= 0) {
            viewPager.setCurrentItem(index);
        }

        viewPager.getViewTreeObserver().addOnGlobalLayoutListener(pagerlayoutlistener);
    }

    private final ViewTreeObserver.OnGlobalLayoutListener pagerlayoutlistener = new ViewTreeObserver.OnGlobalLayoutListener() {
        @Override
        public void onGlobalLayout() {
            viewPager.getViewTreeObserver().removeOnGlobalLayoutListener(pagerlayoutlistener);
            ActivityCompat.startPostponedEnterTransition(ImageDetailActivity.this);
        }
    };

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

        private int containerId;

        private final ImageIds images;
        private final FragmentManager fragmentManager;

        public ImagePagerAdapter(FragmentManager fm, ImageIds images) {
            super(fm);
            this.images = images;
            this.fragmentManager = fm;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            containerId = container.getId();
            return super.instantiateItem(container, position);
        }

        @Override
        public Fragment getItem(int position) {
            String tag = makeFragmentName(containerId, getItemId(position));
            Fragment fragment = fragmentManager.findFragmentByTag(tag);
            return fragment != null
                    ? fragment
                    : ImageDetailFragment.newInstance(images.get(position));
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

        public View findView(int imageId) {
            int index = indexOf(imageId);
            return index >= 0
                    ? getItem(index).getView()
                    : null;
        }

        private String makeFragmentName(int viewId, long id) {
            return "android:switcher:" + viewId + ":" + id;
        }
    }

    private final SharedElementCallback enterTransitionCallback = new SharedElementCallback() {
        @Override
        public void onMapSharedElements(List<String> names, Map<String, View> sharedElements) {
            View view = null;

            if (viewPager.getChildCount() > 0) {
                view =  ((ImagePagerAdapter) viewPager.getAdapter()).findView(firstImageId);
            }

            if (view != null) {
                sharedElements.put(names.get(0), view.findViewById(R.id.image));
            }
        }
    };

    public static Intent createIntent(Context context, ImageId imageId) {
        Intent intent = new Intent(context, ImageDetailActivity.class);
        intent.putExtra(EXTRA_FIRST_IMAGE_ID, imageId.getId());
        return intent;
    }
}
