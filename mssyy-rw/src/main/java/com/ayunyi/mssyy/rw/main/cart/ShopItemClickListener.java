package com.ayunyi.mssyy.rw.main.cart;

import android.graphics.Color;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.Toast;

import com.ayunyi.mssyy.rw.R;
import com.ayunyi.mssyy.rw.main.index.IndexItemClickListener;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.SimpleClickListener;
import com.hss01248.dialog.StyledDialog;
import com.hss01248.dialog.interfaces.MyDialogListener;
import com.hss01248.dialog.interfaces.MyItemDialogListener;
import com.yy.core.app.Latte;
import com.yy.core.fragments.LatteFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ft on 2018/9/5.
 */
public class ShopItemClickListener extends SimpleClickListener {


    private LatteFragment mLatteFragment;

    private ShopItemClickListener(LatteFragment latteFragment) {
        this.mLatteFragment = latteFragment;
    }

    public static SimpleClickListener create(LatteFragment latteFragment) {
        return new ShopItemClickListener(latteFragment);
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

    }

    @Override
    public void onItemLongClick(BaseQuickAdapter adapter, View view, int position) {
        Toast.makeText(Latte.getApplicationContext(), position + "", Toast.LENGTH_SHORT).show();

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
                showToast("" + i);
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
        Toast.makeText(Latte.getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }


}
