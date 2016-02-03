package android.coding.interview.makeitawesome.domain;

import android.coding.interview.makeitawesome.domain.entity.Events;

import rx.Observable;

/**
 * Interface for implementing repository pattern
 */
public interface Repository {
    String KEY_EVENTS_UPCOMING_MOVIES = "upcoming_movies";
    String KEY_EVENTS_NOW_IN_THEATRES = "now_in_theatres";

    Observable<Events> getUpcomingMovies();

    Observable<Events> getNowShowingMovies(int areaId);
}
