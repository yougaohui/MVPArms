package me.jessyan.mvparms.demo.app.service;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.jess.arms.base.BaseService;

import me.jessyan.mvparms.demo.ProcessService;

/**
 * Created by jess on 9/7/16 16:59
 * Contact with jess.yan.effort@gmail.com
 */
public class CoreService extends BaseService {

    private CoreBinder binder;
    private CoreConn conn;

    @Override
    public void init() {
        ToastUtils.showShort("启动服务。。。。");
        //进程间通信
        binder = new CoreBinder();
        if (conn == null) {
            conn = new CoreConn();
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //绑定远程服务
        bindService(new Intent(CoreService.this, CoreService.class),
                conn,
                Context.BIND_IMPORTANT);
        return super.onStartCommand(intent, flags, startId);
    }

    /***********************双进程守护***********************/
    class CoreBinder extends ProcessService.Stub {
        @Override
        public String getServiceName() throws RemoteException {
            return "DemoService";
        }
    }

    /**
     * 绑定连接需要ServiceConnection
     */
    class CoreConn implements ServiceConnection {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            LogUtils.e(TAG, "CoreService连接远程服务成功 --------");
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            //远程服务被干掉；连接断掉的时候走此回调
            //在连接RemoateService异常断时，会回调；也就是RemoteException
            LogUtils.e(TAG, "DaemonService killed--------");
            startService(new Intent(CoreService.this, DemoService.class));
            //绑定远程服务
            bindService(new Intent(CoreService.this, DemoService.class),
                    conn, Context.BIND_IMPORTANT);
        }
    }
}
