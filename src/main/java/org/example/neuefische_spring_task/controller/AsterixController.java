package org.example.neuefische_spring_task.controller;

import org.example.neuefische_spring_task.model.AsterixCharacter;
import org.example.neuefische_spring_task.repo.AsterixRepo;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/asterix")
public class AsterixController {

    private AsterixRepo asterixRepo;

    public AsterixController(AsterixRepo asterixRepo) {
        this.asterixRepo = asterixRepo;
    }

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

    @GetMapping("{id}")
    public AsterixCharacter getAsterixCharacterById(@PathVariable String id) {
        return asterixRepo.findById(id).orElseThrow();
    }

    @PostMapping
    public AsterixCharacter addAsterixCharacter(@RequestBody AsterixCharacter asterixCharacter) {
        return asterixRepo.save(asterixCharacter);
    }

    @PutMapping("{id}")
    public AsterixCharacter updateAsterixCharacter(@PathVariable String id, @RequestBody AsterixCharacter asterixCharacter) {
        AsterixCharacter updateCharacter = asterixRepo.findById(id).orElseThrow();
        updateCharacter = updateCharacter.withId(asterixCharacter.id());
        updateCharacter = updateCharacter.withName(asterixCharacter.name());
        updateCharacter = updateCharacter.withAge(asterixCharacter.age());
        updateCharacter = updateCharacter.withJob(asterixCharacter.job());

        return updateCharacter;
    }

    @DeleteMapping
    public String deleteAsterixCharacter(@RequestBody AsterixCharacter asterixCharacter) {
        asterixRepo.delete(asterixCharacter);
        return "Successfully deleted";
    }
}
