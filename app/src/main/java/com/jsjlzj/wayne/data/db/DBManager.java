package com.jsjlzj.wayne.data.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.jsjlzj.wayne.constant.PublicConstant;


public class DBManager {
    private static final String dbName = PublicConstant.APP_NAME;

    private static class LazyHolder {
        private static final DBManager ME = new DBManager();
    }

    private Context context;
    private DaoMaster.DevOpenHelper helper = null;
    private DaoSession readDaoSession = null;
    private DaoSession writeDaoSession = null;

    public static DBManager getInstance() {
        return DBManager.LazyHolder.ME;
    }

    public void initDBManager(Context context){
        this.context = context;
    }

    private SQLiteDatabase getReadableDatabase() {
        if (helper == null) {
            helper = new DaoMaster.DevOpenHelper(context, dbName, null);
        }
        return helper.getReadableDatabase();
    }

    private SQLiteDatabase getWritableDatabase() {
        if (helper == null) {
            helper = new DaoMaster.DevOpenHelper(context, dbName, null);
        }
        return helper.getWritableDatabase();
    }

    public DaoSession getReadDaoSession() {
        if (readDaoSession == null) {
            readDaoSession = new DaoMaster(getReadableDatabase()).newSession();
        }

        return readDaoSession;
    }

    public DaoSession getWriteDaoSession() {
        if (writeDaoSession == null) {
            writeDaoSession = new DaoMaster(getWritableDatabase()).newSession();
        }

        return writeDaoSession;
    }

}
