package com.blubito.phonebook.service;

import com.blubito.phonebook.dbo.PhoneNumberDbo;
import com.blubito.phonebook.dto.FullDetailsDto;

import java.util.Optional;
import java.util.Set;

public interface PhoneNumberService {

    Iterable<PhoneNumberDbo> getAllNumbers();

    Optional<PhoneNumberDbo> findNumberById(Integer id);

    FullDetailsDto addNumberToExistingContact(FullDetailsDto fullDetailsDto);

    void deleteNumberById(Integer id);

    void updateNumber(FullDetailsDto fullDetailsDto);

    Set<PhoneNumberDbo> all();
}
