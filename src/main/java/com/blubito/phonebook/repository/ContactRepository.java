package com.blubito.phonebook.repository;

import com.blubito.phonebook.dbo.ContactDbo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepository extends CrudRepository<ContactDbo,Integer> {

}
