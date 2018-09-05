package com.ssyy.example;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;

import com.ft.example.R;
import com.yy.core.fragments.LatteFragment;
import com.yy.core.net.RestClient;
import com.yy.core.net.callback.IError;
import com.yy.core.net.callback.IFailure;
import com.yy.core.net.callback.ISuccess;
import com.yy.core.ui.Loader.LoaderStyle;

import butterknife.Unbinder;


public class ExampleDelegate extends LatteFragment {


    public Unbinder unbinder = null;

    @Override
    public Object setLayout() {
        return R.layout.delegate_example;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {
        testRequest();

    }


    private void testRequest() {
        RestClient.builder()
                //    .url("http://127.0.0.1:8080/RestServer/api/baidu_image.php")
                .url("index.php")
                //     .params("key", "value")
                .loader(getContext(), LoaderStyle.BallZigZagIndicator)
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        Log.d("HAHAHA", response + "");

//                      try {
//                          JSONObject jsonObject =new JSONObject(response);
//                          String name =jsonObject.optString("name");
//                          String sex =jsonObject.optString("sex");
//                          String age =jsonObject.optString("age");
//                          Log.d("HAHAHA",name+age+sex);
//
//
//                      } catch (JSONException e) {
//                          e.printStackTrace();
//                      }


                        //    Toast.makeText(getContext(),response,Toast.LENGTH_LONG).show();
                    }
                })
                .failure(new IFailure() {
                    @Override
                    public void onFailure() {
                        Log.d("HAHAHA", "SHIBAI");
                    }
                })
                .error(new IError() {
                    @Override
                    public void onError(int code, String msg) {
                        Log.d("HAHAHA", code + msg);
                    }
                })
                .build()
                .get();

    }


}
