package android.coding.interview.makeitawesome;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * Created by pinghelram on 27/07/15.
 */
public class Picture extends Activity {
    ImageView myImage;
    public static final String IMAGEURL = "image_url";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
        myImage = (ImageView) findViewById(R.id.iv_image);

        Intent myIntent = getIntent();
        String url = myIntent.getStringExtra(IMAGEURL);

        Glide.with(this).load(url).into(myImage);
    }




}
