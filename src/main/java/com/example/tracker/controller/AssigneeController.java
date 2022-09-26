package com.example.tracker.controller;

import com.example.tracker.dto.AssigneeDTO;
import com.example.tracker.service.AssigneeService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor

public class AssigneeController {
    private final AssigneeService assigneeService;

    @PostMapping("/assignees")
    public AssigneeDTO createAssignee(@RequestBody AssigneeDTO assigneeDTO) {
        return assigneeService.createAssignee(assigneeDTO);
    }

    @GetMapping("/assignees")
    public List<AssigneeDTO> getAssignees(){
        return assigneeService.getAssignees();
    }

    @GetMapping("/assignees/{id}")
    public AssigneeDTO getAssignee(@PathVariable("id") Long id) {
        return assigneeService.getAssignee(id);
    }
}
