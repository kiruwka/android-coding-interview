package android.coding.interview.makeitawesome.domain.interactor;

import android.coding.interview.makeitawesome.domain.Repository;
import android.coding.interview.makeitawesome.domain.entity.Events;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by Liang on 2016/1/30.
 */
public class GetUpcomingMoviesUseCaseImpl extends BaseUseCaseImpl implements GetUpcomingMoviesUseCase {


    @Inject
    public GetUpcomingMoviesUseCaseImpl(Repository repository) {
        super(repository);
    }

    @Override
    public Observable<Events> execute() {
        return mRepository.getUpcomingMovies();
    }
}
