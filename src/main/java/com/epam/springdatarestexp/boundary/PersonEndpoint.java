package com.epam.springdatarestexp.boundary;

import com.epam.springdatarestexp.control.repository.HibernateCriteriaQueryRepository;
import com.epam.springdatarestexp.control.repository.JdbcTemplateRepository;
import com.epam.springdatarestexp.control.repository.NamedParameterJdbcTemplateRepository;
import com.epam.springdatarestexp.control.repository.PersonSpringDataRepository;
import com.epam.springdatarestexp.control.PersonRepositoryService;
import com.epam.springdatarestexp.entity.Person;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("rest")
@RequiredArgsConstructor
public class PersonEndpoint {

    @Autowired
    PersonRepositoryService service;

    @Autowired
    PersonSpringDataRepository springData;

    @Autowired
    HibernateCriteriaQueryRepository hibernateCriteriaQueryRepository;

    @Autowired
    JdbcTemplateRepository jdbcTemplateRepository;

    @Autowired
    NamedParameterJdbcTemplateRepository namedParameterJdbcTemplateRepository;

    @Autowired
    PagedResourcesAssembler<Person> resourceAssembler; //на самом дела bean есть, все работает. просто idea почему то не видит.

    @GetMapping("/hello")
    public String helloPage(){
        return "Hello Page";
    }

    @GetMapping(value = "/json", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<Person> personJSON (){
        return service.findAll();
    }

    @GetMapping(value = "/responseentity", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<Person>> personJSON2 (){
        return new ResponseEntity<>(service.findAll(), HttpStatus.ACCEPTED) ;
    }

    @GetMapping(value = "/pageable", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Page<Person> personPage (@PageableDefault(size = 1) Pageable pageable ){
        return service.findAll(pageable);
    }

    @GetMapping(value = "/pagedresources", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public PagedResources<Resource<Person>> personPagedResourcesAssembler (@PageableDefault(size = 1) Pageable pageable ){
        Page<Person> personPage = service.findAll(pageable);
        return resourceAssembler.toResource(personPage);
    }

    @GetMapping(value = "/findone", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Person findOne() { return springData.findById(1).get();}

    @GetMapping(value = "/save", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String save() {
        Person person = new Person();
        person.setFirstName("AAA");
        person.setLastName("KKK");
        springData.save(person);
        return "Saved";
    }

    @GetMapping(value = "/findonenative", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Person findOneNative() { return springData.findByIdNativeQuery();}

    @GetMapping(value = "/findonejpql", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Person findOneJPQL() { return springData.findByJPQL("A");}

    @GetMapping(value = "/findonesemanticparam", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Person findOneSemanticParam() { return springData.findByPersonID(1);}

    @GetMapping(value = "/findonecriteriaquery", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Person findOneCriteriaQuery() { return hibernateCriteriaQueryRepository.getPersonViaPredicates(1, "A");}

    @GetMapping(value = "/findonejdbctemplate", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Person findOneJdbcTemplate() { return jdbcTemplateRepository.getPerson(1);}

    @GetMapping(value = "/findonenamedParameterjdbctemplate", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Person findOneNamedParameterJdbcTemplate() { return namedParameterJdbcTemplateRepository.getPerson(1);}














    @GetMapping(value = "/cookie", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String cookie (HttpServletResponse response, @CookieValue("myCookie_1") String cookie1, @CookieValue("myCookie_2") String cookie2){
        Cookie cookie_1 = new Cookie("myCookie_1", "myValue_1");
        Cookie cookie_2 = new Cookie("myCookie_2", "myValue_2");

        response.addCookie(cookie_1);
        response.addCookie(cookie_2);

        return cookie1.concat(cookie2);
    }




}
