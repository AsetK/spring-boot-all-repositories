package com.epam.springdatarestexp.control;

import com.epam.springdatarestexp.entity.Person;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PersonRepositoryService {

    private final PersonRepository personRepository;

    public Page<Person> findAll(Pageable pageable){
        return personRepository.findAll(pageable);
    }


}
