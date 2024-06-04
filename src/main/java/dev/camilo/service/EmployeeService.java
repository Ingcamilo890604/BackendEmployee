package dev.camilo.service;

import dev.camilo.classes.EmployeeRecord;
import dev.camilo.general.utils.Result;
import dev.camilo.models.Employee;
import dev.camilo.repository.EmployeeRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;



public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository){this.employeeRepository = employeeRepository;}

    public Result<Employee> getEmployeeById(Integer id) {
        Optional<Employee> employeeOptional = employeeRepository.findById(id);
        return employeeOptional.map(Result::success).orElseGet(() -> Result.failure("No fue posible encontrar el empleado con ID: " + id));
    }

    public void createEmployee(EmployeeRecord employeeRecord){
        employeeRepository.createEmployee(employeeRecord);
    }

    public Result<Object> updatePosition(Integer id, String position) {
        return employeeRepository.findById(id)
                .map(employee -> {
                    employeeRepository.updatePosition(id, position);
                    return Result.success(null);
                })
                .orElse(Result.failure("No se encontró ningún empleado con ID: " + id));
    }

    public Result<Collection<Employee>> getAllEmployees() {
        Collection<Employee> employees = employeeRepository.getEmployees();
        if (!employees.isEmpty()) {
            return Result.success(employees);
        } else {
            return Result.failure("No se encontraron empleados.");
        }
    }
    public Result<List<Employee>> getEmployeeByPosition(String position) {
        Optional<List<Employee>> listOptional = employeeRepository.findByPosition(position);
        return listOptional.map(Result::success)
                .orElseGet(() -> Result.failure("No fue posible encontrar un empleado con la posicion: " + position));
    }


}
