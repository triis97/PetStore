package com.zinkworks.gradpetstore.service;

import com.zinkworks.gradpetstore.exceptions.PetNotFoundException;
import com.zinkworks.gradpetstore.model.Pet;

import java.util.List;

public interface IPetService {

    List<Pet> getAllPets();

    Pet getPetById(Long id) throws PetNotFoundException;

    List<Pet> getByStatus(Pet.Status status) throws PetNotFoundException;
    List<Pet> getByTag(String tag) throws PetNotFoundException;

    Long createPet(Pet pet);

    Pet updatePet(Pet pet) throws PetNotFoundException;

    void deletePet(Long id) throws PetNotFoundException;

}
