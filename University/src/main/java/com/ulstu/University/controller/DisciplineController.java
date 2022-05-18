package com.ulstu.University.controller;
import com.ulstu.University.configuration.WebConfiguration;
import com.ulstu.University.service.DisciplineService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(WebConfiguration.REST_API +"/discipline")
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
    public DisciplineDto createDiscipline(@RequestBody @Valid DisciplineDto disciplineDto) {
        return new DisciplineDto(disciplineService.addDiscipline(disciplineDto.getDisciplineName(),
                disciplineDto.getDepartment().getId()));
    }

    @PutMapping("/{id}")
    public DisciplineDto updateDiscipline(@PathVariable Long id,
                                          @RequestBody @Valid DisciplineDto disciplineDto) {
        return new DisciplineDto(disciplineService.updateDiscipline(id, disciplineDto.getDisciplineName()));
    }

    @DeleteMapping("/{id}")
    public DisciplineDto deleteDiscipline(@PathVariable Long id) {
        return new DisciplineDto(disciplineService.deleteDiscipline(id));
    }
}
