package com.blubito.phonebook.dbo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name ="contact")
@Getter
@Setter
@NoArgsConstructor
public class ContactDbo {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "contact_id")
    private Integer id;

    @Column(name = "firstname")
    private String firstname;

    @Column(name = "lastname")
    private String lastname;

    public ContactDbo(String firstname, String lastname) {
        this.firstname = firstname;
        this.lastname = lastname;
    }
}
