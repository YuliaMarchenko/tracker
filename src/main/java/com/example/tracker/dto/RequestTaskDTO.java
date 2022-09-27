package com.example.tracker.dto;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class RequestTaskDTO {
    private String name;
    private Long assigneeId;
    private Long parentTaskId;
}
