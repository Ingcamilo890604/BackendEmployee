package dev.camilo.repository;

import dev.camilo.models.Employee;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class EmpolyeeRepository {
    private final Map<String, Employee> employeeStorage = new HashMap<>();

    public void save(Employee employee) {
        employeeStorage.put(employee.getId(), employee);
    }

}
