package ru.ulstu.is.sbapp.student.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import ru.ulstu.is.sbapp.student.model.Discipline;
import ru.ulstu.is.sbapp.student.model.TypeReporting;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
public class DisciplineService {
    @PersistenceContext
    private EntityManager em;

    @Transactional
    public Discipline addDiscipline(String DisciplineName) {
        if (!StringUtils.hasText(DisciplineName)) {
            throw new IllegalArgumentException("Discipline name is null or empty");
        }
        final Discipline discipline = new Discipline(DisciplineName);
        em.persist(discipline);
        return discipline;
    }

    @Transactional(readOnly = true)
    public Discipline findDiscipline(Long id) {
        final Discipline discipline = em.find(Discipline.class, id);
        if (discipline == null) {
            throw new EntityNotFoundException(String.format("Discipline with id [%s] is not found", id));
        }
        return discipline;
    }

    @Transactional(readOnly = true)
    public List<Discipline> findAllDisciplines() {
        return em.createQuery("select s from Discipline s", Discipline.class)
                .getResultList();
    }

    @Transactional
    public Discipline updateDiscipline(Long id, String DisciplineName) {
        if (!StringUtils.hasText(DisciplineName)) {
            throw new IllegalArgumentException("Discipline name is null or empty");
        }
        final Discipline currentDiscipline = findDiscipline(id);
        currentDiscipline.setDisciplineName(DisciplineName);
        return em.merge(currentDiscipline);
    }

    @Transactional
    public Discipline deleteDiscipline(Long id) {
        final Discipline currentDiscipline = findDiscipline(id);
        em.remove(currentDiscipline);
        return currentDiscipline;
    }

    @Transactional
    public void deleteAllDisciplines() {
        em.createQuery("delete from Discipline").executeUpdate();
    }

    @Transactional
    public void addTypeReportingToDiscipline(Discipline discipline, TypeReporting typeReporting) {
        if (typeReporting.toString().isEmpty()) {
            throw new IllegalArgumentException("TypeReporting is null or empty");
        }
        discipline.addTypeReporting(typeReporting);
        em.merge(discipline);
    }
}
