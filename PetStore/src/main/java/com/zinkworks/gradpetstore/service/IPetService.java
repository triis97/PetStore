package com.zinkworks.gradpetstore.service;

import com.zinkworks.gradpetstore.dto.PetDTO;
import com.zinkworks.gradpetstore.exceptions.PetNotFoundException;
import com.zinkworks.gradpetstore.model.Pet;

import java.util.List;

public interface IPetService {

    List<PetDTO> getAllPets();

    PetDTO getPetById(Long id) throws PetNotFoundException;

    List<PetDTO> getByShortName() throws PetNotFoundException;

    Long createPet(PetDTO pet);

    PetDTO updatePet(long id, PetDTO pet) throws PetNotFoundException;

    void deletePet(Long id) throws PetNotFoundException;

}
