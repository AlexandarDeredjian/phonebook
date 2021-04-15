package com.blubito.phonebook.service;

import com.blubito.phonebook.controller.ContactController;
import com.blubito.phonebook.dbo.ContactDbo;
import com.blubito.phonebook.dbo.PhoneNumberDbo;
import com.blubito.phonebook.dto.FullDetailsDto;
import com.blubito.phonebook.exception.ArgumentNotFoundException;
import com.blubito.phonebook.exception.DuplicatePhoneNumberException;
import com.blubito.phonebook.exception.MissingInputException;
import com.blubito.phonebook.repository.ContactRepository;
import com.blubito.phonebook.repository.PhoneNumberRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.blubito.phonebook.exception.ExceptionMessages.*;

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
        try {
            return contactRepository.findAll();
        } catch (IllegalArgumentException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public ContactDbo findContactById(Integer id) {
        log.info("Invoked method findContactById");

        try {
            boolean containsKey = contactDboCache.containsKey(id);
            if (containsKey) {
                return contactDboCache.get(id);
            }
            Optional<ContactDbo> optional = contactRepository.findById(id);
            if (optional.isEmpty()) {
                throw new ArgumentNotFoundException(NO_CONTACT_WITH_THIS_ID);
            }
        } catch (IllegalArgumentException e) {
            throw new IllegalStateException(e);
        }
        contactDboCache.put(id, contactRepository.findById(id).get());
        return contactDboCache.get(id);
    }

    @Override
    public FullDetailsDto createContact(FullDetailsDto fullDetailsDto) {
        log.info("Invoked method createContact");

        if (phonenumberRepository.findAllPhoneNumbers().contains(fullDetailsDto.getPhoneNumber())) {
            throw new DuplicatePhoneNumberException(THIS_NUMBER_ALREADY_EXISTS);
        }

        try {

            if (fullDetailsDto.getFirstname() == null || fullDetailsDto.getLastname() == null) {
                throw new MissingInputException(MANDATORY_FIELD_MISSING);
            }

            ContactDbo contactDbo = ContactDbo.builder()
                    .firstname(fullDetailsDto.getFirstname())
                    .lastname(fullDetailsDto.getLastname())
                    .build();

            contactDboCache.put(contactDbo.getId(), contactDbo);
            contactRepository.save(contactDbo);

            if (fullDetailsDto.getPhoneNumber() != null && fullDetailsDto.getNumberType() != null) {

                PhoneNumberDbo phonenumberDbo = PhoneNumberDbo.builder()
                        .phoneNumber(fullDetailsDto.getPhoneNumber())
                        .numberType(fullDetailsDto.getNumberType())
                        .contact_id(contactDbo.getId())
                        .build();

                phonenumberRepository.save(phonenumberDbo);
            }
        } catch (IllegalArgumentException e) {
            throw new IllegalStateException(e);
        }
        return fullDetailsDto;
    }

    @Override
    public void deleteAll() {
        log.info("Invoked method deleteAll");
        try {
            contactDboCache.clear();
            contactRepository.deleteAll();
            phonenumberRepository.deleteAll();
        } catch (IllegalArgumentException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void deleteContactById(Integer id) {
        log.info("Invoked method deleteContactById");
        try {
            if (!contactRepository.existsById(id)) {
                throw new ArgumentNotFoundException(NO_CONTACT_WITH_THIS_ID);
            }
            if (contactDboCache.containsKey(id)) {
                contactDboCache.remove(id);
            }
            contactRepository.deleteById(id);
        } catch (IllegalArgumentException e) {
            throw new IllegalStateException(e);
        }
    }


    @Override
    public Optional<ContactDbo> updateName(FullDetailsDto fullDetailsDto) {
        log.info("Invoked method updateName");
        try {
            if (!contactRepository.existsById(fullDetailsDto.getId())) {
                throw new ArgumentNotFoundException(NO_CONTACT_WITH_THIS_ID);
            }
            Optional<ContactDbo> contactDbo = contactRepository.findById(fullDetailsDto.getId());
            contactDbo.get().setFirstname(fullDetailsDto.getFirstname());
            contactDbo.get().setLastname(fullDetailsDto.getLastname());
            contactRepository.save(contactDbo.get());
            contactDboCache.replace(contactDbo.get().getId(),contactDbo.get());
            return contactDbo;
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e);
        }
    }

}
