package com.ayunyi.mssyy.rw.main.personal.user.profile;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.net.Uri;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.widget.Toast;

import com.ayunyi.mssyy.rw.R;
import com.ayunyi.mssyy.rw.main.personal.user.ListBean;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.SimpleClickListener;
import com.yy.core.fragments.RedWineFragment;
import com.yy.core.net.RestClient;
import com.yy.core.net.callback.ISuccess;
import com.yy.core.ui.date.DateDialogUtil;
import com.yy.core.util.callback.CallbackManager;
import com.yy.core.util.callback.CallbackType;
import com.yy.core.util.callback.IGlobalCallback;
import com.yy.core.util.logger.FengLogger;

import de.hdodenhof.circleimageview.CircleImageView;


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
                CallbackManager.getInstance().addCallback(CallbackType.ON_CROP, new IGlobalCallback<Uri>() {
                    @Override
                    public void executeCallback(Uri uri) {
                        FengLogger.d("ON_CROP",uri.getPath());
                        CircleImageView circleImageView = view.findViewById(R.id.img_user_profile);
                        Glide.with(DELEGATE)
                                .load(uri)
                                .into(circleImageView);

                        RestClient.builder()
                                .url("baidu_image.php")
                                .loader(DELEGATE.getContext())
                              //  .file(uri.getPath())
                                .success(new ISuccess() {
                                    @Override
                                    public void onSuccess(String response) {
                                        Toast.makeText(DELEGATE.getContext(), "头像同步成功", Toast.LENGTH_SHORT).show();
                                        //通知服务器更新信息
                                        RestClient.builder()
                                                .url("baidu_image.php")
                                                .loader(DELEGATE.getContext())
                                                .success(new ISuccess() {
                                                    @Override
                                                    public void onSuccess(String response) {
                                                        //获取更新后的用户信息，然后更新本地数据库
                                                        Toast.makeText(DELEGATE.getContext(), "头像已保存数据库", Toast.LENGTH_SHORT).show();
                                                    }
                                                })
                                                .build()
                                                .get();
                                    }
                                })
                                .build()
                              //   .upload();
                                .get();
                    }
                });
                DELEGATE.startCameraWithCheck();
                break;
            case name:
                RedWineFragment latteFragment = bean.getDelegate();
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
