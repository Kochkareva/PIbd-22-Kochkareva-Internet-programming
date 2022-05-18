package com.ulstu.University.controller;
import com.ulstu.University.service.LessonService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/lesson")
public class LessonController {
    private final LessonService lessonService;

    public LessonController(LessonService lessonService) {
        this.lessonService = lessonService;
    }

    @GetMapping("/{id}")
    public LessonDto getLesson(@PathVariable Long id) {
        return new LessonDto(lessonService.findLesson(id));
    }

    @GetMapping("/")
    public List<LessonDto> getLessons() {
        return lessonService.findAllLessons().stream()
                .map(LessonDto::new)
                .toList();
    }

    /**
     * private String LessonName;
     *     private String LessonDate;
     */
    @PostMapping("/")
    public LessonDto createLesson(@RequestParam("LessonName") String LessonName,
                                  @RequestParam("LessonDate") String LessonDate) {
        return new LessonDto(lessonService.addLesson(LessonName, LessonDate));
    }

    @PostMapping("/{LessonId} {TypeReportingId}")
    public LessonDto addTypeReporting(@RequestParam("LessonId") Long LessonId,
                                      @RequestParam("TypeReportingId") Long TypeReportingId){
        return new LessonDto(lessonService.addTypeReportingToLesson(LessonId, TypeReportingId));
    }
    @PutMapping("/{id}")
    public LessonDto updateLesson(@PathVariable Long id,
                                      @RequestParam("LessonName") String LessonName,
                                      @RequestParam("LessonDate") String LessonDate) {
        return new LessonDto(lessonService.updateLesson(id, LessonName, LessonDate));
    }

    @DeleteMapping("/{id}")
    public LessonDto deleteLesson(@PathVariable Long id) {
        return new LessonDto(lessonService.deleteLesson(id));
    }
}
