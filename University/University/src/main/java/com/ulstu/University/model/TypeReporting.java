package com.ulstu.University.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class TypeReporting {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column()
    private int ReportNumber;
    private String ReportName;
    @ManyToMany(mappedBy = "typeReportings")
    @JsonBackReference
    private List<Lesson> lessons = new ArrayList<>();
    @ManyToMany(mappedBy = "typeReportings")
    @JsonManagedReference
    private List<Discipline> disciplines = new ArrayList<>();
    @ManyToOne
    @JoinColumn(name = "typeReporting_fk")
    Department department;

    public TypeReporting() {
    }

    public TypeReporting(int ReportNumber, String ReportName, Department department){
        this.ReportNumber = ReportNumber;
        this.ReportName = ReportName;
        this.department = department;
    }

    public Long getId() {
        return id;
    }

    public int getReportNumber(){return ReportNumber;}
    public void setReportNumber(int ReportNumber){this.ReportNumber = ReportNumber;}

    public String getReportName(){return  ReportName;}
    public void setReportName(String ReportName){this.ReportName = ReportName;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TypeReporting typeReporting = (TypeReporting) o;
        return Objects.equals(id, typeReporting.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "TypeReporting{" +
                "id = " + id +
                ", ReportNumber = " + ReportNumber +
                ", ReportName = '" + ReportName + '\'' +
                '}';
    }

    public void setDisciplines (List<Discipline> disciplines)
    {
        this.disciplines = disciplines;
    }

    public void addDiscipline(Discipline discipline) {
        disciplines.add(discipline);
        if (!discipline.getTypeReportings().contains(this)) {
            discipline.addTypeReporting(this);
        }
    }

    public List<Discipline> getDisciplines(){return this.disciplines;}

    public void setLessons (List<Lesson> lessons)
    {
        this.lessons = lessons;
    }

    public void addLesson(Lesson lesson) {
        lessons.add(lesson);
        if (!lesson.getTypeReportings().contains(this)) {
            lesson.addTypeReporting(this);
        }
    }

    public List<Lesson> getLessons(){return this.lessons;}

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
        if(!department.getTypeReportings().contains(this)){
            department.getTypeReportings().add(this);
        }
    }
}
