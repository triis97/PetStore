package com.zinkworks.gradpetstore.repository;

import com.zinkworks.gradpetstore.model.Pet;
import com.zinkworks.gradpetstore.util.AnimalTypes;
import com.zinkworks.gradpetstore.utils.TestUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
class PetRepositoryTest {

    @Autowired
    private PetRepository petRepository;

    @BeforeEach
    public void beforeEach() {
        petRepository.deleteAll();
    }

    @Test
    public void testCreate() {
        Pet pet = Pet.builder().name("test").description("test").type(AnimalTypes.BIRD).build();
        Pet savedPet = petRepository.save(pet);
        assertTrue(TestUtils.contentEquals(pet, savedPet), "Both entities should be the same");
    }

    @Test
    public void testGetByShortName() {
        Pet pet = Pet.builder().name("test").description("test").type(AnimalTypes.BIRD).build();
        Pet pet2 = Pet.builder().name("test2").description("test2").type(AnimalTypes.DOG).build();
        petRepository.save(pet);
        petRepository.save(pet2);

        List<Pet> petRetrieved = petRepository.findByShortName();

        assertTrue(petRetrieved.size() == 1, "The list should have just one element");
    }
}