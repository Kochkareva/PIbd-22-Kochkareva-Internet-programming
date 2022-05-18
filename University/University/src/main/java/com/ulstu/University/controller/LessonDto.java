package com.ulstu.University.controller;

import com.ulstu.University.model.Lesson;
import com.ulstu.University.model.TypeReporting;

import java.util.List;

public class LessonDto {
    private final long id;
    private String LessonName;
    private String LessonDate;
    private List<TypeReporting> typeReportings;

    public LessonDto(Lesson lesson) {
        this.id = lesson.getId();
        this.LessonName = String.format("%s",lesson.getLessonName());
        this.LessonDate = String.format("%s",lesson.getLessonDate());
        this.typeReportings = lesson.getTypeReportings();
    }

    public long getId() {
        return id;
    }

    public String getLessonName(){return  LessonName;}

    public String getLessonDate(){return LessonDate;}

    public List<TypeReporting> getTypeReportings(){return typeReportings;}
}
