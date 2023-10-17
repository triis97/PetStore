package com.zinkworks.gradpetstore.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zinkworks.gradpetstore.exceptions.PetNotFoundException;
import com.zinkworks.gradpetstore.model.Pet;
import com.zinkworks.gradpetstore.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PetService implements IPetService {
    @Autowired
    PetRepository petRepository;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public Long createPet(Pet pet) {
        return petRepository.save(pet).getId();
    }

    @Override
    public List<Pet> getAllPets() {

        return petRepository.findAll();
    }

    @Override
    public Pet getPetById(Long id) throws PetNotFoundException {
        Pet pet = petRepository.findById(id).orElseThrow(() -> {
            //TODO log here
            return new PetNotFoundException("Unable to find a pet with that id");
        });
        return pet;
    }

    @Override
    public List<Pet> getByStatus(Pet.Status status) {
        return petRepository.findByStatus(status);
    }

    @Override
    public List<Pet> getByTag(String tag) {
        return petRepository.getByTagsName(tag);
    }


    @Override
    public Pet updatePet(Pet pet) throws PetNotFoundException {
        if (!petRepository.existsById(pet.getId())) {
            throw new PetNotFoundException("The id you are trying to update is not present");
        }

        return petRepository.save(pet);
    }

    @Override
    public void deletePet(Long id) throws PetNotFoundException {
        if (!petRepository.existsById(id)) {
            throw new PetNotFoundException("The id you are trying to delete is not present");
        }
        petRepository.deleteById(id);
    }
}
