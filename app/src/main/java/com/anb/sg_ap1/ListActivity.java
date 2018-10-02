package com.anb.sg_ap1;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.anb.sg_ap1.adapter.ListPagerAdapter;
import com.anb.sg_ap1.database.UserRepo;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ListActivity extends AppCompatActivity {

    @BindView(R.id.viewpager)
    ViewPager vp;
    @BindView(R.id.tabLayout)
    TabLayout tl;

    UserRepo repo = new UserRepo(this);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_data);

        ButterKnife.bind(this);

        ListPagerAdapter mSectionsPagerAdapter = new ListPagerAdapter(getSupportFragmentManager(), repo.getUserList());
        vp.setAdapter(mSectionsPagerAdapter);
        tl.setupWithViewPager(vp);

    }
}
