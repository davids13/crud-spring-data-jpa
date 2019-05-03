package org.academiadecodigo.bootcamp.crudspringDataJpa.service;

import org.academiadecodigo.bootcamp.crudspringDataJpa.dao.EmployeeRepository;
import org.academiadecodigo.bootcamp.crudspringDataJpa.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    // @Transactional - we can remove since jpaRepository provides this functionality

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    //@Transactional //handles transaction management, so we dont have to manually start and commit transaction
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @Override
    // @Transactional - we can remove since jpaRepository provides this functionality
    public Employee findById(int id) {
        Optional<Employee> result = employeeRepository.findById(id);

        Employee employee = null;
        if(result.isPresent()){
            employee = result.get();
        }
        else {
            throw new RuntimeException("Did not find employee id: " + id);
        }

        return employee;
    }

    @Override
    // @Transactional - we can remove since jpaRepository provides this functionality
    public void save(Employee employee) {
        employeeRepository.save(employee);
    }

    @Override
    // @Transactional - we can remove since jpaRepository provides this functionality
    public void deleteById(int id) {
        employeeRepository.deleteById(id);
    }
}