package jasc.jama.applications;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseInstallation;

import jasc.jama.resources.Constants;

public class ParseInitApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        Parse.enableLocalDatastore(this);

        Parse.initialize(this, Constants.APP_ID, Constants.CLIENT_KEY);
        ParseInstallation.getCurrentInstallation().saveInBackground();

    }
}

