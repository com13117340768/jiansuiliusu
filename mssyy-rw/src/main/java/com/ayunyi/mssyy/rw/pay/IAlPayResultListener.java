package com.ayunyi.mssyy.rw.pay;

/**
 * Created by ft on 2018/9/13.
 */
public interface IAlPayResultListener {

    void onPaySuccess();

    void onPaying();

    void onPayFail();

    void onPayCancel();

    void onPayConnectError();

}
