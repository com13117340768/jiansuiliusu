package com.ayunyi.mssyy.rw.main.sort.content;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;

import com.ayunyi.mssyy.rw.R;
import com.ayunyi.mssyy.rw.R2;
import com.yy.core.fragments.RedWineFragment;
import com.yy.core.net.RestClient;

import java.util.List;

import butterknife.BindView;

/**
 * Created by ft on 2018/8/19.
 */
public class ContentFragment extends RedWineFragment {
    private static final String ARG_CONTENT_ID = "CONTENT_ID";
    private int mContentId = -1;
    private List<SectionBean> mData = null;

    @BindView(R2.id.rv_list_content)
    RecyclerView mRecyclerView = null;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            mContentId = bundle.getInt(ARG_CONTENT_ID);
        }
    }

    public static ContentFragment newInstance(int contentId) {
        final Bundle args = new Bundle();
        args.putInt(ARG_CONTENT_ID, contentId);
        final ContentFragment contentDelegate = new ContentFragment();
        contentDelegate.setArguments(args);
        return contentDelegate;
    }


    @Override
    public Object setLayout() {
        return R.layout.delegate_list_content;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {
        final StaggeredGridLayoutManager manager =
                new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(manager);
        initData();
    }

    private void initData() {
        RestClient.builder()
                .url("sort_content_list.php?contentId=" + mContentId)
                .success(response -> {
                    Log.d("HAHAHA", "initData=" + response);
                    mData = new SectionDataConverter().convert(response);
                    final SectionAdapter sectionAdapter = new SectionAdapter(R.layout.item_section_content,
                            R.layout.item_section_header, mData);
                    mRecyclerView.setAdapter(sectionAdapter);
                })
                .failure(() -> {
                })
                .error((code, msg) -> {
                })
                .build()
                .get();
    }
}
