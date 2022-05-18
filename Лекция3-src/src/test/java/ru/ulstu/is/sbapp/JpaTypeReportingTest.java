package ru.ulstu.is.sbapp;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.ulstu.is.sbapp.student.model.Discipline;
import ru.ulstu.is.sbapp.student.model.Lesson;
import ru.ulstu.is.sbapp.student.model.TypeReporting;
import ru.ulstu.is.sbapp.student.service.DisciplineService;
import ru.ulstu.is.sbapp.student.service.LessonService;
import ru.ulstu.is.sbapp.student.service.TypeReportingService;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@SpringBootTest
public class JpaTypeReportingTest {
    private static final Logger log = LoggerFactory.getLogger(JpaDepartmentTests.class);

    @Autowired
    private TypeReportingService typeReportingService;
    @Autowired
    private LessonService lessonService;
    @Autowired
    private DisciplineService disciplineService;

    @Test
    void testTypeReportingCreate() {
        typeReportingService.deleteAllTypeReportings();
        final TypeReporting typeReporting = typeReportingService.addTypeReporting(123, "зачет");
        log.info(typeReporting.toString());
        Assertions.assertNotNull(typeReporting.getId());
    }

    @Test
    void testTypeReportingRead() {
        typeReportingService.deleteAllTypeReportings();
        final TypeReporting typeReporting = typeReportingService.addTypeReporting(123, "зачет");
        log.info(typeReporting.toString());
        final TypeReporting findTypeReporting = typeReportingService.findTypeReporting(typeReporting.getId());
        log.info(typeReporting.toString());
        Assertions.assertEquals(typeReporting, findTypeReporting);
    }

    @Test
    void testTypeReportingReadNotFound() {
        typeReportingService.deleteAllTypeReportings();
        Assertions.assertThrows(EntityNotFoundException.class, () -> typeReportingService.findTypeReporting(-1L));
    }

    @Test
    void testTypeReportingReadAll() {
        typeReportingService.deleteAllTypeReportings();
        typeReportingService.addTypeReporting(123, "зачет");
        typeReportingService.addTypeReporting(123, "зачет");
        final List<TypeReporting> typeReportings = typeReportingService.findAllTypeReportings();
        log.info(typeReportings.toString());
        Assertions.assertEquals(typeReportings.size(), 2);
    }

    @Test
    void testTypeReportingReadAllEmpty() {
        typeReportingService.deleteAllTypeReportings();
        final List<TypeReporting> typeReportings = typeReportingService.findAllTypeReportings();
        log.info(typeReportings.toString());
        Assertions.assertEquals(typeReportings.size(), 0);
    }

    @Test
    void testAddDisciplineToTypeReporting() {
        disciplineService.deleteAllDisciplines();
        typeReportingService.deleteAllTypeReportings();
        final TypeReporting typeReporting = typeReportingService.addTypeReporting(123,"зачет");
        final Discipline discipline = disciplineService.addDiscipline("Философия");
        typeReportingService.addDisciplineToTypeReporting(discipline, typeReporting);
        log.info(typeReporting.toString());
        Assertions.assertEquals(discipline, typeReporting.getDisciplines().get(0));
    }

    @Test
    void testAddLessonToTypeReporting() {
        lessonService.deleteAllLessons();
        typeReportingService.deleteAllTypeReportings();
        final TypeReporting typeReporting = typeReportingService.addTypeReporting(123,"зачет");
        final Lesson lesson = lessonService.addLesson("Философия", "1.09.2022");
        typeReportingService.addLessonToTypeReporting(lesson, typeReporting);
        log.info(typeReporting.toString());
        Assertions.assertEquals(lesson, typeReporting.getLessons().get(0));
    }
}
