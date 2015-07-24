package android.coding.interview.makeitawesome.fragment;

import android.coding.interview.makeitawesome.R;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by km on 7/24/15.
 */
public class WelcomeScreenFragment extends Fragment {

    public static Fragment newInstance() {
        return new WelcomeScreenFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.welcome_fragment, container, false);
    }
}
