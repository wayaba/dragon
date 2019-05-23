package com.blitox.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.blitox.model.Employee;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Long>{

}
