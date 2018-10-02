package com.anb.sg_ap1.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.anb.sg_ap1.ListFragment;
import com.anb.sg_ap1.model.User;

import java.util.ArrayList;

public class ListPagerAdapter extends FragmentPagerAdapter {

    ArrayList<User> listUser;

    public ListPagerAdapter(FragmentManager fm, ArrayList<User> listUser) {
        super(fm);
        this.listUser = listUser;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return ListFragment.newInstance("Laki-laki", listUser);
            case 1:
                return ListFragment.newInstance("Perempuan", listUser);
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Laki-laki";
            case 1:
                return "Perempuan";
            default:
                return null;
        }

    }
}