package me.jessyan.mvparms.demo.app.utils;


import org.simple.eventbus.EventBus;

/**
 * EventBus事件总线工具类
 * Created by gaohui.you on 3/8/18
 * Email:839939978@qq.com
 */
public class EventBusUtils extends BaseUtils {

    /**
     * 取消订阅
     */
    public static void unregister(Object subscriber) {
        EventBus.getDefault().unregister(subscriber);
    }

    /**
     * 订阅事件
     */
    public static void register(Object subscriber) {
        EventBus.getDefault().register(subscriber);
    }

    /**
     * 发送事件
     */
    public static void post(Object event,String tag) {
        EventBus.getDefault().post(event,tag);
    }

}
