package com.sample.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.log4j.Logger;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.util.ResourceUtils;


import com.sample.exception.ApplicationException;

import com.sample.dto.EmployeeVo;
import com.sample.model.EmployeeBo;

/**
 * This is a common utility class
 * 
 * @author hmolla
 *
 */
public class CommonUtil {
    private static final String ERROR_OCCURED = "Error occurred";
    private static final String PATH_APP_PROPERTIES = "conf/application.properties";
    private static final String pattern = "dd-MM-yyyy";
    private static Logger LOG = Logger.getLogger(CommonUtil.class);

    /**
     * Method to load properties file.
     * 
     * @return
     */
    public static Properties loadApplicationProperties() {
        Properties properties = null;
        try {
            properties = PropertiesLoaderUtils
                    .loadProperties(new FileSystemResource(ResourceUtils
                            .getFile(PATH_APP_PROPERTIES)));
        } catch (final FileNotFoundException e) {
            LOG.error(ERROR_OCCURED, e);
        } catch (final IOException e) {
            LOG.error(ERROR_OCCURED, e);
        }
        return properties;

    }

    /**
     * Read SQL from file and convert to String.
     * 
     * @param resource
     * @return
     */
    public static String readResourceAsString(String resource) {
        try (InputStream is = new ClassPathResource(resource).getInputStream()) {
            return IOUtils.toString(is);
        } catch (IOException e) {
            LOG.error(ERROR_OCCURED, e);
            return null;
        }
    }

    /**
     * Method to load properties
     * 
     * @param properties
     */
    public static void initSpringProfile(Properties properties) {
        if (properties != null) {
            for (String propertyName : properties.stringPropertyNames()) {
                String propertyValue = properties.getProperty(propertyName);
                System.setProperty(propertyName, propertyValue);
            }
        }
    }

    /**
     * Method to find a string is numeric or not.
     * 
     * @param str
     * @return
     */
    public static boolean isNumericRegex(String str) {
        if (str == null)
            return false;
        return str.matches("-?\\d+");
    }

   
	
	/**
     * Method to convert Bo object to Vo object of Employee
     * 
     * @param EmployeeBo
     * @return EmployeeVo
     */
    public static EmployeeVo convertEmployeeBoToVo(
            EmployeeBo employeeBo) {
				EmployeeVo employeeVo = null;
        if (employeeBo != null) {
		employeeVo = new EmployeeVo();
		employeeVo.setId(String.valueOf(employeeBo.getId()));
		employeeVo.setName(employeeBo.getName());
		 employeeVo.setSalary(BigDecimal.valueOf(
                    employeeBo.getSalary()).toPlainString());
		
		}
		
		return employeeVo;
				
	}
	
	/**
     * Method to convert Vo object to Bo object of Employee
     * 
     * @param EmployeeVo
     * @return EmployeeBo
     */
    public static EmployeeBo convertEmployeeVoToBo(
            EmployeeVo employeeVo, boolean createFlow) {
				EmployeeBo employeeBo = null;
        if (employeeVo != null) {
		employeeBo = new EmployeeBo();
		if(!createFlow){
			employeeBo.setId(Long.valueOf( employeeVo.getId()));
		}
		
		employeeBo.setName(employeeVo.getName());
					
		 Double salary = employeeVo.getSalary() != null
                        ? Double.valueOf(employeeVo.getSalary())
                        : 0.0;
		employeeBo.setSalary(salary);
		}
		
		return employeeBo;
	}

   
    /**
     * Method to convert date to string
     * 
     * @param date
     * @return
     */
    public static String convertDateToString(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        if (date != null) {
            return simpleDateFormat.format(date);
        } else {
            return null;
        }
    }

    /**
     * Method to convert date to string
     * 
     * @param date
     * @return
     * @throws ParseException
     */
    public static Date convertStringDate(String date) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        if (date != null) {
            return simpleDateFormat.parse(date);
        } else {
            return null;
        }
    }

    

}
