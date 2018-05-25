package me.jessyan.mvparms.demo.app.network;


import com.blankj.utilcode.util.LogUtils;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import me.jessyan.mvparms.demo.app.network.handler.MobileClientInitializer;
import me.jessyan.mvparms.demo.app.network.listener.NetConnStateServiceListener;
import me.jessyan.mvparms.demo.app.network.mode.SocketConfig;

/**
 * Created by gaohui.you on 2/28/18
 * Email:839939978@qq.com
 */
public class MobileClient {

    private static MobileClient mobileClient = new MobileClient();

    private SocketConfig socketConfig;

    private EventLoopGroup group;

    private NetConnStateServiceListener netConnStateServiceListener;

    private Channel channel;

    private boolean isConnect = false;

    private int reconnectNum = Integer.MAX_VALUE;

    private long reconnectIntervalTime = 5 * 1000;//重连等待时间

    private int CONNECT_TIMEOUT_MILLIS = 3 * 1000;//连接超时处理

    private boolean isOnDestroy = false;

    public static MobileClient getInstance(SocketConfig config, NetConnStateServiceListener netConnStateServiceListener) {
        mobileClient.setSocketConfig(config);
        mobileClient.setNetConnStateServiceListener(netConnStateServiceListener);
        mobileClient.setOnDestroy(false);
        return mobileClient;
    }

    public static MobileClient getInstance() {
        mobileClient.setOnDestroy(false);
        return mobileClient;
    }

    public synchronized MobileClient connect() {
        if (!isOnDestroy && isConnect == false) {
            group = new NioEventLoopGroup();
            Bootstrap bootstrap = new Bootstrap().group(group)
                    .option(ChannelOption.SO_REUSEADDR, true)
                    .option(ChannelOption.SO_KEEPALIVE, true)
                    .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, CONNECT_TIMEOUT_MILLIS)
                    .channel(NioSocketChannel.class)
                    .handler(new MobileClientInitializer(netConnStateServiceListener));
            try {
                bootstrap.connect(socketConfig.host, socketConfig.port).addListener(new ChannelFutureListener() {
                    @Override
                    public void operationComplete(ChannelFuture channelFuture) throws Exception {
                        if (channelFuture.isSuccess()) {
                            isConnect = true;
                            channel = channelFuture.channel();
                        } else {
                            isConnect = false;
                        }
                    }
                }).sync();

            } catch (Exception e) {
                netConnStateServiceListener.onServiceStatusConnectChanged(NetConnStateServiceListener.STATUS_CONNECT_ERROR);
                reconnect();
            }
        }
        return this;
    }

    public void disconnect() {
        group.shutdownGracefully();
    }

    public void reconnect() {
        if (reconnectNum > 0 && !isConnect) {
            reconnectNum--;
            disconnect();
            try {
                Thread.sleep(reconnectIntervalTime);
            } catch (InterruptedException e) {
            }
            LogUtils.i("重新连接服务器");
            connect();
        } else {
            disconnect();
        }
    }

    public void sendMsgToServer(byte[] msg) {
        if (channel == null || msg == null) {
            return;
        }
        channel.writeAndFlush(Unpooled.copiedBuffer(msg));
    }

    public void sendMsgToServer(byte[] msg, ChannelFutureListener listener) {
        if (channel == null || msg == null) {
            return;
        }
        channel.writeAndFlush(Unpooled.copiedBuffer(msg)).addListener(listener);
    }

    public void setReconnectNum(int reconnectNum) {
        this.reconnectNum = reconnectNum;
    }

    public void setReconnectIntervalTime(long reconnectIntervalTime) {
        this.reconnectIntervalTime = reconnectIntervalTime;
    }

    private void setSocketConfig(SocketConfig socketConfig) {
        this.socketConfig = socketConfig;
    }

    public boolean getConnectStatus() {
        return isConnect;
    }

    public void setConnectStatus(boolean status) {
        this.isConnect = status;
    }

    public void setNetConnStateServiceListener(NetConnStateServiceListener netConnStateServiceListener) {
        this.netConnStateServiceListener = netConnStateServiceListener;
    }

    public void setOnDestroy(boolean onDestroy) {
        isOnDestroy = onDestroy;
    }

    /**
     * 销毁Socket通信请求
     */
    public void onDestroy() {
        mobileClient.disconnect();
        isOnDestroy = true;
    }
}
