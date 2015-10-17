package jasc.jama.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

import jasc.jama.R;

public class DashBoardActivity extends AppCompatActivity {

    public static Intent createIntent(Context context) {
        return new Intent(context, DashBoardActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);

        new AlertDialog.Builder(DashBoardActivity.this)
                .setTitle("DISCLAIMER")
                .setIcon(R.drawable.ic_info_outline)
                .setMessage("ABCD")
                .setPositiveButton(android.R.string.ok, null)
                .create().show();
    }
}
