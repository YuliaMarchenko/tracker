package com.example.tracker.entities;

import lombok.*;

import javax.persistence.*;

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

    @Enumerated(EnumType.STRING)
    @Column(name = "task_type")
    private TaskType taskType;

    @Enumerated(EnumType.STRING)
    @Column(name = "task_status")
    private TaskStatus taskStatus;
}
