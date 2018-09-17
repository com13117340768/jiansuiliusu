package com.ayunyi.mssyy.rw.main.personal.list.profile;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.ayunyi.mssyy.rw.R;
import com.ayunyi.mssyy.rw.main.personal.list.ListBean;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.SimpleClickListener;
import com.yy.core.fragments.LatteFragment;
import com.yy.core.ui.date.DateDialogUtil;

/**
 * Created by ft on 2018/9/14.
 */
public class UserProfileClickListener extends SimpleClickListener {


    public final static int image = 1;
    public final static int name = 2;
    public final static int sex = 3;
    public final static int age = 4;
    private int chosen = 0;
    private UserProFileFragment DELEGATE;
    private String[] mGenders = new String[]{"男", "女", "保密"};

    UserProfileClickListener(UserProFileFragment delegate) {
        this.DELEGATE = delegate;
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        ListBean bean = (ListBean) adapter.getData().get(position);
        final int id = bean.getId();
        switch (id) {
            case image:
                DELEGATE.startCameraWithCheck();
                break;
            case name:
                LatteFragment latteFragment = bean.getDelegate();
                DELEGATE.getSupportDelegate().start(latteFragment);
                break;
            case sex:
                getGenderDialog(new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        final AppCompatTextView textView = view.findViewById(R.id.tv_arrow_value);
                        textView.setText(mGenders[which]);
                        chosen = which;
                        dialog.cancel();
                    }
                });
                break;
            case age:
                final DateDialogUtil dateDialogUtil = new DateDialogUtil();
                dateDialogUtil.setDateListener(new DateDialogUtil.IDateListener() {
                    @Override
                    public void onDateChange(String date) {
                        final AppCompatTextView textView = view.findViewById(R.id.tv_arrow_value);
                        textView.setText(date);
                    }
                });
                dateDialogUtil.showDialog(DELEGATE.getContext());
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

    private void getGenderDialog(DialogInterface.OnClickListener listener) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(DELEGATE.getContext());
        builder.setSingleChoiceItems(mGenders, chosen, listener);
        builder.show();
    }

}
