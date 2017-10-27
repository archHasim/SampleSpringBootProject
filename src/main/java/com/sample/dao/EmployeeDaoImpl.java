package com.sample.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.sample.model.EmployeeBo;

/**
 * Class to fetch employee information.
 * 
 * @author hmolla
 *
 */
@Component
public class EmployeeDaoImpl implements EmployeeDao {
    private static final Logger LOG = Logger.getLogger(EmployeeDaoImpl.class);
    private static final String ID = "id";
    private static final String SORT_CODE = "sortCode";
    @PersistenceContext
    private EntityManager entityManager;

    /**
     * Method to get Employee based on id
     * (non-Javadoc)
     * 
     * @see
     * com.sample.dao.EmployeeDao#getEmployeeById(
     * long, long)
     */
    public EmployeeBo getEmployeeById(long id) {
        EmployeeBo result = null;
        LOG.info("Started executing getEmployeeById");
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();

        CriteriaQuery<EmployeeBo> cq = cb.createQuery(EmployeeBo.class);
        Root<EmployeeBo> employee = cq.from(EmployeeBo.class);
        cq.select(employee);
		/*
        cq.where(cb.and(cb.equal(accountInfo.get(ID), accountNumber),
                cb.equal(accountInfo.get(SORT_CODE), sortCode)));
		*/
		cq.where(cb.equal(employee.get(ID), id));		
				
				
        TypedQuery<EmployeeBo> q = entityManager.createQuery(cq);
        List<EmployeeBo> employeeBos = q.getResultList();

        if (employeeBos != null && employeeBos.size() > 0) {
            result = employeeBos.get(0);
        }
        LOG.info("Finished executing getEmployeeById");
        return result;

    }

    @Override
    public EmployeeBo createEmployee(EmployeeBo employeeBo) {
        LOG.info("Started executing createEmployee");
        entityManager.persist(employeeBo);
        LOG.info("Finished executing createEmployee");
        
        return employeeBo;
    }

    @Override
    public EmployeeBo updateEmployee(EmployeeBo employeeBo) {
        LOG.info("Started executing updateEmployee");
        employeeBo =  entityManager.merge(employeeBo);
        LOG.info("Finished executing updateEmployee");
        return employeeBo;
    }
}
