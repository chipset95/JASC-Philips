package jasc.jama.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.parse.ParseUser;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

import jasc.jama.R;
import jasc.jama.adapters.healthTabPagerAdapter;

/**
 * Created by anirudhraghunath on 17/10/15.
 */
public class HealthDataFragment extends Fragment {

    public static HealthDataFragment newInstance() {

        return new HealthDataFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_health_data, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TabLayout healthTabLayout = (TabLayout) view.findViewById(R.id.health_tab_layout);
        ViewPager pager = (ViewPager) view.findViewById(R.id.health_view_pager);
        ArrayList<String> data = (ArrayList<String>) ParseUser.getCurrentUser().get("dept_list");
        Object[] interim = data.toArray();
        String[] titles = Arrays.copyOf(interim, interim.length, String[].class);
        pager.setAdapter(new healthTabPagerAdapter(getActivity().getSupportFragmentManager(), titles));
        healthTabLayout.setupWithViewPager(pager);
    }
}
