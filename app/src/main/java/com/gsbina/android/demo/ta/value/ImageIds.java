package com.gsbina.android.demo.ta.value;

import com.gsbina.android.demo.ta.R;

import java.util.ArrayList;

public class ImageIds extends ArrayList<ImageId> {

    public int indexOf(int imageId) {
        for (int i = 0; i < this.size(); i++) {
            if (get(i).getId() == imageId) {
                return i;
            }
        }

        return ImageId.NO_ID;
    }

    public static ImageIds buildImageIds() {
        ImageIds ids = new ImageIds();
        ids.add(new ImageId(R.drawable.image1));
        ids.add(new ImageId(R.drawable.image2));
        ids.add(new ImageId(R.drawable.image3));
        ids.add(new ImageId(R.drawable.image4));
        ids.add(new ImageId(R.drawable.image5));
        ids.add(new ImageId(R.drawable.image6));
        ids.add(new ImageId(R.drawable.image7));
        ids.add(new ImageId(R.drawable.image8));
        ids.add(new ImageId(R.drawable.image9));
        ids.add(new ImageId(R.drawable.image10));
        ids.add(new ImageId(R.drawable.image11));
        ids.add(new ImageId(R.drawable.image12));
        ids.add(new ImageId(R.drawable.image13));
        return ids;
    }
}
