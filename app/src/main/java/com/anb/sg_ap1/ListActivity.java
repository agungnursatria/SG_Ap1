package com.anb.sg_ap1;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.anb.sg_ap1.adapter.ListUserAdapter;
import com.anb.sg_ap1.model.User;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {

    static ArrayList<User> listUser = new ArrayList<>();
    RecyclerView rv_user;
    ListUserAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_data);

        rv_user = findViewById(R.id.rv_user);

        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        adapter = new ListUserAdapter(listUser);
        rv_user.setAdapter(adapter);
        rv_user.setHasFixedSize(true);
        rv_user.setLayoutManager(mLayoutManager);

        Bundle extras = getIntent().getExtras();
        User user = new User();
        if (extras != null) {
            user.nama = extras.getString("nama");
            user.umur = extras.getInt("umur");
            user.gender = extras.getString("gender");
            listUser.add(user);
        }
    }
}
