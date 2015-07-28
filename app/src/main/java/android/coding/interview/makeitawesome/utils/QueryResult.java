package android.coding.interview.makeitawesome.utils;

import android.coding.interview.makeitawesome.data.ImageData;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by pinghelram on 27/07/15.
 */
public class QueryResult {
    private ArrayList<ImageData> photos = new ArrayList<>();

    public ArrayList<ImageData> getphotos() {
        return photos;
    }

    public void setPoints(ArrayList<ImageData> photos) {
        this.photos = photos;
    }
}
