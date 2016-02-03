package android.coding.interview.makeitawesome;

import android.app.Application;
import android.coding.interview.makeitawesome.di.AppComponent;
import android.coding.interview.makeitawesome.di.AppModule;
import android.coding.interview.makeitawesome.di.DaggerAppComponent;

import com.squareup.leakcanary.LeakCanary;

/**
 * Created by Liang on 2016/1/30.
 */
public class App extends Application {
    private AppComponent mAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        LeakCanary.install(this);
        mAppComponent = DaggerAppComponent.builder().appModule(new AppModule(this)).build();
    }

    public AppComponent getAppComponent() {
        return mAppComponent;
    }

    public void setAppComponent(AppComponent appComponent) {
        this.mAppComponent = appComponent;
    }
}
