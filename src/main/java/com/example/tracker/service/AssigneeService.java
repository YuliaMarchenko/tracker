package com.example.tracker.service;

import com.example.tracker.dto.AssigneeDTO;

import java.util.List;

public interface AssigneeService {

    AssigneeDTO createAssignee (AssigneeDTO assigneeDTO);

    List<AssigneeDTO> getAssignees();

    AssigneeDTO getAssignee (Long id);
}
