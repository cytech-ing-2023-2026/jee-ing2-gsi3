package fr.cyu.jee.dto;

import jakarta.validation.constraints.NotNull;

public class DeleteCourseDTO {

    @NotNull
    private int id;

    @NotNull
    public int getId() {
        return id;
    }

    public void setId(@NotNull int id) {
        this.id = id;
    }
}
