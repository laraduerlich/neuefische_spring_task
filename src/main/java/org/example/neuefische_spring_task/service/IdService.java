package org.example.neuefische_spring_task.service;

import lombok.RequiredArgsConstructor;
import org.example.neuefische_spring_task.util.UUIDGenerator;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class IdService {

    private final UUIDGenerator uuidGenerator;

    public String generateId() {
        return uuidGenerator.generateUUID();
    }
}
