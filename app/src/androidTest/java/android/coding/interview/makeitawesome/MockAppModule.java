package android.coding.interview.makeitawesome;

import android.app.Application;
import android.coding.interview.makeitawesome.data.RepositoryImpl;
import android.coding.interview.makeitawesome.di.AppModule;
import android.coding.interview.makeitawesome.domain.Repository;

/**
 * Mock module to facilitate testing
 *
 * Created by liang on 01/02/16.
 */
public class MockAppModule extends AppModule {

    private Repository mRepository;

    public MockAppModule(Application app, Repository repository) {
        super(app);
        mRepository = repository;
    }

    @Override
    public Repository provideRepository(RepositoryImpl repository) {
        return mRepository;
    }
}
