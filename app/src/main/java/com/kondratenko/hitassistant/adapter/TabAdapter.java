package com.kondratenko.hitassistant.adapter;

import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v13.app.FragmentStatePagerAdapter;

import com.kondratenko.hitassistant.fragment.Con_Lenght;
import com.kondratenko.hitassistant.fragment.Con_Press;
import com.kondratenko.hitassistant.fragment.Con_Temp;
import com.kondratenko.hitassistant.fragment.Con_Weight;

/**
 * Created by Manson on 20.10.2015.
 */
public class TabAdapter extends FragmentStatePagerAdapter {
    private int numberOfTabs;

    public TabAdapter(FragmentManager fm, int numberOfTabs) {
        super(fm);
        this.numberOfTabs = numberOfTabs;

    }

    @Override
    public Fragment getItem(int i) {
        switch (i){
            case 0:
                return new Con_Weight();
            case 1:
                return new Con_Lenght();
            case 2:
                return new Con_Temp();
            case 3:
                return new Con_Press();
            default: return null;
        }
    }

    @Override
    public int getCount() {
        return numberOfTabs;
    }
}
