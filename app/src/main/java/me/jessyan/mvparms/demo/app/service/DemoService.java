/*
 * Copyright 2017 JessYan
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package me.jessyan.mvparms.demo.app.service;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

import com.jess.arms.base.BaseService;

import me.jessyan.mvparms.demo.ProcessService;

/**
 * ================================================
 * 展示 {@link BaseService} 的用法
 * <p>
 * Created by JessYan on 09/07/2016 16:59
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * ================================================
 */
public class DemoService extends BaseService {
    private DaemonBinder binder;   //和LocalService的binder相互绑定
    private DaemonConn myConn;
    private final String TAG = "DaemonService";

    private class MessengerHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
            }
        }
    }

    private Messenger messenger = new Messenger(new MessengerHandler());

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return messenger.getBinder();
    }

    @Override
    public void init() {
        binder = new DaemonBinder();
        if (myConn == null) {
            myConn = new DaemonConn();
        }
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        bindService(new Intent(this, CoreService.class),
                myConn,
                Context.BIND_IMPORTANT);
        return super.onStartCommand(intent, flags, startId);
    }

    class DaemonBinder extends ProcessService.Stub {
        @Override
        public String getServiceName() throws RemoteException {
            return "DaemonService";
        }
    }


    class DaemonConn implements ServiceConnection {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.e(TAG, "DaemonService连接成功--------");
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.e(TAG, "DaemonService killed--------");
            startService(new Intent(DemoService.this, CoreService.class));
            bindService(new Intent(DemoService.this, CoreService.class),
                    myConn, Context.BIND_IMPORTANT);
        }
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
