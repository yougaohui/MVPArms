package me.jessyan.mvparms.demo.db;

import android.app.Application;
import android.content.ContextWrapper;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;

import java.io.File;

import me.jessyan.mvparms.demo.app.utils.ProjectUtils;


/**
 * 定义数据库路径
 */
public class DBContextWrapper extends ContextWrapper {

    // DB路径
    private String path;

    public DBContextWrapper(Application application) {
        super(application);
    }

    public DBContextWrapper(Application application, String path) {
        super(application);
        this.path = path;
    }

    /**
     * 获得数据库路径
     *
     * @param dbName
     */
    @Override
    public File getDatabasePath(String dbName) {
        StringBuffer buffer = new StringBuffer();
        buffer.append(TextUtils.isEmpty(path) ? ProjectUtils.DB : path);
        buffer.append(dbName);
        return new File(buffer.toString());
    }

    /**
     * Android2.3及以下会调用这个方法
     *
     * @param name
     * @param mode
     * @param factory
     */
    @Override
    public SQLiteDatabase openOrCreateDatabase(String name, int mode, SQLiteDatabase.CursorFactory factory) {
        return SQLiteDatabase.openOrCreateDatabase(getDatabasePath(name), factory);
    }

    /**
     * Android 4.0会调用此方法获取数据库
     *
     * @param name
     * @param mode
     * @param factory
     * @param errorHandler
     */
    @Override
    public SQLiteDatabase openOrCreateDatabase(String name, int mode, SQLiteDatabase.CursorFactory factory, DatabaseErrorHandler errorHandler) {
        return SQLiteDatabase.openOrCreateDatabase(getDatabasePath(name), factory);
    }
}