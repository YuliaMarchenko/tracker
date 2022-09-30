package com.example.tracker.entities;

import com.example.tracker.entities.type.TaskStatus;
import com.example.tracker.entities.type.TaskType;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "task_item")

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder

public class TaskItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "assignee_id")
    private Assignee assignee;

    @ManyToOne
    @JoinColumn(name = "parent_task_id")
    private TaskItem parentTask;

    @OneToMany
    @JoinColumn(name = "parent_task_id")
    private List<TaskItem> subTasks;

    @Enumerated(EnumType.STRING)
    @Column(name = "task_type")
    private TaskType taskType;

    @Enumerated(EnumType.STRING)
    @Column(name = "task_status")
    private TaskStatus taskStatus;
}
