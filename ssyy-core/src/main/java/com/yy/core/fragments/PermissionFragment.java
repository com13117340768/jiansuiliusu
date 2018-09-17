package com.yy.core.fragments;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

import com.orhanobut.logger.Logger;
import com.yalantis.ucrop.UCrop;
import com.yy.core.ui.camera.CameraImageBean;
import com.yy.core.ui.camera.LatteCamera;
import com.yy.core.ui.camera.RequestCodes;

import java.util.Objects;

import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnNeverAskAgain;
import permissions.dispatcher.OnPermissionDenied;
import permissions.dispatcher.OnShowRationale;
import permissions.dispatcher.PermissionRequest;
import permissions.dispatcher.RuntimePermissions;

@RuntimePermissions
public abstract class PermissionFragment extends BaseFragment {
    //不是直接调用方法


    @NeedsPermission(Manifest.permission.CAMERA)
    void startCamera() {
        LatteCamera.start(this);
    }

    @NeedsPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
    void startWrite() {
        LatteCamera.startWrite(this);
    }

    public void startWriteWithCheck() {
        PermissionFragmentPermissionsDispatcher.startWriteWithPermissionCheck(this);
    }

    //这个是真正调用的方法
    public void startCameraWithCheck() {
        PermissionFragmentPermissionsDispatcher.startCameraWithPermissionCheck(this);

    }

    //扫描二维码(不直接调用)
    @NeedsPermission(Manifest.permission.CAMERA)
    void startScan(BaseFragment delegate) {
        //    delegate.getSupportDelegate().startForResult(new ScannerDelegate(), RequestCodes.SCAN);
    }

    //  public void startScanWithCheck(BaseFragment delegate) {
    //       //  PermissionFragmentPermissionsDispatcher.startScanWithCheck(this, delegate);
    //  }


    @OnPermissionDenied(Manifest.permission.WRITE_EXTERNAL_STORAGE)
    void onWriteDenied() {
        Toast.makeText(getContext(), "不允许文件读取", Toast.LENGTH_LONG).show();
    }

    @OnNeverAskAgain(Manifest.permission.WRITE_EXTERNAL_STORAGE)
    void onWriteNever() {
        Toast.makeText(getContext(), "需要您请前往设置进行手动开启文件读取权限", Toast.LENGTH_LONG).show();
    }

    @OnShowRationale(Manifest.permission.WRITE_EXTERNAL_STORAGE)
    void onWriteRationale(PermissionRequest request) {
        showRationaleDialog(request);
    }



    @OnPermissionDenied({Manifest.permission.CAMERA})
    void onCameraDenied() {
        Toast.makeText(getContext(), "不允许拍照", Toast.LENGTH_LONG).show();
    }

    @OnNeverAskAgain({Manifest.permission.CAMERA})
    void onCameraNever() {
        Toast.makeText(getContext(), "需要您请前往设置进行手动开启拍照权限", Toast.LENGTH_LONG).show();
    }

    @OnShowRationale(Manifest.permission.CAMERA)
    void onCameraRationale(PermissionRequest request) {
        showRationaleDialog(request);
    }

    private void showRationaleDialog(final PermissionRequest request) {

        new AlertDialog.Builder(Objects.requireNonNull(getContext()))
                .setPositiveButton("同意使用", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setNegativeButton("拒绝使用", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setCancelable(false)
                .setMessage("权限管理")
                .show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionFragmentPermissionsDispatcher.onRequestPermissionsResult(this, requestCode, grantResults);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Logger.d("hahaha", "requestCode");

        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case RequestCodes.TAKE_PHOTO:
                    final Uri resultUri = CameraImageBean.getInstance().getPath();
                    UCrop.of(resultUri, resultUri)
                            .withMaxResultSize(400, 400)
                            .start(Objects.requireNonNull(getContext()), this);
                    break;
                case RequestCodes.PICK_PHOTO:
                    if (data != null) {
                        final Uri pickPath = data.getData();
                        //从相册选择后需要有个路径存放剪裁过的图片
                        final String pickCropPath = LatteCamera.createCropFile().getPath();
                        UCrop.of(pickPath, Uri.parse(pickCropPath))
                                .withMaxResultSize(400, 400)
                                .start(Objects.requireNonNull(getContext()), this);
                    }
                    break;
                case RequestCodes.CROP_PHOTO:
//                    final Uri cropUri = UCrop.getOutput(data);
//                    //拿到剪裁后的数据进行处理
//                    @SuppressWarnings("unchecked")
//                    final IGlobalCallback<Uri> callback = CallbackManager
//                            .getInstance()
//                            .getCallback(CallbackType.ON_CROP);
//                    if (callback != null) {
//                        callback.executeCallback(cropUri);
//                    }
                    break;
                case RequestCodes.CROP_ERROR:
                    Toast.makeText(getContext(), "剪裁出错", Toast.LENGTH_SHORT).show();
                    break;
                default:
                    break;
            }
        }
    }
}