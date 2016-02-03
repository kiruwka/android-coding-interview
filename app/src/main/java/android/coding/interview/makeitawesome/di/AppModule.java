package android.coding.interview.makeitawesome.di;

import android.app.Application;
import android.coding.interview.makeitawesome.data.FinnkinoApi;
import android.coding.interview.makeitawesome.data.RepositoryImpl;
import android.coding.interview.makeitawesome.domain.Repository;

import javax.inject.Singleton;

import au.com.gridstone.rxstore.RxStore;
import au.com.gridstone.rxstore.converters.GsonConverter;
import dagger.Module;
import dagger.Provides;

/**
 * Created by Liang on 2016/1/31.
 */
@Module
public class AppModule {
    private final Application mApplication;

    public AppModule(Application application) {
        this.mApplication = application;
    }

    @Singleton
    @Provides
    public FinnkinoApi provideFinnkinoApi() {
        return FinnkinoApi.Factory.create();
    }

    @Singleton
    @Provides
    public RxStore provideRxStore() {
        return  RxStore.withContext(mApplication).using(new GsonConverter());
    }

    @Singleton
    @Provides
    public Repository provideRepository(RepositoryImpl repository) {
        return repository;
    }
}
