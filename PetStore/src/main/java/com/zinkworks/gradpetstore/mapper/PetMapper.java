package com.zinkworks.gradpetstore.mapper;

import com.zinkworks.gradpetstore.dto.PetDTO;
import com.zinkworks.gradpetstore.model.Pet;

public class PetMapper {

    public static Pet toPet(PetDTO petDTO) {
        return Pet.builder()
                .name(petDTO.getName())
                .description(petDTO.getDescription())
                .type(petDTO.getType())
                .build();
    }

    public static PetDTO toPetDTO(Pet petDTO) {
        return PetDTO.builder()
                .name(petDTO.getName())
                .description(petDTO.getDescription())
                .type(petDTO.getType())
                .build();
    }
}
