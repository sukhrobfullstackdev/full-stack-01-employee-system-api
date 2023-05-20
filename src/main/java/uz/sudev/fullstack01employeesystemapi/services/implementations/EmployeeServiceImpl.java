package uz.sudev.fullstack01employeesystemapi.services.implementations;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.sudev.fullstack01employeesystemapi.entity.Employee;
import uz.sudev.fullstack01employeesystemapi.payload.EmployeeDTO;
import uz.sudev.fullstack01employeesystemapi.payload.Message;
import uz.sudev.fullstack01employeesystemapi.repository.EmployeeRepository;
import uz.sudev.fullstack01employeesystemapi.services.EmployeeService;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public ResponseEntity<List<Employee>> getEmployees() {
        return ResponseEntity.ok(employeeRepository.findAll());
    }

    @Override
    public ResponseEntity<?> getEmployee(Long id) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);
        if (optionalEmployee.isPresent()) {
            return ResponseEntity.ok(optionalEmployee.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Message(false,"The employee has not been found!"));
        }
    }

    @Override
    public ResponseEntity<Message> createEmployee(EmployeeDTO employeeDTO) {
        if (!employeeRepository.existsByEmail(employeeDTO.getEmail())) {
            if (!employeeRepository.existsByFirstNameAndLastName(employeeDTO.getFirstName(), employeeDTO.getLastName())) {
                employeeRepository.save(new Employee(employeeDTO.getFirstName(), employeeDTO.getLastName(), employeeDTO.getEmail()));
                return ResponseEntity.status(HttpStatus.CREATED).body(new Message(true, "The employee has been successfully saved!"));
            } else {
                return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(new Message(false, "The first name and last name has been already registered!"));
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(new Message(false, "The email has been already registered!"));
        }
    }

    @Override
    public ResponseEntity<Message> editEmployee(Long id, EmployeeDTO employeeDTO) {
        Optional<Employee> employeeOptional = employeeRepository.findById(id);
        if (employeeOptional.isPresent()) {
            Employee employee = employeeOptional.get();
            employee.setFirstName(employeeDTO.getFirstName());
            employee.setLastName(employeeDTO.getLastName());
            employee.setEmail(employeeDTO.getEmail());
            employeeRepository.save(employee);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(new Message(false, "The employee has been successfully edited!"));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Message(false, "The employee has not been found!"));
        }
    }

    @Override
    public ResponseEntity<Message> deleteEmployee(Long id) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);
        if (optionalEmployee.isPresent()) {
            employeeRepository.delete(optionalEmployee.get());
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new Message(true, "The employee has been successfully deleted!"));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Message(false, "The employee is not found!"));
        }
    }
}
