package uz.sudev.fullstack01employeesystemapi.services;

import org.springframework.http.ResponseEntity;
import uz.sudev.fullstack01employeesystemapi.entity.Employee;
import uz.sudev.fullstack01employeesystemapi.payload.EmployeeDTO;
import uz.sudev.fullstack01employeesystemapi.payload.Message;

import java.util.List;

public interface EmployeeService {
    ResponseEntity<Message> createEmployee(EmployeeDTO employeeDTO);

    ResponseEntity<List<Employee>> getEmployees();

    ResponseEntity<Message> deleteEmployee(Long id);

    ResponseEntity<Message> editEmployee(Long id, EmployeeDTO employeeDTO);

    ResponseEntity<?> getEmployee(Long id);
}
