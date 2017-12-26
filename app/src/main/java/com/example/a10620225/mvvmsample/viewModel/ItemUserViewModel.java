package com.example.a10620225.mvvmsample.viewModel;

import android.databinding.BaseObservable;
import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.a10620225.mvvmsample.model.User;

import java.util.Observable;

/**
 * Created by 10620225 on 12/20/2017.
 */

public class ItemUserViewModel extends BaseObservable {
    private User user;

    public ItemUserViewModel(User user){
        this.user = user;
    }
    public String gerProfileThumb(){
        return user.picture.medium;
    }
    @BindingAdapter("imageUrl") public static void setImageUrl(ImageView imageView, String url){
        Glide.with(imageView.getContext()).load(url).into(imageView);
    }
    public String getCell() { return user.cell; }

    public String getEmail() { return user.email; }

    public String getFullName() {

        user.fullName = user.name.title + "." + user.name.first + " " + user.name.last;

        return user.fullName;

    }
    public void setUser(User user) {
        this.user = user;
        notifyChange();
    }
}
