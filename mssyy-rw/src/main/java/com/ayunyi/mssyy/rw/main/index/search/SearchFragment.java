package com.ayunyi.mssyy.rw.main.index.search;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.ayunyi.mssyy.rw.R;
import com.ayunyi.mssyy.rw.R2;
import com.yy.core.fragments.RedWineFragment;
import com.yy.core.net.RestClient;
import com.yy.core.net.callback.ISuccess;

import butterknife.BindView;
import butterknife.OnClick;
import me.yokeyword.fragmentation.anim.DefaultVerticalAnimator;
import me.yokeyword.fragmentation.anim.FragmentAnimator;

/**
 * Created by ft on 2018/9/26.
 */
public class SearchFragment extends RedWineFragment{

    @BindView(R2.id.rv_search)
    RecyclerView mRecyclerView = null;
    @BindView(R2.id.et_search_view)
    AppCompatEditText mSearchEdit = null;

    @OnClick(R2.id.icon_top_search_back)
    void onClickBack() {
        getSupportDelegate().pop();
    }

    @OnClick(R2.id.tv_top_search)
    void onClickSearch() {
        RestClient.builder()
                .url("index.php")
                .loader(getContext())
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        mSearchEdit.setText("");
                        Toast.makeText(getContext(),"功能还未完成",Toast.LENGTH_SHORT).show();
                    }
                })
                .build()
                .get();
    }

    @Override
    public Object setLayout() {
        return R.layout.fragment_search;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {

    }

    @Override
    public FragmentAnimator onCreateFragmentAnimator() {
        return new DefaultVerticalAnimator();
    }

    @Override
    public void onPause() {
        super.onPause();
        this.getSupportDelegate().hideSoftInput();
    }
}
