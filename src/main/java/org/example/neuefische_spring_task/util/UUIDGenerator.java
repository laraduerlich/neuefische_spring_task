package org.example.neuefische_spring_task.util;

import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class UUIDGenerator {

    public String generateUUID() {
        return UUID.randomUUID().toString();
    }
}
