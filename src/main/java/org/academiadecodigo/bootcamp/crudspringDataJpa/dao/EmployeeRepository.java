package org.academiadecodigo.bootcamp.crudspringDataJpa.dao;

import org.academiadecodigo.bootcamp.crudspringDataJpa.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    //that´s it... no need to write any code

    //we dont need the implementation classes like dao interface and dao implementation... erase those classes :)

    //APP -> REST CONTROLLER -> SERVICE LAYER -> REPOSITORY LAYER -> DB
}