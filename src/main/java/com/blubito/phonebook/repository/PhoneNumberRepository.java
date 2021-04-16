package com.blubito.phonebook.repository;

import com.blubito.phonebook.dbo.PhoneNumberDbo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface PhoneNumberRepository extends JpaRepository<PhoneNumberDbo,Integer> {

    @Query(value = "SELECT * FROM phonenumber;", nativeQuery = true)
    Set<PhoneNumberDbo> all();
}
