package com.example.tracker.service.impl;

import com.example.tracker.dto.RequestAssigneeDTO;
import com.example.tracker.dto.ResponseAssigneeDTO;
import com.example.tracker.entities.type.AccountStatus;
import com.example.tracker.entities.Assignee;
import com.example.tracker.repository.AssigneeRepository;
import com.example.tracker.service.AssigneeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@AllArgsConstructor

public class AssigneeServiceImpl implements AssigneeService {

    private final AssigneeRepository assigneeRepository;

    @Override
    public ResponseAssigneeDTO createAssignee(RequestAssigneeDTO assigneeDTO) {

        Assignee assignee = Assignee.builder()
                .name(assigneeDTO.getName())
                .accountStatus(AccountStatus.ACTIVE)
                .build();

        assignee = assigneeRepository.save(assignee);

        return ResponseAssigneeDTO.builder()
                .id(assignee.getId())
                .name(assignee.getName())
                .accountStatus(assignee.getAccountStatus())
                .build();
    }

    @Override
    public List<ResponseAssigneeDTO> getAssignees() {

        return assigneeRepository.findAll().stream()
                .map(assignee -> ResponseAssigneeDTO.builder()
                        .id(assignee.getId())
                        .name(assignee.getName())
                        .accountStatus(assignee.getAccountStatus())
                        .build()).toList();
    }

    @Override
    public ResponseAssigneeDTO getAssignee(Long id) {

        Assignee assignee = assigneeRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        return ResponseAssigneeDTO.builder()
                .id(assignee.getId())
                .name(assignee.getName())
                .accountStatus(assignee.getAccountStatus())
                .build();
    }

    @Override
    public ResponseAssigneeDTO changeStatusAssignee(Long id) {

        Assignee assignee = assigneeRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        if (assignee.getAccountStatus()==AccountStatus.INACTIVE) {
            assignee.setAccountStatus(AccountStatus.ACTIVE);
        } else {
            assignee.setAccountStatus(AccountStatus.INACTIVE);
        }

        assignee = assigneeRepository.save(assignee);

        return ResponseAssigneeDTO.builder()
                .id(assignee.getId())
                .name(assignee.getName())
                .accountStatus(assignee.getAccountStatus())
                .build();
    }
}
