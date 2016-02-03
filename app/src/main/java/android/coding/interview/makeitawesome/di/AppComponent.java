package android.coding.interview.makeitawesome.di;

import android.coding.interview.makeitawesome.domain.Repository;

import javax.inject.Singleton;

import au.com.gridstone.rxstore.RxStore;
import dagger.Component;

/**
 * Created by Liang on 2016/1/31.
 */
@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {
    Repository repository();
    RxStore rxStore();
}
