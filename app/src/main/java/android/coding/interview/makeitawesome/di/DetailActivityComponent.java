package android.coding.interview.makeitawesome.di;

import android.coding.interview.makeitawesome.DetailActivity;

import dagger.Component;

/**
 * Created by Liang on 2016/2/1.
 */
@Component(dependencies = AppComponent.class)
@PerActivity
public interface DetailActivityComponent {
    void inject(DetailActivity activity);
}
