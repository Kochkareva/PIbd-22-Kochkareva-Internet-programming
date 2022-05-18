package ru.ulstu.is.sbapp;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.ulstu.is.sbapp.student.model.Discipline;
import ru.ulstu.is.sbapp.student.model.TypeReporting;
import ru.ulstu.is.sbapp.student.service.DisciplineService;
import ru.ulstu.is.sbapp.student.service.TypeReportingService;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@SpringBootTest
public class JpaDisciplineTest {
    private static final Logger log = LoggerFactory.getLogger(JpaDisciplineTest.class);

    @Autowired
    private DisciplineService disciplineService;
    @Autowired
    private TypeReportingService typeReportingService;

    @Test
    void testDisciplineCreate() {
        disciplineService.deleteAllDisciplines();
        final Discipline discipline = disciplineService.addDiscipline("Философия");
        log.info(discipline.toString());
        Assertions.assertNotNull(discipline.getId());
    }

    @Test
    void testDisciplineRead() {
        disciplineService.deleteAllDisciplines();
        final Discipline discipline = disciplineService.addDiscipline("Философия");
        log.info(discipline.toString());
        final Discipline findDiscipline = disciplineService.findDiscipline(discipline.getId());
        log.info(findDiscipline.toString());
        Assertions.assertEquals(discipline, findDiscipline);
    }

    @Test
    void testDisciplineReadNotFound() {
        disciplineService.deleteAllDisciplines();
        Assertions.assertThrows(EntityNotFoundException.class, () -> disciplineService.findDiscipline(-1L));
    }

    @Test
    void testDisciplineReadAll() {
        disciplineService.deleteAllDisciplines();
        disciplineService.addDiscipline("Философия");
        disciplineService.addDiscipline("Философия");
        final List<Discipline> disciplines = disciplineService.findAllDisciplines();
        log.info(disciplines.toString());
        Assertions.assertEquals(disciplines.size(), 2);
    }

    @Test
    void testDisciplineReadAllEmpty() {
        disciplineService.deleteAllDisciplines();
        final List<Discipline> disciplines = disciplineService.findAllDisciplines();
        log.info(disciplines.toString());
        Assertions.assertEquals(disciplines.size(), 0);
    }

    @Test
    void testDisciplineAddTypeReporting() {
        disciplineService.deleteAllDisciplines();
        typeReportingService.deleteAllTypeReportings();
        final TypeReporting typeReporting = typeReportingService.addTypeReporting(123,"зачет");
        final Discipline discipline = disciplineService.addDiscipline("Философия");
        disciplineService.addTypeReportingToDiscipline(discipline, typeReporting);

        log.info(discipline.toString());
        Assertions.assertEquals(typeReporting, discipline.getTypeReportings().get(0));
    }
}
