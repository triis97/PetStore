package com.zinkworks.gradpetstore.repository;

import com.zinkworks.gradpetstore.model.Pet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PetRepository extends JpaRepository<Pet, Long> {
//    @Query(value = "SELECT * FROM pets WHERE status<=:status", nativeQuery = true)
    List<Pet> findByStatus(Pet.Status status);

    List<Pet> getByTagsName(String tag);

}
