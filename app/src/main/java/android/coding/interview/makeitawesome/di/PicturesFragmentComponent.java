package android.coding.interview.makeitawesome.di;

import android.coding.interview.makeitawesome.presenter.NowShowingInTheatresPresenter;

import dagger.Component;

@Component(dependencies = AppComponent.class, modules = MoviesModule.class)
@PerFragment
public interface PicturesFragmentComponent {
    NowShowingInTheatresPresenter presenter();
}
