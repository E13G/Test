package com.jpinto.amaris.utils;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.jpinto.amaris.R;
import com.jpinto.amaris.ui.ex1.FirstFragment;
import com.jpinto.amaris.ui.ex2.SecondFragment;
import com.jpinto.amaris.ui.ex3.ThirdFragment;
import com.jpinto.amaris.ui.ex4.FourthFragment;

/*
 * Created by JPinto on 1/25/2018.
 */

public class SimpleFragmentPagerAdapter extends FragmentPagerAdapter {

    private Context mContext;

    public SimpleFragmentPagerAdapter(Context context,FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new FirstFragment();
        } else if (position == 1){
            return new SecondFragment();
        } else if (position == 2) {
            return new ThirdFragment();
        } else {
            return new FourthFragment();
        }
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public CharSequence getPageTitle(int position) {

        if (position == 0) {
            return mContext.getString(R.string.category_first);
        } else if (position == 1) {
            return mContext.getString(R.string.category_second);
        } else if (position == 2) {
            return mContext.getString(R.string.category_third);
        } else {
            return mContext.getString(R.string.category_fourth);
        }

    }
}
