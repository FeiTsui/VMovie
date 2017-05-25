package tsui.com.vmovie.adapters;

/**
 * Email：tsui@onetos.cc
 * Created by Tsui on 2017/5/25 00:15
 */

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

import tsui.com.vmovie.fragments.ChannelFragment;
import tsui.com.vmovie.fragments.MainFragment;


public class MainPagerAdapter extends FragmentPagerAdapter {
    private ArrayList<Fragment> fragments;

    public MainPagerAdapter(FragmentManager fm) {
        super(fm);
        fragments = new ArrayList<>();
        fragments.add(new MainFragment());
        fragments.add(new ChannelFragment());
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}
