package com.ryan.common.logbackplugin.init;

import ch.qos.logback.classic.LoggerContext;
import com.ryan.common.logbackplugin.servlet.LogbackServlet;
import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.WebApplicationInitializer;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

/**
 * Created by kongryan on 15/9/29.
 */
public class LogbackLogLevelControlPluginInitializer implements WebApplicationInitializer{
    private Logger logger = LoggerFactory.getLogger(getClass());

    public final static String rootPath = "/logbackplugin";

    public void onStartup(ServletContext servletContext)
            throws ServletException {
        ILoggerFactory loggerFactoryImpl =  LoggerFactory.getILoggerFactory();
        if (loggerFactoryImpl == null){
            System.out.println("### can`t active logbackplugin-log-level-control ,because not using logbackplugin");
        }
        else if(loggerFactoryImpl instanceof LoggerContext){
            logger.info("### as using logbackplugin as logger, active the logbackplugin-log-level-control ");
            ServletRegistration.Dynamic registration= servletContext.addServlet("##logLevelPlugin", new LogbackServlet());
            registration.addMapping(rootPath+"/*");
            logger.info(" Mapped URL path [" + rootPath + "/*] onto handler 'LogbackController'");

        }else{
            logger.info("### can`t active logbackplugin-log-level-control ,because not using logbackplugin");
        }

    }
}
