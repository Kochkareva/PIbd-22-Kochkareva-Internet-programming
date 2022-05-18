package com.ulstu.University.service;
import com.ulstu.University.model.Discipline;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.ulstu.University.repository.DisciplineRepository;
import com.ulstu.University.util.validation.ValidatorUtil;

import java.util.List;
import java.util.Optional;

@Service
public class DisciplineService {
    private final DisciplineRepository disciplineRepository;
    private final ValidatorUtil validatorUtil;

    public DisciplineService(DisciplineRepository disciplineRepository, ValidatorUtil validatorUtil) {
        this.disciplineRepository = disciplineRepository;
        this.validatorUtil = validatorUtil;
    }

    /**
     * private String DepartmentName;
     *     private String Login;
     *     private String Password;
     */
    @Transactional
    public Discipline addDiscipline(String DisciplineName) {
        final Discipline discipline = new Discipline(DisciplineName);
        validatorUtil.validate(discipline);
        return disciplineRepository.save(discipline);
    }

    @Transactional(readOnly = true)
    public Discipline findDiscipline(Long id) {
        final Optional<Discipline> discipline = disciplineRepository.findById(id);
        return discipline.orElseThrow(() -> new DisciplineNotFoundException(id));
    }

    @Transactional(readOnly = true)
    public List<Discipline> findAllDisciplines() {
        return disciplineRepository.findAll();
    }

    @Transactional
    public Discipline updateDiscipline(Long id, String DisciplineName) {
        final Discipline currentDiscipline = findDiscipline(id);
        currentDiscipline.setDisciplineName(DisciplineName);
        validatorUtil.validate(currentDiscipline);
        return disciplineRepository.save(currentDiscipline);
    }

    @Transactional
    public Discipline deleteDiscipline(Long id) {
        final Discipline currentDiscipline = findDiscipline(id);
        disciplineRepository.delete(currentDiscipline);
        return currentDiscipline;
    }

    @Transactional
    public void deleteAllDisciplines() {
        disciplineRepository.deleteAll();
    }
}
