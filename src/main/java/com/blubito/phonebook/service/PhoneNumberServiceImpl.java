package com.blubito.phonebook.service;

import com.blubito.phonebook.controller.ContactController;
import com.blubito.phonebook.dbo.PhoneNumberDbo;
import com.blubito.phonebook.dto.FullDetailsDto;
import com.blubito.phonebook.repository.ContactRepository;
import com.blubito.phonebook.repository.PhoneNumberRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static com.blubito.phonebook.mapper.DboMapper.mapContactDbo;

@Service
public class PhoneNumberServiceImpl implements PhoneNumberService {

    private static final Logger log = LoggerFactory.getLogger(ContactController.class);

    @Autowired
    ContactRepository contactRepository;

    @Autowired
    PhoneNumberRepository phonenumberRepository;

    @Override
    public Iterable<PhoneNumberDbo> getAllNumbers() {
        log.info("Invoked method getAllNumbers");
        return phonenumberRepository.findAll();
    }

    @Override
    public Optional<PhoneNumberDbo> findNumberById(Integer id) {
        log.info("Invoked method findContactById");
        return phonenumberRepository.findById(id);
    }


    @Override
    public FullDetailsDto addNumberToExistingContact(FullDetailsDto fullDetailsDto) {
        log.info("Invoked method addNumberToExistingContact");
        Set<PhoneNumberDbo> phoneNumberDbos = new HashSet<>();
        phoneNumberDbos = all();
        contactRepository.save(mapContactDbo(fullDetailsDto, phoneNumberDbos));
        return fullDetailsDto;
    }

    @Override
    public void deleteNumberById(Integer id) {
        log.info("Invoked method deleteNumberById");
        phonenumberRepository.deleteById(id);
    }

    @Override
    public void updateNumber(FullDetailsDto fullDetailsDto) {
        log.info("Invoked method updateNumber");
        Set<PhoneNumberDbo> phoneNumberDbos = new HashSet<>();
        phoneNumberDbos = all();
        mapContactDbo(fullDetailsDto, phoneNumberDbos);
    }

    @Override
    public Set<PhoneNumberDbo> all(){
        return phonenumberRepository.all();
    };
}
