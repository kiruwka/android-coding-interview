package android.coding.interview.makeitawesome.di;

import android.coding.interview.makeitawesome.domain.interactor.GetNowShowingMoviesUseCase;
import android.coding.interview.makeitawesome.domain.interactor.GetNowShowingMoviesUseCaseImpl;
import android.coding.interview.makeitawesome.domain.interactor.GetUpcomingMoviesUseCase;
import android.coding.interview.makeitawesome.domain.interactor.GetUpcomingMoviesUseCaseImpl;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Liang on 2016/1/31.
 */
@Module
public class MoviesModule {
    @PerFragment
    @Provides
    GetUpcomingMoviesUseCase provideUpcomingMoviesUseCase(GetUpcomingMoviesUseCaseImpl getUpcomingMoviesUseCase) {
        return getUpcomingMoviesUseCase;
    }

    @PerFragment
    @Provides
    GetNowShowingMoviesUseCase provideNowShowingMoviesUseCase(GetNowShowingMoviesUseCaseImpl getNowShowingMoviesUseCase) {
        return getNowShowingMoviesUseCase;
    }
}
