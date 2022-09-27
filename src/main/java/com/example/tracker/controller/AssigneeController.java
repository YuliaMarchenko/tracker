package com.example.tracker.controller;

import com.example.tracker.dto.RequestAssigneeDTO;
import com.example.tracker.dto.ResponseAssigneeDTO;
import com.example.tracker.service.AssigneeService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor

public class AssigneeController {
    private final AssigneeService assigneeService;

    @PostMapping("/assignees")
    public ResponseAssigneeDTO createAssignee(@RequestBody RequestAssigneeDTO assigneeDTO) {
        return assigneeService.createAssignee(assigneeDTO);
    }

    @GetMapping("/assignees")
    public List<ResponseAssigneeDTO> getAssignees() {
        return assigneeService.getAssignees();
    }

    @GetMapping("/assignees/{id}")
    public ResponseAssigneeDTO getAssignee(@PathVariable("id") Long id) {
        return assigneeService.getAssignee(id);
    }

    @PutMapping("/assignees/{id}/toggle-course")
    public ResponseAssigneeDTO changeStatusAssignee(@PathVariable("id") Long id){
        return assigneeService.changeStatusAssignee(id);
    }
}
