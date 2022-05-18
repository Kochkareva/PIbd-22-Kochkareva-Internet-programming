package com.ulstu.University.controller;
import com.ulstu.University.service.TypeReportingService;
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
@RequestMapping("/typeReporting")
public class TypeReportingController {
    private final TypeReportingService typeReportingService;

    public TypeReportingController(TypeReportingService typeReportingService) {
        this.typeReportingService = typeReportingService;
    }

    @GetMapping("/{id}")
    public TypeReportingDto getTypeReporting(@PathVariable Long id) {
        return new TypeReportingDto(typeReportingService.findTypeReporting(id));
    }

    @GetMapping("/")
    public List<TypeReportingDto> getTypeReportings() {
        return typeReportingService.findAllTypeReportings().stream()
                .map(TypeReportingDto::new)
                .toList();
    }

    /**
     * private int ReportNumber;
     *     private String ReportName;
     */
    @PostMapping("/")
    public TypeReportingDto createTypeReporting(@RequestParam("ReportNumber") int ReportNumber,
                                  @RequestParam("ReportName") String ReportName,
                                                @RequestParam("DepartmentId") Long DepartmentId) {
        return new TypeReportingDto(typeReportingService.addTypeReporting(ReportNumber, ReportName, DepartmentId));
    }
    @PostMapping("/{TypeReportingId} {DisciplineId}")
    public TypeReportingDto addDiscipline(@RequestParam("TypeReportingId") Long TypeReportingId,
                                      @RequestParam("DisciplineId") Long DisciplineId){
        return new TypeReportingDto(typeReportingService.addDisciplineToTypeReporting(TypeReportingId, DisciplineId));
    }

    @PutMapping("/{id}")
    public TypeReportingDto updateTypeReporting(@PathVariable Long id,
                                  @RequestParam("ReportNumber") int ReportNumber,
                                  @RequestParam("ReportName") String ReportName) {
        return new TypeReportingDto(typeReportingService.updateTypeReporting(id, ReportNumber, ReportName));
    }

    @DeleteMapping("/{id}")
    public TypeReportingDto deleteTypeReporting(@PathVariable Long id) {
        return new TypeReportingDto(typeReportingService.deleteTypeReporting(id));
    }
}
