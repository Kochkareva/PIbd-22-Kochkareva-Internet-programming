package ru.ulstu.is.sbapp.speaker.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.ulstu.is.sbapp.speaker.service.TaskService;

@RestController
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService speakerService) {
        this.taskService = speakerService;
    }

    @GetMapping("/")
    public String hello(@RequestParam(value = "name", defaultValue = "Мир") String name,
                        @RequestParam(value = "task", defaultValue = "upper") String task){
        return taskService.task(task, name);
    }
}

