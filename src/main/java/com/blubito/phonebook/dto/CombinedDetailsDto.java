package com.blubito.phonebook.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CombinedDetailsDto {

    private Integer contactId;
    private String firstname;
    private String lastname;
    private List<PhoneNumberDto> phoneNumbers;
}
