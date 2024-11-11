package fr.cyu.jee.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;

public class AddGradeDTO {

    @NotBlank
    @Email
    private String email;

    @PositiveOrZero
    private double grade;

    public @NotBlank @Email String getEmail() {
        return email;
    }

    public void setEmail(@NotBlank @Email String email) {
        this.email = email;
    }

    @PositiveOrZero
    public double getGrade() {
        return grade;
    }

    public void setGrade(@PositiveOrZero double grade) {
        this.grade = grade;
    }
}
