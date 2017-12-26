package com.example.a10620225.mvvmsample.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import com.example.a10620225.mvvmsample.R;
import com.example.a10620225.mvvmsample.app.MyApplication;
import com.example.a10620225.mvvmsample.databinding.ContentHomeBinding;
import com.example.a10620225.mvvmsample.view.adapter.UserListAdapter;
import com.example.a10620225.mvvmsample.viewModel.UserViewModel;

import java.util.Observable;
import java.util.Observer;

import javax.inject.Inject;

public class Home extends AppCompatActivity implements Observer{
    private static final String TAG = Home.class.getName();
    @Inject
    UserViewModel userViewModel;

    ContentHomeBinding homeBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println(TAG + " UserViewModel bean onClick");
        ((MyApplication)getApplication()).getAppComponent().inject(this);
        initDataBinding();
        setupRecyclerView(homeBinding.userRecycler);
        setupObserver();
    }
    private void initDataBinding(){
        homeBinding = DataBindingUtil.setContentView(this, R.layout.content_home);
        homeBinding.setUserViewModel(userViewModel);
    }
    private void setupRecyclerView(RecyclerView recyclerView){
        UserListAdapter adapter = new UserListAdapter();
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
    private void setupObserver(){userViewModel.addObserver(this);}

    @Override
    public void update(Observable observable, Object o) {
        Log.v(TAG, "update called");
        UserListAdapter adapter = (UserListAdapter) homeBinding.userRecycler.getAdapter();
        UserViewModel userViewModel = (UserViewModel) observable;
        adapter.setUserList(userViewModel.getUserList());
        Log.v(TAG, "msgActivity" + new Integer(userViewModel.getUserList().size()));
        adapter.notifyDataSetChanged();
    }
    @Override protected void onDestroy() {
        super.onDestroy();
        userViewModel.reset();
    }
}
