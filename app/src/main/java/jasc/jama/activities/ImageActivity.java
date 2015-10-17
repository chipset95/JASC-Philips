package jasc.jama.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import jasc.jama.R;
import uk.co.senab.photoview.PhotoViewAttacher;


public class ImageActivity extends AppCompatActivity {
    ImageView mImageView;

    public static Intent createIntent(Context context) {
        return new Intent(context, ImageActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
        String url = getIntent().getStringExtra("img");

        mImageView = (ImageView) findViewById(R.id.image_view);
        final ProgressBar imageProgressBar = (ProgressBar) findViewById(R.id.image_progress_bar);

        Picasso.with(getApplicationContext()).load(url).into(mImageView, new Callback() {
            @Override
            public void onSuccess() {
                imageProgressBar.setVisibility(View.GONE);
            }

            @Override
            public void onError() {
                Picasso.with(getApplicationContext()).load(R.mipmap.ic_launcher).into(mImageView);
                imageProgressBar.setVisibility(View.GONE);
            }
        });


        PhotoViewAttacher photoViewAttacher = new PhotoViewAttacher(mImageView);
        photoViewAttacher.update();
    }
}
