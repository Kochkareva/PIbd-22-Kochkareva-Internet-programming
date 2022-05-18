package com.ulstu.University.controller;

import com.ulstu.University.model.Discipline;

public class DisciplineDto {
    private final long id;
    private String DisciplineName;

    public DisciplineDto(Discipline discipline) {
        this.id = discipline.getId();
        this.DisciplineName = discipline.getDisciplineName();
    }

    public long getId() {
        return id;
    }

    public String getDisciplineName(){return  DisciplineName;}
}
