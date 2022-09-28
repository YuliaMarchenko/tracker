package com.example.tracker.controller;

import com.example.tracker.dto.RequestTaskDTO;
import com.example.tracker.dto.ResponseTaskDTO;
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
    public ResponseTaskFullInfoDTO createTask(@RequestBody RequestTaskDTO requestTaskDTO){
        return taskItemService.createTask(requestTaskDTO);
    }

    @GetMapping("/tasks")
    public List<ResponseTaskDTO> getTasks(){
        return taskItemService.getTasks();
    }

    @GetMapping("/tasks/{id}")
    public ResponseTaskFullInfoDTO getTask(@PathVariable("id") Long id){
        return taskItemService.getTask(id);
    }

    @PatchMapping("/tasks/{id}/promote")
    public ResponseTaskDTO promoteTask(@PathVariable("id") Long id){
        return taskItemService.promote(id);
    }

    @PatchMapping("/tasks/{id}/demote")
    public ResponseTaskDTO demoteTask(@PathVariable("id") Long id){
        return taskItemService.demote(id);
    }
}
