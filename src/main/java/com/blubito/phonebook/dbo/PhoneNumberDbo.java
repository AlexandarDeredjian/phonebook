package com.blubito.phonebook.dbo;

import com.blubito.phonebook.types.NumberType;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "phonenumber")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PhoneNumberDbo {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "phonenumber_id")
    private Integer id;

    @Column(name = "phonenumber")
    private String phoneNumber;

    @Column(name = "number_type")
    private NumberType numberType;

    @Column(name = "contact_id")
    private Integer contact_id;

}
