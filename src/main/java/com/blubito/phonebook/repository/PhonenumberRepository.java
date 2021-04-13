package com.blubito.phonebook.repository;

import com.blubito.phonebook.dbo.PhonenumberDbo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhonenumberRepository extends JpaRepository<PhonenumberDbo,Integer> {
}
