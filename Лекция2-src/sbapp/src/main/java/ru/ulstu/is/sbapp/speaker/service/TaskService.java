package ru.ulstu.is.sbapp.speaker.service;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import ru.ulstu.is.sbapp.Lab2.IDopTask;

@Service
public class TaskService {
    private final ApplicationContext applicationContext;

    public TaskService(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    public String task(String task, String name){
        final IDopTask iDopTask = (IDopTask) applicationContext.getBean(task);
        return String.format("%s", iDopTask.task(name));
    }
}
