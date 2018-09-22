package com.ayunyi.mssyy.rw.login;

/**
 * Created by ft on 2018/9/19.
 */
public interface ISignListener {

    void onSignInSuccess(FromWhereOnLoginTag onLoginTag);

    void onSignUpSuccess();
}
