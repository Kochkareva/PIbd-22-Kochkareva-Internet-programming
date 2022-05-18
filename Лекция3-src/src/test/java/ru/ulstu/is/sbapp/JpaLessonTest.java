package ru.ulstu.is.sbapp;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.ulstu.is.sbapp.student.model.Lesson;
import ru.ulstu.is.sbapp.student.model.TypeReporting;
import ru.ulstu.is.sbapp.student.service.LessonService;
import ru.ulstu.is.sbapp.student.service.TypeReportingService;

import javax.persistence.EntityNotFoundException;
import java.util.Date;
import java.util.List;

@SpringBootTest
public class JpaLessonTest {
    private static final Logger log = LoggerFactory.getLogger(JpaDepartmentTests.class);

    @Autowired
    private LessonService lessonService;
    @Autowired
    private TypeReportingService typeReportingService;

    @Test
    void testLessonCreate() {
        lessonService.deleteAllLessons();
        final Lesson lesson = lessonService.addLesson("Философия", "1.09.2022");
        log.info(lesson.toString());
        Assertions.assertNotNull(lesson.getId());
    }

    @Test
    void testLessonRead() {
        lessonService.deleteAllLessons();
        final Lesson lesson = lessonService.addLesson("Философия", "1.09.2022");
        log.info(lesson.toString());
        final Lesson findLesson = lessonService.findLesson(lesson.getId());
        log.info(findLesson.toString());
        Assertions.assertEquals(lesson, findLesson);
    }

    @Test
    void testLessonReadNotFound() {
        lessonService.deleteAllLessons();
        Assertions.assertThrows(EntityNotFoundException.class, () -> lessonService.findLesson(-1L));
    }

    @Test
    void testLessonReadAll() {
        lessonService.deleteAllLessons();
        lessonService.addLesson("Философия", "1.09.2022");
        lessonService.addLesson("Философия", "1.09.2022");
        final List<Lesson> lessons = lessonService.findAllLessons();
        log.info(lessons.toString());
        Assertions.assertEquals(lessons.size(), 2);
    }

    @Test
    void testLessonReadAllEmpty() {
        lessonService.deleteAllLessons();
        final List<Lesson> lessons = lessonService.findAllLessons();
        log.info(lessons.toString());
        Assertions.assertEquals(lessons.size(), 0);
    }

    @Test
    void testAddTypeReportingToLesson() {
        lessonService.deleteAllLessons();
        typeReportingService.deleteAllTypeReportings();
        final TypeReporting typeReporting = typeReportingService.addTypeReporting(123,"зачет");
        final Lesson lesson = lessonService.addLesson("Философия", "1.09.2022");

        lessonService.addTypeReportingToLesson(lesson, typeReporting);
        log.info(lesson.toString());
        Assertions.assertEquals(typeReporting, lesson.getTypeReportings().get(0));
    }
}
