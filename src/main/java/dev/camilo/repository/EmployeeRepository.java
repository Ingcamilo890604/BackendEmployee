package dev.camilo.repository;

import dev.camilo.classes.EmployeeRecord;
import dev.camilo.general.enums.EnumCitys;
import dev.camilo.models.Employee;
import dev.camilo.models.Office;

import java.time.LocalDate;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class EmployeeRepository {
    private final Map<Integer, Employee> employeeMap = new HashMap<>();
    private final OfficeRepository officeRepository;

    public EmployeeRepository(OfficeRepository officeRepository) {
        this.officeRepository = officeRepository;
        Optional<Office> office = officeRepository.findById(1);
        office.ifPresent(o -> {
            Employee employee = new Employee("Camilo", "Developer", LocalDate.parse("2023-08-23"), 20000000f, o);
            employeeMap.put(employee.getId(), employee);
            o.addEmployee(employee);
        });
    }

    public void createEmployee(EmployeeRecord employeeRecord) {
        Optional<Office> office = officeRepository.findById(employeeRecord.officeId());
        if (office.isPresent()) {
            Employee employee = new Employee(employeeRecord.name(), employeeRecord.position(), employeeRecord.hiringDate(), employeeRecord.salary(), office.get());
            employeeMap.put(employee.getId(), employee);
            office.get().addEmployee(employee);
        } else {
            throw new IllegalArgumentException("Office not found");
        }
    }

    public void updatePosition(Integer id, String newPosition) {
        Employee employee = employeeMap.get(id);
        if (employee != null) {
            employee.setPosition(newPosition);
        }
    }

    public Collection<Employee> getEmployees() {
        return employeeMap.values();
    }

    public Optional<Employee> findById(int id) {
        return Optional.ofNullable(employeeMap.get(id));
    }

    public Optional<List<Employee>> findByPosition(String position) {
        List<Employee> filteredEmployees = employeeMap.values().stream()
                .filter(employee -> employee.getPosition().equalsIgnoreCase(position))
                .toList();
        return filteredEmployees.isEmpty() ?
                Optional.empty() :
                Optional.of(filteredEmployees);

    }
}
