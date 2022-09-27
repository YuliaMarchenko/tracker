package com.example.tracker.dto;

import com.example.tracker.entities.AccountStatus;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor


public class ResponseAssigneeDTO {
    private Long id;
    private String name;
    private AccountStatus accountStatus;
}
