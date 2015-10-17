package jasc.jama.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.Arrays;

import jasc.jama.R;
import jasc.jama.adapters.HealthTabPagerAdapter;

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
        final TabLayout healthTabLayout = (TabLayout) view.findViewById(R.id.health_tab_layout);
        final ViewPager pager = (ViewPager) view.findViewById(R.id.health_view_pager);
        final ParseUser user = ParseUser.getCurrentUser();
        user.fetchInBackground(new GetCallback<ParseUser>() {
            @Override
            public void done(ParseUser object, ParseException e) {
                if (e == null) {
                    ArrayList<String> data = (ArrayList) user.get("dept_list");
                    Object[] interim = data.toArray();
                    String[] titles = Arrays.copyOf(interim, interim.length, String[].class);
                    pager.setAdapter(new HealthTabPagerAdapter(getActivity().getSupportFragmentManager(), titles));
                    healthTabLayout.setupWithViewPager(pager);
                }
            }
        });

    }
}
