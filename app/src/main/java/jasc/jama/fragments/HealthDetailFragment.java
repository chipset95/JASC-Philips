package jasc.jama.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.List;

/**
 * Created by anirudhraghunath on 17/10/15.
 */
public class HealthDetailFragment extends Fragment {

    private String type;

    public void setType(String type){

        this.type = type;
    }

    public static HealthDetailFragment newInstance(String type){

        HealthDetailFragment f = new HealthDetailFragment();
        f.setType(type);
        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Details");
        query.whereEqualTo("user_id", ParseUser.getCurrentUser().getObjectId());
        query.whereEqualTo("type",type);
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {

            }
        });
    }
}
