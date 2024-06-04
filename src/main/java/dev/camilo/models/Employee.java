package dev.camilo.models;

import java.time.LocalDate;
import java.util.Optional;

public class Employee {
    private static int count = 0;
    private final Integer id;
    private final String name;
    private String position;
    private final LocalDate hiringDate;
    private final Boolean status;
    private final Float salary;
    private final Optional<Office> office;

    public Employee(String name, String position, LocalDate hiringDate, Float salary, Office office) {
        this.id = ++count;
        this.name = name;
        this.position = position;
        this.hiringDate = hiringDate;
        this.status = true;
        this.salary = salary;
        this.office = Optional.ofNullable(office);
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public LocalDate getHiringDate() {
        return hiringDate;
    }

    public Float getSalary() {
        return salary;
    }

    public Optional<Office> getOffice() {
        return office;
    }


    @Override
    public String toString() {
        return "Employee{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", position='" + position + '\'' +
                ", hiringDate=" + hiringDate +
                ", status=" + status +
                ", salary=" + salary +
                '}';
    }


}
