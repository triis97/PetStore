package com.zinkworks.gradpetstore.service;


import com.zinkworks.gradpetstore.PetStoreApplication;
import com.zinkworks.gradpetstore.dto.PetDTO;
import com.zinkworks.gradpetstore.model.Pet;
import com.zinkworks.gradpetstore.repository.PetRepository;
import com.zinkworks.gradpetstore.util.AnimalTypes;
import com.zinkworks.gradpetstore.utils.TestUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.event.annotation.BeforeTestClass;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = PetStoreApplication.class)
class PetServiceTest {

    @InjectMocks
    PetService petService;

    @Mock
    PetRepository petRepository;


    @Test
    void createPet() {
        when(petRepository.save(any())).thenReturn(getPet(1L, "test", "desc", AnimalTypes.BIRD));

        PetDTO petDTO = PetDTO.builder()
                .name("test")
                .description("test")
                .type(AnimalTypes.BIRD)
                .build();

        Long result = petService.createPet(petDTO);
        assertEquals(1, result, "The returned Id should be 1");

    }

    @Test
    void getAllPets() {
        when(petRepository.findAll()).thenReturn(Arrays.asList(
                getPet(1L, "test", "desc", AnimalTypes.BIRD),
                getPet(2L, "test", "desc", AnimalTypes.DOG))
        );

        PetDTO expectedPetDTO = PetDTO.builder()
                .name("test")
                .description("desc")
                .type(AnimalTypes.BIRD)
                .build();

        List<PetDTO> result = petService.getAllPets();
        assertEquals(2, result.size(), "The returned size should be 2");
        assertTrue(TestUtils.contentEquals(expectedPetDTO, result.get(0)), "Content should match");
    }

    @Test
    void getPetById() {
    }

    @Test
    void getByShortName() {
    }

    @Test
    void updatePet() {
    }

    @Test
    void deletePet() {
    }

    private  Pet getPet(Long id, String name, String desc, AnimalTypes animalTypes){
        return Pet.builder().id(id).name(name).description(desc).type(animalTypes).build();
    }
}