package dev.camilo.classes;

import java.time.LocalDate;

public record EmployeeRecord(String name, String position, LocalDate hiringDate, Float salary, int officeId) {
}