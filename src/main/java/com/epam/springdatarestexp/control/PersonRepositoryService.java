package com.epam.springdatarestexp.control;

import com.epam.springdatarestexp.entity.Person;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PersonRepositoryService {

    private final SomeInterface someInterface;      // Cheching how @RequiredArgsConstructor works on interface. There should be bean of class that implements this interface
    private final PersonRepository personRepository;

    public Page<Person> findAll(Pageable pageable){
        someInterface.check();                      // Cheching how @RequiredArgsConstructor works on interface. There should be bean of class that implements this interface
        return personRepository.findAll(pageable);
    }


}
