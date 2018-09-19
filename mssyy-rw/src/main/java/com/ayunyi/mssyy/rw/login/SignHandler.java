package com.ayunyi.mssyy.rw.login;

/**
 * Created by ft on 2018/9/19.
 */
public class SignHandler {


    public static void onSignIn(ISignListener listener) {
        listener.onSignInSuccess();
    }

    public static void onSignUp(ISignListener listener) {
        listener.onSignUpSuccess();
    }
}
