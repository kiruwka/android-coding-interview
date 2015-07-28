package android.coding.interview.makeitawesome.networking;

import android.coding.interview.makeitawesome.model.Album;

import java.util.List;

import retrofit.RetrofitError;

/**
 * Created by antonio on 26/07/15.
 */
public class AlbumEvent {
    private List<Album> albums;
    private RetrofitError error;

    public AlbumEvent(List<Album> albums) {
        this.albums = albums;
    }

    public AlbumEvent(RetrofitError error) {
        this.error = error;
    }

    public Exception getError() {
        return error;
    }

    public List<Album> getObject() {
        return albums;
    }
}
