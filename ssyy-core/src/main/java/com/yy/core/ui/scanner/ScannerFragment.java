package com.yy.core.ui.scanner;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.yy.core.R;
import com.yy.core.R2;
import com.yy.core.fragments.RedWineFragment;
import com.yy.core.ui.camera.RequestCodes;
import com.yy.core.util.logger.FengLogger;

import butterknife.BindView;
import butterknife.OnClick;
import cn.bingoogolapple.qrcode.core.QRCodeView;
import cn.bingoogolapple.qrcode.zbar.ZBarView;
import me.yokeyword.fragmentation.anim.DefaultVerticalAnimator;
import me.yokeyword.fragmentation.anim.FragmentAnimator;

import static android.content.Context.VIBRATOR_SERVICE;

/**
 * Created by ft on 2018/9/25.
 */
public class ScannerFragment extends RedWineFragment implements QRCodeView.Delegate {
    private static final String TAG = ScannerFragment.class.getSimpleName();

    @BindView(R2.id.zbarview)
    ZBarView mZBarView = null;

    @BindView(R2.id.open_flashlight)
    AppCompatTextView open_flashlight = null;
    @BindView(R2.id.close_flashlight)
    AppCompatTextView close_flashlight = null;

    @OnClick(R2.id.open_flashlight)
    void OpenFlashlight() {
        if (mZBarView != null)
            mZBarView.openFlashlight();
    }

    @OnClick(R2.id.close_flashlight)
    void CloseFlashlight() {
        if (mZBarView != null)
            mZBarView.closeFlashlight();
    }

    @Override
    public Object setLayout() {
        return R.layout.fragment_scanner;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {
        mZBarView.setDelegate(this);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
        if (mZBarView != null) mZBarView.startCamera(); // 打开后置摄像头开始预览，但是并未开始识别
//        mZBarView.startCamera(Camera.CameraInfo.CAMERA_FACING_FRONT); // 打开前置摄像头开始预览，但是并未开始识别

        if (mZBarView != null) mZBarView.startSpotAndShowRect(); // 显示扫描框，并且延迟0.5秒后开始识别
    }

    @Override
    public void onStop() {
        if (mZBarView != null) mZBarView.stopCamera(); // 关闭摄像头预览，并且隐藏扫描框
        super.onStop();
    }

    @Override
    public void onDestroy() {
        if (mZBarView != null) {
            mZBarView.onDestroy(); // 销毁二维码扫描控件
        }
        super.onDestroy();
    }

    private void vibrate() {
        Vibrator vibrator = (Vibrator) getActivity().getSystemService(VIBRATOR_SERVICE);
        vibrator.vibrate(200);
    }

    @Override
    public void onScanQRCodeSuccess(String result) {
        vibrate();
        FengLogger.d(TAG, "result:" + result);
        setTitle("扫描结果为：" + result);
        getSupportDelegate().pop();
    }

    private void setTitle(String s) {
        Toast.makeText(getContext(), s, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onScanQRCodeOpenCameraError() {
        Log.e(TAG, "打开相机出错");
    }


    @Override
    public void onFragmentResult(int requestCode, int resultCode, Bundle data) {
        super.onFragmentResult(requestCode, resultCode, data);
        if (requestCode == RequestCodes.SCAN) {
            if (data != null) {
                FengLogger.d(TAG, "从首页过来的Scanner");
            }
        }
    }

    @Override
    public boolean onBackPressedSupport() {
        getSupportDelegate().pop();
        return true;
    }

    @Override
    public FragmentAnimator onCreateFragmentAnimator() {
        return new DefaultVerticalAnimator();
    }
}
