package com.zinkworks.gradpetstore.controller;

import com.zinkworks.gradpetstore.dto.ErrorDTO;
import com.zinkworks.gradpetstore.dto.PetDTO;
import com.zinkworks.gradpetstore.dto.ResponseDTO;
import com.zinkworks.gradpetstore.exceptions.PetNotFoundException;
import com.zinkworks.gradpetstore.service.IPetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping("/api")
public class PetController {

    @Autowired
    private IPetService petService;

    @GetMapping("/pets")
    public ResponseEntity<ResponseDTO> getAllPets() {
        try {
            return new ResponseEntity<>(
                    ResponseDTO.builder()
                            .response(petService.getAllPets()).build(),
                    HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(
                    ResponseDTO.builder()
                             .response(ErrorDTO.builder().message(e.getMessage()).build()).build(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/pets/{id}")
    public ResponseEntity<ResponseDTO> getById(@PathVariable("id") long id) {

        try {
            return new ResponseEntity<>(
                    ResponseDTO.builder()
                            .response(petService.getPetById(id)).build(),
                    HttpStatus.OK);
        } catch (PetNotFoundException e) {
            return new ResponseEntity<>(ResponseDTO.builder()
                     .response(ErrorDTO.builder().message(e.getMessage()).build()).build(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/pets/shortname")
    public ResponseEntity<ResponseDTO> getAllPetsWithShortName() {
        try {
            return new ResponseEntity<>(
                    ResponseDTO.builder()
                            .response(petService.getByShortName())
                            .build(),
                    HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(
                    ResponseDTO.builder()
                             .response(ErrorDTO.builder().message(e.getMessage()).build()).build(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/pets")
    public ResponseEntity<ResponseDTO> createPet(@RequestBody PetDTO pet) {
        try {
            Long id = petService.createPet(pet);
            MultiValueMap<String, String> headers = new HttpHeaders();
            headers.add("PetId", id.toString());
            return new ResponseEntity<>(
                    ResponseDTO.builder()
                            .response(pet).build(),
                    headers,
                    HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(
                    ResponseDTO.builder()
                             .response(ErrorDTO.builder().message(e.getMessage()).build()).build(),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    @PutMapping("/pets/{id}")
    public ResponseEntity<ResponseDTO> updatePet(@PathVariable("id") long id, @RequestBody PetDTO pet) {
        try {
            return new ResponseEntity<>(
                    ResponseDTO.builder()
                            .response(petService.updatePet(id, pet)).build(),
                    HttpStatus.OK
            );
        } catch (Exception e) {
            return new ResponseEntity<>(
                    ResponseDTO.builder()
                             .response(ErrorDTO.builder().message(e.getMessage()).build()).build(),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    @DeleteMapping("/pets/{id}")
    public ResponseEntity<ResponseDTO> deletePet(@PathVariable("id") long id) {
        try {
            PetDTO pet = petService.getPetById(id);
            petService.deletePet(id);
            return new ResponseEntity<>(
                    ResponseDTO.builder()
                            .response(pet).build(),
                    HttpStatus.OK
            );
        } catch (Exception e) {
            return new ResponseEntity<>(
                    ResponseDTO.builder()
                            .response(ErrorDTO.builder().message(e.getMessage()).build()).build(),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }
}
