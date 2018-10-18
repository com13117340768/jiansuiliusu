package com.ayunyi.mssyy.rw.main.personal.address.detaiaddress;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.SwitchCompat;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.ayunyi.mssyy.rw.R;
import com.ayunyi.mssyy.rw.R2;
import com.yy.core.fragments.RedWineFragment;
import com.yy.core.net.RestClient;
import com.yy.core.net.callback.ISuccess;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by ft on 2018/10/18.
 */
public class NeyLyAddressFragment extends RedWineFragment {

    @BindView(R2.id.toolbar)
    Toolbar toolbar = null;

    private String mTitle = "";
    public static final String TITLE = "TITLE";


    @BindView(R2.id.newly_Addressee)
    AppCompatEditText newly_Addressee_name = null;

    @BindView(R2.id.newly_phone)
    AppCompatEditText newly_phone = null;

    @BindView(R2.id.newly_province)
    AppCompatEditText newly_province = null;

    @BindView(R2.id.newly_detailed_address)
    AppCompatEditText newly_detailed_address = null;

    @BindView(R2.id.newly_switch)
    SwitchCompat newly_switch = null;

    @BindView(R2.id.newly_commit_button)
    AppCompatButton newly_button = null;


    @OnClick(R2.id.newly_commit_button)
    void SubButtonClick() {
        if (!checkForm()) {
            Toast.makeText(getContext(), "请确认格式输入正确再提交！", Toast.LENGTH_SHORT).show();
        } else {
            RestClient.builder()
                    .loader(getContext())
                    .url("coupon.php")
                    .success(new ISuccess() {
                        @Override
                        public void onSuccess(String response) {
                            Toast.makeText(getContext(), "提交成功！", Toast.LENGTH_SHORT).show();
                            getSupportDelegate().pop();
                        }
                    })
                    .build()
                    .get();


        }
    }


    private boolean checkForm() {
        final String name = newly_Addressee_name.getText().toString();
        final String phone = newly_phone.getText().toString();
        final String detailed_address = newly_detailed_address.getText().toString();
        final String province = newly_province.getText().toString();
        //    final boolean isChecked = newly_switch.isChecked();
        boolean isPass = true;
        if (name.isEmpty()) {
            isPass = false;
        }
        if (phone.isEmpty() || phone.length() != 11) {
            isPass = false;
        }
        if (province.isEmpty()) {
            isPass = false;
        }

        if (detailed_address.isEmpty()) {
            isPass = false;
        }
        return isPass;
    }


    public static NeyLyAddressFragment getInstance(String title) {
        Bundle bundle = new Bundle();
        bundle.putString(TITLE, title);
        NeyLyAddressFragment fragment = new NeyLyAddressFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            mTitle = bundle.getString(TITLE);
        }
    }

    @Override
    public Object setLayout() {
        return R.layout.fragment_receiving_address;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {
        newly_button.setText("提交");
        toolbar.setTitle(mTitle);
        initToolbarNav(toolbar);


    }
}
