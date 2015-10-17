package jasc.jama.activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.view.View;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

import jasc.jama.R;

public class SignInActivity extends AppCompatActivity {

    private TextInputLayout usernameTextInputLayout, passwordTextInputLayout;
    private AppCompatButton loginButton;

    public static Intent createIntent(Context context) {
        return new Intent(context, SignInActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        usernameTextInputLayout = (TextInputLayout) findViewById(R.id.username_text_input_layout);
        passwordTextInputLayout = (TextInputLayout) findViewById(R.id.password_text_input_layout);

        loginButton = (AppCompatButton) findViewById(R.id.do_signin);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                usernameTextInputLayout.setError(null);
                passwordTextInputLayout.setError(null);

                final ProgressDialog pgDialog = new ProgressDialog(SignInActivity.this);
                pgDialog.setMessage("Please Wait..");
                String username = usernameTextInputLayout.getEditText().getText().toString();
                String password = passwordTextInputLayout.getEditText().getText().toString();

                if (username.isEmpty())
                    usernameTextInputLayout.setError("Enter Username");
                if (password.isEmpty())
                    passwordTextInputLayout.setError("Enter Password");

                if (!username.isEmpty() && !password.isEmpty()) {
                    pgDialog.show();
                    ParseUser.logInInBackground(username, password, new LogInCallback() {
                        public void done(ParseUser user, ParseException e) {
                            pgDialog.hide();
                            if (user != null) {
                                Intent intent = new Intent(SignInActivity.this, DashBoardActivity.class)
                                        .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(intent);
                            } else {
                                Snackbar.make(findViewById(android.R.id.content), "Username/Password Incorrect.", Snackbar.LENGTH_SHORT)
                                        .setActionTextColor(Color.WHITE)
                                        .show();
                            }
                        }
                    });
                }
            }
        });

    }
}
