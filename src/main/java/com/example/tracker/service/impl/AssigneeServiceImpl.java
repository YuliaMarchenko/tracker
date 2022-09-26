package com.example.tracker.service.impl;

import com.example.tracker.dto.AssigneeDTO;
import com.example.tracker.entities.AccountStatus;
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
    public AssigneeDTO createAssignee(AssigneeDTO assigneeDTO) {
        Assignee assignee = Assignee.builder()
                .name(assigneeDTO.getName())
                .build();

        assignee.setAccountStatus(AccountStatus.ACTIVE);
        assignee = assigneeRepository.save(assignee);

        assigneeDTO.setId(assignee.getId());
        assigneeDTO.setAccountStatus(AccountStatus.ACTIVE);

        return assigneeDTO;
    }

    @Override
    public List<AssigneeDTO> getAssignees() {
        return null;
    }

    @Override
    public AssigneeDTO getAssignee(Long id) {
        Assignee assignee = assigneeRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        return AssigneeDTO.builder()
                .id(assignee.getId())
                .name(assignee.getName())
                .accountStatus(assignee.getAccountStatus())
                .build();
    }
}
