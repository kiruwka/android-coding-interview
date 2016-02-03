package android.coding.interview.makeitawesome.domain.interactor;

import android.coding.interview.makeitawesome.domain.Repository;

/**
 * Created by Liang on 2016/1/30.
 */
public class BaseUseCaseImpl {
    protected final Repository mRepository;

    public BaseUseCaseImpl(Repository mRepository) {
        this.mRepository = mRepository;
    }
}
