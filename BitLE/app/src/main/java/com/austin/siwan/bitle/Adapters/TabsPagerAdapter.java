package com.austin.siwan.bitle.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.austin.siwan.bitle.Fragments.FragmentDrink;
import com.austin.siwan.bitle.Fragments.FragmentSnack;

/**
 * Created by Jojo on 7/19/14.
 */

public class TabsPagerAdapter extends FragmentStatePagerAdapter {

    public TabsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int index) {

        switch (index) {
            case 0:
                // Snack fragment activity
                return new FragmentSnack();
            case 1:
                // Drink fragment activity
                return new FragmentDrink();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        // get item count - equal to number of tabs
        return 2;
    }

    @Override
    public int getItemPosition(Object object) {
        return super.getItemPosition(object);
    }

}
