package com.blubito.phonebook.dbo;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Set;

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

    @NotEmpty
    @Column(name = "firstname")
    private String firstname;

    @NotEmpty
    @Column(name = "lastname")
    private String lastname;

//    @OneToMany(cascade=CascadeType.ALL)
//    @JoinColumn(name="contact_id")
//    private Set<PhoneNumberDbo> phoneNumbers;


}
