package uz.sudev.fullstack01employeesystemapi.controller;


import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import uz.sudev.fullstack01employeesystemapi.entity.Employee;
import uz.sudev.fullstack01employeesystemapi.payload.EmployeeDTO;
import uz.sudev.fullstack01employeesystemapi.payload.Message;
import uz.sudev.fullstack01employeesystemapi.services.EmployeeService;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping(path = "/employee")
public class EmployeeController {
    final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public ResponseEntity<List<Employee>> getEmployees() {
        return employeeService.getEmployees();
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<?> getEmployee(@PathVariable Long id) {
        return employeeService.getEmployee(id);
    }

    @PostMapping(path = "/add")
    public ResponseEntity<Message> createEmployee(@RequestBody @Valid EmployeeDTO employeeDTO) {
        return employeeService.createEmployee(employeeDTO);
    }

    @PutMapping(path = "/edit/{id}")
    public ResponseEntity<Message> editEmployee(@PathVariable Long id, @RequestBody @Valid EmployeeDTO employeeDTO) {
        return employeeService.editEmployee(id, employeeDTO);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Message> deleteEmployee(@PathVariable Long id) {
        return employeeService.deleteEmployee(id);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Message> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Message message = new Message();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String errorMessage = error.getDefaultMessage();
            message.setMessage(errorMessage);
            message.setSuccess(false);
        });
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
    }

}
