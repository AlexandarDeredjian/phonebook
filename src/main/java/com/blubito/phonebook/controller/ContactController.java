package com.blubito.phonebook.controller;

import com.blubito.phonebook.dbo.ContactDbo;
import com.blubito.phonebook.dbo.PhonenumberDbo;
import com.blubito.phonebook.dto.FullDetailsDto;
import com.blubito.phonebook.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ContactController {

    @Autowired
    ContactService contactService;

    @GetMapping("/allContacts")
    public Iterable<ContactDbo> getAllContacts() {
        return contactService.getAllContacts();
    }

    @GetMapping("/allNumbers")
    public Iterable<PhonenumberDbo> getAllNumbers() {
        return contactService.getAllNumbers();
    }

    @GetMapping("/findContactById/{id}")
    public Optional<ContactDbo> findContactById(@PathVariable Integer id) {
        return contactService.findContactById(id);
    }

    @GetMapping("/findNumberById/{id}")
    public Optional<PhonenumberDbo> findNumberById(@PathVariable Integer id) {
        return contactService.findNumberById(id);
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

    @DeleteMapping("/deleteNumberById/{id}")
    public void deleteNumberById(@PathVariable Integer id) {
        contactService.deleteNumberById(id);
    }

    @PutMapping("/updateName")
    public Optional<ContactDbo> updateName(@RequestBody FullDetailsDto fullDetailsDto) {
        return contactService.updateName(fullDetailsDto);
    }

    @PutMapping("/updateNumber")
    public Optional<PhonenumberDbo> updateNumber(@RequestBody FullDetailsDto fullDetailsDto) {
        return contactService.updateNumber(fullDetailsDto);
    }
}
