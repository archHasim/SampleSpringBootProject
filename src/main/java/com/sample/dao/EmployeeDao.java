package com.sample.dao;

import org.springframework.stereotype.Component;

import com.sample.model.EmployeeBo;

/**
 * Interface to fetch data of employee.
 * 
 * @author hmolla
 *
 */
@Component
public interface EmployeeDao {
    /**
     * Method to get employee information based on id
     * 
     * @param accountNumber
     * @param sortCode
     * @return
     */
    public EmployeeBo getEmployeeById(long id);

    /**
     * Method to create employee based on inputs.
     * 
     * @param accountNumber
     * @param sortCode
     * @return
     */
    public EmployeeBo createEmployee(EmployeeBo employeeBo);

    /**
     * Method to update employee based on inputs.
     * @param accountInfoBo
     * @return
     */
    public EmployeeBo updateEmployee(EmployeeBo employeeBo);
}
