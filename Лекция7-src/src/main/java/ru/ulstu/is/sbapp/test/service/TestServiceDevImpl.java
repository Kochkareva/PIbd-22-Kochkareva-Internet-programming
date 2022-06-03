package ru.ulstu.is.sbapp.test.service;

import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import ru.ulstu.is.sbapp.test.model.TestDto;

@Service
@Profile("dev")
public class TestServiceDevImpl extends TestServiceImpl {
    public TestServiceDevImpl(Environment environment) {
        super(environment);
    }

    @Override
    public TestDto doSomething(TestDto testDto) {
        log.info("TestServiceDevImpl.doSomething");
        return testDto;
    }
}
