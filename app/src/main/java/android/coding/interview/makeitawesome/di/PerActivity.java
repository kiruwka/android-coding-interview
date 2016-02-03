package android.coding.interview.makeitawesome.di;

import java.lang.annotation.Retention;

import javax.inject.Scope;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by Liang on 2016/2/1.
 */
@Scope
@Retention(RUNTIME)
public @interface PerActivity {
}
