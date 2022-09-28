package com.example.tracker.converter;

import com.example.tracker.dto.ResponseAssigneeDTO;
import com.example.tracker.dto.ResponseTaskDTO;
import com.example.tracker.dto.ResponseTaskFullInfoDTO;
import com.example.tracker.dto.ResponseTaskShortDTO;
import com.example.tracker.entities.Assignee;
import com.example.tracker.entities.TaskItem;

public class TaskItemConverter {
    public static ResponseTaskFullInfoDTO convertToFullInfoDTO(TaskItem taskItem){
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
                .subTasks(taskItem.getSubTasks() == null ? null : taskItem.getSubTasks()
                        .stream()
                        .map(task -> ResponseTaskShortDTO.builder()
                                .id(task.getId())
                                .name(task.getName())
                                .build()).toList())
                .build();
    }

    public static ResponseTaskDTO convertToTaskDTO(TaskItem taskItem) {
        return ResponseTaskDTO.builder()
                .id(taskItem.getId())
                .name(taskItem.getName())
                .taskStatus(taskItem.getTaskStatus())
                .taskType(taskItem.getTaskType())
                .build();
    }
}
