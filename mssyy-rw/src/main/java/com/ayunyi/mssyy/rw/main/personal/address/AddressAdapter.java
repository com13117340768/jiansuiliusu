package com.ayunyi.mssyy.rw.main.personal.address;

import android.os.Bundle;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.ayunyi.mssyy.rw.R;
import com.ayunyi.mssyy.rw.main.personal.address.detaiaddress.DetailAddressFragment;
import com.hss01248.dialog.StyledDialog;
import com.hss01248.dialog.interfaces.MyDialogListener;
import com.joanzapata.iconify.widget.IconTextView;
import com.yy.core.app.RedWine;
import com.yy.core.fragments.RedWineFragment;
import com.yy.core.net.RestClient;
import com.yy.core.net.callback.ISuccess;
import com.yy.core.ui.recycler.MultipleFields;
import com.yy.core.ui.recycler.MultipleItemEntity;
import com.yy.core.ui.recycler.MultipleRecyclerAdapter;
import com.yy.core.ui.recycler.MultipleViewHolder;

import java.util.List;

/**
 * Created by ft on 2018/9/24.
 */
public class AddressAdapter extends MultipleRecyclerAdapter {

    private final RedWineFragment redWineFragment;

    AddressAdapter(List<MultipleItemEntity> data, RedWineFragment redWineFragment) {
        super(data);
        this.redWineFragment = redWineFragment;
        addItemType(AddressItemType.ITEM_ADDRESS, R.layout.item_address);
    }

    @Override
    protected void convert(MultipleViewHolder holder, MultipleItemEntity entity) {
        super.convert(holder, entity);

        switch (holder.getItemViewType()) {
            case AddressItemType.ITEM_ADDRESS:

                String name = entity.getField(MultipleFields.NAME);
                String phone = entity.getField(AddressItemFields.PHONE);
                String address = entity.getField(AddressItemFields.ADDRESS);
                boolean isDefault = entity.getField(MultipleFields.TAG);
                int id = entity.getField(MultipleFields.ID);

                final AppCompatTextView nameText = holder.getView(R.id.tv_address_name);
                final AppCompatTextView phoneText = holder.getView(R.id.tv_address_phone);
                final AppCompatTextView addressText = holder.getView(R.id.tv_address_address);
                final AppCompatTextView deleteTextView = holder.getView(R.id.tv_address_delete);
                final AppCompatTextView defaultTextView = holder.getView(R.id.tv_address_default);
                final IconTextView arrow = holder.getView(R.id.icon_arrow);


                deleteTextView.setOnClickListener(v -> StyledDialog.buildMdAlert("删除后不可恢复,是否确认删除？", "", new MyDialogListener() {
                    @Override
                    public void onFirst() {
                        RestClient.builder()
                                .url("address.php")
                                .loader(RedWine.getApplicationContext())
                                .params("id", id)
                                .success(response -> remove(holder.getLayoutPosition()))
                                .build()
                                .post();
                    }

                    @Override
                    public void onSecond() {
                    }
                })
                        .setTitleColor(R.color.app_main_color)
                        .setBtnColor(R.color.app_main_color, R.color.app_main_color, R.color.app_main_color)
                        .show());


                arrow.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Bundle bundle = new Bundle();
                        bundle.putString(DetailAddressFragment.TITLE, "编辑收货地址");
                        bundle.putString("name", name);
                        bundle.putString("phone", phone);
                        bundle.putString("address", address);
                        bundle.putBoolean("isDefault", isDefault);
                        bundle.putInt("id", id);
                        if (redWineFragment != null) {
                            redWineFragment.startForResult(DetailAddressFragment.getInstance(bundle), 2);
                        }

                    }
                });

                addressText.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Bundle bundle = new Bundle();
                        bundle.putString(DetailAddressFragment.TITLE, "编辑收货地址");
                        bundle.putString("name", name);
                        bundle.putString("phone", phone);
                        bundle.putString("address", address);
                        bundle.putBoolean("isDefault", isDefault);
                        bundle.putInt("id", id);
                        if (redWineFragment != null) {
                            redWineFragment.start(DetailAddressFragment.getInstance(bundle));
                        }
                    }
                });
                if (isDefault) {
                    defaultTextView.setVisibility(View.VISIBLE);
                }
                nameText.setText(name);
                phoneText.setText(phone);
                addressText.setText(address);
                break;
            default:
                break;

        }


    }

    private void startNewlyAddFragment() {

    }
}
