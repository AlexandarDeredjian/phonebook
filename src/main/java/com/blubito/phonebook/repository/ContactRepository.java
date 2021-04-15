package com.blubito.phonebook.repository;

import com.blubito.phonebook.dbo.ContactDbo;
import com.blubito.phonebook.dbo.PhoneNumberDbo;
import com.blubito.phonebook.dto.FullDetailsDto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactRepository extends CrudRepository<ContactDbo,Integer> {

}
