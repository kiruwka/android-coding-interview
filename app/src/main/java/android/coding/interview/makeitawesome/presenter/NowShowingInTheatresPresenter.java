package android.coding.interview.makeitawesome.presenter;

import android.coding.interview.makeitawesome.base.presenter.BaseRxLcePresenter;
import android.coding.interview.makeitawesome.data.FinnkinoApi;
import android.coding.interview.makeitawesome.domain.entity.Event;
import android.coding.interview.makeitawesome.domain.entity.Events;
import android.coding.interview.makeitawesome.domain.interactor.GetNowShowingMoviesUseCase;
import android.coding.interview.makeitawesome.fragment.NowShowingInTheatresView;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by Liang on 2016/1/31.
 */
public class NowShowingInTheatresPresenter extends BaseRxLcePresenter<NowShowingInTheatresView, List<Event>> {
    private final GetNowShowingMoviesUseCase mGetNowShowingMoviesUseCase;

    @Inject
    public NowShowingInTheatresPresenter(GetNowShowingMoviesUseCase getUpcomingMoviesUseCase) {
        mGetNowShowingMoviesUseCase = getUpcomingMoviesUseCase;
    }

    public void getUpComingMovies(boolean pullToRefresh) {
        subscribe(mGetNowShowingMoviesUseCase.execute(FinnkinoApi.AreaID.Helsinki).map(Events::getEvents), pullToRefresh);
    }
}
