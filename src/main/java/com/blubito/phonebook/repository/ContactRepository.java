package com.blubito.phonebook.repository;

import com.blubito.phonebook.dbo.ContactDbo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface ContactRepository extends CrudRepository<ContactDbo,Integer> {

//    @Query(value = "SELECT * FROM Contact u WHERE u.contact_id =7", nativeQuery = true)
//    List<ContactDbo> find(@Param("id") String id);

}
