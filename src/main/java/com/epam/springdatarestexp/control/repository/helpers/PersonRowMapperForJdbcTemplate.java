package com.epam.springdatarestexp.control.repository.helpers;

import com.epam.springdatarestexp.entity.Person;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PersonRowMapperForJdbcTemplate implements RowMapper<Person> {


    @Override
    public Person mapRow(ResultSet rs, int rowNum) throws SQLException {
        Person person = new Person();
        person.setPersonID(rs.getInt(1));
        person.setFirstName(rs.getString(2));
        person.setLastName(rs.getString(3));
        return person;
    }
}
