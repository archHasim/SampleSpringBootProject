package com.sample.util;

import java.io.File;
import java.sql.SQLException;

import org.h2.tools.Server;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Util class to initiate application.
 * 
 * @author hmolla
 *
 */
public class ApplicationContextUtil implements ApplicationContextAware {
    private static final String LOG_FILE_PATH = "conf/log4j.properties";
    private static final String APP_CONTEXT_PATH_STR = "app.context.file.path";
    private static final String APP_CONTEXT_PATH_VALUE = "classpath:conf/app-config.xml";
    private static Logger LOG = Logger.getLogger(ApplicationContextUtil.class);
    private static ApplicationContext context;
	private static final String H2_SER_PORT = "server.h2.port";
	private static final String H2_SER_PORT_DFLT = "9000";
	private static final String IS_IN_MEMORY_DB = "app.inmemory.db";
    private static final String TCP_PORT_STR = "-tcpPort";

    @Override
    public void setApplicationContext(ApplicationContext applicationContext)
            throws BeansException {
        context = applicationContext;
    }
    public static ApplicationContext getApplicationContext() {
        return context;
    }

    /**
     * Method to initiate application.
     * 
     * @throws SQLException
     */
    public static void init() throws SQLException {

        CommonUtil.initSpringProfile(CommonUtil.loadApplicationProperties());
        PropertyConfigurator.configure(new File(LOG_FILE_PATH)
                .getAbsolutePath());

        String contextFilePath = System.getProperty(APP_CONTEXT_PATH_STR,
                APP_CONTEXT_PATH_VALUE);
		
		 String isInMemoryDb = System.getProperty(IS_IN_MEMORY_DB,
                "");	
		
			
		
		if("y".equalsIgnoreCase(isInMemoryDb)){
		String h2ServPort = System.getProperty(H2_SER_PORT,
                H2_SER_PORT_DFLT);			
		 String[] params = {TCP_PORT_STR, h2ServPort};
            try {
              Server  server = Server.createTcpServer(params);
                server.start();
                
            } catch (SQLException e) {
                e.printStackTrace();
            }
		}
		
			
				
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext(
                contextFilePath);
        applicationContext.registerShutdownHook();
    }
}
