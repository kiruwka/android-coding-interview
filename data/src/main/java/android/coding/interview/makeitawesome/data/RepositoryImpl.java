package android.coding.interview.makeitawesome.data;

import android.coding.interview.makeitawesome.domain.Repository;
import android.coding.interview.makeitawesome.domain.entity.Events;

import javax.inject.Inject;

import au.com.gridstone.rxstore.RxStore;
import rx.Observable;

/**
 * Simple implementation of repository pattern, it caches data fetched from Internet for "offline" usage
 */
public class RepositoryImpl implements Repository {
    private final FinnkinoApi mFinnkinoApi;
    private final RxStore rxStore;


    @Inject
    public RepositoryImpl(FinnkinoApi mFinnkinoApi, RxStore rxStore) {
        this.mFinnkinoApi = mFinnkinoApi;
        this.rxStore = rxStore;
    }

    @Override
    public Observable<Events> getUpcomingMovies() {
        return mFinnkinoApi
                .getEvents(FinnkinoApi.ListType.ComingSoon, FinnkinoApi.AreaID.Helsinki)
                .flatMap(events -> rxStore.put(KEY_EVENTS_UPCOMING_MOVIES, events))
                .onErrorResumeNext(error -> rxStore.get(KEY_EVENTS_UPCOMING_MOVIES, Events.class));
    }

    @Override
    public Observable<Events> getNowShowingMovies(int areaId) {
        return mFinnkinoApi
                .getEvents(FinnkinoApi.ListType.NowInThreatres, areaId)
                .flatMap(events -> rxStore.put(KEY_EVENTS_NOW_IN_THEATRES, events))
                .onErrorResumeNext(error -> rxStore.get(KEY_EVENTS_NOW_IN_THEATRES, Events.class));
    }
}
