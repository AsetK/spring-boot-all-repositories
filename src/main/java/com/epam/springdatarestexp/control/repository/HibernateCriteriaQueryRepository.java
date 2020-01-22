package com.epam.springdatarestexp.control.repository;

import com.epam.springdatarestexp.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

@Component
public class HibernateCriteriaQueryRepository {

    @Autowired
    EntityManager entityManager;

    //CriteriaBuilder выполняет параметризированные запросы, т.е. план запроса - отдельно, параметры - отдельно.
    //Но при передаче Integer как параметр, выполняется НЕ параметризированный запрос, но если вместо Integer попытаться послать String для SQL Injection, выкинет Exception

    public Person getPersonViaExpression(Integer id){
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Person> query = cb.createQuery(Person.class);    //что вернуть
        Root<Person> root = query.from(Person.class);                  //из чего вернуть. (из какой таблицы, из какой сущности)

//        query.select(root).where(cb.equal(root.get("firstName"), "A"));  //сам запрос с использованием: cb.equal - Predicate, root.get("firstName"), "A") - Expression
        query.select(root).where(cb.equal(root.get("personID"), id));
        TypedQuery<Person> query1 = entityManager.createQuery(query);  //выполнить
        List<Person> resultList = query1.getResultList();  //результаты

        return resultList.get(0);
    }

    public String getColumnViaExpression(Integer id){
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<String> query = cb.createQuery(String.class);    //что вернуть
        Root<Person> root = query.from(Person.class);                  //из чего вернуть. (из какой таблицы, из какой сущности)

        query.select(root.get("lastName")).where(cb.equal(root.get("firstName"), "A"));  //сам запрос с использованием Expression
        TypedQuery<String> query1 = entityManager.createQuery(query);  //выполнить
        List<String> resultList = query1.getResultList();  //результаты

        return resultList.get(0);
    }

    public String getPersonViaPredicate(Integer id){
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<String> query = cb.createQuery(String.class);    //что вернуть
        Root<Person> root = query.from(Person.class);                  //из чего вернуть. (из какой таблицы, из какой сущности

        Predicate predicate = cb.equal(root.get("personID"), id);

        query.select(root.get("lastName")).where(predicate);  //сам запрос
        TypedQuery<String> query1 = entityManager.createQuery(query);  //выполнить
        List<String> resultList = query1.getResultList();  //результаты

        return resultList.get(0);
    }

    public Person getPersonViaPredicates(Integer id, String firstName){
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Person> query = cb.createQuery(Person.class);    //что вернуть
        Root<Person> root = query.from(Person.class);                  //из чего вернуть. (из какой таблицы, из какой сущности

        Predicate predicate_1 = cb.equal(root.get("personID"), id);
        Predicate predicate_2 = cb.equal(root.get("firstName"), firstName);

        query.select(root).where(cb.and(predicate_1,predicate_2));  //сам запрос
        TypedQuery<Person> query1 = entityManager.createQuery(query);  //выполнить
        List<Person> resultList = query1.getResultList();  //результаты

        return resultList.get(0);
    }
}
