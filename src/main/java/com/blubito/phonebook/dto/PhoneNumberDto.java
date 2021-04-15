package com.blubito.phonebook.dto;

import com.blubito.phonebook.types.NumberType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class PhoneNumberDto {

    private Integer id;

    private NumberType numberType;

    private String phoneNumber;
}
