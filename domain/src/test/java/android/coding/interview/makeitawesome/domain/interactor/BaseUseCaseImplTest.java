package android.coding.interview.makeitawesome.domain.interactor;

import android.coding.interview.makeitawesome.domain.Repository;

import org.junit.Before;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

/**
 * Created by Liang on 2016/1/31.
 */
public class BaseUseCaseImplTest {
    @Mock
    Repository repository;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }
}
