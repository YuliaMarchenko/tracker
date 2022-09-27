package com.example.tracker.service;

import com.example.tracker.dto.RequestTaskDTO;
import com.example.tracker.dto.ResponseTaskDTO;

public interface TaskItemService {
    ResponseTaskDTO createTask(RequestTaskDTO requestTaskDTO);
}
