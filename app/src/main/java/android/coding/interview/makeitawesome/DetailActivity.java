package android.coding.interview.makeitawesome;

import android.app.Activity;
import android.coding.interview.makeitawesome.di.DaggerDetailActivityComponent;
import android.coding.interview.makeitawesome.di.DetailActivityComponent;
import android.coding.interview.makeitawesome.domain.entity.Events;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import au.com.gridstone.rxstore.RxStore;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 *Simple Detail View of a Finnkio movie event, not all the information is showed.
 */
public class DetailActivity extends AppCompatActivity {

    private static final String TAG = DetailActivity.class.getSimpleName();
    private static final String TRANSITION_NAME_COVER = "cover";
    private static final String KEY = "key";
    private static final String POSITION = "position";
    private ImageView mCoverImageView;
    private TextView mTitle;
    private TextView mSynopsis;
    private TextView mEventLink;

    @Inject
    RxStore mRxStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        injectDependencies();
        initViews();

        String key = getIntent().getExtras().getString(KEY);
        int position = getIntent().getExtras().getInt(POSITION, 0);
        Log.d(TAG, "key: " + key);
        Log.d(TAG, "position: " + position);
        if (key == null)
            onBackPressed();
        ViewCompat.setTransitionName(mCoverImageView, TRANSITION_NAME_COVER);
        mRxStore.get(key, Events.class)
                .map(events -> events.getEvents().get(position))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(event -> {
                    Log.d(TAG, "event: " + event.getTitle());
                    Picasso.with(DetailActivity.this)
                            .load(event.getImages().getEventMediumImagePortrait())
                            .fit()
                            .centerCrop()
                            .into(mCoverImageView);
                    mTitle.setText(event.getTitle());
                    mSynopsis.setText(event.getSynopsis());
                    mEventLink.setText(event.getEventURL());
                    mSynopsis.setVisibility(View.VISIBLE);
                    mEventLink.setVisibility(View.VISIBLE);
                }, Throwable::printStackTrace);
    }

    public static void launch(Activity activity, View cover, String key, int position) {
        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(activity,
                Pair.create(cover, TRANSITION_NAME_COVER));
        Intent intent = new Intent(activity, DetailActivity.class);
        intent.putExtra(KEY, key);
        intent.putExtra(POSITION, position);
        ActivityCompat.startActivity(activity, intent, options.toBundle());
    }

    private void initViews() {
        mTitle = (TextView) findViewById(R.id.activity_detail_title);
        mSynopsis = (TextView) findViewById(R.id.activity_detail_synopsis);
        mEventLink = (TextView) findViewById(R.id.activity_detail_event_link);
        mCoverImageView = (ImageView) findViewById(R.id.item_movie_cover);
    }

    private void injectDependencies() {
        App app = (App) getApplication();
        DetailActivityComponent component = DaggerDetailActivityComponent
                .builder()
                .appComponent(app.getAppComponent())
                .build();
        component.inject(this);
    }
}
