package ru.ulstu.is.sbapp;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.ulstu.is.sbapp.speaker.service.TaskService;

@SpringBootTest
class SbappApplicationTests {
    @Autowired
    TaskService taskService;

    @Test
    void testTaskUpper() {
        final String res = taskService.task("upper", "upper");
        Assertions.assertEquals("UPPER", res);
    }

    @Test
    void testTaskLower() {
        final String res = taskService.task("lower", "LOWER");
        Assertions.assertEquals("lower", res);
    }

    @Test
    void testTaskFirst() {
        final String res = taskService.task("first", "first");
        Assertions.assertEquals("First", res);
    }

    @Test
    void testSpeakerErrorWired() {
        Assertions.assertThrows(NoSuchBeanDefinitionException.class, () -> taskService.task("Мир", "upper"));
    }
}
