package org.example.neuefische_spring_task.service;

import org.example.neuefische_spring_task.util.UUIDGenerator;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class IdServiceTest {

    UUIDGenerator uuidGenerator = Mockito.mock(UUIDGenerator.class);

    @Test
    void generateId() {
        // GIVEN
        IdService idService = new IdService(uuidGenerator);
        String expectedId = "123e4567-e89b-12d3-a456-426614174000";
        when(uuidGenerator.generateUUID()).thenReturn(expectedId);
        // WHEN
        String id = idService.generateId();
        // THEN
        assertEquals(expectedId, id);
    }
  
}