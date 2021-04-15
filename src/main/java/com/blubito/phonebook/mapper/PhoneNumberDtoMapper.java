package com.blubito.phonebook.mapper;

import com.blubito.phonebook.dbo.PhoneNumberDbo;
import com.blubito.phonebook.dto.PhoneNumberDto;

import java.util.ArrayList;
import java.util.List;

public class PhoneNumberDtoMapper {

    public static List<PhoneNumberDto> mapToPhoneNumberDto(List<PhoneNumberDbo> dboList){
        List<PhoneNumberDto> dtoList = new ArrayList<>();

        for (PhoneNumberDbo phoneNumberDbo : dboList){
            PhoneNumberDto phoneNumberDto = new PhoneNumberDto();
            phoneNumberDto.setId(phoneNumberDbo.getId());
            phoneNumberDto.setNumberType(phoneNumberDbo.getNumberType());
            phoneNumberDto.setPhoneNumber(phoneNumberDbo.getPhoneNumber());
            dtoList.add(phoneNumberDto);
        }

        return dtoList;
    }
}
