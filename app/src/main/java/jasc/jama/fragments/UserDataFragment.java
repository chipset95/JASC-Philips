package jasc.jama.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import jasc.jama.R;

/**
 * Developer: chipset
 * Package : jasc.jama.fragments
 * Project : JAMA
 * Date : 17/10/15
 */
public class UserDataFragment extends Fragment {

    public static UserDataFragment newInstance() {
        return new UserDataFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_user_data, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
