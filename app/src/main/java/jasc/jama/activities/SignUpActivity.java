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
import android.util.Patterns;
import android.view.View;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

import java.util.regex.Matcher;

import jasc.jama.R;

public class SignUpActivity extends AppCompatActivity {

    private TextInputLayout usernameTextInputLayout, passwordTextInputLayout, emailTextInputLayout;
    private AppCompatButton signupButton;

    public static Intent createIntent(Context context) {
        return new Intent(context, SignUpActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        usernameTextInputLayout = (TextInputLayout) findViewById(R.id.username_text_input_layout);
        passwordTextInputLayout = (TextInputLayout) findViewById(R.id.password_text_input_layout);
        emailTextInputLayout = (TextInputLayout) findViewById(R.id.email_text_input_layout);

        signupButton = (AppCompatButton) findViewById(R.id.do_signup);

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                usernameTextInputLayout.setError(null);
                emailTextInputLayout.setError(null);
                passwordTextInputLayout.setError(null);

                final ProgressDialog pgDialog = new ProgressDialog(SignUpActivity.this);
                pgDialog.setMessage("Please Wait..");
                String username = usernameTextInputLayout.getEditText().getText().toString();
                String password = passwordTextInputLayout.getEditText().getText().toString();
                String email = emailTextInputLayout.getEditText().getText().toString();


                Matcher matcher = Patterns.EMAIL_ADDRESS.matcher(email);
                if (username.isEmpty())
                    usernameTextInputLayout.setError("Enter Username");
                if (password.isEmpty())
                    passwordTextInputLayout.setError("Enter Password");
                if (!matcher.matches())
                    emailTextInputLayout.setError("Invalid Email");

                if (!username.isEmpty() && !password.isEmpty()) {
                    pgDialog.show();
                    ParseUser user = new ParseUser();
                    user.setUsername(username);
                    user.setPassword(password);
                    user.setEmail(email);
                    user.signUpInBackground(new SignUpCallback() {
                        @Override
                        public void done(ParseException e) {
                            pgDialog.hide();
                            if (e != null) {
                                Snackbar.make(findViewById(android.R.id.content), e.getMessage(), Snackbar.LENGTH_SHORT)
                                        .setActionTextColor(Color.WHITE).show();
                                e.printStackTrace();
                            } else {
                                startActivity(DashBoardActivity.createIntent(getApplicationContext())
                                        .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK));
                            }
                        }
                    });
                }
            }
        });

    }
}
