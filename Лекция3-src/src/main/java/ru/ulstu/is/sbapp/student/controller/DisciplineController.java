package ru.ulstu.is.sbapp.student.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.ulstu.is.sbapp.student.model.Discipline;
import ru.ulstu.is.sbapp.student.service.DisciplineService;

import java.util.List;

@RestController
@RequestMapping("/discipline")
public class DisciplineController {
    private final DisciplineService disciplineService;


    public DisciplineController(DisciplineService disciplineService) {
        this.disciplineService = disciplineService;
    }

    @GetMapping("/{id}")
    public Discipline getDiscipline(@PathVariable Long id) {
        return disciplineService.findDiscipline(id);
    }

    @GetMapping("/")
    public List<Discipline> getDisciplines() {
        return disciplineService.findAllDisciplines();
    }

    @PostMapping("/")
    public Discipline createDiscipline(@RequestParam("DisciplineName") String DisciplineName){
        return disciplineService.addDiscipline(DisciplineName);
    }

    @PatchMapping("/{id}")
    public Discipline updateDiscipline(@PathVariable Long id,
                                 @RequestParam("DisciplineName") String DisciplineName) {
        return disciplineService.updateDiscipline(id, DisciplineName);
    }

    @DeleteMapping("/{id}")
    public Discipline deleteDiscipline(@PathVariable Long id) {
        return disciplineService.deleteDiscipline(id);
    }
}
