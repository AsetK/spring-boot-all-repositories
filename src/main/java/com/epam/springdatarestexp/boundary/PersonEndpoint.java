package com.epam.springdatarestexp.boundary;

import com.epam.springdatarestexp.control.PersonRepositoryService;
import com.epam.springdatarestexp.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("rest")
public class PersonEndpoint {

    @Autowired
    PersonRepositoryService service;

    @GetMapping(value = "/json", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<Person> personJSON (){
        return service.findAll();
    }

    @GetMapping(value = "/pageable", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Page<Person> personPage (@PageableDefault(size = 1) Pageable pageable ){
        return service.findAll(pageable);
    }
}
