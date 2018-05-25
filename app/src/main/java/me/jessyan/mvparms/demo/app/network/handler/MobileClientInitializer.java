package me.jessyan.mvparms.demo.app.network.handler;


import java.util.concurrent.TimeUnit;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.timeout.IdleStateHandler;
import me.jessyan.mvparms.demo.app.network.listener.NetConnStateServiceListener;

/**
 * Created by gaohui.you on 2/28/18
 * Email:839939978@qq.com
 */
public class MobileClientInitializer extends ChannelInitializer<Channel> {

    private NetConnStateServiceListener mNetConnStateServiceListener;

    private int WRITE_WAIT_SECONDS = 0;//写数据等待时间

    private int READ_WAIT_SECONDS = 0;//读数据等待时间

    private int ALL_IDLE_TIME = 0;//所有等待时间

    public MobileClientInitializer(NetConnStateServiceListener netConnStateServiceListener) {
        this.mNetConnStateServiceListener = netConnStateServiceListener;
    }

    @Override
    protected void initChannel(Channel channel) throws Exception {
        ChannelPipeline pipeline = channel.pipeline();
        pipeline.addLast(new IdleStateHandler(READ_WAIT_SECONDS, WRITE_WAIT_SECONDS, ALL_IDLE_TIME, TimeUnit.MILLISECONDS));
        pipeline.addLast("handler", new MobileClientHandler(mNetConnStateServiceListener));
    }
}
