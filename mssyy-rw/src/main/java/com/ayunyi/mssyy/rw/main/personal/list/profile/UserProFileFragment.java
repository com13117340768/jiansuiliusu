package com.ayunyi.mssyy.rw.main.personal.list.profile;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.ayunyi.mssyy.rw.R;
import com.ayunyi.mssyy.rw.R2;
import com.ayunyi.mssyy.rw.main.personal.list.ListAdapter;
import com.ayunyi.mssyy.rw.main.personal.list.ListBean;
import com.ayunyi.mssyy.rw.main.personal.list.ListItemType;
import com.ayunyi.mssyy.rw.main.personal.list.settings.ISubmitReName;
import com.ayunyi.mssyy.rw.main.personal.list.settings.NameFragment;
import com.yy.core.fragments.RedWineFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import me.yokeyword.fragmentation.anim.FragmentAnimator;

/**
 * Created by ft on 2018/9/14.
 */
public class UserProFileFragment extends RedWineFragment implements ISubmitReName {


    @BindView(R2.id.toolbar_user_profile)
    Toolbar toolbar = null;

    @BindView(R2.id.rv_user_profile)
    RecyclerView recyclerView = null;

    @Override
    public Object setLayout() {
        return R.layout.delegate_user_profile;
    }

    View view = null;


    @Override
    public FragmentAnimator onCreateFragmentAnimator() {
        return new FragmentAnimator();
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        toolbar.setBackgroundColor(this.getResources().getColor(R.color.app_main));
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {


        NameFragment nameDelegate = new NameFragment();
        nameDelegate.setIReNameLister(this);

        view = rootView;

        ListBean image = new ListBean.Builder()
                .setId(UserProfileClickListener.image)
                .setItemType(ListItemType.ITEM_AVATAR)
                .setImageUrl("http://img2.woyaogexing.com/2017/09/02/d5c9dceec0060119%21400x400_big.jpg")
                .setValue("上传头像")
                .build();

        ListBean name = new ListBean.Builder()
                .setId(UserProfileClickListener.name)
                .setText("昵称")
                .setDelegate(nameDelegate)
                .setItemType(ListItemType.ITEM_NAME)
                .setValue("未设置昵称")
                .build();

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
        textView.setText(rename);
    }
}
