package com.blubito.phonebook.controller;

import com.blubito.phonebook.dbo.PhoneNumberDbo;
import com.blubito.phonebook.dto.FullDetailsDto;
import com.blubito.phonebook.service.PhoneNumberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api")
public class PhoneNumberController {

    @Autowired
    PhoneNumberService phoneNumberService;

    @GetMapping("/allNumbers")
    public Iterable<PhoneNumberDbo> getAllNumbers() {
        return phoneNumberService.getAllNumbers();

    }

    @GetMapping("/findNumberById/{id}")
    public Optional<PhoneNumberDbo> findNumberById(@PathVariable Integer id) {
        return phoneNumberService.findNumberById(id);
    }

    @PostMapping("/addNumberToExistingContact")
    public FullDetailsDto addNumberToExistingContact(@RequestBody FullDetailsDto fullDetailsDto) {
        return phoneNumberService.addNumberToExistingContact(fullDetailsDto);
    }

    @DeleteMapping("/deleteNumberById/{id}")
    public void deleteNumberById(@PathVariable Integer id) {
        phoneNumberService.deleteNumberById(id);
    }

    @PutMapping("/updateNumber")
    public void updateNumber(@RequestBody FullDetailsDto fullDetailsDto) {
        phoneNumberService.updateNumber(fullDetailsDto);
    }

}
