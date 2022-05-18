package com.ulstu.University.service;
import com.ulstu.University.model.Lesson;
import com.ulstu.University.model.TypeReporting;
import com.ulstu.University.repository.LessonRepository;
import com.ulstu.University.repository.TypeReportingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.ulstu.University.util.validation.ValidatorUtil;

import java.util.List;
import java.util.Optional;

@Service
public class LessonService {
    private final LessonRepository lessonRepository;
    private final TypeReportingRepository typeReportingRepository;
    private final ValidatorUtil validatorUtil;

    public LessonService(LessonRepository lessonRepository, TypeReportingRepository typeReportingRepository,ValidatorUtil validatorUtil) {
        this.lessonRepository = lessonRepository;
        this.typeReportingRepository = typeReportingRepository;
        this.validatorUtil = validatorUtil;
    }

    /**
     * private String LessonName;
     *     private String LessonDate;
     */
    @Transactional
    public Lesson addLesson(String LessonName, String LessonDate) {
        final Lesson lesson = new Lesson(LessonName, LessonDate);
        validatorUtil.validate(lesson);
        return lessonRepository.save(lesson);
    }

    @Transactional
    public Lesson addTypeReportingToLesson(Long LessonId, Long TypeReportingId){
        final Optional<Lesson> lesson = lessonRepository.findById(LessonId);
        final Optional<TypeReporting> typeReporting = typeReportingRepository.findById(TypeReportingId);
        lesson.orElseThrow(()-> new LessonNotFoundException(LessonId)).addTypeReporting(typeReporting.orElseThrow(()->
                new TypeReportingNotFoundException(TypeReportingId)));
        return lessonRepository.save(lesson.orElseThrow(()->new LessonNotFoundException(LessonId)));
    }

    @Transactional(readOnly = true)
    public Lesson findLesson(Long id) {
        final Optional<Lesson> lesson = lessonRepository.findById(id);
        return lesson.orElseThrow(() -> new LessonNotFoundException(id));
    }

    @Transactional(readOnly = true)
    public List<Lesson> findAllLessons() {
        return lessonRepository.findAll();
    }

    @Transactional
    public Lesson updateLesson(Long id, String LessonName, String LessonDate) {
        final Lesson currentLesson = findLesson(id);
        currentLesson.setLessonName(LessonName);
        currentLesson.setLessonDate(LessonDate);
        validatorUtil.validate(currentLesson);
        return lessonRepository.save(currentLesson);
    }

    @Transactional
    public Lesson deleteLesson(Long id) {
        final Lesson currentLesson = findLesson(id);
        lessonRepository.delete(currentLesson);
        return currentLesson;
    }

    @Transactional
    public void deleteAllLessons() {
        lessonRepository.deleteAll();
    }
}
