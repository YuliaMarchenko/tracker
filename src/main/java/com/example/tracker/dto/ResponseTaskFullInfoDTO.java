package com.example.tracker.dto;

import com.example.tracker.entities.Assignee;
import com.example.tracker.entities.TaskItem;
import com.example.tracker.entities.TaskStatus;
import com.example.tracker.entities.TaskType;
import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class ResponseTaskFullInfoDTO {
    private Long id;
    private String name;
    private TaskType taskType;
    private TaskStatus taskStatus;
    private ResponseTaskShortDTO parentTask;
    private ResponseAssigneeDTO assignee;
    private List<ResponseTaskShortDTO> subTasks;
}
