package me.jessyan.mvparms.demo.app.utils;

import android.text.TextUtils;

import com.blankj.utilcode.util.FileUtils;
import com.jess.arms.utils.DeviceUtils;

/**
 * ProjectUtils
 */
public class ProjectUtils extends BaseUtils {

    // 目录名称
    public static String PROJECT_NAME = "MVP";
    // 项目目录
    public static String ROOT_PATH = PathUtils.getExternalStoragePath();
    // DB路径
    public static String DB = PathUtils.getDbDir();
    // 日志路径
    public static String LOG = PathUtils.getLogDir();
    // 缓存路径
    public static String CACHE = PathUtils.getCacheDir();
    // 其他路径
    public static String OTHER = PathUtils.getOtherDir();
    // 相机
    public static String CAMERA = PathUtils.getCameraDir();

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
            result = FileUtils.createOrExistsDir(ROOT_PATH);
            result = FileUtils.createOrExistsDir(DB);
            result = FileUtils.createOrExistsDir(LOG);
            result = FileUtils.createOrExistsDir(CACHE);
            result = FileUtils.createOrExistsDir(OTHER);
            result = FileUtils.createOrExistsDir(CAMERA);
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
        return init();
    }
}