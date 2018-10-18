package com.ayunyi.mssyy.rw.main.cart;

import android.graphics.Color;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.Toast;

import com.ayunyi.mssyy.rw.main.discover.fragmentation.DetailFragment;
import com.ayunyi.mssyy.rw.main.index.detail.GoodsDetailFragment;
import com.ayunyi.mssyy.rw.main.personal.address.detaiaddress.DetailAddressFragment;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.SimpleClickListener;
import com.hss01248.dialog.StyledDialog;
import com.hss01248.dialog.interfaces.MyItemDialogListener;
import com.yy.core.app.RedWine;
import com.yy.core.fragments.RedWineFragment;
import com.yy.core.net.RestClient;
import com.yy.core.net.callback.ISuccess;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ft on 2018/9/5.
 */
public class ShopItemClickListener extends SimpleClickListener {


    private RedWineFragment mLatteFragment;

    private ShopItemClickListener(RedWineFragment latteFragment) {
        this.mLatteFragment = latteFragment;
    }

    public static SimpleClickListener create(RedWineFragment latteFragment) {
        return new ShopItemClickListener(latteFragment);
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        mLatteFragment.start(new GoodsDetailFragment());
    }

    @Override
    public void onItemLongClick(BaseQuickAdapter adapter, View view, int position) {
        Toast.makeText(RedWine.getApplicationContext(), position + "", Toast.LENGTH_SHORT).show();

        SpannableString spannableString = new SpannableString("删除");
        ForegroundColorSpan colorSpan = new ForegroundColorSpan(Color.parseColor("#ffcc0000"));
        spannableString.setSpan(colorSpan, 0, spannableString.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        List<CharSequence> list = new ArrayList<>();
        list.add("取消");
        list.add("保存到收藏夹");
        list.add(spannableString);
        StyledDialog.buildIosSingleChoose(list, new MyItemDialogListener() {
            @Override
            public void onItemClick(CharSequence charSequence, int i) {
                switch (i) {
                    case 0:

                        break;
                    case 1:
                        RestClient.builder()
                                .url("coupon.php")
                                .loader(mLatteFragment.getContext())
                                .success(new ISuccess() {
                                    @Override
                                    public void onSuccess(String response) {
                                        showToast("添加收藏夹成功!");
                                    }
                                })
                                .build()
                                .get();
                        break;
                    case 2:
                        break;
                    default:
                        break;
                }

            }
        })
                .show();
    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

    }

    @Override
    public void onItemChildLongClick(BaseQuickAdapter adapter, View view, int position) {

    }

    private void showToast(String msg) {
        Toast.makeText(RedWine.getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }


}
