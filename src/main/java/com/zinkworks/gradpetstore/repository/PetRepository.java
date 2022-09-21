package com.zinkworks.gradpetstore.repository;

import com.zinkworks.gradpetstore.model.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PetRepository extends JpaRepository<Pet, Long> {
    @Query(value = "SELECT * FROM pets WHERE LENGTH(name)<=4", nativeQuery = true)
    List<Pet> findByShortName();

}
