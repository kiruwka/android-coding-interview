package android.coding.interview.makeitawesome.domain.interactor;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Created by Liang on 2016/1/31.
 */
public class GetNowShowingMoviesUseCaseImplTest extends BaseUseCaseImplTest{

    GetNowShowingMoviesUseCase getNowShowingMoviesUseCase;

    @Before
    public void setUp() throws Exception {
        super.setUp();
        getNowShowingMoviesUseCase = new GetNowShowingMoviesUseCaseImpl(repository);
    }

    @Test
    public void testExecute() throws Exception {
        getNowShowingMoviesUseCase.execute(anyInt());
        verify(repository, times(1)).getNowShowingMovies(anyInt());
    }
}