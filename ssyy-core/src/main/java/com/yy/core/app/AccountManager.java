package com.yy.core.app;

import com.yy.core.util.sharepreference.RedWinePreference;

/**
 * Created by ft on 2018/9/19.
 * message:管理用户状态信息
 */
public class AccountManager {

    private enum SignTag {
        SIGN_TAG
    }

    public static void setSignState(boolean state) {
        RedWinePreference.setAppFlag(SignTag.SIGN_TAG.name(), state);
    }

    private static boolean isSignIn() {
        return RedWinePreference.getAppFlag(SignTag.SIGN_TAG.name());
    }

    public static void checkAccount(IUserChecker checker) {
        if (isSignIn()) {
            checker.onSignIn();
        } else {
            checker.onNotSignIn();
        }
    }

    public static boolean checkAccount() {
        return isSignIn();
    }

    public static void exitLoginState() {
        if (isSignIn()) {
            RedWinePreference.setAppFlag(SignTag.SIGN_TAG.name(), false);
        }
    }
}
