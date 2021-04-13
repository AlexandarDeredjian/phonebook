package com.blubito.phonebook.service;

import com.blubito.phonebook.controller.ContactController;
import com.blubito.phonebook.dbo.ContactDbo;
import com.blubito.phonebook.dbo.PhonenumberDbo;
import com.blubito.phonebook.dto.FullDetailsDto;
import com.blubito.phonebook.exception.ArgumentNotFoundException;
import com.blubito.phonebook.repository.ContactRepository;
import com.blubito.phonebook.repository.PhonenumberRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
            throw new IllegalStateException();
        }
    }

    @Override
    public Iterable<PhonenumberDbo> getAllNumbers() {
        log.info("Invoked method getAllNumbers");
        try {
            return phonenumberRepository.findAll();
        } catch (IllegalArgumentException e) {
            throw new IllegalStateException();
        }
    }

    @Override
    public Optional<ContactDbo> findContactById(Integer id) {
        log.info("Invoked method findContactById");
        try {
            Optional<ContactDbo> optional = contactRepository.findById(id);
            if (optional.isEmpty()) {
                throw new ArgumentNotFoundException();
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
                throw new ArgumentNotFoundException();
            }
        } catch (IllegalArgumentException e) {
            throw new IllegalStateException(e);
        }
        return phonenumberRepository.findById(id);
    }

    @Override
    public FullDetailsDto createContact(FullDetailsDto fullDetailsDto) {
        log.info("Invoked method createContact");

        try {
            ContactDbo contactDbo = new ContactDbo(fullDetailsDto.getFirstname(), fullDetailsDto.getLastname());
            contactRepository.save(contactDbo);

            if (fullDetailsDto.getPhoneNumber() != null && fullDetailsDto.getNumberType() != null) {
                PhonenumberDbo phonenumberDbo = new PhonenumberDbo();
                phonenumberDbo.setPhoneNumber(fullDetailsDto.getPhoneNumber());
                phonenumberDbo.setNumberType(fullDetailsDto.getNumberType());
                phonenumberDbo.setContact_id(contactDbo.getId());
                phonenumberRepository.save(phonenumberDbo);
            }
        } catch (IllegalArgumentException e) {
            throw new IllegalStateException();
        }
        return fullDetailsDto;
    }

    @Override
    public FullDetailsDto addNumberToExistingContact(FullDetailsDto fullDetailsDto) {
        log.info("Invoked method addNumberToExistingContact");
        try {
            if (contactRepository.existsById(fullDetailsDto.getId())) {
                PhonenumberDbo phonenumberDbo = new PhonenumberDbo();
                phonenumberDbo.setPhoneNumber(fullDetailsDto.getPhoneNumber());
                phonenumberDbo.setNumberType(fullDetailsDto.getNumberType());
                phonenumberDbo.setContact_id(fullDetailsDto.getId());
                phonenumberRepository.save(phonenumberDbo);
            }
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException();
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
            throw new IllegalStateException();
        }
    }

    @Override
    public void deleteContactById(Integer id) {
        log.info("Invoked method deleteContactById");
        try {
            if (contactRepository.existsById(id)) {
                throw new ArgumentNotFoundException();
            }
            contactRepository.deleteById(id);
        } catch (IllegalArgumentException e) {
            throw new IllegalStateException();
        }
    }

    @Override
    public void deleteNumberById(Integer id) {
        log.info("Invoked method deleteNumberById");
        try {
            if (phonenumberRepository.existsById(id)) {
                throw new ArgumentNotFoundException();
            }
            phonenumberRepository.deleteById(id);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public Optional<ContactDbo> updateName(FullDetailsDto fullDetailsDto) {
        log.info("Invoked method updateName");
        try {
            if (!contactRepository.existsById(fullDetailsDto.getId())) {
                throw new ArgumentNotFoundException();
            }
            Optional<ContactDbo> contactDbo = contactRepository.findById(fullDetailsDto.getId());
            contactDbo.get().setFirstname(fullDetailsDto.getFirstname());
            contactDbo.get().setLastname(fullDetailsDto.getLastname());
            contactRepository.save(contactDbo.get());
            return contactDbo;
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public Optional<PhonenumberDbo> updateNumber(FullDetailsDto fullDetailsDto) {
        log.info("Invoked method updateNumber");
        try {
            if (!phonenumberRepository.existsById(fullDetailsDto.getId())) {
                throw new ArgumentNotFoundException();
            }
            Optional<PhonenumberDbo> phonenumberDbo = phonenumberRepository.findById(fullDetailsDto.getId());
            phonenumberDbo.get().setPhoneNumber(fullDetailsDto.getPhoneNumber());
            phonenumberDbo.get().setNumberType(fullDetailsDto.getNumberType());
            phonenumberRepository.save(phonenumberDbo.get());
            return phonenumberDbo;
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException();
        }
    }
}
