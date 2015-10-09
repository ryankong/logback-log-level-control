package com.ryan.common.logbackplugin.servlet;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ryan.common.logbackplugin.controller.LogbackController;
import com.ryan.common.logbackplugin.init.LogbackLogLevelControlPluginInitializer;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by kongryan on 15/9/29.
 */
public class LogbackServlet extends HttpServlet {
    public static JSONArray functionInfo = new JSONArray() ;
    public LogbackServlet(){
        functionInfo.add(new JSONObject(new HashMap<String, Object>() {
            {
                put("url", LogbackLogLevelControlPluginInitializer.rootPath+"/logbackSetLevel");
                put("function", "设置所有日志等级");
            }
        }));
        functionInfo.add(new JSONObject(new HashMap<String, Object>() {
            {
                put("url", LogbackLogLevelControlPluginInitializer.rootPath+"/getLoggerAndLevel");
                put("function", "获取所有日志和等级信息");
            }
        }));
        functionInfo.add(new JSONObject(new HashMap<String, Object>() {
            {
                put("url", LogbackLogLevelControlPluginInitializer.rootPath+"/getLoggerAndLevelByName");
                put("function", "根据日志名获取日志信息");
            }
        }));
        functionInfo.add(new JSONObject(new HashMap<String, Object>() {
            {
                put("url", LogbackLogLevelControlPluginInitializer.rootPath+"/logbackSetLevelByName");
                put("function", "设置指定日志的日志等级");
            }
        }));
    }
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        //获取访问的uri
        String requestURI = request.getRequestURI();
        Object rs = null;

        if (requestURI.endsWith(LogbackLogLevelControlPluginInitializer.rootPath+"/logbackSetLevel")) {
            String level = request.getParameter("level");
            rs = LogbackController.logbackSetLevel(level);

        } else if (requestURI.endsWith(LogbackLogLevelControlPluginInitializer.rootPath+"/getLoggerAndLevel")) {
            rs = LogbackController.getLoggerAndLevel();

        } else if (requestURI.endsWith(LogbackLogLevelControlPluginInitializer.rootPath+"/getLoggerAndLevelByName")){
            String loggerName = request.getParameter("loggerName");
            rs = LogbackController.getLoggerAndLevelByName(loggerName);

        } else if (requestURI.endsWith(LogbackLogLevelControlPluginInitializer.rootPath+"/logbackSetLevelByName")){
            String loggerName = request.getParameter("loggerName");
            String level = request.getParameter("level");
            rs = LogbackController.logbackSetLevelByName(loggerName,level);

        } else {// 默认跳转到插件的功能接口说明
            rs = functionInfo;
        }

        // 生成HTTP响应结果
        PrintWriter out;
        // content type
        response.setContentType("application/json;charset=UTF-8");
        // write html page
        out = response.getWriter();
        out.println(JSON.toJSONString(rs));


        out.close();
    }

    public void doPost(HttpServletRequest req, HttpServletResponse res)
            throws IOException, ServletException {

        this.doGet(req, res);
    }
}