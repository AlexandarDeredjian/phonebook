package com.blubito.phonebook.dto;

import com.blubito.phonebook.types.NumberType;
import lombok.*;

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
