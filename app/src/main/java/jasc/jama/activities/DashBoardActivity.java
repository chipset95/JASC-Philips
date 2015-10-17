package jasc.jama.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import jasc.jama.R;
import jasc.jama.fragments.DashHomeFragment;
import jasc.jama.fragments.HealthDataFragment;
import jasc.jama.fragments.UserDataFragment;

public class DashBoardActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;

    public static Intent createIntent(Context context) {
        return new Intent(context, DashBoardActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);
        Toolbar toolbar = (Toolbar) findViewById(R.id.dash_toolbar);
        setSupportActionBar(toolbar);

        new AlertDialog.Builder(DashBoardActivity.this)
                .setTitle("DISCLAIMER")
                .setIcon(R.drawable.ic_info_outline)
                .setMessage("ABCD")
                .setPositiveButton(android.R.string.ok, null)
                .create().show();

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerToggle = new ActionBarDrawerToggle(DashBoardActivity.this, mDrawerLayout, toolbar, R.string.app_name, R.string.app_name);
        mDrawerLayout.setDrawerListener(mDrawerToggle);

        getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, DashHomeFragment.newInstance()).commit();

        NavigationView navigationView = (NavigationView) findViewById(R.id.drawer_navigation_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.drawer_user)
                    getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, UserDataFragment.newInstance()).commit();
                if (id == R.id.drawer_home)
                    getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, DashHomeFragment.newInstance()).commit();
                if (id == R.id.drawer_health)
                    getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, HealthDataFragment.newInstance()).commit();
                mDrawerLayout.closeDrawers();
                return true;
            }
        });
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return mDrawerToggle.onOptionsItemSelected(item) || onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawers();
        }
    }
}
