package com.example.tracker.service;


import com.example.tracker.dto.RequestTaskDTO;
import com.example.tracker.dto.ResponseTaskDTO;
import com.example.tracker.dto.ResponseTaskForListDTO;
import com.example.tracker.dto.ResponseTaskFullInfoDTO;

import java.util.List;

public interface TaskItemService {
    ResponseTaskDTO createTask(RequestTaskDTO requestTaskDTO);

    List<ResponseTaskForListDTO> getTasks();

    ResponseTaskFullInfoDTO getTask(Long id);
}
