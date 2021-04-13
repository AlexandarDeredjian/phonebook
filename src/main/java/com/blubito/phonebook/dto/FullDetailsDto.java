package com.blubito.phonebook.dto;

import com.blubito.phonebook.types.NumberType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class FullDetailsDto {

    private Integer id;
    private String firstname;
    private String lastname;
    private String phoneNumber;
    private NumberType numberType;


}
