package com.blubito.phonebook.service;

import com.blubito.phonebook.dbo.ContactDbo;
import com.blubito.phonebook.dbo.PhonenumberDbo;
import com.blubito.phonebook.dto.FullDetailsDto;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

public interface ContactService {

    Iterable<ContactDbo> getAllContacts();

    Iterable<PhonenumberDbo> getAllNumbers();

    Optional<ContactDbo> findContactById(Integer id);

    Optional<PhonenumberDbo> findNumberById(Integer id);

    FullDetailsDto createContact(FullDetailsDto fullDetailsDto);

    FullDetailsDto addNumberToExistingContact(FullDetailsDto fullDetailsDto);

    void deleteAll();

    void deleteContactById(Integer id);

    void deleteNumberById(Integer id);

    Optional<ContactDbo> updateName(FullDetailsDto fullDetailsDto);

    Optional<PhonenumberDbo> updateNumber(FullDetailsDto fullDetailsDto);

}
