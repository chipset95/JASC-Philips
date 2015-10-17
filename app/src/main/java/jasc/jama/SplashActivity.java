package jasc.jama;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;

import com.github.jorgecastillo.FillableLoader;
import com.github.jorgecastillo.State;
import com.github.jorgecastillo.listener.OnStateChangeListener;

public class SplashActivity extends AppCompatActivity {

    private String path = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        final Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.anim_fade_in);

        FillableLoader splashFillableLoader = (FillableLoader) findViewById(R.id.splash_fillable_loader);
        final LinearLayout layout = (LinearLayout) findViewById(R.id.button_layout);
        final AppCompatButton signinButton = (AppCompatButton) findViewById(R.id.splash_to_signin_button);
        final AppCompatButton signupButton = (AppCompatButton) findViewById(R.id.splash_to_signup_button);


        layout.setAnimation(animation);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                signinButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
                signupButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        splashFillableLoader.setSvgPath(path);
        splashFillableLoader.start();

        splashFillableLoader.setOnStateChangeListener(new OnStateChangeListener() {
            @Override
            public void onStateChange(int i) {
                if (i == State.FINISHED) {
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            layout.setVisibility(View.VISIBLE);
                            layout.animate();
                        }
                    }, 1000);

                }
            }
        });
    }


}
