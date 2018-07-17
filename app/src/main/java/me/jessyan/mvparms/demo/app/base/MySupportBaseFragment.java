package me.jessyan.mvparms.demo.app.base;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;

import com.jess.arms.base.BaseFragment;

/**
 * Created by gaohui.you on 2018/7/17 0017
 * Email:839939978@qq.com
 */
public abstract class MySupportBaseFragment extends BaseFragment implements Handler.Callback{
    protected Context mContext;
    protected Handler mHandler;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mContext = getActivity();
        this.mHandler = new Handler(this);
    }

    @Override
    public boolean handleMessage(Message msg) {
        return false;
    }
}
