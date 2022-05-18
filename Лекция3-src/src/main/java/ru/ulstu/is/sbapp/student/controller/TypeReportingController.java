package ru.ulstu.is.sbapp.student.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.ulstu.is.sbapp.student.model.TypeReporting;
import ru.ulstu.is.sbapp.student.service.TypeReportingService;

import java.util.List;

@RestController
@RequestMapping("/typeReporting")
public class TypeReportingController {
    private final TypeReportingService typeReportingService;


    public TypeReportingController(TypeReportingService typeReportingService) {
        this.typeReportingService = typeReportingService;
    }

    @GetMapping("/{id}")
    public TypeReporting getTypeReporting(@PathVariable Long id) {
        return typeReportingService.findTypeReporting(id);
    }

    @GetMapping("/")
    public List<TypeReporting> getTypeReportings() {
        return typeReportingService.findAllTypeReportings();
    }

    /**
     *
     private int ReportNumber;
     private String ReportName;
     */
    @PostMapping("/")
    public TypeReporting createTypeReporting(@RequestParam("ReportNumber") int ReportNumber,
                                 @RequestParam("ReportName") String ReportName) {
        return typeReportingService.addTypeReporting(ReportNumber, ReportName);
    }

    @PatchMapping("/{id}")
    public TypeReporting updateTypeReporting(@PathVariable Long id,
                                             @RequestParam("ReportNumber") int ReportNumber,
                                             @RequestParam("ReportName") String ReportName) {
        return typeReportingService.updateTypeReporting(id, ReportNumber, ReportName);
    }

    @DeleteMapping("/{id}")
    public TypeReporting deleteTypeReporting(@PathVariable Long id) {
        return typeReportingService.deleteTypeReporting(id);
    }
}
