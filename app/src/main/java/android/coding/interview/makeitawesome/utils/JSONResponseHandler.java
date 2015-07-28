package android.coding.interview.makeitawesome.utils;

/**
 * Created by pinghelram on 03/03/15.
 */

import android.coding.interview.makeitawesome.data.ImageData;
import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.impl.client.BasicResponseHandler;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * parse the results
 */
public class JSONResponseHandler implements ResponseHandler<ArrayList<ImageData>> {
    private static final String TAG = "JSONResponseHandler";

    private static final String ALBUMID = "albumId";
    //private static final String ID = "id";
    private static final String TITLE = "title";
    private static final String URL = "url";
    private static final String THUMBNAIL_URL = "thumbnailUrl";

    @Override
    public ArrayList<ImageData> handleResponse(HttpResponse response) throws ClientProtocolException, IOException {
        //QueryResult mQueryResult = new QueryResult();
        ArrayList<ImageData> result = new ArrayList<>();
        if (response.getStatusLine().getStatusCode() == 200){
            String JSONResponse = new BasicResponseHandler().handleResponse(response);
            try {
                JSONArray responseObject = (JSONArray) new JSONTokener(JSONResponse).nextValue();
                Log.v(TAG, "responseObject" + responseObject.toString());
                for (int i = 0; i < responseObject.length(); i++){
                    JSONObject albumObject = (JSONObject) responseObject.get(i);
                    ImageData imageData = new ImageData();
                    imageData.setTitle(albumObject.optString(TITLE));
                    imageData.setThumbnailUrl(albumObject.optString(THUMBNAIL_URL));
                    imageData.setUrl(albumObject.optString(URL));
                    result.add(imageData);

                }
                //mQueryResult.setPoints(result);
                Log.v(TAG, "got here");
                return result;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
