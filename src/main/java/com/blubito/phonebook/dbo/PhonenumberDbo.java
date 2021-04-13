package com.blubito.phonebook.dbo;

import com.blubito.phonebook.types.NumberType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "phonenumber")
@Getter
@Setter
@NoArgsConstructor
public class PhonenumberDbo {

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

    public PhonenumberDbo(String phonenumber, NumberType numberType) {
        this.phoneNumber = phoneNumber;
        this.numberType = numberType;
        this.contact_id = null;
    }
}
