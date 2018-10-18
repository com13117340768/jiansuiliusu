package com.ayunyi.mssyy.rw.main.personal.address.detaiaddress;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.SwitchCompat;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.ayunyi.mssyy.rw.R;
import com.ayunyi.mssyy.rw.R2;
import com.hss01248.dialog.StyledDialog;
import com.hss01248.dialog.interfaces.MyDialogListener;
import com.joanzapata.iconify.IconDrawable;
import com.yy.core.fragments.RedWineFragment;
import com.yy.core.net.RestClient;
import com.yy.core.net.callback.ISuccess;
import com.yy.core.util.icon.RWIcons;
import com.yy.core.util.logger.FengLogger;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by ft on 2018/10/15.
 */
public class DetailAddressFragment extends RedWineFragment {
    String name = "";
    String phone = "";
    String address = "";
    String province = "";
    boolean isDefault = false;
    @BindView(R2.id.toolbar)
    Toolbar toolbar = null;
    private String mTitle = "";
    private Bundle mBundle = null;
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


    @OnClick(R2.id.newly_commit_button)
    void SubButtonClick() {
        if (checkEditTextChange()) {
            Toast.makeText(getContext(), "您没有做出修改", Toast.LENGTH_SHORT).show();
            getSupportDelegate().pop();
        } else {
            RestClient.builder()
                    .url("coupon.php")
                    .loader(getContext())
                    .success(new ISuccess() {
                        @Override
                        public void onSuccess(String response) {
                            Toast.makeText(getContext(), "修改成功", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .build().get();

        }

    }

    private boolean checkEditTextChange() {

        final String name_ = newly_Addressee_name.getText().toString();
        final String phone_ = newly_phone.getText().toString();
        final String detailed_address_ = newly_detailed_address.getText().toString();
        final String newly_province_ = newly_province.getText().toString();
        final boolean isDefault_ = newly_switch.isChecked();

        boolean isChange = true;
        if (!name_.equals(name)) {
            isChange = false;
        }
        if (!phone_.equals(phone)) {
            isChange = false;
        }
        if (!detailed_address_.equals(address)) {
            isChange = false;
        }
        if (!newly_province_.equals(province)) {
            isChange = false;
        }
        if (!isDefault_ == isDefault) {
            isChange = false;
        }

        return isChange;
    }


    @Override
    public Object setLayout() {
        return R.layout.fragment_receiving_address;
    }


    public static DetailAddressFragment getInstance(String title) {
        Bundle bundle = new Bundle();
        bundle.putString(TITLE, title);
        DetailAddressFragment fragment = new DetailAddressFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    public static DetailAddressFragment getInstance(Bundle bundle) {
        DetailAddressFragment fragment = new DetailAddressFragment();
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        mBundle = bundle;
        String title = bundle != null ? bundle.getString(TITLE) : "";
        if (!TextUtils.isEmpty(title)) {
            mTitle = title;
        }
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {
        toolbar.setTitle(mTitle);
        initToolbarNav(toolbar);

        newly_phone.addTextChangedListener(new ClassOfTextWatcher());

        if (mBundle != null) {
            name = mBundle.getString("name");
            phone = mBundle.getString("phone");
            address = mBundle.getString("address");
            isDefault = mBundle.getBoolean("isDefault");
            province = "上海市 普陀区 长征镇";

            newly_switch.setChecked(isDefault);
            newly_Addressee_name.setText(name);
            newly_phone.setText(phone);
            newly_detailed_address.setText(address);
            if (mTitle.equals("编辑收货地址")) {
                newly_province.setText(province);
            }
        }
    }

    @Override
    public boolean onBackPressedSupport() {
        if (!checkEditTextChange()) {
            StyledDialog.buildMdAlert("有未保存的数据,确认要退出吗", "", new MyDialogListener() {
                @Override
                public void onFirst() {
                    getSupportDelegate().pop();
                }

                @Override
                public void onSecond() {
                }
            })
                    .setTitleColor(R.color.app_main_color)
                    .setBtnColor(R.color.app_main_color, R.color.app_main_color, R.color.app_main_color)
                    .show();
        } else {
            return false;
        }
        return true;
    }

    @Override
    public void onFragmentResult(int requestCode, int resultCode, Bundle data) {
        super.onFragmentResult(requestCode, resultCode, data);
        Toast.makeText(getContext(), "调用了", Toast.LENGTH_SHORT).show();
        FengLogger.d("taotao", requestCode + data.toString());


    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        FengLogger.d("taotao", "Activity----" + requestCode + data.toString());
    }

    private boolean checkForm() {
        final String name = newly_Addressee_name.getText().toString();
        final String phone = newly_phone.getText().toString();
        final String detailed_address = newly_detailed_address.getText().toString();
        final String province = newly_province.getText().toString();
        final boolean isChecked = newly_switch.isChecked();
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

    private class ClassOfTextWatcher implements TextWatcher {

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }


        @Override
        public void afterTextChanged(Editable s) {

        }
    }


    protected void initToolbarNav(Toolbar toolbar) {
        toolbar.setNavigationIcon(new IconDrawable(getContext(), RWIcons.icon_return).color(Color.WHITE).actionBarSize());
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!checkEditTextChange()) {
                    StyledDialog.buildMdAlert("有未保存的数据,确认要退出吗", "", new MyDialogListener() {
                        @Override
                        public void onFirst() {
                            getSupportDelegate().pop();
                        }

                        @Override
                        public void onSecond() {
                        }
                    })
                            .setTitleColor(R.color.app_main_color)
                            .setBtnColor(R.color.app_main_color, R.color.app_main_color, R.color.app_main_color)
                            .show();
                } else {
                    getSupportDelegate().pop();
                }
            }
        });
    }


}
