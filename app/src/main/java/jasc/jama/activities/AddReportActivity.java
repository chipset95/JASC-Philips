package jasc.jama.activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.io.ByteArrayOutputStream;

import jasc.jama.R;

public class AddReportActivity extends AppCompatActivity {

    private TextInputLayout nameTextInputLayout;
    private TextInputLayout timeTextInputLayout;
    private TextInputLayout detailTextInputLayout;
    private TextInputLayout typeNameTextInputLayout;
    private AppCompatButton attachImageButton;
    private byte[] byteArray;

    private final int REQUEST_IMAGE_CAPTURE = 1;

    public static Intent createIntent(Context context) {
        return new Intent(context, AddReportActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_report);

        nameTextInputLayout = (TextInputLayout) findViewById(R.id.nameTextInputLayout);
        detailTextInputLayout = (TextInputLayout) findViewById(R.id.detailTextInputLayout);
        timeTextInputLayout = (TextInputLayout) findViewById(R.id.timeTextInputLayout);
        typeNameTextInputLayout = (TextInputLayout) findViewById(R.id.typeTextInputLayout);
        attachImageButton = (AppCompatButton) findViewById(R.id.image_button);

        attachImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dispatchTakePictureIntent();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_done) {
            final ProgressDialog dialog = new ProgressDialog(AddReportActivity.this);
            dialog.setMessage("Please Wait");
            dialog.show();
            String name = nameTextInputLayout.getEditText().getText().toString();
            String type = typeNameTextInputLayout.getEditText().getText().toString();
            String time = timeTextInputLayout.getEditText().getText().toString();
            String detail = detailTextInputLayout.getEditText().getText().toString();

            if (name.isEmpty())
                name = "NA";
            if (type.isEmpty())
                type = "NA";
            if (time.isEmpty())
                time = "NA";
            if (detail.isEmpty())
                detail = "NA";

            ParseObject object = new ParseObject("Details");
            object.put("Name", name);
            object.put("detail", detail);
            object.put("time", time);
            object.put("type", type);
            object.put("user_id", ParseUser.getCurrentUser().getObjectId());
            if (byteArray.length != 0) {
                ParseFile file = new ParseFile("image.png", byteArray);
                object.put("image", file);
            } else object.put("image", null);

            object.saveInBackground(new SaveCallback() {
                @Override
                public void done(ParseException e) {
                    dialog.hide();
                    if (e == null) {
                        nameTextInputLayout.getEditText().setText("");
                        typeNameTextInputLayout.getEditText().setText("");
                        timeTextInputLayout.getEditText().setText("");
                        detailTextInputLayout.getEditText().setText("");
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

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            imageBitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
            byteArray = stream.toByteArray();
        }
    }
}
