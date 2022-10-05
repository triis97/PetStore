package controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zinkworks.gradpetstore.PetStoreApplication;
import com.zinkworks.gradpetstore.controller.PetController;
import com.zinkworks.gradpetstore.dto.PetDTO;
import com.zinkworks.gradpetstore.dto.ResponseDTO;
import com.zinkworks.gradpetstore.service.IPetService;
import com.zinkworks.gradpetstore.service.PetService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
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

        RequestBuilder request = MockMvcRequestBuilders
                .post("/api/pets")
                .accept(MediaType.APPLICATION_JSON);

        ResponseDTO expectedResponse = ResponseDTO.builder().response(PetDTO.builder().build()).status(HttpStatus.CREATED.toString()).build();

        MvcResult result = mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(expectedResponse)))
                .andReturn();
        //JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
    }
}
