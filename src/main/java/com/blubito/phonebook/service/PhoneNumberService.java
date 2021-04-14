package com.blubito.phonebook.service;

import com.blubito.phonebook.dbo.PhoneNumberDbo;
import com.blubito.phonebook.dto.FullDetailsDto;

import java.util.Optional;

public interface PhoneNumberService {

    Iterable<PhoneNumberDbo> getAllNumbers();

    Optional<PhoneNumberDbo> findNumberById(Integer id);

    FullDetailsDto addNumberToExistingContact(FullDetailsDto fullDetailsDto);

    void deleteNumberById(Integer id);

    Optional<PhoneNumberDbo> updateNumber(FullDetailsDto fullDetailsDto);
}
