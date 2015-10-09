package com.ryan.common.logbackplugin.controller;

import ch.qos.logback.classic.Level;
import com.ryan.common.logbackplugin.service.LogbackService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by kongryan on 15/9/29.
 */
public class LogbackController {

    public static String logbackSetLevel(String level){
        String result = "修改成功";
        try {

        } catch (Exception e) {
            e.printStackTrace();
            result ="操作失败！提示信息：" + e.getMessage();
        }

        return result;
    }


    public static List<Map<String, String>> getLoggerAndLevel(){
        List<Map<String, String>> loggerSettingInfo = new ArrayList<Map<String,String>>();
        try {
            loggerSettingInfo = LogbackService.getLoggerAndLevel();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return loggerSettingInfo;
    }

    public static Map<String, String> getLoggerAndLevelByName(String loggerName){
        Map<String, String> loggerSettingInfo = new HashMap<String, String>();
        try {
            loggerSettingInfo = LogbackService.getLoggerAndLevelByName(loggerName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return loggerSettingInfo;
    }

    public static String logbackSetLevelByName(String loggerName,String level){
        String result = "修改成功";
        try {
            LogbackService.setLogLevel(loggerName, Level.toLevel(level));
        } catch (Exception e) {
            e.printStackTrace();
            result ="操作失败！提示信息：" + e.getMessage();
        }
        return result;
    }
}
