package com.example.tracker.dto;

import com.example.tracker.entities.AccountStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter

public class AssigneeDTO {
    private Long id;
    private String name;
    private AccountStatus accountStatus;
}
