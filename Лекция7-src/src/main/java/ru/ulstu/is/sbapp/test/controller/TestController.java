package ru.ulstu.is.sbapp.test.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.ulstu.is.sbapp.configuration.WebConfiguration;
import ru.ulstu.is.sbapp.test.model.TestDto;
import ru.ulstu.is.sbapp.test.service.TestService;

import javax.validation.Valid;

@RestController
@RequestMapping(WebConfiguration.REST_API + "/test")
public class TestController {
    private final TestService testService;

    public TestController(TestService testService) {
        this.testService = testService;
    }

    @PostMapping
    public TestDto testValidation(@RequestBody @Valid TestDto testDto) {
        return testService.doSomething(testDto);
    }
}
