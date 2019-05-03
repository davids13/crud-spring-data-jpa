package org.academiadecodigo.bootcamp.crudspringDataJpa.service;

import org.academiadecodigo.bootcamp.crudspringDataJpa.entity.Employee;

import java.util.List;

public interface EmployeeService {

    List<Employee> findAll();

    Employee findById(int id);

    void save(Employee employee);

    void deleteById(int id);
}
