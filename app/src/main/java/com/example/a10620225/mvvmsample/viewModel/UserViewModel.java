package com.example.a10620225.mvvmsample.viewModel;

import android.content.Context;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.util.Log;
import android.view.View;

import com.example.a10620225.mvvmsample.app.MyApplication;
import com.example.a10620225.mvvmsample.model.User;
import com.example.a10620225.mvvmsample.model.UserResponse;
import com.example.a10620225.mvvmsample.network.UsersService;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

import static com.example.a10620225.mvvmsample.app.Constants.RANDOM_USER_URL;

/**
 * Created by 10620225 on 12/20/2017.
 */

public class UserViewModel extends Observable {
    private static final String TAG = UserViewModel.class.getName();
    @Inject
    UsersService usersService;

    public ObservableInt progressBar;
    public ObservableInt userRecycler;
    public ObservableInt userLabel;
    public ObservableField<String> messageLabel;

    private List<User> userList;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    public UserViewModel(Context context){
        Log.d("hello" , " world");
        ((MyApplication)context).getAppComponent().inject(this);
        userList = new ArrayList<>();
        progressBar = new ObservableInt(View.GONE);
        userRecycler = new ObservableInt(View.GONE);
        userLabel = new ObservableInt(View.VISIBLE);
        messageLabel = new ObservableField<>("Press the button to load content");
    }
    public void onClickFab(View view){
        System.out.println(TAG + " UserViewModel bean onClick");
        initializeView();
        fetchUserList();
    }
    public void initializeView(){
        progressBar.set(View.VISIBLE);
        userRecycler.set(View.VISIBLE);
        userLabel.set(View.GONE);
    }
    public void fetchUserList(){
        Disposable disposable = usersService.fetchUsers(RANDOM_USER_URL)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<UserResponse>() {
                               @Override
                               public void accept(UserResponse userResponse) throws Exception {
                                   updateUserList(userResponse.getPeopleList());
                                   progressBar.set(View.GONE);
                               }
                           },
                        new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Exception {
                                progressBar.set(View.GONE);
                                messageLabel.set("Error in loading....!" + throwable.getMessage());
                                userLabel.set(View.VISIBLE);
                                userRecycler.set(View.GONE);
                            }
                        });
        compositeDisposable.add(disposable);
    }
    public void updateUserList(List<User> users){
        userList.addAll(users);
        Log.v(TAG, "msg "+ new Integer(userList.size()).toString() );
        setChanged();
        notifyObservers();
    }
    public List<User> getUserList(){
        return userList;
    }
    private void unSubscribeFromObservable() {
        if (compositeDisposable != null && !compositeDisposable.isDisposed()) {
            compositeDisposable.dispose();
        }
    }

    public void reset() {
        unSubscribeFromObservable();
        compositeDisposable = null;
    }
}
