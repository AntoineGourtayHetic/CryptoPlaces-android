package com.antoinegourtay.mob_e16_android.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.antoinegourtay.mob_e16_android.fragments.CoursFragment;
import com.antoinegourtay.mob_e16_android.fragments.TransactionFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by antoinegourtay on 20/12/2017.
 */

public class WalletAdapter extends FragmentPagerAdapter {

    private Fragment mCurrentFragement;

    private final List<Fragment> mFragmentList = new ArrayList<>();
    private final List<String> mFragmentTitleList = new ArrayList<>();

    public WalletAdapter(FragmentManager fm) {
        super(fm);
    }


    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }

    public void addFrag(Fragment fragment, String title) {
        mFragmentList.add(fragment);
        mFragmentTitleList.add(title);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mFragmentTitleList.get(position);
    }

    public Fragment getmCurrentFragement(){
        return mCurrentFragement;
    }
}
