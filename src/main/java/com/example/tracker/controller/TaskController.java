package com.example.tracker.controller;

import com.example.tracker.dto.RequestTaskDTO;
import com.example.tracker.dto.ResponseTaskDTO;
import com.example.tracker.dto.ResponseTaskForListDTO;
import com.example.tracker.dto.ResponseTaskFullInfoDTO;
import com.example.tracker.service.TaskItemService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor

public class TaskController {

    private final TaskItemService taskItemService;

    @PostMapping("/tasks")
    public ResponseTaskDTO createTask(@RequestBody RequestTaskDTO requestTaskDTO){
        return taskItemService.createTask(requestTaskDTO);
    }

    @GetMapping("/tasks")
    public List<ResponseTaskForListDTO> getTasks(){
        return taskItemService.getTasks();
    }

    @GetMapping("/tasks/{id}")
    public ResponseTaskFullInfoDTO getTask(@PathVariable("id") Long id){
        return taskItemService.getTask(id);
    }
}
