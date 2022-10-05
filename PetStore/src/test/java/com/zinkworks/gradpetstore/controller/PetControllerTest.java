package com.zinkworks.gradpetstore.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zinkworks.gradpetstore.dto.ErrorDTO;
import com.zinkworks.gradpetstore.dto.PetDTO;
import com.zinkworks.gradpetstore.dto.ResponseDTO;
import com.zinkworks.gradpetstore.exceptions.PetNotFoundException;
import com.zinkworks.gradpetstore.service.IPetService;
import com.zinkworks.gradpetstore.util.AnimalTypes;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PetController.class)
public class PetControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IPetService petService;

    @Test
    public void createPetSuccessful() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        when(petService.createPet(any())).thenReturn(1L);

        PetDTO petDTO = PetDTO.builder().name("test").description("test").type(AnimalTypes.BIRD).build();

        RequestBuilder request = MockMvcRequestBuilders
                .post("/api/pets")
                .content(objectMapper.writeValueAsString(petDTO))
                .header("Content-Type", "application/json");

        ResponseDTO expectedResponse = ResponseDTO.builder().response(petDTO).build();

        mockMvc.perform(request)
                .andExpect(status().isCreated())
                .andExpect(content().json(objectMapper.writeValueAsString(expectedResponse)))
                .andReturn();
    }

    @Test
    public void getPeByIdError() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        when(petService.getPetById(any())).thenThrow(new PetNotFoundException("Pet not found test"));

        ErrorDTO errorDTO = ErrorDTO.builder().message("Pet not found test").build();

        RequestBuilder request = MockMvcRequestBuilders
                .get("/api/pets/{id}", 1)
                .header("Content-Type", "application/json");

        ResponseDTO expectedResponse = ResponseDTO.builder().response(errorDTO).build();

        mockMvc.perform(request)
                .andExpect(status().is5xxServerError())
                .andExpect(content().json(objectMapper.writeValueAsString(expectedResponse)))
                .andReturn();
    }

    @Test
    public void getPeByIdSuccessful() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        PetDTO petDTO = PetDTO.builder().name("test").description("test").type(AnimalTypes.BIRD).build();

        when(petService.getPetById(any())).thenReturn(petDTO);

        RequestBuilder request = MockMvcRequestBuilders
                .get("/api/pets/{id}", 1)
                .header("Content-Type", "application/json");

        ResponseDTO expectedResponse = ResponseDTO.builder().response(petDTO).build();

        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(expectedResponse)))
                .andReturn();
    }
}
