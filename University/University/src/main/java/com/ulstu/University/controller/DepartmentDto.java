package com.ulstu.University.controller;

import com.ulstu.University.model.Department;
import com.ulstu.University.model.TypeReporting;

import java.util.List;

public class DepartmentDto {
    private final long id;
    private String DepartmentName;
    private String Login;
    private String Password;
    private List<TypeReporting> typeReportings;

    public DepartmentDto(Department department) {
        this.id = department.getId();
        this.DepartmentName = department.getDepartmentName();
        this.Login = department.getLogin();
        this.Password = department.getPassword();
        this.typeReportings = department.getTypeReportings();
    }

    public long getId() {
        return id;
    }

    public String getDepartmentName() {
        return DepartmentName;
    }

    public String getLogin(){return Login;}

    public String getPassword(){return Password;}

    public List<TypeReporting> getTypeReportings(){return typeReportings;}
}
