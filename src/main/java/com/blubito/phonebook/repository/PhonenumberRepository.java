package com.blubito.phonebook.repository;

import com.blubito.phonebook.dbo.PhonenumberDbo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PhonenumberRepository extends JpaRepository<PhonenumberDbo,Integer> {

    @Query(value = "SELECT phonenumber FROM PHONENUMBER;", nativeQuery = true)
    List<String> findAllPhoneNumbers();

}
