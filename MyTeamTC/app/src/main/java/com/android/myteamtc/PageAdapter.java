package com.android.myteamtc;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;


public class PageAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public PageAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                return new Fragment_Home();
            case 1:
                return new Fragment_Ctgram();
            case 2:
                return new Fragment_Goods();
            case 3:
                return new Fragment_Art();
            case 4:
                return new Fragment_Place();
            case 5:
                return new Fragment_Work();

            default:
                return null;
        }
    }

    public int getCount() {
        return mNumOfTabs;
    }
}
