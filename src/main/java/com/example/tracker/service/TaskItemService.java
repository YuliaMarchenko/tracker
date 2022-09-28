package com.example.tracker.service;


import com.example.tracker.dto.RequestTaskDTO;
import com.example.tracker.dto.ResponseTaskDTO;
import com.example.tracker.dto.ResponseTaskFullInfoDTO;

import java.util.List;

public interface TaskItemService {
    ResponseTaskFullInfoDTO createTask(RequestTaskDTO requestTaskDTO);

    List<ResponseTaskDTO> getTasks();

    ResponseTaskFullInfoDTO getTask(Long id);

    ResponseTaskDTO promote(Long id);

    ResponseTaskDTO demote(Long id);
}
