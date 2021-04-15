package com.blubito.phonebook.service;

import com.blubito.phonebook.controller.ContactController;
import com.blubito.phonebook.dbo.PhoneNumberDbo;
import com.blubito.phonebook.dto.CombinedDetailsDto;
import com.blubito.phonebook.dto.FullDetailsDto;
import com.blubito.phonebook.dto.PhoneNumberDto;
import com.blubito.phonebook.exception.ArgumentNotFoundException;
import com.blubito.phonebook.exception.DuplicatePhoneNumberException;
import com.blubito.phonebook.repository.ContactRepository;
import com.blubito.phonebook.repository.PhoneNumberRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.blubito.phonebook.exception.ExceptionMessages.NO_PHONE_NUMBER_WITH_THIS_ID;
import static com.blubito.phonebook.exception.ExceptionMessages.THIS_NUMBER_ALREADY_EXISTS;
import static com.blubito.phonebook.mapper.PhoneNumberDtoMapper.mapToPhoneNumberDto;

@Service
public class PhoneNumberServiceImpl implements PhoneNumberService {

    private static final Logger log = LoggerFactory.getLogger(ContactController.class);

    @Autowired
    ContactService contactService;

    @Autowired
    ContactRepository contactRepository;

    @Autowired
    PhoneNumberRepository phonenumberRepository;

    @Override
    public Iterable<PhoneNumberDbo> getAllNumbers() {
        log.info("Invoked method getAllNumbers");
        try {
            return phonenumberRepository.findAll();
        } catch (IllegalArgumentException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public Optional<PhoneNumberDbo> findNumberById(Integer id) {
        log.info("Invoked method findContactById");
        try {
            Optional<PhoneNumberDbo> optional = phonenumberRepository.findById(id);
            if (optional.isEmpty()) {
                throw new ArgumentNotFoundException(NO_PHONE_NUMBER_WITH_THIS_ID);
            }
        } catch (IllegalArgumentException e) {
            throw new IllegalStateException(e);
        }
        return phonenumberRepository.findById(id);
    }


    @Override
    public FullDetailsDto addNumberToExistingContact(FullDetailsDto fullDetailsDto) {
        log.info("Invoked method addNumberToExistingContact");

        if (phonenumberRepository.findAllPhoneNumbers().contains(fullDetailsDto.getPhoneNumber())) {
            throw new DuplicatePhoneNumberException(THIS_NUMBER_ALREADY_EXISTS);
        }

        Optional<PhoneNumberDbo> byId = phonenumberRepository.findById(fullDetailsDto.getId());
        if (byId.isPresent() && byId.get().getNumberType().equals(fullDetailsDto.getNumberType())) {
            byId.get().setPhoneNumber(fullDetailsDto.getPhoneNumber());
            phonenumberRepository.save(byId.get());
            return fullDetailsDto;
        }

        try {
            if (contactRepository.existsById(fullDetailsDto.getId())) {

                PhoneNumberDbo phonenumberDbo = PhoneNumberDbo.builder()
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
    public void deleteNumberById(Integer id) {
        log.info("Invoked method deleteNumberById");
        try {
            if (!phonenumberRepository.existsById(id)) {
                throw new ArgumentNotFoundException(NO_PHONE_NUMBER_WITH_THIS_ID);
            }
            phonenumberRepository.deleteById(id);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public Optional<PhoneNumberDbo> updateNumber(FullDetailsDto fullDetailsDto) {
        log.info("Invoked method updateNumber");
        try {
            if (!phonenumberRepository.existsById(fullDetailsDto.getId())) {
                throw new ArgumentNotFoundException(NO_PHONE_NUMBER_WITH_THIS_ID);
            }
            Optional<PhoneNumberDbo> phonenumberDbo = phonenumberRepository.findById(fullDetailsDto.getId());
            phonenumberDbo.get().setPhoneNumber(fullDetailsDto.getPhoneNumber());
            phonenumberDbo.get().setNumberType(fullDetailsDto.getNumberType());
            phonenumberRepository.save(phonenumberDbo.get());
            return phonenumberDbo;
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public CombinedDetailsDto findPhoneNumbersByContactId(int id) {
        log.info("Invoked method findPhoneNumbersByContactId");

        return CombinedDetailsDto.builder()
                .phoneNumbers(mapToPhoneNumberDto(phonenumberRepository.findPhoneNumbersByContactId(id)))
                .contactId(contactService.findContactById(id).getId())
                .firstname(contactService.findContactById(id).getFirstname())
                .lastname(contactService.findContactById(id).getLastname())
                .build();
    }

}
