package org.aihdint.aihd.PageAdapters;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import org.aihdint.aihd.fragments.dm_initial.Initial_page_view_1;
import org.aihdint.aihd.fragments.dm_initial.Initial_page_view_2;
import org.aihdint.aihd.fragments.dm_initial.Initial_page_view_3;
import org.aihdint.aihd.fragments.dm_initial.Initial_page_view_4;
import org.aihdint.aihd.fragments.dm_initial.Initial_page_view_5;

public class DM_Initial_View_Adapter extends FragmentStatePagerAdapter {

    private int tabsNumber;

    public DM_Initial_View_Adapter(FragmentManager fm, int tabsNumber) {
        super(fm);
        this.tabsNumber = tabsNumber;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                return new Initial_page_view_1();
            case 1:
                return new Initial_page_view_2();
            case 2:
                return new Initial_page_view_3();
            case 3:
                return new Initial_page_view_4();
            case 4:
                return new Initial_page_view_5();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tabsNumber;
    }

}