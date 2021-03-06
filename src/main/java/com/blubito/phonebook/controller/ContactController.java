package com.blubito.phonebook.controller;

import com.blubito.phonebook.dbo.ContactDbo;
import com.blubito.phonebook.dbo.PhoneNumberDbo;
import com.blubito.phonebook.dto.CombinedDetailsDto;
import com.blubito.phonebook.dto.FullDetailsDto;
import com.blubito.phonebook.dto.PhoneNumberDto;
import com.blubito.phonebook.service.ContactService;
import com.blubito.phonebook.service.PhoneNumberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ContactController {

    @Autowired
    ContactService contactService;

    @Autowired
    PhoneNumberService phoneNumberService;

    @GetMapping("/allContacts")
    public Iterable<ContactDbo> getAllContacts() {
        return contactService.getAllContacts();
    }

    @GetMapping("/findContactById/{id}")
    public ContactDbo findContactById(@PathVariable Integer id) {
        return contactService.findContactById(id);
    }

    @PostMapping("/createContact")
    public FullDetailsDto createContact(@RequestBody FullDetailsDto fullDetailsDto) {
        return contactService.createContact(fullDetailsDto);
    }

    @DeleteMapping("/deleteAll")
    public void deleteAll() {
        contactService.deleteAll();
    }

    @DeleteMapping("/deleteContactById/{id}")
    public void deleteContactById(@PathVariable Integer id) {
        contactService.deleteContactById(id);
    }

    @PutMapping("/updateName")
    public Optional<ContactDbo> updateName(@RequestBody FullDetailsDto fullDetailsDto) {
        return contactService.updateName(fullDetailsDto);
    }

}
