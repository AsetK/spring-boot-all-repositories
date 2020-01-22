package com.epam.springdatarestexp.control.repository;

import com.epam.springdatarestexp.control.repository.helpers.PersonRowMapperForJdbcTemplate;
import com.epam.springdatarestexp.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class NamedParameterJdbcTemplateRepository {

    @Autowired
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public Person getPerson(Integer id) {
        SqlParameterSource param = new MapSqlParameterSource().addValue("personId", id);
        List<Person> personList = namedParameterJdbcTemplate.query("select * from person where person_id = :personId", param, new PersonRowMapperForJdbcTemplate());
        return personList.get(0);
    }
}
