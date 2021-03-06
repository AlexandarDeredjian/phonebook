package com.blubito.phonebook.repository;

import com.blubito.phonebook.dbo.PhoneNumberDbo;
import com.blubito.phonebook.dto.PhoneNumberDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface PhoneNumberRepository extends JpaRepository<PhoneNumberDbo,Integer> {

    @Query(value = "SELECT phonenumber FROM phonenumber;", nativeQuery = true)
    List<String> findAllPhoneNumbers();

    @Query(value = "SELECT * FROM phonenumber WHERE contact_id = ?#{[0]}", nativeQuery = true)
    List<PhoneNumberDbo> findPhoneNumbersByContactId(int id);
}
