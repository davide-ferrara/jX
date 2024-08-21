package com.davideferrara.jx.services;

import com.davideferrara.jx.exceptions.PersonException;
import com.davideferrara.jx.models.Person;
import com.davideferrara.jx.repositories.PersonRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {
    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public void deletePerson(Long personId) throws PersonException {
        boolean exist = personRepository.existsById(personId);
        if (!exist) {
            throw new PersonException("Person with ID " + personId + " does not exist!");
        }
        personRepository.deleteById(personId);
    }

}


