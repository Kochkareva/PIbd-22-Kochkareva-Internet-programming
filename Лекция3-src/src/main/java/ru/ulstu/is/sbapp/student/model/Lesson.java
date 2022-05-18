package ru.ulstu.is.sbapp.student.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Lesson {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column()
    private String LessonName;
    private String LessonDate;
    @ManyToMany
    @JoinTable(name="lessons_typeReportings",
    joinColumns = @JoinColumn(name ="lesson_fk"),
    inverseJoinColumns = @JoinColumn(name = "typeReporting_fk"))
    private List<TypeReporting> typeReportings = new ArrayList<>();
    @ManyToOne
    @JoinColumn(name = "lesson_fk")
    private Department department;

    public Lesson() {
    }

    public Lesson(String LessonName, String LessonDate){
        this.LessonName = LessonName;
        this.LessonDate = LessonDate;
    }

    public Long getId() {
        return id;
    }

    public String getLessonName(){return  LessonName;}
    public void setLessonName(String LessonName) {this.LessonName = LessonName;}

    public String getLessonDate(){return LessonDate;}
    public void setLessonDate(String LessonDate){this.LessonDate = LessonDate;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lesson lesson = (Lesson) o;
        return Objects.equals(id, lesson.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Lesson{" +
                "id = " + id +
                ", LessonName = '" + LessonName + '\'' +
                ", LessonDate = '" + LessonDate + '\'' +
                '}';
    }

    public void setTypeReportings (List<TypeReporting> typeReportings)
    {
        this.typeReportings = typeReportings;
    }

    public void addTypeReporting(TypeReporting typeReporting) {
        typeReportings.add(typeReporting);
        if (!typeReporting.getLessons().contains(this)) {
            typeReporting.addLesson(this);
        }
    }

    public List<TypeReporting> getTypeReportings(){return this.typeReportings;}

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
        if(!department.getLessons().contains(this)){
            department.getLessons().add(this);
        }
    }
}
