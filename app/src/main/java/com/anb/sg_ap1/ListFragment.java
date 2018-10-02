package com.anb.sg_ap1;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.anb.sg_ap1.database.UserRepo;
import com.anb.sg_ap1.adapter.ListUserAdapter;
import com.anb.sg_ap1.model.User;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ListFragment extends Fragment implements ListUserAdapter.OnItemClickListener {

    @BindView(R.id.rv_list_data)
    RecyclerView rv_list_data;

    ListUserAdapter adapter;
    ArrayList<User> selectedUser;
    Context context;
    UserRepo repo;

    public static ListFragment newInstance(String gender, ArrayList<User> listUser) {
        ListFragment fragment = new ListFragment();
        Bundle args = new Bundle();
        args.putString(Constant.GENDER, gender);
        args.putParcelableArrayList(Constant.LISTUSER, listUser);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        context = view.getContext();
        repo = new UserRepo(context);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ArrayList<User> listUser = null;
        String gender = "";
        if (getArguments() != null) {
            listUser = getArguments().getParcelableArrayList(Constant.LISTUSER);
            gender = getArguments().getString(Constant.GENDER);
        }

        selectedUser = new ArrayList<>();
        if (listUser != null) {
            for (int i = 0; i < listUser.size(); i++){
                if (listUser.get(i).gender.equals(gender)){
                    selectedUser.add(listUser.get(i));
                }
            }
        }

        LinearLayoutManager mLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        adapter = new ListUserAdapter(selectedUser, this);
        rv_list_data.setAdapter(adapter);
        rv_list_data.setHasFixedSize(true);
        rv_list_data.setLayoutManager(mLayoutManager);
    }

    @Override
    public void onItemClick(User user) {
        repo.delete(user.id);
        int position = selectedUser.indexOf(user);
        selectedUser.remove(position);
        adapter.notifyItemRemoved(position);
    }
}