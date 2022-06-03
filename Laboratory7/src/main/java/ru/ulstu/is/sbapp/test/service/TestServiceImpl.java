package ru.ulstu.is.sbapp.test.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import ru.ulstu.is.sbapp.test.model.TestDto;

abstract class TestServiceImpl implements TestService {
    protected final Logger log = LoggerFactory.getLogger(TestServiceImpl.class);

    protected final Environment environment;

    TestServiceImpl(Environment environment) {
        this.environment = environment;
        printCurrentProfile();
    }

    private void printCurrentProfile() {
        final String[] profiles = environment.getActiveProfiles().length == 0 ?
                environment.getDefaultProfiles() :
                environment.getActiveProfiles();
        log.warn("Current profile is {}", String.join(", ", profiles));
    }

    @Override
    abstract public TestDto doSomething(TestDto testDto);
}
