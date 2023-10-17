package com.zinkworks.gradpetstore.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.zinkworks.gradpetstore.exceptions.PetNotFoundException;
import com.zinkworks.gradpetstore.model.ParsedApi;
import com.zinkworks.gradpetstore.model.Pet;
import com.zinkworks.gradpetstore.service.IPetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.yaml.snakeyaml.Yaml;

import javax.websocket.server.PathParam;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

@RestController
@RequestMapping("/api")
public class PetController {

    @Autowired
    private IPetService petService;

//    @GetMapping("/pet")
//    public ResponseEntity<ResponseDTO> getAllPets() {
//        try {
//            return new ResponseEntity<>(
//                    ResponseDTO.builder()
//                            .response(petService.getAllPets()).build(),
//                    HttpStatus.OK);
//        } catch (Exception e) {
//            return new ResponseEntity<>(
//                    ResponseDTO.builder()
//                             .response(ErrorDTO.builder().message(e.getMessage()).build()).build(),
//                    HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }

    @GetMapping("/pet/{id}")
    public ResponseEntity<Pet> getById(@PathVariable("id") long id) {

        try {
            return new ResponseEntity<>(
                    petService.getPetById(id),
                    HttpStatus.OK);
        } catch (PetNotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/pet/findByStatus")
    public ResponseEntity<List<Pet>> getAllPetsByStatus(@Param("status") Pet.Status status) {
        try {
            return new ResponseEntity<>(
                    petService.getByStatus(status),
                    HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(
                    null,
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/pet/findByTags")
    public ResponseEntity<List<Pet>> getAllPetsByTag(@Param("tag") String tag) {
        try {
            return new ResponseEntity<>(
                    petService.getByTag(tag),
                    HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(
                    null,
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/pet/mock")
    public ResponseEntity<ParsedApi> getMockStructure() throws JsonProcessingException {
        String value = "{ \"paths\": { \"/pet\": [ { \"post\": { \"summary\": \"Add a new pet to the store\", \"scenario\": \"\\tScenario: Add a new pet to the store\\n\\t\\tGiven path 'pet'\\n\\t\\tAnd request {\\\"id\\\": 10, \\\"name\\\":\\\"doggie\\\", \\\"category\\\": {\\\"id\\\":\\\"1\\\", \\\"name\\\": \\\"Dogs\\\"}, \\\"photoUrls\\\": [\\\"www.example.com\\\"], \\\"tags\\\":[{\\\"id\\\":\\\"1\\\", \\\"name\\\": \\\"petTag\\\"}], \\\"status\\\": \\\"available\\\" }\\n\\t\\tWhen method post\\n\\t\\tThen status 201\\n\\t\\t* print response\\n\" } }, { \"put\": { \"summary\": \"Update an existing pet\", \"scenario\": \"\\tScenario: Update an existing pet\\n\\t\\tGiven path 'pet'\\n\\t\\tAnd request {\\\"id\\\": 10, \\\"name\\\":\\\"doggie\\\", \\\"category\\\": {\\\"id\\\":\\\"1\\\", \\\"name\\\": \\\"Dogs\\\"}, \\\"photoUrls\\\": [\\\"www.example.com\\\"], \\\"tags\\\":[{\\\"id\\\":\\\"1\\\", \\\"name\\\": \\\"petTag\\\"}], \\\"status\\\": \\\"available\\\" }\\n\\t\\tWhen method put\\n\\t\\tThen status 200\\n\\t\\t* print response\\n\" } } ], \"/pet/findByStatus\": [ { \"get\": { \"summary\": \"Finds Pets by status\", \"scenario\": \"\\tScenario: Finds Pets by status\\n\\t\\tGiven path 'pet/findByStatus'\\n\\t\\t* param status = 'available'\\n\\t\\tWhen method get\\n\\t\\tThen status 200\\n\\t\\t* print response\\n\\t\\tAnd match $[0].name == \\\"doggie\\\"\\n\" } } ], \"/pet/findByTags\": [ { \"get\": { \"summary\": \"Finds Pets by tags\", \"scenario\": \"\\tScenario: Finds Pets by tags\\n\\t\\tGiven path 'pet/findByTags'\\n\\t\\t* param tag = 'petTag'\\n\\t\\tWhen method get\\n\\t\\tThen status 200\\n\\t\\t* print response\\n\\t\\tAnd match $[0].name == \\\"doggie\\\"\\n\" } } ], \"/pet/{petId}\": [ { \"get\": { \"summary\": \"Find pet by ID\", \"scenario\": \"\\tScenario: Find pet by ID\\n\\t\\tGiven path 'pet/' + 10\\n\\t\\tWhen method get\\n\\t\\tThen status 200\\n\\t\\t* print response\\n\\t\\tAnd match $.name == \\\"doggie\\\"\\n\" } }, { \"delete\": { \"summary\": \"Deletes a pet\", \"scenario\": \"\\tScenario: Deletes a pet\\n\\t\\tGiven path 'pet/' + 10\\n\\t\\tWhen method delete\\n\\t\\tThen status 200\\n\\t\\t* print response\\n\" } } ], \"/user\": [ { \"post\": { \"summary\": \"Create user\", \"scenario\": \"\\tScenario: Create user\\n\\t\\tGiven path 'user'\\n\\t\\tAnd request {\\\"id\\\": 10, \\\"username\\\":\\\"theUser\\\", \\\"firstName\\\": \\\"John\\\", \\\"lastName\\\": \\\"James\\\", \\\"email\\\": \\\"john@email.com\\\", \\\"password\\\": \\\"12345\\\", \\\"phone\\\": \\\"12345\\\", \\\"userStatus\\\": 1 }\\n\\t\\tWhen method post\\n\\t\\tThen status 201\\n\\t\\t* print response\\n\" } } ], \"/user/{username}\": [ { \"get\": { \"summary\": \"Get user by user name\", \"scenario\": \"\\tScenario: Get user by user name\\n\\t\\tGiven path 'user/' + 'theUser'\\n\\t\\tWhen method get\\n\\t\\tThen status 200\\n\\t\\t* print response\\n\\t\\tAnd match $.username == \\\"theUser\\\"\\n\" } }, { \"delete\": { \"summary\": \"Delete user\", \"scenario\": \"\\tScenario: Delete user\\n\\t\\tGiven path 'user/' + 'theUser'\\n\\t\\tWhen method delete\\n\\t\\tThen status 200\\n\\t\\t* print response\\n\" } } ] }, \"schemas\": [ { \"name\": \"Order\", \"value\": { \"id\": 10, \"petId\": 198772, \"quantity\": 7, \"shipDate\": \"2022-01-01T00:00:00Z\", \"status\": \"approved\", \"complete\": true } }, { \"name\": \"Customer\", \"value\": { \"id\": 100000, \"username\": \"fehguy\", \"address\": [ { \"street\": \"437 Lytton\", \"city\": \"Palo Alto\", \"state\": \"CA\", \"zip\": \"94301\" } ] } }, { \"name\": \"Category\", \"value\": { \"id\": 1, \"name\": \"Dogs\" } }, { \"name\": \"User\", \"value\": { \"id\": 10, \"username\": \"theUser\", \"firstName\": \"John\", \"lastName\": \"James\", \"email\": \"john@email.com\", \"password\": \"12345\", \"phone\": \"12345\", \"userStatus\": 1 } }, { \"name\": \"Tag\", \"value\": { \"id\": 1, \"name\": \"petTag\" } }, { \"name\": \"Pet\", \"value\": { \"id\": 10, \"name\": \"doggie\", \"category\": { \"id\": 1, \"name\": \"Dogs\" }, \"photoUrls\": [ \"www.example.com\" ], \"tags\": [ { \"id\": 1, \"name\": \"petTag\" } ], \"status\": \"available\" } } ] }";
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode root = objectMapper.readTree(value);
        ArrayList<ParsedApi.Paths> paths = new ArrayList<>();
        ArrayList<ParsedApi.Schemas> schemas = new ArrayList<>();
        Iterator<String> pathFieldNames = root.get("paths").fieldNames();
        root.get("paths").forEach(child -> {
            String apiPath = pathFieldNames.next();
            child.forEach(method -> {
                Iterator<String> methodFieldNames = method.fieldNames();
                method.forEach(values -> {
                    String apiMethod = methodFieldNames.next();
                    paths.add(new ParsedApi.Paths(apiPath, apiMethod, values.get("scenario").asText(), values.get("summary").asText()));
                });
            });
        });
        root.get("schemas").forEach(child -> {
            schemas.add(new ParsedApi.Schemas(child.get("name").asText(), child.get("value").toString()));
        });

        ParsedApi parsedApi = new ParsedApi(paths, schemas);
        return new ResponseEntity<>(
                parsedApi,
                HttpStatus.OK);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/pet/mock")
    public ResponseEntity<ParsedApi> postMockStructure(@RequestParam("file") MultipartFile file) throws IOException {


        ObjectMapper yamlReader = new ObjectMapper(new YAMLFactory());
        Object obj = yamlReader.readValue(file.getInputStream(), Object.class);

        ObjectMapper jsonWriter = new ObjectMapper();
        String jsonValue = jsonWriter.writeValueAsString(obj);

        String value = "{ \"paths\": { \"/pet\": [ { \"post\": { \"summary\": \"Add a new pet to the store\", \"scenario\": \"\\tScenario: Add a new pet to the store\\n\\t\\tGiven path 'pet'\\n\\t\\tAnd request {\\\"id\\\": 10, \\\"name\\\":\\\"doggie\\\", \\\"category\\\": {\\\"id\\\":\\\"1\\\", \\\"name\\\": \\\"Dogs\\\"}, \\\"photoUrls\\\": [\\\"www.example.com\\\"], \\\"tags\\\":[{\\\"id\\\":\\\"1\\\", \\\"name\\\": \\\"petTag\\\"}], \\\"status\\\": \\\"available\\\" }\\n\\t\\tWhen method post\\n\\t\\tThen status 201\\n\\t\\t* print response\\n\" } }, { \"put\": { \"summary\": \"Update an existing pet\", \"scenario\": \"\\tScenario: Update an existing pet\\n\\t\\tGiven path 'pet'\\n\\t\\tAnd request {\\\"id\\\": 10, \\\"name\\\":\\\"doggie\\\", \\\"category\\\": {\\\"id\\\":\\\"1\\\", \\\"name\\\": \\\"Dogs\\\"}, \\\"photoUrls\\\": [\\\"www.example.com\\\"], \\\"tags\\\":[{\\\"id\\\":\\\"1\\\", \\\"name\\\": \\\"petTag\\\"}], \\\"status\\\": \\\"available\\\" }\\n\\t\\tWhen method put\\n\\t\\tThen status 200\\n\\t\\t* print response\\n\" } } ], \"/pet/findByStatus\": [ { \"get\": { \"summary\": \"Finds Pets by status\", \"scenario\": \"\\tScenario: Finds Pets by status\\n\\t\\tGiven path 'pet/findByStatus'\\n\\t\\t* param status = 'available'\\n\\t\\tWhen method get\\n\\t\\tThen status 200\\n\\t\\t* print response\\n\\t\\tAnd match $[0].name == \\\"doggie\\\"\\n\" } } ], \"/pet/findByTags\": [ { \"get\": { \"summary\": \"Finds Pets by tags\", \"scenario\": \"\\tScenario: Finds Pets by tags\\n\\t\\tGiven path 'pet/findByTags'\\n\\t\\t* param tag = 'petTag'\\n\\t\\tWhen method get\\n\\t\\tThen status 200\\n\\t\\t* print response\\n\\t\\tAnd match $[0].name == \\\"doggie\\\"\\n\" } } ], \"/pet/{petId}\": [ { \"get\": { \"summary\": \"Find pet by ID\", \"scenario\": \"\\tScenario: Find pet by ID\\n\\t\\tGiven path 'pet/' + 10\\n\\t\\tWhen method get\\n\\t\\tThen status 200\\n\\t\\t* print response\\n\\t\\tAnd match $.name == \\\"doggie\\\"\\n\" } }, { \"delete\": { \"summary\": \"Deletes a pet\", \"scenario\": \"\\tScenario: Deletes a pet\\n\\t\\tGiven path 'pet/' + 10\\n\\t\\tWhen method delete\\n\\t\\tThen status 400\\n\\t\\t* print response\\n\" } } ] }, \"schemas\": [ { \"name\": \"Category\", \"value\": { \"id\": \"1\", \"name\": \"Dogs\" } }, { \"name\": \"Tag\", \"value\": { \"id\": \"1\", \"name\": \"petTag\" } }, { \"name\": \"Pet\", \"value\": { \"id\": 10, \"name\": \"doggie\", \"category\": { \"id\": \"1\", \"name\": \"Dogs\" }, \"photoUrls\": [\"www.example.com\"], \"tags\": [ { \"id\": \"1\", \"name\": \"petTag\" } ], \"status\": \"available\" } } ] }{ \"paths\": { \"/pet\": [ { \"post\": { \"summary\": \"Add a new pet to the store\", \"scenario\": \"\\tScenario: Add a new pet to the store\\n\\t\\tGiven path 'pet'\\n\\t\\tAnd request {\\\"id\\\": 10, \\\"name\\\":\\\"doggie\\\", \\\"category\\\": {\\\"id\\\":\\\"1\\\", \\\"name\\\": \\\"Dogs\\\"}, \\\"photoUrls\\\": [\\\"www.example.com\\\"], \\\"tags\\\":[{\\\"id\\\":\\\"1\\\", \\\"name\\\": \\\"petTag\\\"}], \\\"status\\\": \\\"available\\\" }\\n\\t\\tWhen method post\\n\\t\\tThen status 201\\n\\t\\t* print response\\n\" } }, { \"put\": { \"summary\": \"Update an existing pet\", \"scenario\": \"\\tScenario: Update an existing pet\\n\\t\\tGiven path 'pet'\\n\\t\\tAnd request {\\\"id\\\": 10, \\\"name\\\":\\\"doggie\\\", \\\"category\\\": {\\\"id\\\":\\\"1\\\", \\\"name\\\": \\\"Dogs\\\"}, \\\"photoUrls\\\": [\\\"www.example.com\\\"], \\\"tags\\\":[{\\\"id\\\":\\\"1\\\", \\\"name\\\": \\\"petTag\\\"}], \\\"status\\\": \\\"available\\\" }\\n\\t\\tWhen method put\\n\\t\\tThen status 200\\n\\t\\t* print response\\n\" } } ], \"/pet/findByStatus\": [ { \"get\": { \"summary\": \"Finds Pets by status\", \"scenario\": \"\\tScenario: Finds Pets by status\\n\\t\\tGiven path 'pet/findByStatus'\\n\\t\\t* param status = 'available'\\n\\t\\tWhen method get\\n\\t\\tThen status 200\\n\\t\\t* print response\\n\\t\\tAnd match $[0].name == \\\"doggie\\\"\\n\" } } ], \"/pet/findByTags\": [ { \"get\": { \"summary\": \"Finds Pets by tags\", \"scenario\": \"\\tScenario: Finds Pets by tags\\n\\t\\tGiven path 'pet/findByTags'\\n\\t\\t* param tag = 'petTag'\\n\\t\\tWhen method get\\n\\t\\tThen status 200\\n\\t\\t* print response\\n\\t\\tAnd match $[0].name == \\\"doggie\\\"\\n\" } } ], \"/pet/{petId}\": [ { \"get\": { \"summary\": \"Find pet by ID\", \"scenario\": \"\\tScenario: Find pet by ID\\n\\t\\tGiven path 'pet/' + 10\\n\\t\\tWhen method get\\n\\t\\tThen status 200\\n\\t\\t* print response\\n\\t\\tAnd match $.name == \\\"doggie\\\"\\n\" } }, { \"delete\": { \"summary\": \"Deletes a pet\", \"scenario\": \"\\tScenario: Deletes a pet\\n\\t\\tGiven path 'pet/' + 10\\n\\t\\tWhen method delete\\n\\t\\tThen status 400\\n\\t\\t* print response\\n\" } } ] }, \"schemas\": [ { \"name\": \"Category\", \"value\": { \"id\": \"1\", \"name\": \"Dogs\" } }, { \"name\": \"Tag\", \"value\": { \"id\": \"1\", \"name\": \"petTag\" } }, { \"name\": \"Pet\", \"value\": { \"id\": 10, \"name\": \"doggie\", \"category\": { \"id\": \"1\", \"name\": \"Dogs\" }, \"photoUrls\": [\"www.example.com\"], \"tags\": [ { \"id\": \"1\", \"name\": \"petTag\" } ], \"status\": \"available\" } } ] }";
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode root = objectMapper.readTree(value);
        ArrayList<ParsedApi.Paths> paths = new ArrayList<>();
        ArrayList<ParsedApi.Schemas> schemas = new ArrayList<>();
        Iterator<String> pathFieldNames = root.get("paths").fieldNames();
        root.get("paths").forEach(child -> {
            String apiPath = pathFieldNames.next();
            child.forEach(method -> {
                Iterator<String> methodFieldNames = method.fieldNames();
                method.forEach(values -> {
                    String apiMethod = methodFieldNames.next();
                    paths.add(new ParsedApi.Paths(apiPath, apiMethod, values.get("scenario").asText(), values.get("summary").asText()));
                });
            });
        });
        root.get("schemas").forEach(child -> {
            schemas.add(new ParsedApi.Schemas(child.get("name").asText(), child.get("value").toString()));
        });

        ParsedApi parsedApi = new ParsedApi(paths, schemas);
        return new ResponseEntity<>(
                parsedApi,
                HttpStatus.OK);
    }

    @PostMapping("/pet")
    public ResponseEntity<Pet> createPet(@RequestBody Pet pet) {
        try {
            Long id = petService.createPet(pet);
            MultiValueMap<String, String> headers = new HttpHeaders();
            headers.add("PetId", id.toString());
            return new ResponseEntity<>(
                    pet,
                    headers,
                    HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(
                    null,
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    @PutMapping("/pet")
    public ResponseEntity<Pet> updatePet(@RequestBody Pet pet) {
        try {
            return new ResponseEntity<>(
                    petService.updatePet(pet),
                    HttpStatus.OK
            );
        } catch (Exception e) {
            return new ResponseEntity<>(
                    null,
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    @DeleteMapping("/pet/{id}")
    public ResponseEntity<Pet> deletePet(@PathVariable("id") long id) {
        try {
            petService.deletePet(id);
            return new ResponseEntity<>(
                    null,
                    HttpStatus.OK
            );
        } catch (Exception e) {
            return new ResponseEntity<>(
                    null,
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }
}
