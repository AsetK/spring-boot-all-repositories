package com.epam.springdatarestexp.control.repository;

import com.epam.springdatarestexp.control.repository.helpers.PersonRowMapperForJdbcTemplate;
import com.epam.springdatarestexp.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class JdbcTemplateRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public String getColumn(Integer id) {
        String firstName = jdbcTemplate.queryForObject("select first_name from person where person_id = ?", String.class, id);
        return firstName;
    }

    public Person getPerson(Integer id) {
        Person person = jdbcTemplate.queryForObject("select * from person where person_id = ?", new PersonRowMapperForJdbcTemplate(), id);
        return person;
    }

}
