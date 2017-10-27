package com.sample.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "Employee")
public class EmployeeBo {

    @Id
    @SequenceGenerator(name = "employee_info_seq_GENERATOR", sequenceName = "employee_info_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "employee_info_seq_GENERATOR")
    private Long id;
	/*
    @OneToMany(targetEntity = TransactionBo.class, mappedBy = "accountInfo", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    Set<TransactionBo> transactionBos;
*/
    @Column(name = "salary")
    Double salary;
	
	@Column(name = "name")
    String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
	/*
    public Set<TransactionBo> getTransactionBos() {
        return transactionBos;
    }

    public void setTransactionBos(Set<TransactionBo> transactionBos) {
        this.transactionBos = transactionBos;
    }
*/
    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

	 public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
