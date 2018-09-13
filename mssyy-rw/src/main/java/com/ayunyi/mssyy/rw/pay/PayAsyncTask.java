package com.ayunyi.mssyy.rw.pay;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;

import com.alipay.sdk.app.PayTask;
import com.yy.core.ui.Loader.LatteLoader;

/**
 * Created by ft on 2018/9/13.
 */
public class PayAsyncTask extends AsyncTask<String, Void, String> {

    private Activity ACTIVITY;
    private IAlPayResultListener LISTENER;
    //订单支付成功
    private static final String AL_PAY_STATUS_SUCCESS = "9000";
    //订单处理中
    private static final String AL_PAY_STATUS_PAYING = "8000";
    //订单支付失败
    private static final String AL_PAY_STATUS_FAIL = "4000";
    //用户取消
    private static final String AL_PAY_STATUS_CANCEL = "6001";
    //支付网络错误
    private static final String AL_PAY_STATUS_CONNECT_ERROR = "6002";

    public PayAsyncTask(Activity activity, IAlPayResultListener listener) {
        this.ACTIVITY = activity;
        this.LISTENER = listener;
    }


    @Override
    protected String doInBackground(String... params) {
        final String alPaySign = params[0];
        PayTask payTask = new PayTask(ACTIVITY);
        return payTask.pay(alPaySign, true);
    }


    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        LatteLoader.stopLoading();
        PayResult payResult = new PayResult(result);
        final String resultInfo = payResult.getResult();
        final String resultStatus = payResult.getResultStatus();
        Log.d("AL_PAY_RESULT", resultInfo);
        Log.d("AL_PAY_RESULT", resultStatus);
        switch (resultStatus) {
            case AL_PAY_STATUS_SUCCESS:
                if (LISTENER != null) {
                    LISTENER.onPaySuccess();
                }
                break;
            case AL_PAY_STATUS_FAIL:
                if (LISTENER != null) {
                    LISTENER.onPayFail();
                }
                break;
            case AL_PAY_STATUS_PAYING:
                if (LISTENER != null) {
                    LISTENER.onPaying();
                }
                break;
            case AL_PAY_STATUS_CANCEL:
                if (LISTENER != null) {
                    LISTENER.onPayCancel();
                }
                break;
            case AL_PAY_STATUS_CONNECT_ERROR:
                if (LISTENER != null) {
                    LISTENER.onPayConnectError();
                }
                break;
            default:
                break;
        }

    }
}