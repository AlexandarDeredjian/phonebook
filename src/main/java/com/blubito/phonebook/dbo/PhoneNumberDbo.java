package com.blubito.phonebook.dbo;

import com.blubito.phonebook.types.NumberType;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

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

    @NotNull
    @Column(name = "number_type")
    private NumberType numberType;

    @Column(name = "contact_id")
    private Integer contact_id;

//    @ManyToOne
//    @JoinColumn(name="contact_id")
//    private ContactDbo contactDbo;

}
