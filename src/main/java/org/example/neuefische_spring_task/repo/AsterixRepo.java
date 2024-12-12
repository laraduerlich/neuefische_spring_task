package org.example.neuefische_spring_task.repo;

import org.example.neuefische_spring_task.model.AsterixCharacter;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AsterixRepo extends MongoRepository<AsterixCharacter, String> {

}
