package com.example.tracker.service.impl;

import com.example.tracker.dto.RequestTaskDTO;
import com.example.tracker.dto.ResponseTaskDTO;
import com.example.tracker.entities.Assignee;
import com.example.tracker.entities.TaskItem;
import com.example.tracker.entities.TaskStatus;
import com.example.tracker.entities.TaskType;
import com.example.tracker.repository.AssigneeRepository;
import com.example.tracker.repository.TaskItemRepository;
import com.example.tracker.service.TaskItemService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@AllArgsConstructor

public class TaskItemServiceImpl implements TaskItemService {

    private final TaskItemRepository taskItemRepository;
    private final AssigneeRepository assigneeRepository;

    @Override
    public ResponseTaskDTO createTask(RequestTaskDTO requestTaskDTO) {

        Assignee assignee = assigneeRepository.findById(requestTaskDTO.getAssigneeId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND);

        TaskItem taskItem = TaskItem.builder()
                .name(requestTaskDTO.getName())
                .assignee(assignee)
                .parentTask(requestTaskDTO.getParentTaskId())
                .taskType(setTaskType(requestTaskDTO.getParentTaskId()))
                .taskStatus(TaskStatus.TODO)
                .build();

        taskItem = taskItemRepository.save(taskItem);

        return ResponseTaskDTO.builder()
                .id(taskItem.getId())
                .name(taskItem.getName())
                .assigneeId(assignee.getId())
                .parentTaskId(taskItem.getParentTask())
                .taskStatus(taskItem.getTaskStatus())
                .taskType(taskItem.getTaskType())
                .build();
    }

    private TaskType setTaskType(Long parentTaskId) {

        TaskType taskTypeParent = taskItemRepository.findById(parentTaskId).get().getTaskType();

        switch (taskTypeParent) {
            case PROJECT:
                return TaskType.FEATURE;
            case   FEATURE:
                return TaskType.EPIC;
            case EPIC:
            case TASK:
                return TaskType.TASK;
            default:
                return TaskType.PROJECT;
        }
    }
}


