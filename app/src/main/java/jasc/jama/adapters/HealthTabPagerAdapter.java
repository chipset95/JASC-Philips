package jasc.jama.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import jasc.jama.fragments.HealthDetailFragment;

/**
 * Developer: chipset
 * Package : jasc.jama.adapters
 * Project : JAMA
 * Date : 17/10/15
 */
public class HealthTabPagerAdapter extends FragmentStatePagerAdapter {

    private String[] titles;

    public HealthTabPagerAdapter(FragmentManager fm, String[] titles) {
        super(fm);
        this.titles = titles;
    }

    @Override
    public Fragment getItem(int position) {
        return HealthDetailFragment.newInstance(titles[position]);
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
