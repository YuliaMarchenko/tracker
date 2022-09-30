package com.example.tracker.dto;

import com.example.tracker.entities.type.TaskStatus;
import com.example.tracker.entities.type.TaskType;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class ResponseTaskDTO {
    private Long id;
    private String name;
    private TaskType taskType;
    private TaskStatus taskStatus;
}
