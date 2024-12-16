package org.example.neuefische_spring_task.controller;

import org.example.neuefische_spring_task.model.AsterixCharacter;
import org.example.neuefische_spring_task.repo.AsterixRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class AsterixControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private AsterixRepo repo;

    @Test
    void getAllCharacters_shouldReturnEmptyList_whenCalledInitially() throws Exception {
        // WHEN & THEN
        mockMvc.perform(get("/api/asterix"))
                .andExpect(status().isOk())
                .andExpect(content().json("[]"));
    }

    @Test
    void getCharacterById_shouldReturnCharacter_whenCalledWithValidId() throws Exception {
        // GIVEN
        AsterixCharacter character = new AsterixCharacter(
                "1",
                "Asterix",
                35,
                "Krieger");
        repo.save(character);
        // WHEN & THEN
        mockMvc.perform(get("api/asterix/" + character.id()))
                .andExpect(status().isOk())
                .andExpect(content().json("""
                                          {
                                            "id": "1",
                                            "name": "Asterix",
                                            "age": 35,
                                            "job": "Krieger"
                                          }
                                          """));
    }

    @Test
    void createCharacter_shouldReturnCharacter_whenCalledWithValidId() throws Exception {
        // GIVEN
        // WHEN & THEN
        mockMvc.perform(post("/api/asterix")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                  {
                                    "id": "1",
                                    "name": "Asterix",
                                    "age": 40,
                                    "job": "Krieger"
                                  }"""))

                .andExpect(status().isOk())
                .andExpect(content().json("""
                                          {
                                            "name": "Asterix",
                                            "age": 35,
                                            "job": "Krieger"
                                          }
                                          """))
                .andExpect(jsonPath("$.id").isNotEmpty());
    }
    @Test
    void updateCharacter_shouldReturnCharacter_whenCalledWithValidId() throws Exception {
        // GIVEN
        AsterixCharacter character = new AsterixCharacter(
                "1",
                "Asterix",
                35,
                "Krieger");
        repo.save(character);
        // WHEN & THEN
        mockMvc.perform(put("/api/asterix/" + character.id())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                  {
                                    "id": "1",
                                    "name": "Asterix",
                                    "age": 40,
                                    "job": "Krieger"
                                  }"""))
                .andExpect(status().isOk())
                .andExpect(content().json("""
                                          {
                                            "name": "Asterix",
                                            "age": 40,
                                            "job": "Krieger"
                                          }
                                          """))
                .andExpect(jsonPath("$.id").isNotEmpty());
    }

    @Test
    void deleteCharacter_shouldReturnCharacter_whenCalledWithValidId() throws Exception {
        // GIVEN
        AsterixCharacter character = new AsterixCharacter(
                "1",
                "Asterix",
                35,
                "Krieger");
        repo.save(character);
        // WHEN & THEN
        assertTrue(repo.existsById(character.id()));
        mockMvc.perform(delete("/api/asterix/" + character.id()))
                .andExpect(status().isOk());
        assertFalse(repo.existsById(character.id()));
    }
}