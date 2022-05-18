package com.ulstu.University.controller;

import com.ulstu.University.model.Department;
import com.ulstu.University.model.Discipline;
import com.ulstu.University.model.Lesson;
import com.ulstu.University.model.TypeReporting;
import java.util.List;

public class TypeReportingDto {
    private final long id;
    private int ReportNumber;
    private String ReportName;
    private List<Lesson> lessons;
    private List<Discipline> disciplines;
    Department department;

    public TypeReportingDto(TypeReporting typeReporting) {
        this.id = typeReporting.getId();
        this.ReportNumber = typeReporting.getReportNumber();
        this.ReportName = String.format("%s",typeReporting.getReportName());
        this.disciplines = typeReporting.getDisciplines();
        this.lessons = typeReporting.getLessons();
        this.department = typeReporting.getDepartment();
    }

    public long getId() {
        return id;
    }

    public int getReportNumber(){return ReportNumber;}

    public String getReportName(){return ReportName;}

    public List<Discipline> getDisciplines(){return disciplines;}

    public List<Lesson> getLessons(){return lessons;}

    public Department getDepartment(){return department;}
}
