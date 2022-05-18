package ru.ulstu.is.sbapp.student.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.ulstu.is.sbapp.student.model.Department;
import ru.ulstu.is.sbapp.student.service.DepartmentService;

import java.util.List;

@RestController
@RequestMapping("/department")
public class DepartmentController {
    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("/{id}")
    public Department getDepartment(@PathVariable Long id) {
        return departmentService.findDepartment(id);
    }

    @GetMapping("/")
    public List<Department> getDepartments() {
        return departmentService.findAllDepartments();
    }

    /**
     * private String DepartmentName;
     *     private String Login;
     *     private String Password;
     */
    @PostMapping("/")
    public Department createDepartment(@RequestParam("DepartmentName") String DepartmentName,
                                 @RequestParam("Login") String Login,
                                    @RequestParam("Password") String Password) {
        return departmentService.addDepartment(DepartmentName, Login, Password);
    }

    @PatchMapping("/{id}")
    public Department updateDepartment(@PathVariable Long id,
                                       @RequestParam("DepartmentName") String DepartmentName,
                                       @RequestParam("Login") String Login,
                                       @RequestParam("Password") String Password) {
        return departmentService.updateDepartment(id, DepartmentName, Login, Password);
    }

    @DeleteMapping("/{id}")
    public Department deleteDepartment(@PathVariable Long id) {
        return departmentService.deleteDepartment(id);
    }
}
