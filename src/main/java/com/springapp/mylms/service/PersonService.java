package com.springapp.mylms.service;

import com.springapp.mylms.repository.PersonRepository;
import com.springapp.mylms.domain.PersonEntity;
import com.springapp.mylms.modal.UserReport;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * Created by bezdj on 04/02/2017.
 */

@Service
public class PersonService {

    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public void insertPerson(PersonEntity person) {
        personRepository.save(person);
    }

    public PersonEntity validateUser(String username, String password) {
        return personRepository.findByUsernameAndPassword(username, password);
    }


    public List<PersonEntity> getAllUsers() {
        return personRepository.findAll();
    }

    public List<PersonEntity> getAllUnerolledUsers(int courseid) {
        // return personRepository.getAllUnerolledUsers(courseid);
        return Collections.emptyList();
    }

    public List<PersonEntity> getAllEnrolledUsers(Long courseid) {
        //return personRepository.getAllEnrolledUsers(courseid);
        return Collections.emptyList();
    }

    public PersonEntity getUserById(Long userid) {
        return personRepository.findById(userid).get();
    }

    public void removeUser(Long userid) {
        personRepository.deleteById(userid);
    }

    public List<UserReport> getUserReports() {
        //return personRepository.getUserReports();
        return Collections.emptyList();
    }
}
