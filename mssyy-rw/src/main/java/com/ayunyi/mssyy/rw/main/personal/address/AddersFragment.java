package com.ayunyi.mssyy.rw.main.personal.address;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.ayunyi.mssyy.rw.R;
import com.ayunyi.mssyy.rw.R2;
import com.yy.core.fragments.RedWineFragment;
import com.yy.core.net.RestClient;
import com.yy.core.net.callback.ISuccess;
import com.yy.core.ui.recycler.MultipleItemEntity;
import com.yy.core.util.logger.FengLogger;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by ft on 2018/9/23.
 */
public class AddersFragment extends RedWineFragment implements ISuccess {


    @BindView(R2.id.rv_address)
    RecyclerView mRecyclerView = null;

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
        AddressAdapter addressAdapter = new AddressAdapter(itemEntities);
        mRecyclerView.setAdapter(addressAdapter);
        mRecyclerView.addOnItemTouchListener(new AddressItemClickListener());
    }
}
