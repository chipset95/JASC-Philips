package jasc.jama.fragments;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import jasc.jama.R;
import jasc.jama.adapters.UserTabPagerAdapter;

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
        TabLayout userTabLayout = (TabLayout) view.findViewById(R.id.user_tab_layout);
        ViewPager pager = (ViewPager) view.findViewById(R.id.user_view_pager);
        String titles[] = getActivity().getResources().getStringArray(R.array.user_tabs);
        pager.setAdapter(new UserTabPagerAdapter(((AppCompatActivity) getActivity()).getSupportFragmentManager(), titles));
        userTabLayout.setupWithViewPager(pager);
    }
}
