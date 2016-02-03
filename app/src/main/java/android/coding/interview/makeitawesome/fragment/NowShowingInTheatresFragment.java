package android.coding.interview.makeitawesome.fragment;

import android.coding.interview.makeitawesome.DetailActivity;
import android.coding.interview.makeitawesome.R;
import android.coding.interview.makeitawesome.adapter.EventsAdapter;
import android.coding.interview.makeitawesome.base.view.BaseLceFragment;
import android.coding.interview.makeitawesome.base.view.EmptyRecyclerView;
import android.coding.interview.makeitawesome.di.DaggerPicturesFragmentComponent;
import android.coding.interview.makeitawesome.di.MoviesModule;
import android.coding.interview.makeitawesome.di.PicturesFragmentComponent;
import android.coding.interview.makeitawesome.domain.Repository;
import android.coding.interview.makeitawesome.domain.entity.Event;
import android.coding.interview.makeitawesome.presenter.NowShowingInTheatresPresenter;
import android.coding.interview.makeitawesome.utils.ItemClickSupport;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.hannesdorfmann.mosby.mvp.viewstate.lce.LceViewState;
import com.hannesdorfmann.mosby.mvp.viewstate.lce.data.RetainingFragmentLceViewState;

import java.util.ArrayList;
import java.util.List;

/**
 * This fragment fetches the NowInTheatres events from Finnkino server and shows them
 *
 * @see <a href="http://www.finnkino.fi/XML">Finnkino Api</a>
 */
public class NowShowingInTheatresFragment extends BaseLceFragment<SwipeRefreshLayout, List<Event>, NowShowingInTheatresView, NowShowingInTheatresPresenter>
        implements SwipeRefreshLayout.OnRefreshListener,
        ItemClickSupport.OnItemClickListener {

    private static final String TAG = NowShowingInTheatresFragment.class.getSimpleName();

    public static Fragment newInstance() {
        return new NowShowingInTheatresFragment();
    }

    private EventsAdapter mAdapter;


    public EmptyRecyclerView emptyRecyclerView;


    public TextView emptyView;

    PicturesFragmentComponent component;

    @Override
    protected int getLayoutRes() {
        return R.layout.pictures_list_fragment;
    }

    @NonNull
    @Override
    public NowShowingInTheatresPresenter createPresenter() {
        return component.presenter();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        emptyRecyclerView = (EmptyRecyclerView) view.findViewById(R.id.recyclerView);
        emptyView = (TextView) view.findViewById(R.id.emptyView);
        contentView.setOnRefreshListener(this);
        setupRecyclerView(emptyRecyclerView);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    protected String getErrorMessage(Throwable e, boolean pullToRefresh) {
        return e.getMessage();
    }

    /**
     * Show content view
     */
    @Override
    public void showContent() {
        super.showContent();
        if (emptyRecyclerView.getEmptyView() == null) emptyRecyclerView.setEmptyView(emptyView);
        //contentView.setRefreshing(false);
        contentView.post(() -> contentView.setRefreshing(false));
    }

    /**
     * Show error view
     *
     * @param e             error to be showed
     * @param pullToRefresh isPullToRefresh
     */
    @Override
    public void showError(Throwable e, boolean pullToRefresh) {
        super.showError(e, pullToRefresh);
        contentView.setRefreshing(false);
    }

    /**
     * Show loading view
     *
     * @param pullToRefresh isPullToRefresh
     */
    @Override
    public void showLoading(boolean pullToRefresh) {
        super.showLoading(pullToRefresh);
        if (pullToRefresh && !contentView.isRefreshing()) {
            // Workaround for measure bug: https://code.google.com/p/android/issues/detail?id=77712
            contentView.post(() -> contentView.setRefreshing(true));
        }
    }

    @NonNull
    @Override
    public LceViewState<List<Event>, NowShowingInTheatresView> createViewState() {
        return new RetainingFragmentLceViewState<>(this);
    }

    @Override
    public List<Event> getData() {
        return mAdapter.getData();
    }

    @Override
    public void setData(List<Event> data) {
        mAdapter.refreshData(data);
    }

    @Override
    public void loadData(boolean pullToRefresh) {
        presenter.getUpComingMovies(pullToRefresh);
    }

    @Override
    protected void injectDependencies() {
        component = DaggerPicturesFragmentComponent
                .builder()
                .appComponent(getAppComponent())
                .moviesModule(new MoviesModule())
                .build();
    }

    /**
     * SwipeRefreshLayout refresh callback
     */
    @Override
    public void onRefresh() {
        loadData(true);
    }

    private void setupRecyclerView(RecyclerView recyclerView) {
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),
                getResources().getInteger(R.integer.span_count_movie)));
        mAdapter = new EventsAdapter(new ArrayList<>());
        recyclerView.setAdapter(mAdapter);
        ItemClickSupport.addTo(recyclerView).setOnItemClickListener(this);
    }

    /**
     * Callback method to be invoked when an item in the RecyclerView
     * has been clicked.
     *
     * @param recyclerView The RecyclerView where the click happened.
     * @param view         The view within the RecyclerView that was clicked
     *                     (this will be a view provided by the adapter).
     * @param position     The position of the view in the adapter.
     */
    @Override
    public void onItemClicked(RecyclerView recyclerView, View view, int position) {
        DetailActivity.launch(getActivity(), view.findViewById(R.id.item_movie_cover), Repository.KEY_EVENTS_NOW_IN_THEATRES, position);
    }
}
