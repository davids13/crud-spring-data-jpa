package org.academiadecodigo.bootcamp.crudspringDataJpa.controller.rest;

import org.academiadecodigo.bootcamp.crudspringDataJpa.dto.EmployeeDTOToEmployee;
import org.academiadecodigo.bootcamp.crudspringDataJpa.dto.EmployeeDto;
import org.academiadecodigo.bootcamp.crudspringDataJpa.dto.EmployeeToEmployeeDTO;
import org.academiadecodigo.bootcamp.crudspringDataJpa.entity.Employee;
import org.academiadecodigo.bootcamp.crudspringDataJpa.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController //Annotating a class with @RestController is the same as annotating it with both @Controller and @ResponseBody
@RequestMapping("/api")
public class EmployeeRestController {

    private EmployeeService employeeService;
    private EmployeeDTOToEmployee employeeDTOToEmployee;
    private EmployeeToEmployeeDTO employeeToEmployeeDTO;

    //set up constructor injection
        // constructor injection (automatically created by spring boot)
    @Autowired
    public EmployeeRestController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Autowired
    public void setEmployeeDTOToEmployee(EmployeeDTOToEmployee employeeDTOToEmployee) {
        this.employeeDTOToEmployee = employeeDTOToEmployee;
    }

    @Autowired
    public void setEmployeeToEmployeeDTO(EmployeeToEmployeeDTO employeeToEmployeeDTO) {
        this.employeeToEmployeeDTO = employeeToEmployeeDTO;
    }

    //expose "/employees" and return list of employees
    @GetMapping("/employees")
    public ResponseEntity<List<EmployeeDto>> findAll() {

        List<EmployeeDto> employeeDtos = new ArrayList<>();

        for(Employee employee : employeeService.findAll()) {
            employeeDtos.add(employeeToEmployeeDTO.convert(employee));
        }

        return new ResponseEntity<>(employeeDtos, HttpStatus.OK);
        //return employeeService.findAll();
    }

    /**
     * @param id the employee id
     * @return the response entity
     *
     * The ResponseEntity class represents an HTTP response, consisting of headers,
     * body and response code.
     * It is used to return a specific http status code from the controller.
     */
    @GetMapping("/employee/{id}")
    public ResponseEntity<EmployeeDto> getEmployee(@PathVariable Integer id) {

        Employee employee = employeeService.findById(id);
        if (employee == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(employeeToEmployeeDTO.convert(employee), HttpStatus.OK);
    }

    //@PathVariable annotation can be used on a controller method argument to bind it to the value of a URI template variable:
    //@RequestBody can be used in a controller to implement smart object serialization and deserialization
    @PostMapping("/employee")
    public ResponseEntity<EmployeeDto> addEmployee(@RequestBody EmployeeDto employeeDto) {

        if(employeeDto.getId() != null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        employeeService.save(employeeDTOToEmployee.convert(employeeDto));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("employee/{id}")
    public ResponseEntity<EmployeeDto> updateEmployee(@RequestBody EmployeeDto employeeDto, @PathVariable Integer id) {

        employeeDto.setId(id);
        employeeService.save(employeeDTOToEmployee.convert(employeeDto));
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @DeleteMapping("employee/{id}")
    public ResponseEntity<EmployeeDto> deleteEmployeeById(@PathVariable Integer id) {

        try {
            employeeService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}