package me.jessyan.mvparms.demo.app.utils;

import android.os.Environment;

import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.Utils;

import java.io.File;

/**
 * @创建者 CSDN_LQR
 * @描述 写文件的工具类
 */
public class PathUtils extends BaseUtils {

    public static final String ROOT_DIR = "Android/data/"
            + AppUtils.getAppPackageName();
    public static final String DOWNLOAD_DIR = "download";
    public static final String CACHE_DIR = "cache";
    public static final String ICON_DIR = "icon";
    public static final String FILE_DIR = "file";
    public static final String LOG = "log";
    public static final String DB = "db";
    public static final String OTHER = "other";
    public static final String CAMERA = "camera";

    /**
     * 判断SD卡是否挂载
     */
    public static boolean isSDCardAvailable() {
        if (Environment.MEDIA_MOUNTED.equals(Environment
                .getExternalStorageState())) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 获取下载目录
     */
    public static String getDownloadDir() {
        return getDir(DOWNLOAD_DIR);
    }

    /**
     * 获取缓存目录
     */
    public static String getCacheDir() {
        return getDir(CACHE_DIR);
    }

    /**
     * 获取文件目录
     */
    public static String getFileDir() {
        return getDir(FILE_DIR);
    }

    /**
     * 获取icon目录
     */
    public static String getIconDir() {
        return getDir(ICON_DIR);
    }

    /**
     * 获取日志目录
     */
    public static String getLogDir() {
        return getDir(LOG);
    }

    /**
     * 获取数据库目录
     */
    public static String getDbDir() {
        return getDir(DB);
    }

    /**
     * 获取其他的文件目录
     */
    public static String getOtherDir() {
        return getDir(OTHER);
    }

    /**
     * 获取相机文件目录
     * @return
     */
    public static String getCameraDir() {
        return getDir(CAMERA);
    }

    /**
     * 获取应用目录，当SD卡存在时，获取SD卡上的目录，当SD卡不存在时，获取应用的cache目录
     */
    public static String getDir(String name) {
        StringBuilder sb = new StringBuilder();
        if (isSDCardAvailable()) {
            sb.append(getExternalStoragePath());
        } else {
            sb.append(getCachePath());
        }
        sb.append(name);
        sb.append(File.separator);
        String path = sb.toString();
        return path;
    }

    /**
     * 获取SD下的应用目录
     */
    public static String getExternalStoragePath() {
        StringBuilder sb = new StringBuilder();
        sb.append(Environment.getExternalStorageDirectory().getAbsolutePath());
        sb.append(File.separator);
        sb.append(ROOT_DIR);
        sb.append(File.separator);
        return sb.toString();
    }

    /**
     * 获取应用的cache目录
     */
    public static String getCachePath() {
        File f = Utils.getApp().getCacheDir();
        if (null == f) {
            return null;
        } else {
            return f.getAbsolutePath() + "/";
        }
    }

    /**
     * 获取文件目录
     */
    public static String getFilePath() {
        File f = Utils.getApp().getFilesDir();
        if (null == f) {
            return null;
        } else {
            return f.getAbsolutePath() + "/";
        }
    }
}