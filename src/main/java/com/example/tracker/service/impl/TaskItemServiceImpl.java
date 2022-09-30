package com.example.tracker.service.impl;

import com.example.tracker.entities.converter.TaskItemConverter;
import com.example.tracker.dto.*;
import com.example.tracker.entities.*;
import com.example.tracker.entities.type.AccountStatus;
import com.example.tracker.entities.type.TaskStatus;
import com.example.tracker.entities.type.TaskType;
import com.example.tracker.repository.AssigneeRepository;
import com.example.tracker.repository.TaskItemRepository;
import com.example.tracker.service.TaskItemService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@AllArgsConstructor

public class TaskItemServiceImpl implements TaskItemService {

    private final TaskItemRepository taskItemRepository;
    private final AssigneeRepository assigneeRepository;

    @Override
    public ResponseTaskFullInfoDTO createTask(RequestTaskDTO requestTaskDTO) {
        Assignee assignee = assigneeRepository.findById(requestTaskDTO.getAssigneeId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        if (assignee.getAccountStatus() == AccountStatus.INACTIVE) {
            throw new ResponseStatusException(HttpStatus.PRECONDITION_FAILED,
                    String.format("Assignee with ID %d is in status [INACTIVE]", assignee.getId()));
        }

        TaskItem taskParent;

        if (requestTaskDTO.getParentTaskId() != null) {
            taskParent = taskItemRepository.findById(requestTaskDTO.getParentTaskId()).orElseThrow(
                    () -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                            String.format("Parent task with ID %d cannot be found", requestTaskDTO.getParentTaskId()))
            );
        } else {
            taskParent = null;
        }

        TaskItem taskItem = TaskItem.builder()
                .name(requestTaskDTO.getName())
                .assignee(assignee)
                .parentTask(taskParent)
                .taskType(setTaskType(taskParent))
                .taskStatus(TaskStatus.TODO)
                .build();

        taskItem = taskItemRepository.save(taskItem);

        return TaskItemConverter.convertToFullInfoDTO(taskItem);
    }

    @Override
    public List<ResponseTaskDTO> getTasks() {
        return taskItemRepository.findAll().stream()
                .map(taskItem -> TaskItemConverter.convertToTaskDTO(taskItem)).toList();
    }

    @Override
    public ResponseTaskFullInfoDTO getTask(Long id) {
        TaskItem taskItem = taskItemRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return TaskItemConverter.convertToFullInfoDTO(taskItem);
    }

    @Override
    public ResponseTaskDTO promote(Long id) {

        TaskItem taskItem = taskItemRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        switch (taskItem.getTaskStatus()) {
            case TODO -> taskItem.setTaskStatus(TaskStatus.ANALYSIS);
            case ANALYSIS -> taskItem.setTaskStatus(TaskStatus.IN_PROGRESS);
            case IN_PROGRESS -> taskItem.setTaskStatus(TaskStatus.DONE);
        }

        taskItemRepository.save(taskItem);

        return TaskItemConverter.convertToTaskDTO(taskItem);
    }

    @Override
    public ResponseTaskDTO demote(Long id) {

        TaskItem taskItem = taskItemRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        switch (taskItem.getTaskStatus()) {
            case DONE -> taskItem.setTaskStatus(TaskStatus.IN_PROGRESS);
            case IN_PROGRESS -> taskItem.setTaskStatus(TaskStatus.ANALYSIS);
            case ANALYSIS -> taskItem.setTaskStatus(TaskStatus.TODO);
        }

        taskItemRepository.save(taskItem);

        return TaskItemConverter.convertToTaskDTO(taskItem);
    }

    private TaskType setTaskType(TaskItem parentTask) {

        if (parentTask == null) {
            return TaskType.PROJECT;
        }

        TaskType taskTypeParent = parentTask.getTaskType();

        switch (taskTypeParent) {
            case PROJECT:
                return TaskType.FEATURE;
            case FEATURE:
                return TaskType.EPIC;
            case EPIC:
                return TaskType.TASK;
            case TASK:
                throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY,
                        "Cannot create a subtask out of the lowest task type [Task]");
            default:
                return TaskType.PROJECT;
        }
    }
}


