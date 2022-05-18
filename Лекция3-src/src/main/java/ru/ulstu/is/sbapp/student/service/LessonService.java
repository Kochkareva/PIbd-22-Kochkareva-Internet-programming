package ru.ulstu.is.sbapp.student.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import ru.ulstu.is.sbapp.student.model.Lesson;
import ru.ulstu.is.sbapp.student.model.TypeReporting;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import java.util.Date;
import java.util.List;

@Service
public class LessonService {
    @PersistenceContext
    private EntityManager em;

    /**
     private String LessonName;
     private Date LessonDate;
     */
    @Transactional
    public Lesson addLesson(String LessonName, String LessonDate) {
        if (!StringUtils.hasText(LessonName) || !StringUtils.hasText(LessonDate)) {
            throw new IllegalArgumentException("Lesson name / Date of Lesson is null or empty");
        }
        final Lesson lesson = new Lesson(LessonName, LessonDate);
        em.persist(lesson);
        return lesson;
    }

    @Transactional(readOnly = true)
    public Lesson findLesson(Long id) {
        final Lesson lesson = em.find(Lesson.class, id);
        if (lesson == null) {
            throw new EntityNotFoundException(String.format("Lesson with id [%s] is not found", id));
        }
        return lesson;
    }

    @Transactional(readOnly = true)
    public List<Lesson> findAllLessons() {
        return em.createQuery("select s from Lesson s", Lesson.class)
                .getResultList();
    }

    @Transactional
    public Lesson updateLesson(Long id, String LessonName, String LessonDate) {
        if (!StringUtils.hasText(LessonName) || !StringUtils.hasText(LessonDate)) {
            throw new IllegalArgumentException("Student name / Date of Lesson is null or empty");
        }
        final Lesson currentLesson = findLesson(id);
        currentLesson.setLessonName(LessonName);
        currentLesson.setLessonDate(LessonDate);
        return em.merge(currentLesson);
    }

    @Transactional
    public Lesson deleteLesson(Long id) {
        final Lesson currentLesson = findLesson(id);
        em.remove(currentLesson);
        return currentLesson;
    }

    @Transactional
    public void deleteAllLessons() {
        em.createQuery("delete from Lesson").executeUpdate();
    }

    @Transactional
    public void addTypeReportingToLesson(Lesson lesson, TypeReporting typeReporting) {
        if (lesson.toString().isEmpty()) {
            throw new IllegalArgumentException("Lesson is null or empty");
        }
        lesson.addTypeReporting(typeReporting);
        em.merge(lesson);
    }
}
