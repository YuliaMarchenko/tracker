package com.example.tracker.entities;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "assignee")

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder

public class Assignee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "accountStatus")
    private AccountStatus accountStatus;

    @OneToMany(mappedBy = "assignee")
    private List<TaskItem> tasks;

}
