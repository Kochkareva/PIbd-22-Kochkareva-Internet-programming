package com.ulstu.University.controller;
import com.ulstu.University.service.DisciplineService;
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
@RequestMapping("/discipline")
public class DisciplineController {
    private final DisciplineService disciplineService;

    public DisciplineController(DisciplineService disciplineService) {
        this.disciplineService = disciplineService;
    }

    @GetMapping("/{id}")
    public DisciplineDto getDiscipline(@PathVariable Long id) {
        return new DisciplineDto(disciplineService.findDiscipline(id));
    }

    @GetMapping("/")
    public List<DisciplineDto> getDisciplines() {
        return disciplineService.findAllDisciplines().stream()
                .map(DisciplineDto::new)
                .toList();
    }

    /**
     * private String DisciplineName;
     */
    @PostMapping("/")
    public DisciplineDto createDiscipline(@RequestParam("DisciplineName") String DisciplineName) {
        return new DisciplineDto(disciplineService.addDiscipline(DisciplineName));
    }

    @PutMapping("/{id}")
    public DisciplineDto updateDiscipline(@PathVariable Long id,
                                          @RequestParam("DisciplineName") String DisciplineName) {
        return new DisciplineDto(disciplineService.updateDiscipline(id, DisciplineName));
    }

    @DeleteMapping("/{id}")
    public DisciplineDto deleteDiscipline(@PathVariable Long id) {
        return new DisciplineDto(disciplineService.deleteDiscipline(id));
    }
}
