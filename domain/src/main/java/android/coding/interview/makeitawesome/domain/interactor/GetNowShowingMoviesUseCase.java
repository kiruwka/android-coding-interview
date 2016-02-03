package android.coding.interview.makeitawesome.domain.interactor;

import android.coding.interview.makeitawesome.domain.entity.Events;

import rx.Observable;

/**
 * Interface to get movies are now showing in the theatres
 * <p>
 * Created by Liang on 2016/1/30.
 */
public interface GetNowShowingMoviesUseCase {
    /**
     * @param areaId Theatre Area ID
     * @return NowInTheatres Events Observable
     * @see <a href="http://www.finnkino.fi/XML">Finnkino Api</a>
     */
    Observable<Events> execute(int areaId);
}
