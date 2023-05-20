package uz.sudev.fullstack01employeesystemapi.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.springframework.stereotype.Component;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Component
public class EmployeeDTO {
    @NotNull(message = "The first name cannot be null!")
    @NotBlank(message = "The first name cannot be blank!")
    @Length(min = 3, message = "The first name's minimum length can be 3")
    private String firstName;
    @NotNull(message = "The last name cannot be null!")
    @NotBlank(message = "The last name cannot be blank!")
    @Length(min = 5, message = "The last name's minimum length can be 5")
    private String lastName;
    @NotNull(message = "The email cannot be null!")
    @NotBlank(message = "The email cannot be blank!")
    @Length(min = 7, message = "The email's minimum length can be 7")
    @Email
    private String email;
}
