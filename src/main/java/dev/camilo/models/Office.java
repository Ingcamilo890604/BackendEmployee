package dev.camilo.models;

import dev.camilo.general.enums.EnumCitys;

import java.util.ArrayList;
import java.util.List;

public class Office {
    private static int count = 0;
    private final Integer id;
    private final String name;
    private final EnumCitys city;
    private final List<Employee> employees;

    public Office(String name, EnumCitys city) {
        this.id = ++count;
        this.name = name;
        this.city = city;
        this.employees = new ArrayList<>();
    }

    public void addEmployee(Employee employee) {
        employees.add(employee);
    }

    public int employeeQuantity() {
        return employees.size();
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Office{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", city=" + city +
                ", employees=" + employees +
                '}';
    }
}
