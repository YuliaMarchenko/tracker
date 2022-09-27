package com.example.tracker.service;

import com.example.tracker.dto.RequestAssigneeDTO;
import com.example.tracker.dto.ResponseAssigneeDTO;

import java.util.List;

public interface AssigneeService {

    ResponseAssigneeDTO createAssignee (RequestAssigneeDTO assigneeDTO);

    List<ResponseAssigneeDTO> getAssignees();

    ResponseAssigneeDTO getAssignee (Long id);

    ResponseAssigneeDTO changeStatusAssignee(Long id);
}
