package com.antoinegourtay.mob_e16_android.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.antoinegourtay.mob_e16_android.fragments.CoursFragment;
import com.antoinegourtay.mob_e16_android.fragments.TransactionFragment;

import java.util.ArrayList;

/**
 * Created by antoinegourtay on 20/12/2017.
 */

public class WalletAdapter extends FragmentPagerAdapter {

    private Fragment mCurrentFragement;

    public WalletAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new CoursFragment();
        } else if (position == 1){
            return new TransactionFragment();
        } else {
            return null;
        }
    }


    @Override
    public int getCount() {
        return 5;
    }

    public Fragment getmCurrentFragement(){
        return mCurrentFragement;
    }
}
