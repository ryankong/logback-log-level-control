package com.ryan.common.logbackplugin.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;

/**
 * Created by kongryan on 15/9/29.
 */
public class LogbackService {
    /**
     * 返回日志设置信息
     * @return
     */
    public static List<Map<String, String>> getLoggerAndLevel(){
        LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();
        List<Logger> loggerList = lc.getLoggerList();
        List<Map<String, String>> result = new ArrayList<Map<String,String>>();
        for(Logger logger : loggerList){
            if(logger.getLevel() != null){
                Map<String, String> loggerSettingInfo = new HashMap<String, String>();
                loggerSettingInfo.put("loggerName", logger.getName());
                loggerSettingInfo.put("loggerLevel", logger.getLevel().toString());
                result.add(loggerSettingInfo);
            }
        }
        return result;
    }
    /**
     * 返回日志设置信息
     * @return
     */
    public static Map<String, String> getLoggerAndLevelByName(String loggerName){
        LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();
        Logger logger = lc.getLogger(loggerName);
        Map<String, String> loggerSettingInfo = new HashMap<String, String>();
        if(logger.getLevel() != null){
            loggerSettingInfo.put("loggerName", logger.getName());
            loggerSettingInfo.put("loggerLevel", logger.getLevel().toString());
        }
        return loggerSettingInfo;
    }
    /**
     * 全部设为同一个level
     * @param level
     */
    public static void setLogLevel(Level level){
        LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();
        List<Logger> loggerList = lc.getLoggerList();
        for(Logger logger : loggerList){
            setLogLevel(level, logger);
        }
    }
    /**
     * 设置指定logger级别
     * @param logName
     * @param level
     */
    public static void setLogLevel(String logName,Level level){
        LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();
        Logger logger = lc.getLogger(logName);
        setLogLevel(level, logger);
    }
    /**
     * 设置日志级别，通用做法：打日志和null检查
     * @param level
     * @param logger
     */
    public static void setLogLevel(Level level,Logger logger){
        if (logger.getLevel() != null) {
            String orgLevel = logger.getLevel().levelStr;
            logger.setLevel(level);
            System.out.println("LogbackService.setLogLevel,set " + logger.getName()+" from level: "+ orgLevel + " to level " + level);
        }
    }



}
