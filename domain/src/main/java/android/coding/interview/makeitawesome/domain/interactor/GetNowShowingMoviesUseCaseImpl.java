package android.coding.interview.makeitawesome.domain.interactor;

import android.coding.interview.makeitawesome.domain.Repository;
import android.coding.interview.makeitawesome.domain.entity.Events;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by Liang on 2016/1/30.
 */
public class GetNowShowingMoviesUseCaseImpl extends BaseUseCaseImpl implements GetNowShowingMoviesUseCase {

    @Inject
    public GetNowShowingMoviesUseCaseImpl(Repository mRepository) {
        super(mRepository);
    }

    @Override
    public Observable<Events> execute(int areaId) {
        return mRepository.getNowShowingMovies(areaId);
    }
}
