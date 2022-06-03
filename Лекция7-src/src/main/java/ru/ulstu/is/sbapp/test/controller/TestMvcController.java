package ru.ulstu.is.sbapp.test.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.ulstu.is.sbapp.test.model.TestDto;
import ru.ulstu.is.sbapp.test.service.TestService;

import javax.validation.Valid;

@Controller
@RequestMapping("/test")
public class TestMvcController {
    private final TestService testService;

    public TestMvcController(TestService testService) {
        this.testService = testService;
    }

    @GetMapping
    public String test(Model model) {
        model.addAttribute("testDto", new TestDto());
        return "test";
    }

    @PostMapping
    public String testValidation(@ModelAttribute @Valid TestDto testDto,
                                 BindingResult bindingResult,
                                 Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "test";
        }
        model.addAttribute("testDto", testService.doSomething(testDto));
        return "test-result";
    }
}
