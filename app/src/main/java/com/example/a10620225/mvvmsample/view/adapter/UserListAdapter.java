package com.example.a10620225.mvvmsample.view.adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.a10620225.mvvmsample.R;
import com.example.a10620225.mvvmsample.databinding.ItemUserBinding;
import com.example.a10620225.mvvmsample.model.User;
import com.example.a10620225.mvvmsample.viewModel.ItemUserViewModel;

import java.util.Collections;
import java.util.List;

/**
 * Created by 10620225 on 12/20/2017.
 */

public class UserListAdapter extends RecyclerView.Adapter<UserListAdapter.Holder> {
    List<User> userList;
    public UserListAdapter()
    {
        userList = Collections.emptyList();
    }
    public void setUserList(List<User> userList){this.userList = userList;}
    public class Holder extends RecyclerView.ViewHolder{
        ItemUserBinding itemUserBinding;

         public Holder(ItemUserBinding itemUserBinding){
             super(itemUserBinding.itemPeople);
             this.itemUserBinding = itemUserBinding;
         }
         public void bindUser(User user){
             if(itemUserBinding.getItemUser() == null){
                 itemUserBinding.setItemUser(new ItemUserViewModel(user));
             }
             else{
                 itemUserBinding.getItemUser().setUser(user);
             }
         }
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
            ItemUserBinding itemUserBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_user, parent, false);
            return new Holder(itemUserBinding);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
            holder.bindUser(userList.get(position));
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }
}
