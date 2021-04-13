package com.blubito.phonebook.service;

import com.blubito.phonebook.controller.ContactController;
import com.blubito.phonebook.dbo.ContactDbo;
import com.blubito.phonebook.dbo.PhonenumberDbo;
import com.blubito.phonebook.dto.FullDetailsDto;
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
        return contactRepository.findAll();
    }

    @Override
    public Iterable<PhonenumberDbo> getAllNumbers() {
        log.info("Invoked method getAllNumbers");
        return phonenumberRepository.findAll();
    }

    @Override
    public Optional<ContactDbo> findContactById(Integer id) {
        log.info("Invoked method findContactById");
        return contactRepository.findById(id);
    }

    @Override
    public Optional<PhonenumberDbo> findNumberById(Integer id) {
        log.info("Invoked method findContactById");
        return phonenumberRepository.findById(id);
    }

    @Override
    public FullDetailsDto createContact(FullDetailsDto fullDetailsDto) {
        log.info("Invoked method createContact");

        ContactDbo contactDbo = new ContactDbo(fullDetailsDto.getFirstname(), fullDetailsDto.getLastname());
        contactRepository.save(contactDbo);

        if (fullDetailsDto.getPhoneNumber() != null && fullDetailsDto.getNumberType() != null) {
            PhonenumberDbo phonenumberDbo = new PhonenumberDbo();
            phonenumberDbo.setPhoneNumber(fullDetailsDto.getPhoneNumber());
            phonenumberDbo.setNumberType(fullDetailsDto.getNumberType());
            phonenumberDbo.setContact_id(contactDbo.getId());
            phonenumberRepository.save(phonenumberDbo);
        }
        return fullDetailsDto;
    }

    @Override
    public void deleteAll() {
        log.info("Invoked method deleteAll");
        contactRepository.deleteAll();
        phonenumberRepository.deleteAll();
    }

    @Override
    public void deleteContactById(Integer id) {
        log.info("Invoked method deleteContactById");
        contactRepository.deleteById(id);
    }

    @Override
    public void deleteNumberById(Integer id) {
        log.info("Invoked method deleteNumberById");
        phonenumberRepository.deleteById(id);
    }

    @Override
    public Optional<ContactDbo> updateName(FullDetailsDto fullDetailsDto) {
        log.info("Invoked method updateName");
        Optional<ContactDbo> contactDbo = contactRepository.findById(fullDetailsDto.getId());
        contactDbo.get().setFirstname(fullDetailsDto.getFirstname());
        contactDbo.get().setLastname(fullDetailsDto.getLastname());
        contactRepository.save(contactDbo.get());

        return contactDbo;
    }

    @Override
    public Optional<PhonenumberDbo> updateNumber(FullDetailsDto fullDetailsDto) {
        log.info("Invoked method updateNumber");
        Optional<PhonenumberDbo> phonenumberDbo = phonenumberRepository.findById(fullDetailsDto.getId());
        phonenumberDbo.get().setPhoneNumber(fullDetailsDto.getPhoneNumber());
        phonenumberDbo.get().setNumberType(fullDetailsDto.getNumberType());
        phonenumberRepository.save(phonenumberDbo.get());

        return phonenumberDbo;
    }
}
