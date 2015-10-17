package jasc.jama;

import android.app.Application;
import com.parse.Parse;
import com.parse.ParseInstallation;

public class ParseInitApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        // Enable Local Datastore.
        Parse.enableLocalDatastore(this);

        Parse.initialize(this, "DOzDTu9PLUNITslgtRRwAENLtMail6Brk3CVP4M8", "GTyxXsYw6IzTvNhuqCBkiVU6JkAn9du5NC4WC7q3");
        ParseInstallation.getCurrentInstallation().saveInBackground();

    }
}

