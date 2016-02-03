package android.coding.interview.makeitawesome.di;

import java.lang.annotation.Retention;

import javax.inject.Scope;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by Liang on 2016/1/31.
 */
@Scope
@Retention(RUNTIME)
public @interface PerFragment {
}
