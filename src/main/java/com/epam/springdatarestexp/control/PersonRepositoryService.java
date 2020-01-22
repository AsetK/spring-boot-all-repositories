package com.epam.springdatarestexp.control;

import com.epam.springdatarestexp.control.repository.PersonSpringDataRepository;
import com.epam.springdatarestexp.entity.Person;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class PersonRepositoryService {

    private final SomeInterface someInterface;      // Cheching how @RequiredArgsConstructor works on interface. There should be bean of class that implements this interface.
                                                    // This bean will be injected throw constructor(Spring can inject beans throw constructor parameters without @Autowired annotation)
    private final PersonSpringDataRepository personRepository;

    public Page<Person> findAll(Pageable pageable){
        someInterface.check();                      // Cheching how @RequiredArgsConstructor works on interface. There should be bean of class that implements this interface
        System.out.println(personRepository.getClass());
        return personRepository.findAll(pageable);
    }

    public List<Person> findAll(){
        this.proxyInfo();
        return personRepository.findAll();
    }

    private void proxyInfo(){
        System.out.println("Class name: " + personRepository.getClass());
        System.out.println("Fields: " + personRepository.getClass().getFields().length); //Нет полей - нет обьекта которго можно пнуть(прокси паттерн). Значит не проекси паттерн?
        Arrays.stream(personRepository.getClass().getFields()).forEach(f-> System.out.println("- " + f.getName()));
        System.out.println("Methods: ");            Arrays.stream(personRepository.getClass().getMethods()).forEach(m-> System.out.println("- " + m.getName()));
        System.out.println("Declared Methods: ");   Arrays.stream(personRepository.getClass().getDeclaredMethods()).forEach(dm-> System.out.println("- " + dm.getName()));

        System.out.println(personRepository.getClass().getMethods().length);
        System.out.println(personRepository.getClass().getDeclaredMethods().length);

        System.out.println("Filter methods(excluding inherited methods, but including interface methods:");
        Method[] methods = personRepository.getClass().getMethods();
        Method[] declaredMethods = personRepository.getClass().getDeclaredMethods();

        Set<Method> methodsSet=  new HashSet<Method>(Arrays.asList(methods));
        Set<Method> declaredMethodsSet=  new HashSet<Method>(Arrays.asList(declaredMethods));

        methodsSet.removeAll(declaredMethodsSet);
        methodsSet.stream().forEach(method -> System.out.println("- " + method.getName()));
        Object o = new Object();

    }


}
