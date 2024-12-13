package org.example.neuefische_spring_task.service;

import org.example.neuefische_spring_task.model.AsterixCharacter;
import org.example.neuefische_spring_task.model.AsterixCharacterDTO;
import org.example.neuefische_spring_task.repo.AsterixRepo;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AsterixServiceTest {

    private AsterixRepo repo = mock(AsterixRepo.class);
    private IdService idService = mock(IdService.class);

    @Test
    void getAllCharacters_shouldReturnEmptyList_whenCalledInitinally() {
        // GIVEN
        AsterixService service = new AsterixService(repo, idService);
        List<AsterixCharacterDTO> expected = Collections.emptyList();
        // WHEN
        List<AsterixCharacterDTO> actual = service.getAllCharacters();
        // THEN
        assertEquals(expected, actual);
    }

    @Test
    void getCharacterById_shouldReturnAsterixCharacter_whenCalledWithValidId() {
        // GIVEN
        AsterixService service = new AsterixService(repo, idService);
        AsterixCharacter character = new AsterixCharacter("1", "Asterix", 35, "Krieger");
        AsterixCharacterDTO expected = new AsterixCharacterDTO("Asterix", 35, "Krieger");
        when(repo.findById(character.id())).thenReturn(Optional.of(character));
        // WHEN
        AsterixCharacterDTO actual = service.getCharacterById(character.id());
        // THEN
        assertEquals(expected, actual);
    }

    @Test
    void createCharacter_shouldCreateAsterixCharacter_whenCalledWithDTO() {
        // GIVEN
        AsterixService service = new AsterixService(repo, idService);
        AsterixCharacterDTO dto = new AsterixCharacterDTO("Asterix", 35, "Krieger");
        AsterixCharacter expected = new AsterixCharacter("1", "Asterix", 35, "Krieger");
        when(idService.generateId()).thenReturn("1");
        when(repo.save(expected)).thenReturn(expected);
        // WHEN
        AsterixCharacter actual = service.createCharacter(dto);
        // THEN
        assertEquals(expected, actual);
        verify(repo).save(expected);
    }

    @Test
    void updateCharacter_shouldUpdateAsterixCharacter_whenCalledWithValidId() {
        // GIVEN
        AsterixService service = new AsterixService(repo, idService);
        AsterixCharacter character = new AsterixCharacter("1", "Asterix", 35, "Krieger");
        AsterixCharacter expected = new AsterixCharacter(
                character.id(),
                character.name(),
                character.age(),
                character.job());
        when(repo.existsById(character.id())).thenReturn(true);
        when(repo.findById(character.id())).thenReturn(Optional.of(character));
        // WHEN
        AsterixCharacter actual = service.updateCharacter(character, character.id());
        // THEN
        assertEquals(expected, actual);
        verify(repo).save(expected);
    }

    @Test
    void deleteCharacter_shouldDeleteAsterixCharacter_whenCalledWithValidId() {
        // GIVEN
        AsterixService service = new AsterixService(repo, idService);
        AsterixCharacter character = new AsterixCharacter("1", "Asterix", 35, "Krieger");
        AsterixCharacter expected = new AsterixCharacter(
                character.id(),
                character.name(),
                character.age(),
                character.job());
        when(repo.existsById(character.id())).thenReturn(true);
        // WHEN
        service.deleteCharacter(character.id());
        // THEN
        verify(repo).deleteById(character.id());
    }

}