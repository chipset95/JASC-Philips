package jasc.jama.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import jasc.jama.fragments.UserBasicDetailFragment;
import jasc.jama.fragments.UserFitDetailFragment;

/**
 * Developer: chipset
 * Package : jasc.jama.adapters
 * Project : JAMA
 * Date : 17/10/15
 */
public class UserTabPagerAdapter extends FragmentStatePagerAdapter {

    private String[] titles;

    public UserTabPagerAdapter(FragmentManager fm, String[] titles) {
        super(fm);
        this.titles = titles;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = UserBasicDetailFragment.newInstance();
                break;
            case 1:
                fragment = UserFitDetailFragment.newInstance();
                break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return titles.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}
