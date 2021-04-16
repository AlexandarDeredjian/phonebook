package com.blubito.phonebook.dbo;

import com.blubito.phonebook.types.NumberType;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

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

    @Column(name = "contact_id", insertable = false, updatable = false)
    private Integer contact_id;

    @ManyToOne
    @JoinColumn(name="contact_id")
    @JsonBackReference
    private ContactDbo contactDbo;

}
