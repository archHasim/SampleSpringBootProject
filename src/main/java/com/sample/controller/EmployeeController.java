package com.sample.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sample.dto.EmployeeVo;

import com.sample.exception.ApplicationException;
import com.sample.exception.ErrorResponse;
import com.sample.service.EmployeeService;
import com.sample.util.CommonUtil;

/**
 * Class to handle employee service
 * 
 * @author hmolla
 *
 */
@RestController
@EnableAutoConfiguration
@RequestMapping("/sampleapiservice/employee")
@Api(value="Employee", description="Endpoint for Employee Information")
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;
    private static final Logger LOG = Logger
            .getLogger(EmployeeController.class);
    private static final String ACCOUNT_NUMBER = "accountNumber";
    private static final String SORT_CODE = "sortCode";


    /**
     * Method to create employee details
     * 
     * @param EmployeeVo
     * @return
     * @throws ApplicationException
     */
    @RequestMapping(value = "/employeedetails", method = RequestMethod.POST, headers = "Accept=application/json")
    @ApiOperation(value="This api creates or update employee information ")
    public @ResponseBody EmployeeVo createEmployeeDetails(
            @RequestBody EmployeeVo employeeVo ) throws ApplicationException{
         LOG.info("Started executing createEmployeeDetails");
       
        EmployeeVo result = employeeService.createOrUpdateEmployeeDetails(employeeVo);
        
        
        LOG.info("Finished executing createEmployeeDetails");
        
        return result;
    }
    
    
    
    
    
    
    /**
     * Method to get employee details
     * 
     * @param EmployeeVo
     * @return
     * @throws ApplicationException
     */
    @RequestMapping(value = "/employeedetails/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
    @ApiOperation(value="This api retrieve employee information based on valid id is specified")
    public @ResponseBody EmployeeVo getEmployee(@PathVariable("id") String idStr)
            throws ApplicationException {
        LOG.info("Started executing getEmployee");
        long id;
       
       
        if (CommonUtil.isNumericRegex(idStr)) {
            id = Long.valueOf(idStr);
        } else {
            throw new ApplicationException(
                    " Id  is mandatory and numeric");
        }


        EmployeeVo employeeVo = employeeService.getEmployeeDetails(id);
        if (employeeVo == null) {
            throw new ApplicationException(
                    "No employee found with id " + id);
        }

       
        LOG.info("Finished executing getEmployee");

        return employeeVo;
    }

    @ExceptionHandler(ApplicationException.class)
    public @ResponseBody ErrorResponse exceptionHandler(Exception ex) {

        ErrorResponse error = new ErrorResponse();

        error.setErrorCode(HttpStatus.PRECONDITION_FAILED.value());

        error.setMessage(ex.getMessage());

        LOG.error(" Error occurred ", ex);

        return error;

    }

}
