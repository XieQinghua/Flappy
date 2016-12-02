package com.thvc.flappy.application;

import android.app.Application;

/**
 * 项目名称：Flappy
 * 类描述：
 * 创建人：谢庆华.
 * 创建时间：2016/2/24 17:33
 * 修改人：Administrator
 * 修改时间：2016/2/24 17:33
 * 修改备注：
 */
public class FlappyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        /**腾讯Bugly插件初始化*/
//        Bugly.init(getApplicationContext(), "900031668", true);
    }
}
