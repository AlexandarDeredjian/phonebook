package com.blubito.phonebook.mapper;

import com.blubito.phonebook.dbo.ContactDbo;
import com.blubito.phonebook.dbo.PhoneNumberDbo;
import com.blubito.phonebook.dto.FullDetailsDto;

import java.util.Set;


public class DboMapper {

    public static ContactDbo mapContactDbo(FullDetailsDto fullDetailsDto, Set<PhoneNumberDbo> phoneNumberDbos){

        PhoneNumberDbo phoneNumberDbo = PhoneNumberDbo.builder()
                .numberType(fullDetailsDto.getNumberType())
                .phoneNumber(fullDetailsDto.getPhoneNumber())
                .build();

        phoneNumberDbos.add(phoneNumberDbo);

        ContactDbo contactDbo = ContactDbo.builder()
                .id(fullDetailsDto.getId())
                .firstname(fullDetailsDto.getFirstname())
                .lastname(fullDetailsDto.getLastname())
                .phoneNumbers(phoneNumberDbos)
                .build();

        return contactDbo;
    }
}
