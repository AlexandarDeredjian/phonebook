package com.blubito.phonebook.dbo;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name ="contact")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ContactDbo {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "contact_id")
    private Integer id;

    @Column(name = "firstname")
    private String firstname;

    @Column(name = "lastname")
    private String lastname;

}
