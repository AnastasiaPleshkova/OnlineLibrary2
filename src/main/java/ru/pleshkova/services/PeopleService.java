package ru.pleshkova.services;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.pleshkova.models.Person;
import ru.pleshkova.repositories.PeopleRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class PeopleService {

    private final PeopleRepository peopleRepository;

    @Autowired
    public PeopleService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    public List<Person> findAll() {
        return peopleRepository.findAll();
    }

    public Person findById(int id) {
        return peopleRepository.findById(id).orElse(null);
    }

    public Person findByFio(String Fio) {
        return peopleRepository.findByFio(Fio).orElse(null);
    }
    @Transactional
    public void save(Person person) {
        peopleRepository.save(person);
    }

    @Transactional
    public void update(int id, Person person) {
        peopleRepository.deleteById(id);
        person.setId_person(id);
        peopleRepository.save(person);
    }

    public Person getBooksInService(int id) {
        Person person = peopleRepository.findById(id).orElse(null);
        assert person != null;
        Hibernate.initialize(person.getBooks());
        return person;
    }

    @Transactional
    public void delete(int id) {
        peopleRepository.deleteById(id);
    }
}
