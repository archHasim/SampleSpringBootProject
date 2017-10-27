package com.sample.service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.sample.dto.EmployeeVo;
import com.sample.exception.ApplicationException;

/**
 * Service to fetch account information
 * 
 * @author hmolla
 *
 */
@Service
public interface EmployeeService {
    /**
     * Method to get Employee information.
     * 
     * @param id
     * 
     * @return
     */
    public EmployeeVo getEmployeeDetails(long id);

    /**
     * Method to create or update employee information.
     * @param employeeVo
     * @return
     * @throws ApplicationException 
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public EmployeeVo createOrUpdateEmployeeDetails(
            EmployeeVo employeeVo) throws ApplicationException;

}
