package com.ayunyi.mssyy.rw.main.personal.user.profile;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.ayunyi.mssyy.rw.R;
import com.ayunyi.mssyy.rw.R2;
import com.ayunyi.mssyy.rw.login.LoginFragment;
import com.ayunyi.mssyy.rw.main.UserPerpesKeys;
import com.ayunyi.mssyy.rw.main.personal.user.ListAdapter;
import com.ayunyi.mssyy.rw.main.personal.user.ListBean;
import com.ayunyi.mssyy.rw.main.personal.user.ListItemType;
import com.ayunyi.mssyy.rw.main.personal.user.settings.ISubmitReName;
import com.ayunyi.mssyy.rw.main.personal.user.settings.NameFragment;
import com.bumptech.glide.Glide;
import com.hss01248.dialog.StyledDialog;
import com.hss01248.dialog.interfaces.MyDialogListener;
import com.yy.core.app.AccountManager;
import com.yy.core.fragments.RedWineFragment;
import com.yy.core.util.sharepreference.RedWinePreference;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import me.yokeyword.fragmentation.anim.FragmentAnimator;

/**
 * Created by ft on 2018/9/14.
 */
public class UserProFileFragment extends RedWineFragment implements ISubmitReName {


    @BindView(R2.id.toolbar_user_profile)
    Toolbar toolbar = null;

    @BindView(R2.id.rv_user_profile)
    RecyclerView recyclerView = null;


    @BindView(R2.id.user_exit_login_state)
    AppCompatButton compatButton = null;


    @OnClick(R2.id.user_exit_login_state)
    void ExitLoginState() {
        checkLoginState();
    }


    @OnClick(R2.id.icon_exit)
    void ExitUserProFile() {
        getSupportDelegate().pop();
    }


    View view = null;


    @Override
    public FragmentAnimator onCreateFragmentAnimator() {
        return new FragmentAnimator();
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_user_profile;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {
        setCheckState();
        NameFragment nameDelegate = new NameFragment();
        nameDelegate.setIReNameLister(this);
        view = rootView;
        ListBean image;
        String uriPah = RedWinePreference.getCustomAppProfile(UserPerpesKeys.URI_PATH);
        if (!uriPah.isEmpty()) {
            image = new ListBean.Builder()
                    .setId(UserProfileClickListener.image)
                    .setItemType(ListItemType.ITEM_USER_PROFILE)
                    .setImageUrl(uriPah)
                    .setValue("头像")
                    .build();

        } else {
            image = new ListBean.Builder()
                    .setId(UserProfileClickListener.image)
                    .setItemType(ListItemType.ITEM_USER_PROFILE)
                    .setImageUrl("http://img2.woyaogexing.com/2017/09/02/d5c9dceec0060119%21400x400_big.jpg")
                    .setValue("头像")
                    .build();
        }
        ListBean name;

        String uName = RedWinePreference.getCustomAppProfile(UserPerpesKeys.USER_NAME);
        if (!uName.isEmpty()) {
            name = new ListBean.Builder()
                    .setId(UserProfileClickListener.name)
                    .setText("昵称")
                    .setDelegate(nameDelegate)
                    .setItemType(ListItemType.ITEM_NAME)
                    .setValue(uName)
                    .build();

        } else {
            name = new ListBean.Builder()
                    .setId(UserProfileClickListener.name)
                    .setText("昵称")
                    .setDelegate(nameDelegate)
                    .setItemType(ListItemType.ITEM_NAME)
                    .setValue("未设置昵称")
                    .build();
        }

        ListBean sex = new ListBean.Builder()
                .setId(UserProfileClickListener.sex)
                .setItemType(ListItemType.ITEM_NORMAL)
                .setText("性别")
                .setValue("未设置性别")
                .build();

        ListBean age = new ListBean.Builder()
                .setId(UserProfileClickListener.age)
                .setItemType(ListItemType.ITEM_NORMAL)
                .setText("生日")
                .setValue("未设置生日")
                .build();

        List<ListBean> listBeans = new ArrayList<>();
        listBeans.add(image);
        listBeans.add(name);
        listBeans.add(age);
        listBeans.add(sex);


        //设置recyclerView
        final LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        ListAdapter listAdapter = new ListAdapter(listBeans);
        recyclerView.setAdapter(listAdapter);
        recyclerView.addOnItemTouchListener(new UserProfileClickListener(this));
    }

    @Override
    public void submitReName(String rename) {
        final AppCompatTextView textView = view.findViewById(R.id.tv_arrow_value_one);
        RedWinePreference.addCustomAppProfile(UserPerpesKeys.USER_NAME, rename);
        textView.setText(rename);
    }


    private boolean setCheckState() {
        if (!AccountManager.checkAccount()) {
            compatButton.setText("点击登录");
            compatButton.setBackgroundColor(Color.GRAY);
            return false;
        } else {
            compatButton.setText("退出登录");
            compatButton.setBackgroundColor(Color.RED);
            return true;
        }
    }

    private void checkLoginState() {
        if (setCheckState()) {
            StyledDialog.buildMdAlert("确认退出登录？", "", new MyDialogListener() {
                @Override
                public void onFirst() {
                    AccountManager.setSignState(false);
                    setCheckState();
                    getSupportDelegate().pop();
                    //执行退出登录逻辑
                }

                @Override
                public void onSecond() {

                }
            })
                    .setTitleColor(R.color.app_main_color)
                    .setBtnColor(R.color.app_main_color, R.color.app_main_color, R.color.app_main_color)
                    .show();

        } else {
            //执行登录逻辑
            getSupportDelegate().pop();
            getSupportDelegate().start(new LoginFragment());
        }

    }
}
