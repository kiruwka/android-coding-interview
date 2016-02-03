package android.coding.interview.makeitawesome;

import android.coding.interview.makeitawesome.di.AppComponent;
import android.coding.interview.makeitawesome.di.DaggerAppComponent;
import android.coding.interview.makeitawesome.domain.Repository;
import android.coding.interview.makeitawesome.domain.entity.Event;
import android.coding.interview.makeitawesome.domain.entity.Events;
import android.coding.interview.makeitawesome.domain.entity.Images;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.swipeDown;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.not;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


/**
 * Created by liang on 01/02/16.
 */
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {
    public final ActivityTestRule<MainActivity> mainActivityActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Mock
    Repository mMockRepository;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testErrorViewIsDisplayedWhenAnErrorIsReturned() throws InterruptedException {

        App app = (App) InstrumentationRegistry.getInstrumentation().getTargetContext().getApplicationContext();

        //create mock repository returns error;
        when(mMockRepository.getNowShowingMovies(anyInt())).thenReturn(Observable.error(new Throwable("test")));
        AppComponent component = DaggerAppComponent.builder()
                .appModule(new MockAppModule(app, mMockRepository))
                .build();
        app.setAppComponent(component);

        mainActivityActivityTestRule.launchActivity(null);
        onView(withId(R.id.errorView)).check(matches(isDisplayed()));
        onView(withId(R.id.contentView)).check(matches(not(isDisplayed())));
        onView(withId(R.id.emptyView)).check(matches(not(isDisplayed())));
        onView(withId(R.id.loadingView)).check(matches(not(isDisplayed())));
    }

    @Test
    public void testContentViewIsDisplayedWhenAnEventsIsReturned() throws InterruptedException {
        App app = (App) InstrumentationRegistry.getInstrumentation().getTargetContext().getApplicationContext();

        //create mock repository returns an Events instance
        when(mMockRepository.getNowShowingMovies(anyInt())).thenReturn(Observable.just(createEvents()));
        AppComponent component = DaggerAppComponent.builder()
                .appModule(new MockAppModule(app, mMockRepository))
                .build();
        app.setAppComponent(component);

        mainActivityActivityTestRule.launchActivity(null);
        onView(withId(R.id.contentView)).check(matches(isDisplayed()));
        onView(withId(R.id.errorView)).check(matches(not(isDisplayed())));
        onView(withId(R.id.emptyView)).check(matches(not(isDisplayed())));
        onView(withId(R.id.loadingView)).check(matches(not(isDisplayed())));
    }

    @Test
    public void testEmptyViewIsDisplayedWhenAnEventsWithEmptyEventListIsReturned() throws InterruptedException {

        App app = (App) InstrumentationRegistry.getInstrumentation().getTargetContext().getApplicationContext();

        //create mock repository returns an Events instance with empty Event list
        Events events = new Events();
        events.setEvents(new ArrayList<>());
        when(mMockRepository.getNowShowingMovies(anyInt())).thenReturn(Observable.just(events));
        AppComponent component = DaggerAppComponent.builder()
                .appModule(new MockAppModule(app, mMockRepository))
                .build();
        app.setAppComponent(component);

        mainActivityActivityTestRule.launchActivity(null);
        onView(withId(R.id.errorView)).check(matches(not(isDisplayed())));
        onView(withId(R.id.loadingView)).check(matches(not(isDisplayed())));
        onView(withId(R.id.contentView)).check(matches(isDisplayed()));
        onView(withId(R.id.emptyView)).check(matches(isDisplayed()));
    }

    @Test
    public void testLoadingViewIsDisplayedWhenAnRequestIsGoingOn() throws InterruptedException {

        App app = (App) InstrumentationRegistry.getInstrumentation().getTargetContext().getApplicationContext();
        //create mock repository never returns result.
        when(mMockRepository.getNowShowingMovies(anyInt())).thenReturn(Observable.never());
        AppComponent component = DaggerAppComponent.builder()
                .appModule(new MockAppModule(app, mMockRepository))
                .build();
        app.setAppComponent(component);

        mainActivityActivityTestRule.launchActivity(null);
        onView(withId(R.id.loadingView)).check(matches(isDisplayed()));
        onView(withId(R.id.errorView)).check(matches(not(isDisplayed())));
        onView(withId(R.id.contentView)).check(matches(not(isDisplayed())));
        onView(withId(R.id.emptyView)).check(matches(not(isDisplayed())));
    }

    @Test
    public void testToastMessageIsDisplayedWhenAnErrorReturnedTriggeredBySwipeRefreshLayout() throws InterruptedException {

        App app = (App) InstrumentationRegistry.getInstrumentation().getTargetContext().getApplicationContext();
        //create mock repository returns an Events.
        when(mMockRepository.getNowShowingMovies(anyInt())).thenReturn(Observable.just(mock(Events.class)));
        AppComponent component = DaggerAppComponent.builder()
                .appModule(new MockAppModule(app, mMockRepository))
                .build();
        app.setAppComponent(component);

        mainActivityActivityTestRule.launchActivity(null);

        //create mock repository returns an error.
        when(mMockRepository.getNowShowingMovies(anyInt())).thenReturn(Observable.error(new Throwable("test error")));
        onView(withId(R.id.contentView)).perform(swipeDown());
        onView(withId(R.id.loadingView)).check(matches(not(isDisplayed())));
        onView(withId(R.id.errorView)).check(matches(not(isDisplayed())));
        onView(withId(R.id.contentView)).check(matches(isDisplayed()));

        //verify if Toast message is displayed
        onView(withText("test error"))
                .inRoot(withDecorView(not(mainActivityActivityTestRule.getActivity().getWindow().getDecorView())))
                .check(matches(isDisplayed()));
    }

    @Test
    public void testLoadingViewIsDisplayedWhenErrorViewIsClicked() throws InterruptedException {

        App app = (App) InstrumentationRegistry.getInstrumentation().getTargetContext().getApplicationContext();

        //create mock repository returns error;
        when(mMockRepository.getNowShowingMovies(anyInt())).thenReturn(Observable.error(new Throwable("test")));
        AppComponent component = DaggerAppComponent.builder()
                .appModule(new MockAppModule(app, mMockRepository))
                .build();
        app.setAppComponent(component);

        mainActivityActivityTestRule.launchActivity(null);

        //create mock repository never returns result;
        when(mMockRepository.getNowShowingMovies(anyInt())).thenReturn(Observable.never());
        onView(withId(R.id.errorView)).check(matches(isDisplayed()));
        onView(withId(R.id.errorView)).perform(click());
        onView(withId(R.id.errorView)).check(matches(not(isDisplayed())));
        onView(withId(R.id.contentView)).check(matches(not(isDisplayed())));
        onView(withId(R.id.emptyView)).check(matches(not(isDisplayed())));
        onView(withId(R.id.loadingView)).check(matches(isDisplayed()));
    }

    @Test
    public void testContentViewIsDisplayedWhenErrorViewIsClicked() throws InterruptedException {

        App app = (App) InstrumentationRegistry.getInstrumentation().getTargetContext().getApplicationContext();

        //create mock repository returns error;
        when(mMockRepository.getNowShowingMovies(anyInt())).thenReturn(Observable.error(new Throwable("test")));
        AppComponent component = DaggerAppComponent.builder()
                .appModule(new MockAppModule(app, mMockRepository))
                .build();
        app.setAppComponent(component);

        mainActivityActivityTestRule.launchActivity(null);

        //create mock repository returns an Event;
        when(mMockRepository.getNowShowingMovies(anyInt())).thenReturn(Observable.just(mock(Events.class)));
        onView(withId(R.id.errorView)).check(matches(isDisplayed()));
        onView(withId(R.id.errorView)).perform(click());
        onView(withId(R.id.errorView)).check(matches(not(isDisplayed())));
        onView(withId(R.id.contentView)).check(matches(isDisplayed()));
        onView(withId(R.id.loadingView)).check(matches(not(isDisplayed())));
    }

    @Test
    public void testErrorViewIsDisplayedWhenErrorViewIsClicked() throws InterruptedException {

        App app = (App) InstrumentationRegistry.getInstrumentation().getTargetContext().getApplicationContext();

        //create mock repository returns error;
        when(mMockRepository.getNowShowingMovies(anyInt())).thenReturn(Observable.error(new Throwable("test")));
        AppComponent component = DaggerAppComponent.builder()
                .appModule(new MockAppModule(app, mMockRepository))
                .build();
        app.setAppComponent(component);

        mainActivityActivityTestRule.launchActivity(null);

        onView(withId(R.id.errorView)).check(matches(isDisplayed()));
        onView(withId(R.id.errorView)).perform(click());
        onView(withId(R.id.errorView)).check(matches(isDisplayed()));
        onView(withId(R.id.contentView)).check(matches(not(isDisplayed())));
        onView(withId(R.id.loadingView)).check(matches(not(isDisplayed())));

    }

    private Events createEvents() {
        Events events = new Events();
        Event event = new Event();
        event.setTitle("test");
        Images images = new Images();
        images.setEventMediumImagePortrait("http://media.finnkino.fi/1012/Event_10655/portrait_medium/Zoolander2_1080t.jpg");
        event.setImages(images);
        List<Event> eventList = new ArrayList<>();
        eventList.add(event);
        events.setEvents(eventList);
        return events;
    }
}