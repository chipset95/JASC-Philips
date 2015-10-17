package jasc.jama.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.parse.ParseUser;

import jasc.jama.R;

/**
 * Developer: chipset
 * Package : jasc.jama.fragments
 * Project : JAMA
 * Date : 17/10/15
 */
public class UserBasicDetailFragment extends Fragment {
    public static UserBasicDetailFragment newInstance() {
        return new UserBasicDetailFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_user_basic_detail, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //TextView userNameTextView = (TextView) view.findViewById(R.id.user_name_text_view);
        TextView userUserNameTextView = (TextView) view.findViewById(R.id.user_user_name_name_text_view);
        TextView userEmailTextView = (TextView) view.findViewById(R.id.user_email_name_text_view);

        ParseUser user = ParseUser.getCurrentUser();
        //userNameTextView.setText(user.get);
        userUserNameTextView.setText(user.getUsername());
        userEmailTextView.setText(user.getEmail());
    }
}
