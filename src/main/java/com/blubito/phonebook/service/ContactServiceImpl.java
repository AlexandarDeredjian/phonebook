package com.blubito.phonebook.service;

import com.blubito.phonebook.controller.ContactController;
import com.blubito.phonebook.dbo.ContactDbo;
import com.blubito.phonebook.dbo.PhonenumberDbo;
import com.blubito.phonebook.dto.FullDetailsDto;
import com.blubito.phonebook.exception.ArgumentNotFoundException;
import com.blubito.phonebook.exception.DuplicatePhoneNumberException;
import com.blubito.phonebook.exception.MissingInputException;
import com.blubito.phonebook.repository.ContactRepository;
import com.blubito.phonebook.repository.PhonenumberRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.blubito.phonebook.exception.ExceptionMessages.*;

@Service
public class ContactServiceImpl implements ContactService {

    private static final Logger log = LoggerFactory.getLogger(ContactController.class);

    @Autowired
    ContactRepository contactRepository;

    @Autowired
    PhonenumberRepository phonenumberRepository;

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
    public Iterable<PhonenumberDbo> getAllNumbers() {
        log.info("Invoked method getAllNumbers");
        try {
            return phonenumberRepository.findAll();
        } catch (IllegalArgumentException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public Optional<ContactDbo> findContactById(Integer id) {
        log.info("Invoked method findContactById");
        try {
            Optional<ContactDbo> optional = contactRepository.findById(id);
            if (optional.isEmpty()) {
                throw new ArgumentNotFoundException(NO_CONTACT_WITH_THIS_ID);
            }
        } catch (IllegalArgumentException e) {
            throw new IllegalStateException(e);
        }
        return contactRepository.findById(id);
    }

    @Override
    public Optional<PhonenumberDbo> findNumberById(Integer id) {
        log.info("Invoked method findContactById");
        try {
            Optional<PhonenumberDbo> optional = phonenumberRepository.findById(id);
            if (optional.isEmpty()) {
                throw new ArgumentNotFoundException(NO_PHONE_NUMBER_WITH_THIS_ID);
            }
        } catch (IllegalArgumentException e) {
            throw new IllegalStateException(e);
        }
        return phonenumberRepository.findById(id);
    }

    @Override
    public FullDetailsDto createContact(FullDetailsDto fullDetailsDto) {
        log.info("Invoked method createContact");

        if(phonenumberRepository.findAllPhoneNumbers().contains(fullDetailsDto.getPhoneNumber())){
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

            contactRepository.save(contactDbo);

            if (fullDetailsDto.getPhoneNumber() != null && fullDetailsDto.getNumberType() != null) {

                PhonenumberDbo phonenumberDbo = PhonenumberDbo.builder()
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
    public FullDetailsDto addNumberToExistingContact(FullDetailsDto fullDetailsDto) {
        log.info("Invoked method addNumberToExistingContact");

        Optional<PhonenumberDbo> byId = phonenumberRepository.findById(fullDetailsDto.getId());
        if(byId.isPresent() && byId.get().getNumberType().equals(fullDetailsDto.getNumberType())){
            byId.get().setPhoneNumber(fullDetailsDto.getPhoneNumber());
            phonenumberRepository.save(byId.get());
            return fullDetailsDto;
        }

        try {
            if (contactRepository.existsById(fullDetailsDto.getId())) {

                PhonenumberDbo phonenumberDbo = PhonenumberDbo.builder()
                        .phoneNumber(fullDetailsDto.getPhoneNumber())
                        .numberType(fullDetailsDto.getNumberType())
                        .contact_id(fullDetailsDto.getId())
                        .build();

                phonenumberRepository.save(phonenumberDbo);
            }
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e);
        }

        return fullDetailsDto;
    }

    @Override
    public void deleteAll() {
        log.info("Invoked method deleteAll");
        try {
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
            if (contactRepository.existsById(id)) {
                throw new ArgumentNotFoundException(NO_CONTACT_WITH_THIS_ID);
            }
            contactRepository.deleteById(id);
        } catch (IllegalArgumentException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void deleteNumberById(Integer id) {
        log.info("Invoked method deleteNumberById");
        try {
            if (phonenumberRepository.existsById(id)) {
                throw new ArgumentNotFoundException(NO_PHONE_NUMBER_WITH_THIS_ID);
            }
            phonenumberRepository.deleteById(id);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e);
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
            return contactDbo;
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public Optional<PhonenumberDbo> updateNumber(FullDetailsDto fullDetailsDto) {
        log.info("Invoked method updateNumber");
        try {
            if (!phonenumberRepository.existsById(fullDetailsDto.getId())) {
                throw new ArgumentNotFoundException(NO_PHONE_NUMBER_WITH_THIS_ID);
            }
            Optional<PhonenumberDbo> phonenumberDbo = phonenumberRepository.findById(fullDetailsDto.getId());
            phonenumberDbo.get().setPhoneNumber(fullDetailsDto.getPhoneNumber());
            phonenumberDbo.get().setNumberType(fullDetailsDto.getNumberType());
            phonenumberRepository.save(phonenumberDbo.get());
            return phonenumberDbo;
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
