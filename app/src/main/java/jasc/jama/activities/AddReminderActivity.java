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
import com.parse.SaveCallback;

import jasc.jama.R;

public class AddReminderActivity extends AppCompatActivity {

    private TextInputLayout nameTextInputLayout;
    private TextInputLayout timeTextInputLayout;
    private TextInputLayout frequencyTextInputLayout;
    private TextInputLayout dosageTextInputLayout;

    public static Intent createIntent(Context context) {
        Intent intent = new Intent(context, AddReminderActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_reminder);

        nameTextInputLayout = (TextInputLayout) findViewById(R.id.nameTextInputLayout);
        dosageTextInputLayout = (TextInputLayout) findViewById(R.id.dosageTextInputLayout);
        timeTextInputLayout = (TextInputLayout) findViewById(R.id.timeTextInputLayout);
        frequencyTextInputLayout = (TextInputLayout) findViewById(R.id.frequencyTextInputLayout);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_done) {
            final ProgressDialog dialog = new ProgressDialog(AddReminderActivity.this);
            dialog.setMessage("Please Wait");
            dialog.show();
            String name = nameTextInputLayout.getEditText().getText().toString();
            String dosage = dosageTextInputLayout.getEditText().getText().toString();
            String time = timeTextInputLayout.getEditText().getText().toString();
            String frequency = frequencyTextInputLayout.getEditText().getText().toString();

            if (name.isEmpty())
                name = "NA";
            if (dosage.isEmpty())
                dosage = "NA";
            if (time.isEmpty())
                time = "NA";
            if (frequency.isEmpty())
                frequency = "NA";

            ParseObject object = new ParseObject("Reminders");
            object.put("Name", name);
            object.put("dosage", dosage);
            object.put("Time", time);
            object.put("freq", frequency);
            object.put("type", "medicine");
            object.saveInBackground(new SaveCallback() {
                @Override
                public void done(ParseException e) {
                    dialog.hide();
                    if (e == null) {
                        nameTextInputLayout.getEditText().setText("");
                        dosageTextInputLayout.getEditText().setText("");
                        timeTextInputLayout.getEditText().setText("");
                        frequencyTextInputLayout.getEditText().setText("");
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
