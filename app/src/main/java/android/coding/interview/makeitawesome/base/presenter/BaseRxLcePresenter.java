package android.coding.interview.makeitawesome.base.presenter;

import com.hannesdorfmann.mosby.mvp.lce.MvpLceView;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * A presenter for RxJava, that assumes that only one Observable is subscribed by this presenter.
 * The idea is, that you make your (chain of) Observable and pass it to {@link
 * #subscribe(Observable, boolean)}. The presenter internally subscribes himself as Subscriber to
 * the observable(which executes the observable).
 *
 */
public class BaseRxLcePresenter<V extends MvpLceView<M>, M>
        extends com.hannesdorfmann.mosby.mvp.MvpBasePresenter<V> {

    protected Subscriber<M> mSubscriber;

    /**
     * Unsubscribes the subscriber and set it to null
     */
    protected void unsubscribe() {
        if (mSubscriber != null && !mSubscriber.isUnsubscribed()) {
            mSubscriber.unsubscribe();
        }

        mSubscriber = null;
    }

    /**
     * Subscribes the presenter himself as subscriber on the observable
     *
     * @param observable The observable to subscribe
     * @param pullToRefresh Pull to refresh?
     */
    public void subscribe(Observable<M> observable, final boolean pullToRefresh) {

        if (isViewAttached()) {
            getView().showLoading(pullToRefresh);
        }

        unsubscribe();

        mSubscriber = new Subscriber<M>() {
            private boolean ptr = pullToRefresh;

            @Override public void onCompleted() {
                BaseRxLcePresenter.this.onCompleted();
            }

            @Override public void onError(Throwable e) {
                BaseRxLcePresenter.this.onError(e, ptr);
            }

            @Override public void onNext(M m) {
                BaseRxLcePresenter.this.onNext(m);
            }
        };

        observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(mSubscriber);
    }

    protected void onCompleted() {
        if (isViewAttached()) {
            getView().showContent();
        }
        unsubscribe();
    }

    protected void onError(Throwable e, boolean pullToRefresh) {
        if (isViewAttached()) {
            getView().showError(e, pullToRefresh);
        }
        unsubscribe();
    }

    protected void onNext(M data) {
        if (isViewAttached()) {
            getView().setData(data);
        }
    }

    @Override public void detachView(boolean retainInstance) {
        super.detachView(retainInstance);
        if (!retainInstance) {
            unsubscribe();
        }
    }

}