package me.jessyan.mvparms.demo.app.utils;

import android.os.Environment;
import android.text.TextUtils;

import com.blankj.utilcode.util.FileUtils;
import com.jess.arms.utils.DeviceUtils;

import java.io.File;


/**
 * ProjectUtils
 */
public class ProjectUtils extends BaseUtils {

    // 目录名称
    public static String PROJECT_NAME = "MVP";
    // 项目目录
    public static String ROOT_PATH = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + PROJECT_NAME + File.separator;
    // DB路径
    public static String DB = ROOT_PATH + "db/";
    // 日志路径
    public static String LOG = ROOT_PATH + "log/";
    // 缓存路径
    public static String CACHE = ROOT_PATH + "cache/";
    // 其他路径
    public static String OTHER = ROOT_PATH + "other/";
    // Camera
    public static String CAMERA = ROOT_PATH + "camera/";

    public ProjectUtils() {
        super();
    }

    /**
     * 初始化项目文件夹
     *
     * @return
     */
    public static boolean init() {
        boolean result = true;
        if (DeviceUtils.isExitsSdcard()) {
            result = FileUtils.createOrExistsFile(ROOT_PATH);
            result = FileUtils.createOrExistsFile(DB);
            result = FileUtils.createOrExistsFile(LOG);
            result = FileUtils.createOrExistsFile(CACHE);
            result = FileUtils.createOrExistsFile(OTHER);
            result = FileUtils.createOrExistsFile(CAMERA);
        }
        return result;
    }

    /**
     * 初始化项目文件夹
     *
     * @return
     */
    public static boolean init(String name) {
        PROJECT_NAME = !TextUtils.isEmpty(name) ? name : PROJECT_NAME;
        ROOT_PATH = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + PROJECT_NAME + File.separator;
        DB = ROOT_PATH + "db/";
        LOG = ROOT_PATH + "log/";
        CACHE = ROOT_PATH + "cache/";
        OTHER = ROOT_PATH + "other/";
        CAMERA = ROOT_PATH + "camera/";
        return init();
    }
}