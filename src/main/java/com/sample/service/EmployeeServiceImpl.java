package com.sample.service;

import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sample.dao.EmployeeDao;
import com.sample.dto.EmployeeVo;
import com.sample.exception.ApplicationException;
import com.sample.model.EmployeeBo;
import com.sample.util.CommonUtil;

/**
 * Service to fetch Employee information
 * 
 * @author hmolla
 *
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {
    private static final Logger LOG = Logger
            .getLogger(EmployeeServiceImpl.class);
    @Autowired
    EmployeeDao employeeDao;


    /**
     * Method to get employee information.
     * 
     * @param id
     * 
     * @return
	 * @see com.sample.service.EmployeeService#getEmployeeDetails(id)
     */
	@Override
    public EmployeeVo getEmployeeDetails(long id) {
        LOG.info("Started executing getEmployeeDetails");
        EmployeeBo employeeBo = employeeDao
                .getEmployeeById(id);

        EmployeeVo employeeVo = CommonUtil
                .convertEmployeeBoToVo(employeeBo);
        LOG.info("Finished executing getEmployeeDetails");
        return employeeVo;
    }

    /**
     * Method to create or update employee information. (non-Javadoc)
     * 
     * @throws ApplicationException
     * @see com.sample.service.EmployeeService#createOrUpdateEmployeeDetails(com.emea.dto.EmployeeVo)
     */
    @Override
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public EmployeeVo createOrUpdateEmployeeDetails(
            EmployeeVo employeeVo) throws ApplicationException {
        LOG.info("Started executing createOrUpdateEmployeeDetails");

        boolean createFlow = employeeVo.getId() == null;
        EmployeeBo employeeBo = CommonUtil.convertEmployeeVoToBo(employeeVo,
                createFlow);


       
        if (createFlow) {
             employeeDao
                    .createEmployee(employeeBo);
        } else {
             employeeDao
                    .updateEmployee(employeeBo);
        }
        EmployeeBo employeeBoResult = employeeDao
                .getEmployeeById(employeeBo.getId());

        LOG.info("Finished executing createOrUpdateEmployeeDetails");
        return CommonUtil.convertEmployeeBoToVo(employeeBoResult);
    }

}
