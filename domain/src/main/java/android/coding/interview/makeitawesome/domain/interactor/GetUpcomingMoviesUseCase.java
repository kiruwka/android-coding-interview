package android.coding.interview.makeitawesome.domain.interactor;

import android.coding.interview.makeitawesome.domain.entity.Events;

import rx.Observable;


/**
 * Interface to get upcoming movie events
 */
public interface GetUpcomingMoviesUseCase {
    /**
     * @return ComingSoon Events Observable
     * @see <a href="http://www.finnkino.fi/XML">Finnkino Api</a>
     */
    Observable<Events> execute();
}
