package com.heima.utils.thread;

import com.heima.model.admin.pojos.AdUser;

public class AdUserThreadLocalUtil {
    private static ThreadLocal<AdUser> threadLocal = new ThreadLocal<>();


    //存数据
    public static void setUser(AdUser adUser){
        threadLocal.set(adUser);
    }

    //取数据
    public static AdUser getUser(){
        return threadLocal.get();
    };

    //清空数据
    public static void clear(){
        threadLocal.remove();
    }
}