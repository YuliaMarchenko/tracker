package com.example.tracker.dto;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class ResponseTaskShortDTO {

    private Long id;
    private String name;

}
