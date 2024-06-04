package dev.camilo;

import dev.camilo.classes.EmployeeRecord;
import dev.camilo.classes.OfficeRecord;
import dev.camilo.general.enums.EnumCitys;
import dev.camilo.general.exceptions.OfficeNotFoundException;
import dev.camilo.general.utils.Result;
import dev.camilo.models.Employee;
import dev.camilo.models.Office;
import dev.camilo.repository.EmployeeRepository;
import dev.camilo.repository.OfficeRepository;
import dev.camilo.service.EmployeeService;
import dev.camilo.service.OfficeService;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final OfficeRepository officeRepository = new OfficeRepository();
    private static final EmployeeRepository employeeRepository = new EmployeeRepository(officeRepository);
    private static final EmployeeService employeeService = new EmployeeService(employeeRepository);
    private static final OfficeService officeService = new OfficeService(officeRepository);
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        boolean running = true;

        while (running) {
            mostrarMenu();
            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    createEmployee();
                    break;
                case 2:
                    updateEmployeePosition();
                    break;
                case 3:
                    getEmployees();
                    break;
                case 4:
                    findEmployeeById();
                    break;
                case 5:
                    findEmployeesByPosition();
                    break;
                case 6:
                    findOfficeByEmployee();
                    break;
                case 7:
                    consultEmployeesByOffice();
                    break;
                case 8:
                    createOffice();
                    break;
                case 0:
                    running = false;
                    break;
                default:
                    System.out.println("Opción no válida");
            }
        }
    }

    private static void mostrarMenu() {
        System.out.println("Seleccione una opción:");
        System.out.println("1. Crear empleado");
        System.out.println("2. Actualizar posición de empleado");
        System.out.println("3. Listar empleados");
        System.out.println("4. Buscar empleado por ID");
        System.out.println("5. Buscar empleados por posición");
        System.out.println("6. Consultar oficina de empleado");
        System.out.println("7. Consultar cantidad de empleados en una oficina");
        System.out.println("8. Crear oficina");
        System.out.println("0. Salir");
    }

    private static void createEmployee() {
        System.out.println("Ingrese nombre del empleado:");
        String name = scanner.nextLine();
        System.out.println("Ingrese posición del empleado:");
        String position = scanner.nextLine();
        System.out.println("Ingrese fecha de contratación (YYYY-MM-DD):");
        LocalDate hiringDate = LocalDate.parse(scanner.nextLine());
        System.out.println("Ingrese salario del empleado:");
        Float salary = scanner.nextFloat();
        scanner.nextLine();

        System.out.println("Seleccione la oficina para asociar al empleado:");
        for (Office office : officeService.getAllOffices()) {
            System.out.println(office.getId() + ". " + office.getName());
        }

        try {
            int officeId = Integer.parseInt(scanner.nextLine());
            Office selectedOffice = officeService.getOfficeById(officeId);
            EmployeeRecord employeeRecord = new EmployeeRecord(name, position, hiringDate, salary, officeId);
            employeeService.createEmployee(employeeRecord);
            System.out.println("Empleado creado exitosamente y asociado a la oficina " + selectedOffice.getName() + ".");
        }
        catch (OfficeNotFoundException exception){
            System.out.println("Se presento el siguiente error: " +exception.getMessage());
        }



    }
    private static void createOffice() {
        System.out.println("Ingrese nombre de la oficina:");
        String name = scanner.nextLine();
        System.out.println("Seleccione la ciudad de la oficina:");
        for (int i = 0; i < EnumCitys.values().length; i++) {
            System.out.println((i + 1) + ". " + EnumCitys.values()[i].getName());
        }

        int citySelection = Integer.parseInt(scanner.nextLine());
        EnumCitys[] cities = EnumCitys.values();
        EnumCitys selectedCity = cities[citySelection - 1];
        OfficeRecord officeRecord = new OfficeRecord(name, selectedCity);
        officeService.createOffice(officeRecord);
        System.out.println("Oficina creada exitosamente.");
    }

    private static void updateEmployeePosition() {
        System.out.println("Ingrese ID del empleado:");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Ingrese nueva posición del empleado:");
        String nuevaPosicion = scanner.nextLine();

        employeeService.updatePosition(id, nuevaPosicion);
        System.out.println("Posición actualizada exitosamente.");
    }

    private static void getEmployees() {
        Result<Collection<Employee>> result = employeeService.getAllEmployees();
        if (!result.isSuccess()) {
            System.out.println(result.getErrorMessage());
            return;
        }
        result.getValue().forEach(e ->
                System.out.println(e.getId() + " " + e.getName() + " " + e.getPosition() + " " +
                        e.getHiringDate() + " " + e.getSalary())
        );
    }

    private static void findEmployeeById() {
        System.out.println("Ingrese ID del empleado:");
        int id = scanner.nextInt();
        scanner.nextLine();

        Result<Employee> result = employeeService.getEmployeeById(id);
        if (!result.isSuccess()) {
            System.out.println(result.getErrorMessage());
            return;
        }
        Employee employee = result.getValue();
        System.out.println(employee.getId() + " " + employee.getName() + " " +
                employee.getPosition() + " " + employee.getHiringDate() + " " + employee.getSalary());
    }


    private static void findEmployeesByPosition() {
        System.out.println("Ingrese posición del empleado:");
        String posicion = scanner.nextLine();

        Result<List<Employee>> result = employeeService.getEmployeeByPosition(posicion);
        if(!result.isSuccess()){
            System.out.println(result.getErrorMessage());
            return;
        }
        result.getValue().forEach(e ->
                System.out.println(e.getId() + " " + e.getName() + " " + e.getPosition() + " " +
                        e.getHiringDate() + " " + e.getSalary()));
    }

    private static void findOfficeByEmployee() {
        System.out.println("Ingrese ID del empleado:");
        int id = scanner.nextInt();
        scanner.nextLine();
        Result<Employee> result = employeeService.getEmployeeById(id);
        if(!result.isSuccess()){
            System.out.println(result.getErrorMessage());
            return;
        }
        Employee employee = result.getValue();
        System.out.println("el Empleado se encuentra en la oficina: " + employee.getOffice());


    }

    private static void consultEmployeesByOffice() {
        try{
            System.out.println("Ingrese ID de la oficina:");
            int id = scanner.nextInt();
            scanner.nextLine();

            int quantity = officeService.employeeQuantity(id);
            System.out.println("Cantidad de empleados en la oficina: " + quantity);
        }catch (OfficeNotFoundException exception){
            System.out.println("Se presento el siguiente error: " +exception.getMessage());
        }

    }

}


