package com.ayunyi.mssyy.rw.main.discover.fragmentation;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ayunyi.mssyy.rw.R;
import com.yy.core.fragments.RedWineFragment;


/**
 * Created by YoKeyword on 16/2/7.
 */
public class ModifyDetailFragment extends RedWineFragment {
    private static final String ARG_TITLE = "arg_title";

    private Toolbar mToolbar;
    private EditText mEtModiyTitle;
    private Button mBtnModify, mBtnNext;

    private String mTitle;

    public static ModifyDetailFragment newInstance(String title) {
        Bundle args = new Bundle();
        ModifyDetailFragment fragment = new ModifyDetailFragment();
        args.putString(ARG_TITLE, title);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle args = getArguments();
        if (args != null) {
            mTitle = args.getString(ARG_TITLE);
        }
    }

    @Override
    public Object setLayout() {
        return R.layout.fragment_modify_detail;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {
        initView(rootView);
    }

  /*  @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_modify_detail, container, false);
        initView(view);
        return view;
    }*/

    private void initView(View view) {
        mToolbar = (Toolbar) view.findViewById(R.id.toolbar);
        mEtModiyTitle = (EditText) view.findViewById(R.id.et_modify_title);
        mBtnModify = (Button) view.findViewById(R.id.btn_modify);
        mBtnNext = (Button) view.findViewById(R.id.btn_next);

        mToolbar.setTitle("start_result_test");
        initToolbarNav(mToolbar);

        mEtModiyTitle.setText(mTitle);

        // 显示 软键盘
//        showSoftInput(mEtModiyTitle);

        mBtnModify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString(DetailFragment.KEY_RESULT_TITLE, mEtModiyTitle.getText().toString());
                setFragmentResult(RESULT_OK, bundle);

                //    Toast.makeText(_mActivity, R.string.modify_success, Toast.LENGTH_SHORT).show();
            }
        });
        mBtnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //    start(CycleFragment.newInstance(1));
                Toast.makeText(_mActivity, "111111111111", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onSupportInvisible() {
        super.onSupportInvisible();
        getSupportDelegate().hideSoftInput();
    }

    protected void initToolbarNav(Toolbar toolbar) {
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportDelegate().pop();
            }
        });
    }

    @Override
    public boolean onBackPressedSupport() {
        if (getActivity().getSupportFragmentManager().getBackStackEntryCount() > 1) {
            getSupportDelegate().pop();
            return true;
        }
        return false;
    }
}
