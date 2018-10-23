package com.ayunyi.mssyy.rw.main.personal.user.profile;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ayunyi.mssyy.rw.R;
import com.yy.core.util.logger.FengLogger;
import com.yy.core.util.toast.ToastUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by ft on 2018/10/22.
 */
public class PicturePreviewActivity extends Activity {
    private ImageView zoomView;
    private GestureDetector gestureDetector;
    private LinearLayout layout;
    private boolean isFile;
    private String TAG = PicturePreviewActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //不显示系统的标题栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_picture_preview);
        zoomView = (ImageView) findViewById(R.id.zoom_view);
        layout = (LinearLayout) this.findViewById(R.id.layout_picture_preview);
        /* 大图的下载地址 */
        final String url = getIntent().getStringExtra("url");

        if (url.startsWith("http:")) {//是网络地址
            this.isFile = false;
        } else {
            this.isFile = true;
        }

        /* 缩略图存储在本地的地址 */
        final String smallPath = getIntent().getStringExtra("smallPath");
        final int identify = getIntent().getIntExtra("indentify", -1);
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        final int widthPixels = metrics.widthPixels;
        final int heightPixels = metrics.heightPixels;
        File bigPicFile = new File(getLocalPath(url));

        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View textView = LayoutInflater.from(PicturePreviewActivity.this).inflate(R.layout.toast_view, null);
                TextView textView1 = textView.findViewById(R.id.tv_toast);
                textView1.setText("点击了背景布局...");

                ToastUtils toast = new ToastUtils(PicturePreviewActivity.this, textView, 2);
                toast.show();

                  PicturePreviewActivity.this.finish();
            }
        });

        if (isFile) {
            zoomView.setImageBitmap(zoomBitmap(
                    BitmapFactory.decodeFile(url), widthPixels,
                    heightPixels));
        } else {

            if (bigPicFile.exists()) {/* 如果已经下载过了,直接从本地文件中读取 */
                zoomView.setImageBitmap(zoomBitmap(
                        BitmapFactory.decodeFile(getLocalPath(url)), widthPixels,
                        heightPixels));
            } else if (!TextUtils.isEmpty(url)) {

                FengLogger.d("AAAAA", url);

//                ProgressDialog handle = new ProgressDialog(this) {
//                    Bitmap bitmap = null;
//
//                    @Override
//                    public void handleData() throws
//                            Exception {
//
//                        if (!isFile)  //从网络读取
//                            bitmap = getBitMapFromUrl(url);
//
//
//                        if (bitmap != null) {
//                            savePhotoToSDCard(
//                                    zoomBitmap(bitmap, widthPixels, heightPixels),
//                                    getLocalPath(url));
//                        }
//                    }
//
//                    @Override
//                    public String initialContent() {
//                        return null;
//                    }
//
//                    @Override
//                    public void updateUI() {
//                        if (bitmap != null) {
//                            // recycle();
//
//                            zoomView.setImageBitmap(zoomBitmap(bitmap, widthPixels,
//                                    heightPixels));
//                        } else {
//                            Toast.makeText(ctx, "图片预览图下载失败",
//                                    Toast.LENGTH_LONG).show();
//                            PicturePreviewActivity.this.finish();
//                        }
//                    }
//
//                };
//                if (TextUtils.isEmpty(smallPath) && identify != -1) {
//                    handle.setBackground(BitmapFactory.decodeResource(
//                            getResources(), identify));
//                } else {
//                    handle.setBackground(BitmapFactory.decodeFile(smallPath));
//                }
//                handle.show();
            }
        }
        gestureDetector = new GestureDetector(this,
                new GestureDetector.SimpleOnGestureListener() {
                    @Override
                    public boolean onFling(MotionEvent e1, MotionEvent e2,
                                           float velocityX, float velocityY) {
                        float x = e2.getX() - e1.getX();
                        if (x > 0) {
                            prePicture();
                        } else if (x < 0) {

                            nextPicture();
                        }
                        return true;
                    }
                });
    }

    protected void nextPicture() {
        // TODO Auto-generated method stub

    }

    protected void prePicture() {
        // TODO Auto-generated method stub

    }

    @Override
    public void onResume() {
        super.onResume();
        // recycle();
    }

    public void recycle() {
        if (zoomView != null && zoomView.getDrawable() != null) {
            BitmapDrawable bitmapDrawable = (BitmapDrawable) zoomView
                    .getDrawable();
            if (bitmapDrawable != null && bitmapDrawable.getBitmap() != null
                    && !bitmapDrawable.getBitmap().isRecycled())

            {
                bitmapDrawable.getBitmap().recycle();
            }
        }
    }


    public Bitmap getBitMapFromUrl(String url) {
        Bitmap bitmap = null;
        URL u = null;
        HttpURLConnection conn = null;
        InputStream is = null;
        try {
            u = new URL(url);
            conn = (HttpURLConnection) u.openConnection();
            is = conn.getInputStream();
            bitmap = BitmapFactory.decodeStream(is);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (is != null) {
                    is.close();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
            conn.disconnect();
        }
        return bitmap;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return gestureDetector.onTouchEvent(event);
    }

    /**
     * Resize the bitmap
     *
     * @param bitmap
     * @param width
     * @param height
     * @return
     */
    public static Bitmap zoomBitmap(Bitmap bitmap, int width, int height) {
        if (bitmap == null)
            return bitmap;
        int w = bitmap.getWidth();
        int h = bitmap.getHeight();
        Matrix matrix = new Matrix();
        float scaleWidth = ((float) width / w);
        float scaleHeight = ((float) height / h);
        if (scaleWidth < scaleHeight) {
            matrix.postScale(scaleWidth, scaleWidth);
        } else {
            matrix.postScale(scaleHeight, scaleHeight);
        }
        Bitmap newbmp = Bitmap.createBitmap(bitmap, 0, 0, w, h, matrix, true);
        return newbmp;
    }

    public static String getLocalPath(String url) {
        String fileName = "temp.png";
        if (url != null) {
            if (url.contains("/")) {
                fileName = url
                        .substring(url.lastIndexOf("/") + 1, url.length());
            }
            if (fileName != null && fileName.contains("&")) {
                fileName = fileName.replaceAll("&", "");
            }
            if (fileName != null && fileName.contains("%")) {
                fileName = fileName.replaceAll("%", "");
            }
        }
        return Environment.getExternalStorageDirectory() + "/weike/PracticeImage/" + fileName;
    }


    public static void savePhotoToSDCard(Bitmap photoBitmap, String fullPath) {
        if (checkSDCardAvailable()) {
            File file = new File(fullPath);
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }

            File photoFile = new File(fullPath);
            FileOutputStream fileOutputStream = null;
            try {
                fileOutputStream = new FileOutputStream(photoFile);
                if (photoBitmap != null) {
                    if (photoBitmap.compress(Bitmap.CompressFormat.PNG, 100,
                            fileOutputStream)) {
                        fileOutputStream.flush();
                    }
                }
            } catch (FileNotFoundException e) {
                photoFile.delete();
                e.printStackTrace();
            } catch (IOException e) {
                photoFile.delete();
                e.printStackTrace();
            } finally {
                try {
                    if (fileOutputStream != null) {
                        fileOutputStream.close();
                    }
                    // if (photoBitmap != null && !photoBitmap.isRecycled()) {
                    // photoBitmap.recycle();
                    // }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static boolean checkSDCardAvailable() {
        return android.os.Environment.getExternalStorageState().equals(
                android.os.Environment.MEDIA_MOUNTED);
    }


    public void closeActivity() {
        this.finish();
    }
}
