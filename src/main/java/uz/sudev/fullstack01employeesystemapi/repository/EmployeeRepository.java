package uz.sudev.fullstack01employeesystemapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.sudev.fullstack01employeesystemapi.entity.Employee;


@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    boolean existsByEmail(String email);
    boolean existsByFirstNameAndLastName(String firstName,String lastName);
}
