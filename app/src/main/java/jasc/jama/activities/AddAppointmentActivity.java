package jasc.jama.activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import jasc.jama.R;

public class AddAppointmentActivity extends AppCompatActivity {

    private TextInputLayout reasonTextInputLayout;
    private TextInputLayout timeTextInputLayout;
    private TextInputLayout docsTextInputLayout;
    private TextInputLayout docNameTextInputLayout;

    public static Intent createIntent(Context context) {
        return new Intent(context, AddAppointmentActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_reminder);

        reasonTextInputLayout = (TextInputLayout) findViewById(R.id.reasonTextInputLayout);
        docsTextInputLayout = (TextInputLayout) findViewById(R.id.docsTextInputLayout);
        timeTextInputLayout = (TextInputLayout) findViewById(R.id.timeTextInputLayout);
        docNameTextInputLayout = (TextInputLayout) findViewById(R.id.docNameTextInputLayout);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_done) {
            final ProgressDialog dialog = new ProgressDialog(AddAppointmentActivity.this);
            dialog.setMessage("Please Wait");
            dialog.show();
            String reason = reasonTextInputLayout.getEditText().getText().toString();
            String doc = docNameTextInputLayout.getEditText().getText().toString();
            String time = timeTextInputLayout.getEditText().getText().toString();
            String docs = docsTextInputLayout.getEditText().getText().toString();

            if (reason.isEmpty())
                reason = "NA";
            if (doc.isEmpty())
                doc = "NA";
            if (time.isEmpty())
                time = "NA";
            if (docs.isEmpty())
                docs = "NA";

            ParseObject object = new ParseObject("Reminders");
            object.put("Name", doc);
            object.put("docs", docs);
            object.put("Time", time);
            object.put("reason", reason);
            object.put("type", "appointment");
            object.put("user_id", ParseUser.getCurrentUser().getObjectId());
            object.saveInBackground(new SaveCallback() {
                @Override
                public void done(ParseException e) {
                    dialog.hide();
                    if (e == null) {
                        reasonTextInputLayout.getEditText().setText("");
                        docNameTextInputLayout.getEditText().setText("");
                        timeTextInputLayout.getEditText().setText("");
                        docsTextInputLayout.getEditText().setText("");
                        Snackbar.make(findViewById(android.R.id.content), "Added", Snackbar.LENGTH_SHORT).show();
                    } else {
                        e.printStackTrace();
                        Snackbar.make(findViewById(android.R.id.content), "Something went wrong, please try again", Snackbar.LENGTH_SHORT).show();
                    }
                }
            });
        }
        return super.onOptionsItemSelected(item);
    }
}
