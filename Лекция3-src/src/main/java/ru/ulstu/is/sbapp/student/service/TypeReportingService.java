package ru.ulstu.is.sbapp.student.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import ru.ulstu.is.sbapp.student.model.Discipline;
import ru.ulstu.is.sbapp.student.model.Lesson;
import ru.ulstu.is.sbapp.student.model.TypeReporting;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
public class TypeReportingService {
    @PersistenceContext
    private EntityManager em;

    /**
     private int ReportNumber;
     private String ReportName;
     */

    @Transactional
    public TypeReporting addTypeReporting(int ReportNumber, String ReportName) {
        if (ReportNumber <= 0 || !StringUtils.hasText(ReportName)) {
            throw new IllegalArgumentException("Report Number / Report Name name is null or empty");
        }
        final TypeReporting typeReporting = new TypeReporting(ReportNumber, ReportName);
        em.persist(typeReporting);
        return typeReporting;
    }

    @Transactional(readOnly = true)
    public TypeReporting findTypeReporting(Long id) {
        final TypeReporting typeReporting = em.find(TypeReporting.class, id);
        if (typeReporting == null) {
            throw new EntityNotFoundException(String.format("TypeReporting with id [%s] is not found", id));
        }
        return typeReporting;
    }

    @Transactional(readOnly = true)
    public List<TypeReporting> findAllTypeReportings() {
        return em.createQuery("select s from TypeReporting s", TypeReporting.class)
                .getResultList();
    }

    @Transactional
    public TypeReporting updateTypeReporting(Long id, int ReportNumber, String ReportName) {
        if (ReportNumber <= 0 || !StringUtils.hasText(ReportName)) {
            throw new IllegalArgumentException("Report Number / Report Name name is null or empty");
        }
        final TypeReporting currentTypeReporting = findTypeReporting(id);
        currentTypeReporting.setReportNumber(ReportNumber);
        currentTypeReporting.setReportName(ReportName);
        return em.merge(currentTypeReporting);
    }

    @Transactional
    public TypeReporting deleteTypeReporting(Long id) {
        final TypeReporting currentTypeReporting = findTypeReporting(id);
        em.remove(currentTypeReporting);
        return currentTypeReporting;
    }

    @Transactional
    public void deleteAllTypeReportings() {
        em.createQuery("delete from TypeReporting").executeUpdate();
    }

    @Transactional
    public void addDisciplineToTypeReporting(Discipline discipline, TypeReporting typeReporting) {
        if (discipline.toString().isEmpty()) {
            throw new IllegalArgumentException("Discipline is null or empty");
        }
        typeReporting.addDiscipline(discipline);
        em.merge(typeReporting);
    }

    @Transactional
    public void addLessonToTypeReporting(Lesson lesson, TypeReporting typeReporting) {
        if (lesson.toString().isEmpty()) {
            throw new IllegalArgumentException("Lesson is null or empty");
        }
        typeReporting.addLesson(lesson);
        em.merge(typeReporting);
    }
}
