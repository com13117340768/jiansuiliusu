package com.ayunyi.mssyy.rw.login;

import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

import com.ayunyi.mssyy.rw.database.DatabaseManager;
import com.ayunyi.mssyy.rw.database.UserProfile;
import com.yy.core.app.AccountManager;
import com.yy.core.app.RedWine;
import com.yy.core.util.logger.FengLogger;

/**
 * Created by ft on 2018/9/19.
 */
public class SignHandler {


    public static void onSignIn(String response, ISignListener signListener, int FromWhereTag) {
//        final JSONObject profileJson = JSON.parseObject(response).getJSONObject("data");
//        final long userId = profileJson.getLong("userId");
//        final String name = profileJson.getString("name");
//        final String avatar = profileJson.getString("avatar");
//        final String gender = profileJson.getString("gender");
//        final String address = profileJson.getString("address");

        final long userId = 1;
        final String name = "冯涛";
        final String avatar = "剑穗流苏";
        final String gender = "男";
        final String address = "上海市梅川路1333弄154号";

        //  final UserProfile profile = new UserProfile(userId, name, avatar, gender, address);
        //  DatabaseManager.getInstance().getDao().insert(profile);
        //已经注册并登录成功了
        AccountManager.setSignState(true);
        Toast.makeText(RedWine.getApplicationContext(), "FromWhereTag=" + FromWhereTag, Toast.LENGTH_SHORT).show();
        //  FengLogger.d("fengtao","进入了SignHandler,FromWhereTag="+FromWhereTag);
        if (FromWhereTag == 4) {
            signListener.onSignInSuccess(FromWhereOnLoginTag.USER);
        } else {
            signListener.onSignInSuccess(FromWhereOnLoginTag.INDEX);
        }
    }

    public static void onSignUp(String response, ISignListener signListener) {
//        final JSONObject profileJson = JSON.parseObject(response).getJSONObject("data");
//        final long userId = profileJson.getLong("userId");
//        final String name = profileJson.getString("name");
//        final String avatar = profileJson.getString("avatar");
//        final String gender = profileJson.getString("gender");
//        final String address = profileJson.getString("address");

        final long userId = 1;
        final String name = "冯涛";
        final String avatar = "剑穗流苏";
        final String gender = "男";
        final String address = "上海市梅川路1333弄154号";

        //   final UserProfile profile = new UserProfile(userId, name, avatar, gender, address);
        //  DatabaseManager.getInstance().getDao().insert(profile);
        //注册成功
        AccountManager.setSignState(true);
        signListener.onSignUpSuccess();
    }
}
