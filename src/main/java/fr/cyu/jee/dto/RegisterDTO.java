package fr.cyu.jee.dto;

import fr.cyu.jee.model.UserType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public class RegisterDTO {

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate dob;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String password;

    @NotBlank
    @NotNull
    @Pattern(regexp = "ADMIN|TEACHER|STUDENT")
    private String userType;

    public RegisterDTO() {}

    public RegisterDTO(String firstName, String lastName, LocalDate dob, String email, String password, String userType) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
        this.email = email;
        this.password = password;
        this.userType = userType;
    }

    public RegisterDTO(String firstName, String lastName, LocalDate dob, String email, String password, UserType userType) {
        this(firstName, lastName, dob, email, password, userType.name());
    }

    public @NotBlank String getFirstName() {
        return firstName;
    }

    public void setFirstName(@NotBlank String firstName) {
        this.firstName = firstName;
    }

    public @NotBlank String getLastName() {
        return lastName;
    }

    public void setLastName(@NotBlank String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public @NotBlank @Email String getEmail() {
        return email;
    }

    public void setEmail(@NotBlank @Email String email) {
        this.email = email;
    }

    public @NotBlank String getPassword() {
        return password;
    }

    public void setPassword(@NotBlank String password) {
        this.password = password;
    }

    public UserType getUserType() {
        return UserType.valueOf(userType);
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }


}
