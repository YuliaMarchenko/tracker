package com.example.tracker.dto;

import com.example.tracker.entities.TaskStatus;
import com.example.tracker.entities.TaskType;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class ResponseTaskDTO {
    private Long id;
    private String name;
    private Long assigneeId;
    private Long parentTaskId;
    private TaskType taskType;
    private TaskStatus taskStatus;
}
