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
    private TaskType taskType;
    private TaskStatus taskStatus;
}
