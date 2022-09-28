package com.example.tracker.service.impl;

import com.example.tracker.dto.*;
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

import java.util.List;

@Service
@AllArgsConstructor

public class TaskItemServiceImpl implements TaskItemService {

    private final TaskItemRepository taskItemRepository;
    private final AssigneeRepository assigneeRepository;

    @Override
    public ResponseTaskFullInfoDTO createTask(RequestTaskDTO requestTaskDTO) {

        Assignee assignee = assigneeRepository.findById(requestTaskDTO.getAssigneeId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        TaskItem taskParent;

        if (requestTaskDTO.getParentTaskId() != null) {
            taskParent = taskItemRepository.findById(requestTaskDTO.getParentTaskId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
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

        return ResponseTaskFullInfoDTO.builder()
                .id(taskItem.getId())
                .name(taskItem.getName())
                .taskType(taskItem.getTaskType())
                .taskStatus(taskItem.getTaskStatus())
                .parentTask(taskParent == null ? null : ResponseTaskShortDTO.builder()
                        .id(taskParent.getId())
                        .name(taskParent.getName())
                        .build())
                .assignee(assignee == null ? null : ResponseAssigneeDTO.builder()
                        .id(assignee.getId())
                        .name(assignee.getName())
                        .accountStatus(assignee.getAccountStatus())
                        .build())
                .subTasks(taskItem.getSubTasks() == null ? null : taskItem.getSubTasks()
                        .stream()
                        .map(task -> ResponseTaskShortDTO.builder()
                                .id(task.getId())
                                .name(task.getName())
                                .build()).toList())
                .build();
    }

    @Override
    public List<ResponseTaskDTO> getTasks() {
        return taskItemRepository.findAll().stream()
                .map(taskItem -> ResponseTaskDTO.builder()
                        .id(taskItem.getId())
                        .name(taskItem.getName())
                        .taskStatus(taskItem.getTaskStatus())
                        .taskType(taskItem.getTaskType())
                        .build()).toList();
    }

    @Override
    public ResponseTaskFullInfoDTO getTask(Long id) {
        TaskItem taskItem = taskItemRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        TaskItem taskParent = taskItem.getParentTask();
        Assignee assignee = taskItem.getAssignee();

        return ResponseTaskFullInfoDTO.builder()
                .id(taskItem.getId())
                .name(taskItem.getName())
                .taskType(taskItem.getTaskType())
                .taskStatus(taskItem.getTaskStatus())
                .parentTask(taskParent == null ? null : ResponseTaskShortDTO.builder()
                        .id(taskParent.getId())
                        .name(taskParent.getName())
                        .build())
                .assignee(assignee == null ? null : ResponseAssigneeDTO.builder()
                        .id(assignee.getId())
                        .name(assignee.getName())
                        .accountStatus(assignee.getAccountStatus())
                        .build())
                .subTasks(taskItem.getSubTasks().stream().map(task -> ResponseTaskShortDTO.builder()
                        .id(task.getId())
                        .name(task.getName())
                        .build()).toList())
                .build();
    }

    @Override
    public ResponseTaskDTO promote(Long id) {

        TaskItem taskItem = taskItemRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        switch (taskItem.getTaskStatus()){
            case TODO -> taskItem.setTaskStatus(TaskStatus.ANALYSIS);
            case ANALYSIS -> taskItem.setTaskStatus(TaskStatus.IN_PROGRESS);
            case IN_PROGRESS -> taskItem.setTaskStatus(TaskStatus.DONE);
        }

        taskItemRepository.save(taskItem);

        return ResponseTaskDTO.builder()
                .id(taskItem.getId())
                .name(taskItem.getName())
                .taskStatus(taskItem.getTaskStatus())
                .taskType(taskItem.getTaskType())
                .build();
    }

    @Override
    public ResponseTaskDTO demote(Long id) {

        TaskItem taskItem = taskItemRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        switch (taskItem.getTaskStatus()){
            case DONE -> taskItem.setTaskStatus(TaskStatus.IN_PROGRESS);
            case IN_PROGRESS -> taskItem.setTaskStatus(TaskStatus.ANALYSIS);
            case ANALYSIS -> taskItem.setTaskStatus(TaskStatus.TODO);
        }

        taskItemRepository.save(taskItem);

        return ResponseTaskDTO.builder()
                .id(taskItem.getId())
                .name(taskItem.getName())
                .taskStatus(taskItem.getTaskStatus())
                .taskType(taskItem.getTaskType())
                .build();
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
            case TASK:
                return TaskType.TASK;
            default:
                return TaskType.PROJECT;
        }
    }
}


