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

import java.util.Optional;
import java.util.Set;

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

        Optional<ContactDbo> byId = contactRepository.findById(fullDetailsDto.getId());
        Set<PhoneNumberDbo> phoneNumbers = byId.get().getPhoneNumbers();

        phoneNumbers.add(PhoneNumberDbo.builder()
                .numberType(fullDetailsDto.getNumberType())
                .phoneNumber(fullDetailsDto.getPhoneNumber())
                .build());

        byId.get().setPhoneNumbers(phoneNumbers);
        contactRepository.save(byId.get());

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
        PhoneNumberDbo phoneNumberDbo = phonenumberRepository.findById(fullDetailsDto.getId()).get();
        phoneNumberDbo.setNumberType(fullDetailsDto.getNumberType());
        phoneNumberDbo.setPhoneNumber(fullDetailsDto.getPhoneNumber());
        phonenumberRepository.save(phoneNumberDbo);
    }

    @Override
    public Set<PhoneNumberDbo> all() {
        return phonenumberRepository.all();
    }
}
