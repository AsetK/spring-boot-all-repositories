package com.epam.springdatarestexp.control;

import com.epam.springdatarestexp.entity.Person;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest
public class PersonRepositoryTest {

    @Autowired
    PersonRepository personRepository;


    @Test
    @Transactional
    public void testFindAll (){
        Iterable<Person> allPerson = personRepository.findAll();
        List<Person> personList = new ArrayList<>();
        allPerson.forEach(personList::add);

        assertEquals(4, personList.size());
    }

    @Test
    @Transactional
    public void testFindByID(){
        Optional<Person> person = personRepository.findById(1);
        Person p = person.get();
        assertEquals("A", p.getFirstName());
    }

}