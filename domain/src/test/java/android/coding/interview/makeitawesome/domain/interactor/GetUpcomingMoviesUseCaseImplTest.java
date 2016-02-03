package android.coding.interview.makeitawesome.domain.interactor;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Created by Liang on 2016/1/31.
 */
public class GetUpcomingMoviesUseCaseImplTest extends  BaseUseCaseImplTest{

    GetUpcomingMoviesUseCase getUpcomingMoviesUseCase;

    @Before
    public void setUp() throws Exception {
        super.setUp();
        getUpcomingMoviesUseCase = new GetUpcomingMoviesUseCaseImpl(repository);
    }

    @Test
    public void testExecute() throws Exception {
        getUpcomingMoviesUseCase.execute();
        verify(repository, times(1)).getUpcomingMovies();
    }
}