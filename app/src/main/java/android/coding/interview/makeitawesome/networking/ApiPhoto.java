package android.coding.interview.makeitawesome.networking;

import android.coding.interview.makeitawesome.model.Album;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;

/**
 * Created by antonio on 24/07/15.
 */
public interface ApiPhoto {

    @GET("/")
    public void listPhotos(Callback<List<Album>> callback);

}
