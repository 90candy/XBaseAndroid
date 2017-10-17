package com.hengyi.baseandroidcore.utils;

import android.view.LayoutInflater;
import android.view.View;

import com.hengyi.baseandroidcore.base.BaseApplication;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/10/11.
 * View工具类
 * 用于根据R文件获取View视图。带缓存。
 */

public class ViewUtils {
    private static int MAX_VIEW_COUNT = 10;
    private static ViewUtils instance = null;
    private static Map<Integer,View> views;

    /**
     * 双重枷锁  单列模式
     * @return
     */
    public static synchronized ViewUtils getInstance(){
        synchronized (ViewUtils.class){
            if(instance == null) {
                instance = new ViewUtils();
                views = new HashMap<Integer, View>();
            }
            return instance;
        }
    }

    private View getViews(int layout_id){
        return LayoutInflater.from(BaseApplication.getApplication()).inflate(layout_id,null);
    }

    //获取一个视图
    public View getView(int layout_id){
        View view = null;
        if(views.containsKey(layout_id)){
            view = views.get(layout_id);
        }else{
            view = getViews(layout_id);
            if(views.size() <= MAX_VIEW_COUNT){
                views.put(layout_id,view);
            }
        }

        return view;
    }

}