package android.coding.interview.makeitawesome.data;

import android.coding.interview.makeitawesome.domain.Repository;
import android.coding.interview.makeitawesome.domain.entity.Events;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import au.com.gridstone.rxstore.RxStore;
import rx.Observable;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Liang on 2016/1/31.
 */
/*@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)*/
public class RepositoryImplTest {

    @Mock
    FinnkinoApi finnkinoApi;

    @Mock
    RxStore rxStore;

    Repository repository;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        repository = new RepositoryImpl(finnkinoApi, rxStore);
    }

    @Test
    public void testGetUpcomingMovies() throws Exception {
        when(finnkinoApi.getEvents(FinnkinoApi.ListType.ComingSoon, FinnkinoApi.AreaID.Helsinki)).thenReturn(Observable.<Events>empty());
        repository.getUpcomingMovies();
        verify(finnkinoApi, times(1)).getEvents(FinnkinoApi.ListType.ComingSoon, FinnkinoApi.AreaID.Helsinki);
    }

    @Test
    public void testGetNowShowingMovies() throws Exception {
        when(finnkinoApi.getEvents(FinnkinoApi.ListType.NowInThreatres, FinnkinoApi.AreaID.Helsinki)).thenReturn(Observable.<Events>empty());
        repository.getNowShowingMovies(FinnkinoApi.AreaID.Helsinki);
        verify(finnkinoApi, times(1)).getEvents(FinnkinoApi.ListType.NowInThreatres, FinnkinoApi.AreaID.Helsinki);
    }
}