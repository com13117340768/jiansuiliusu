package com.ayunyi.mssyy.rw.main.personal.address;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.ayunyi.mssyy.rw.R;
import com.ayunyi.mssyy.rw.R2;
import com.ayunyi.mssyy.rw.main.personal.address.detaiaddress.DetailAddressFragment;
import com.ayunyi.mssyy.rw.main.personal.address.detaiaddress.NeyLyAddressFragment;
import com.yy.core.fragments.RedWineFragment;
import com.yy.core.net.RestClient;
import com.yy.core.net.callback.ISuccess;
import com.yy.core.ui.recycler.MultipleItemEntity;
import com.yy.core.util.logger.FengLogger;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by ft on 2018/9/23.
 */
public class AddressFragment extends RedWineFragment implements ISuccess {


    @BindView(R2.id.rv_address)
    RecyclerView mRecyclerView = null;

    @BindView(R2.id.address_toolbar_main)
    Toolbar toolbar = null;


    @OnClick(R2.id.icon_address_add)
    void addAddressClick() {
        getSupportDelegate().start(NeyLyAddressFragment.getInstance("添加收货地址"));
    }

    @OnClick(R2.id.icon_po_return)
    void popAddressFragment() {
        getSupportDelegate().pop();
    }

    @Override
    public Object setLayout() {
        return R.layout.fragment_address;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {
        RestClient.builder()
                .url("address.php")
                .loader(getContext())
                .success(this)
                .build()
                .get();
    }

    @Override
    public void onSuccess(String response) {
        FengLogger.d("AddressFragment", response);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(layoutManager);
        List<MultipleItemEntity> itemEntities = new AddressDateConverter().setJsonData(response).convert();
        AddressAdapter addressAdapter = new AddressAdapter(itemEntities,this);
        mRecyclerView.setAdapter(addressAdapter);
      //  mRecyclerView.addOnItemTouchListener(AddressItemClickListener.create(this));
    }

    @Override
    public void onSupportVisible() {
        super.onSupportVisible();
        getSupportDelegate().hideSoftInput();
    }
}
