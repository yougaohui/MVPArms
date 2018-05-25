package me.jessyan.mvparms.demo.app.network.listener;

/**
 * Created by gaohui.you on 2/28/18
 * Email:839939978@qq.com
 */
public interface NetConnStateServiceListener {

    byte STATUS_CONNECT_SUCCESS = 1;

    byte STATUS_CONNECT_CLOSED = 0;

    byte STATUS_CONNECT_ERROR = 2;


    /**
     * 当接收到系统消息
     */
    void onMessageResponse(byte[] msg);

    /**
     * 当服务状态发生变化时触发
     */
    void onServiceStatusConnectChanged(int statusCode);
}
