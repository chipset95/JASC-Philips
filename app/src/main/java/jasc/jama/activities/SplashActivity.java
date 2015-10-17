package jasc.jama.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.parse.ParseUser;

import jasc.jama.R;

public class SplashActivity extends AppCompatActivity {

    public static Intent createIntent(Context context) {
        return new Intent(context, SplashActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        final Animation animationFadeIn = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.anim_fade_in);
        final Animation animationMoveUp = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.anim_move_up);

        final TextView nameTextView = (TextView) findViewById(R.id.app);
        final LinearLayout layout = (LinearLayout) findViewById(R.id.button_layout);
        final AppCompatButton signinButton = (AppCompatButton) findViewById(R.id.splash_to_signin_button);
        final AppCompatButton signupButton = (AppCompatButton) findViewById(R.id.splash_to_signup_button);

        nameTextView.setAnimation(animationMoveUp);

        layout.setAnimation(animationFadeIn);
        animationFadeIn.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                signinButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(SignInActivity.createIntent(getApplicationContext()));
                    }
                });
                signupButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(SignUpActivity.createIntent(getApplicationContext()));
                    }
                });
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (ParseUser.getCurrentUser() == null) {
                            layout.setVisibility(View.VISIBLE);
                            nameTextView.animate();
                            layout.animate();
                        } else
                            startActivity(DashBoardActivity.createIntent(getApplicationContext())
                                    .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK));
                    }
                }, 1000);
            }
        }, 2000);

    }


}
