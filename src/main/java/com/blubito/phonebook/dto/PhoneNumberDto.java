package com.blubito.phonebook.dto;

import com.blubito.phonebook.types.NumberType;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PhoneNumberDto {

    private Integer id;

    private NumberType numberType;

    private String phoneNumber;
}
