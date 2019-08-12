package com.epam.springdatarestexp.control;

import com.epam.springdatarestexp.entity.Person;
import org.springframework.data.repository.CrudRepository;

public interface PersonRepository extends CrudRepository<Person, Integer> {
}
