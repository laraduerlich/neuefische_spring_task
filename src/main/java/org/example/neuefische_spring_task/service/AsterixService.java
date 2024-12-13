package org.example.neuefische_spring_task.service;

import lombok.RequiredArgsConstructor;
import org.example.neuefische_spring_task.model.AsterixCharacter;
import org.example.neuefische_spring_task.model.AsterixCharacterDTO;
import org.example.neuefische_spring_task.repo.AsterixRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class AsterixService {

    private final AsterixRepo repo;

    public List<AsterixCharacterDTO> getAllCharacters() {
        return repo.findAll().stream()
                .map(character -> {
                    AsterixCharacterDTO dto = new AsterixCharacterDTO(
                            character.name(),
                            character.age(),
                            character.job());
                    return dto;
                })
                .toList();
    }

    public AsterixCharacter getCharacterById(String id) {
        AsterixCharacter temp = repo.findById(id).orElseThrow();
        AsterixCharacterDTO dto = new AsterixCharacterDTO(
                temp.name(),
                temp.age(),
                temp.job());
        return temp;
    }

    public AsterixCharacter createCharacter(AsterixCharacterDTO dto) {
        AsterixCharacter newCharacter = new AsterixCharacter(
                UUID.randomUUID().toString(),
                dto.name(),
                dto.age(),
                dto.job());
        return repo.save(newCharacter);
    }

    public AsterixCharacter updateCharacter(AsterixCharacter character, String id) {
        if(repo.existsById(id)) {
            repo.save(character);
            return repo.findById(id).orElseThrow();
        } else {
            throw new RuntimeException("Character not found");
        }
    }

    public void deleteCharacter(String id) {
        if(repo.existsById(id)) {
            repo.deleteById(id);
        } else {
            throw new RuntimeException("Character not found");
        }
    }


}
