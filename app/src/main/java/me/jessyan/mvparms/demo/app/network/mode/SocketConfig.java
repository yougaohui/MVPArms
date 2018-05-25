package me.jessyan.mvparms.demo.app.network.mode;

/**
 * Created by gaohui.you on 2/28/18
 * Email:839939978@qq.com
 */
public class SocketConfig {
    public String host;
    public int port;
    public int reconnectionDelay = 10000;//重连间隔
    public int reconnectionMaxTimes = 5;//重连次数
}
