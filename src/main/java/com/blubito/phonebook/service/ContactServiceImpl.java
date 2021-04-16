package com.blubito.phonebook.service;

import com.blubito.phonebook.controller.ContactController;
import com.blubito.phonebook.dbo.ContactDbo;
import com.blubito.phonebook.dbo.PhoneNumberDbo;
import com.blubito.phonebook.dto.FullDetailsDto;
import com.blubito.phonebook.repository.ContactRepository;
import com.blubito.phonebook.repository.PhoneNumberRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static com.blubito.phonebook.mapper.DboMapper.mapContactDbo;

@Service
public class ContactServiceImpl implements ContactService {

    private static final Logger log = LoggerFactory.getLogger(ContactController.class);

    @Autowired
    ContactRepository contactRepository;

    @Autowired
    PhoneNumberRepository phonenumberRepository;

    Map<Integer, ContactDbo> contactDboCache = new HashMap();

    @Override
    public Iterable<ContactDbo> getAllContacts() {
        log.info("Invoked method getAllContacts");
        return contactRepository.findAll();
    }

    @Override
    public ContactDbo findContactById(Integer id) {
        log.info("Invoked method findContactById");
        return contactRepository.findById(id).get();
    }

    @Override
    public FullDetailsDto createContact(FullDetailsDto fullDetailsDto) {
        log.info("Invoked method createContact");
        Set<PhoneNumberDbo> phoneNumberDbos = new HashSet<>();
        phoneNumberDbos = phonenumberRepository.all();
        ContactDbo contactDbo = mapContactDbo(fullDetailsDto, phoneNumberDbos);
        contactRepository.save(contactDbo);
        return fullDetailsDto;
    }

    @Override
    public void deleteAll() {
        log.info("Invoked method deleteAll");
        contactRepository.deleteAll();
    }

    @Override
    public void deleteContactById(Integer id) {
        log.info("Invoked method deleteContactById");
        contactRepository.deleteById(id);
    }


    @Override
    public void updateName(FullDetailsDto fullDetailsDto) {
        log.info("Invoked method updateName");
        Set<PhoneNumberDbo> phoneNumberDbos = new HashSet<>();
        phoneNumberDbos = phonenumberRepository.all();
        contactRepository.save(mapContactDbo(fullDetailsDto, phoneNumberDbos));
    }

}
