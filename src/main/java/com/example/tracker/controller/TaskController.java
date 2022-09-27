package com.example.tracker.controller;

import com.example.tracker.dto.RequestTaskDTO;
import com.example.tracker.dto.ResponseTaskDTO;
import com.example.tracker.service.TaskItemService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor

public class TaskController {

    private final TaskItemService taskItemService;

    @PostMapping("/tasks")
    public ResponseTaskDTO createTask(@RequestBody RequestTaskDTO requestTaskDTO){
        return taskItemService.createTask(requestTaskDTO);
    }
}
