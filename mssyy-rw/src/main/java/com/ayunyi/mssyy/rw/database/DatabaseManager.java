package com.ayunyi.mssyy.rw.database;

import android.content.Context;

import org.greenrobot.greendao.database.Database;

/**
 * Created by ft on 2018/9/19.
 */
public class DatabaseManager {

    private DaoSession mDaoSession = null;
    private UserProfileDao mDao = null;

    public DatabaseManager init(Context context) {
        initDao(context);
        return this;
    }

    private static final class Holder {
        private static final DatabaseManager DATABASE_MANAGER = new DatabaseManager();
    }

    public static DatabaseManager getInstance() {
        return Holder.DATABASE_MANAGER;
    }


    public void initDao(Context context) {

        //noinspection SpellCheckingInspection
        final ReleaseOpenHelper helper = new ReleaseOpenHelper(context, "redwine.db");
        final Database db = helper.getWritableDb();
        mDaoSession = new DaoMaster(db).newSession();
        mDao = mDaoSession.getUserProfileDao();
    }

    public final UserProfileDao getDao() {
        return mDao;
    }
}
