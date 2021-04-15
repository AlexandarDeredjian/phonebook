package com.blubito.phonebook.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
public class CombinedDetailsDto {

    private Integer contactId;
    private String firstname;
    private String lastname;
    private List<PhoneNumberDto> phoneNumbers;
}
