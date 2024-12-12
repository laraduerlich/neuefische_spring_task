package org.example.neuefische_spring_task.model;


public record AsterixCharacter(String id,
                               String name,
                               int age,
                               String job) {

    public AsterixCharacter withId(String id) {
        return new AsterixCharacter(id, name, age, job);
    }

    public AsterixCharacter withName(String name) {
        return new AsterixCharacter(id, name, age, job);
    }

    public AsterixCharacter withAge(int age) {
        return new AsterixCharacter(id, name, age, job);
    }

    public AsterixCharacter withJob(String job) {
        return new AsterixCharacter(id, name, age, job);
    }

}
