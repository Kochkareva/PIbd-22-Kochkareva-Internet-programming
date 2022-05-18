package ru.ulstu.is.sbapp.student.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.ulstu.is.sbapp.student.model.Lesson;
import ru.ulstu.is.sbapp.student.service.LessonService;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/lesson")
public class LessonController {
    private final LessonService lessonService;


    public LessonController(LessonService lessonService) {
        this.lessonService = lessonService;
    }

    @GetMapping("/{id}")
    public Lesson getLesson(@PathVariable Long id) {
        return lessonService.findLesson(id);
    }

    @GetMapping("/")
    public List<Lesson> getLessons() {
        return lessonService.findAllLessons();
    }

    /**
     *
     private String LessonName;
     private Date LessonDate;
     */
    @PostMapping("/")
    public Lesson createLesson(@RequestParam("LessonName") String LessonName,
                                 @RequestParam("LessonDate") String LessonDate) {
        return lessonService.addLesson(LessonName, LessonDate);
    }

    @PatchMapping("/{id}")
    public Lesson updateLesson(@PathVariable Long id,
                               @RequestParam("LessonName") String LessonName,
                               @RequestParam("LessonDate") String LessonDate) {
        return lessonService.updateLesson(id, LessonName, LessonDate);
    }

    @DeleteMapping("/{id}")
    public Lesson deleteLesson(@PathVariable Long id) {
        return lessonService.deleteLesson(id);
    }
}
