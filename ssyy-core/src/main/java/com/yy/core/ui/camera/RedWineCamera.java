package com.yy.core.ui.camera;

import android.net.Uri;

import com.yy.core.fragments.PermissionFragment;
import com.yy.core.util.file.FileUtil;

/**
 * Created by ft 2018-09-15.
 * 照相机调用类
 */

public class RedWineCamera {

    public static Uri createCropFile() {
        return Uri.parse(FileUtil.createFile("crop_image",
                        FileUtil.getFileNameByTime("IMG", "jpg")).getPath());
    }

    public static void start(PermissionFragment fragment) {
        new CameraHandler(fragment).beginCameraDialog();
    }

    public static void startWrite(PermissionFragment fragment) {
        new CameraHandler(fragment).takePhoto();
    }

    public static void startScanner(PermissionFragment fragment){

    }

}
