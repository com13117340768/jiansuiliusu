package com.ayunyi.mssyy.rw.main.personal.setup;

import android.view.View;
import android.widget.Toast;

import com.ayunyi.mssyy.rw.main.personal.user.ListBean;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.SimpleClickListener;
import com.yy.core.app.RedWine;
import com.yy.core.fragments.RedWineFragment;

/**
 * Created by ft on 2018/9/25.
 */
public class SystemClickListener extends SimpleClickListener {

    private final RedWineFragment DELEGATE;

    public SystemClickListener(RedWineFragment delegate) {
        this.DELEGATE = delegate;
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        ListBean bean = (ListBean) baseQuickAdapter.getData().get(position);
        int id = bean.getId();
        switch (id) {
            case 1:
                break;
            case 2:
                DELEGATE.getSupportDelegate().start(bean.getDelegate());
                break;
            case 3:
                Toast.makeText(RedWine.getApplicationContext(),"已是最新版本",Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }

    }

    @Override
    public void onItemLongClick(BaseQuickAdapter adapter, View view, int position) {


    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

    }

    @Override
    public void onItemChildLongClick(BaseQuickAdapter adapter, View view, int position) {

    }
}
