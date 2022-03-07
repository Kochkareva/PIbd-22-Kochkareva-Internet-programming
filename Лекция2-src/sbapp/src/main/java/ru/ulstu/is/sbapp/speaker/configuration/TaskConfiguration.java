package ru.ulstu.is.sbapp.speaker.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.ulstu.is.sbapp.Lab2.IDopTask;
import ru.ulstu.is.sbapp.Lab2.LowerCase;
import ru.ulstu.is.sbapp.Lab2.UpperCase;

@Configuration
public class TaskConfiguration {
    private final Logger log = LoggerFactory.getLogger(TaskConfiguration.class);

    @Bean(value = "lower")
    public IDopTask createLowerString() {
        return new LowerCase();
    }

    @Bean(value = "upper")
    public IDopTask createUpperString() {
        return new UpperCase();
    }
}
