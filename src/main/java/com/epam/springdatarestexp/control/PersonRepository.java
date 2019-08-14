package com.epam.springdatarestexp.control;

import com.epam.springdatarestexp.entity.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;

public interface PersonRepository extends CrudRepository<Person, Integer> {

    public List<Person> findByFirstName(@Param("fName") String firstName); //@Param - parameter in url:   http://localhost:8080/persons/search/findByFirstName?fName=A

    @RestResource(path = "bylname", rel = "bylname") //Customize endpoint URL
    public List<Person> findByLastName(@Param("lName") String lastName); // http://localhost:8080/persons/search/bylname?lName=L

    public List<Person> findAll();

    public Page<Person> findAll(Pageable pageable);



    //If we need more sofisticated filtering, wich is not possible to achieve with combination of filtering parameters in the method name,
    // we can use all the power of JPA query language with @Query annotation
//    @Query("select ...")
//    public List<Person> findByLastNameQuery(@Param("lName") String lastName);
}
