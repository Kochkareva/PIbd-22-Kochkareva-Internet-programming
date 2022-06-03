package ru.ulstu.is.sbapp.test.service;

import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import ru.ulstu.is.sbapp.test.model.TestDto;

@Service
@Profile("prod")
public class TestServiceProdImpl extends TestServiceImpl {
    public TestServiceProdImpl(Environment environment) {
        super(environment);
    }

    @Override
    public TestDto doSomething(TestDto testDto) {
        log.info("TestServiceProdImpl.doSomething");
        return testDto;
    }
}
