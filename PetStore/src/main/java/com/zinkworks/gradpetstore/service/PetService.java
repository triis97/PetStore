package com.zinkworks.gradpetstore.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zinkworks.gradpetstore.dto.PetDTO;
import com.zinkworks.gradpetstore.exceptions.PetNotFoundException;
import com.zinkworks.gradpetstore.mapper.PetMapper;
import com.zinkworks.gradpetstore.model.Pet;
import com.zinkworks.gradpetstore.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PetService implements IPetService {
    @Autowired
    PetRepository petRepository;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public Long createPet(PetDTO pet) {
        return petRepository.save(PetMapper.toPet(pet)).getId();
    }

    @Override
    public List<PetDTO> getAllPets() {

        return petRepository.findAll().stream().map(PetMapper::toPetDTO).collect(Collectors.toList());
    }

    @Override
    public PetDTO getPetById(Long id) throws PetNotFoundException {
        Pet pet = petRepository.findById(id).orElseThrow(() -> {
            //TODO log here
            return new PetNotFoundException("Unable to find a pet with that id");
        });
        return PetMapper.toPetDTO(pet);
    }

    @Override
    public List<PetDTO> getByShortName() {
        return petRepository.findByShortName().stream().map(PetMapper::toPetDTO).collect(Collectors.toList());
    }


    @Override
    public PetDTO updatePet(long id, PetDTO pet) throws PetNotFoundException {
        if (!petRepository.existsById(id)) {
            throw new PetNotFoundException("The id you are trying to update is not present");
        }

        Pet petUpdated = PetMapper.toPet(pet);
        petUpdated.setId(id);

        return PetMapper.toPetDTO(petRepository.save(petUpdated));
    }

    @Override
    public void deletePet(Long id) throws PetNotFoundException {
        if (!petRepository.existsById(id)) {
            throw new PetNotFoundException("The id you are trying to delete is not present");
        }
        petRepository.deleteById(id);
    }
}
