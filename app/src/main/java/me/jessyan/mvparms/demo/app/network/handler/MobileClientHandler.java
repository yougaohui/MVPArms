package me.jessyan.mvparms.demo.app.network.handler;


import com.blankj.utilcode.util.LogUtils;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import me.jessyan.mvparms.demo.app.network.MobileClient;
import me.jessyan.mvparms.demo.app.network.listener.NetConnStateServiceListener;

/**
 * Created by gaohui.you on 2/28/18
 * Email:839939978@qq.com
 */
public class MobileClientHandler extends SimpleChannelInboundHandler {

    private NetConnStateServiceListener mNetConnStateServiceListener;

    public MobileClientHandler(NetConnStateServiceListener netConnStateServiceListener) {
        this.mNetConnStateServiceListener = netConnStateServiceListener;
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        LogUtils.i("服务器已连接");
        MobileClient.getInstance().setConnectStatus(true);
        mNetConnStateServiceListener.onServiceStatusConnectChanged(NetConnStateServiceListener.STATUS_CONNECT_SUCCESS);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        LogUtils.i("服务器已断开");
        MobileClient.getInstance().setConnectStatus(false);
        mNetConnStateServiceListener.onServiceStatusConnectChanged(NetConnStateServiceListener.STATUS_CONNECT_CLOSED);
        MobileClient.getInstance().reconnect();
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {

        if (evt instanceof IdleStateEvent) {
            IdleStateEvent event = (IdleStateEvent) evt;
            if (event.state() == IdleState.READER_IDLE) {
                ctx.close();
            }
        }
        super.userEventTriggered(ctx, evt);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Object msg) throws Exception {
        if (msg == null) {
            return;
        }
        ByteBuf byteBuf = (ByteBuf) msg;
        byte[] bytes = new byte[byteBuf.readableBytes()];//读取可用字符长度
        byteBuf.readBytes(bytes);
        mNetConnStateServiceListener.onMessageResponse(bytes);
        String str = new String(bytes, "ascii");
        LogUtils.i("ascii to str = " + str);
    }
}
