package com.blubito.phonebook.service;

import com.blubito.phonebook.dbo.ContactDbo;
import com.blubito.phonebook.dto.FullDetailsDto;

import java.util.List;
import java.util.Optional;

public interface ContactService {

    Iterable<ContactDbo> getAllContacts();

    ContactDbo findContactById(Integer id);

    FullDetailsDto createContact(FullDetailsDto fullDetailsDto);

    void deleteAll();

    void deleteContactById(Integer id);

    Optional<ContactDbo> updateName(FullDetailsDto fullDetailsDto);
}
