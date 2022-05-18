package com.ulstu.University.controller;

import com.ulstu.University.service.DepartmentService;
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
@RequestMapping("/department")
public class DepartmentController {
    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("/{id}")
    public DepartmentDto getDepartment(@PathVariable Long id) {
        return new DepartmentDto(departmentService.findDepartment(id));
    }

    @GetMapping("/")
    public List<DepartmentDto> getDepartments() {
        return departmentService.findAllDepartments().stream()
                .map(DepartmentDto::new)
                .toList();
    }

    /**
     * private String DepartmentName;
     *     private String Login;
     *     private String Password;
     */
    @PostMapping("/")
    public DepartmentDto createDepartment(@RequestParam("DepartmentName") String DepartmentName,
                                    @RequestParam("Login") String Login,
                                    @RequestParam("Password") String Password) {
        return new DepartmentDto(departmentService.addDepartment(DepartmentName, Login, Password));
    }

    @PutMapping("/{id}")
    public DepartmentDto updateDepartment(@PathVariable Long id,
                                          @RequestParam("DepartmentName") String DepartmentName,
                                          @RequestParam("Login") String Login,
                                          @RequestParam("Password") String Password) {
        return new DepartmentDto(departmentService.updateDepartment(id, DepartmentName, Login, Password));
    }

    @DeleteMapping("/{id}")
    public DepartmentDto deleteDepartment(@PathVariable Long id) {
        return new DepartmentDto(departmentService.deleteDepartment(id));
    }
}
