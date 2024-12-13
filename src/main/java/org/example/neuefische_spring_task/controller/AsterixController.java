package org.example.neuefische_spring_task.controller;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.example.neuefische_spring_task.model.AsterixCharacter;
import org.example.neuefische_spring_task.model.AsterixCharacterDTO;
import org.example.neuefische_spring_task.repo.AsterixRepo;
import org.example.neuefische_spring_task.service.AsterixService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/asterix")
@RequiredArgsConstructor
public class AsterixController {

    private final AsterixService service;

//    @GetMapping("/characters")
//    public List<AsterixCharacter> getCharacters() {
//        return List.of(
//                new AsterixCharacter("1", "Asterix", 35, "Krieger"),
//                new AsterixCharacter("2", "Obelix", 35, "Lieferant"),
//                new AsterixCharacter("3", "Miraculix", 60, "Druide"),
//                new AsterixCharacter("4", "Majestix", 60, "Häuptling"),
//                new AsterixCharacter("5", "Troubadix", 25, "Barden"),
//                new AsterixCharacter("6", "Gutemine", 35, "Häuptlingsfrau"),
//                new AsterixCharacter("7", "Idefix", 5, "Hund"),
//                new AsterixCharacter("8", "Geriatrix", 70, "Rentner"),
//                new AsterixCharacter("9", "Automatix", 35, "Schmied"),
//                new AsterixCharacter("10", "Grockelix", 35, "Fischer")
//        );
//    }

    @GetMapping
    public List<AsterixCharacterDTO> getAllCharacters() {
        return service.getAllCharacters();
    }

    @GetMapping("/{id}")
    public AsterixCharacterDTO getCharacterById(@PathVariable String id) {
        return service.getCharacterById(id);
    }

    @PostMapping
    public AsterixCharacter createCharacter(@RequestBody AsterixCharacterDTO character) {
        return service.createCharacter(character);
    }

    @PutMapping("/{id}")
    public AsterixCharacter updateCharacter(@RequestBody AsterixCharacter character, @PathVariable String id) {
      return service.updateCharacter(character, id);
    }

    @DeleteMapping("/{id}")
    public void deleteCharacter(@PathVariable String id) {
        service.deleteCharacter(id);
    }
}
