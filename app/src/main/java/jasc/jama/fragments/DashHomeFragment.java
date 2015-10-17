package jasc.jama.fragments;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

import jasc.jama.R;
import jasc.jama.adapters.DashHomeAdapter;

/**
 * Developer: chipset
 * Package : jasc.jama.fragments
 * Project : JAMA
 * Date : 17/10/15
 */
public class DashHomeFragment extends Fragment {

    private ListView dashboardListView;

    public static DashHomeFragment newInstance() {
        return new DashHomeFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_dash_home, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        dashboardListView = (ListView)getView().findViewById(R.id.dashboardListView);

        final ProgressDialog pgDialog = new ProgressDialog(getContext());
        pgDialog.setMessage("Please Wait..");
        pgDialog.show();
        ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("Reminders");
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {

                pgDialog.hide();
                dashboardListView.setAdapter(new DashHomeAdapter(getContext(), objects));
            }
        });
    }
}
