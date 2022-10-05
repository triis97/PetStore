package com.zinkworks.gradpetstore.utils;

import com.zinkworks.gradpetstore.dto.PetDTO;
import com.zinkworks.gradpetstore.model.Pet;

public class TestUtils {
    public static boolean contentEquals(Pet expected, Pet saved) {
        return expected.getId() == saved.getId() && expected.getName().equals(saved.getName()) && expected.getDescription().equals(saved.getDescription()) && expected.getType() == saved.getType();
    }

    public static boolean contentEquals(PetDTO expected, PetDTO saved) {
        return expected.getName().equals(saved.getName()) && expected.getDescription().equals(saved.getDescription()) && expected.getType() == saved.getType();
    }
}
