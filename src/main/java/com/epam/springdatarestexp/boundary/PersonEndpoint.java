package com.epam.springdatarestexp.boundary;

import com.epam.springdatarestexp.control.PersonRepositoryService;
import com.epam.springdatarestexp.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonEndpoint {

    @Autowired
    PersonRepositoryService service;

    @GetMapping(value = "/pageable")
    public Page<Person> personPage (@PageableDefault(size = 2) Pageable pageable){
        return service.findAll(pageable);
    }
}
