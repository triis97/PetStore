# PetStore

## Description

Small application using spring-boot.
The application main objective is manage a DB of Pets through a rest api,
for that we have to develop endpoints to create, retrieve, update and delete Pets from the DB.

For data storage we are going to use an in-memory DB.

## API Reference

### Pet Store API: /api

### Create Pet

```http
  Post /api/pets
```

##### Request

```json
{
  "name": "dog",
  "description": "Just a normal dog",
  "type": "DOG"
}
```

##### Header

| Key       | Value |
|-----------|-------|
| PetId     | {id}  |

##### Response

```json
{
  "response": {
    "name": "dog",
    "description": "Just a normal dog",
    "type": "DOG"
  },
  "status": "201 CREATED"
}
```

### Get All Pet

```http
  GET /api/pets
```

##### Response

```json
{
  "response": [
    {
      "name": "dog",
      "description": "Just a normal dog",
      "type": "DOG"
    }
  ],
  "status": "200 OK"
}
```

### Get Pet by Id

```http
  GET /api/pets/{id}
```

##### Response

```json
{
  "response": {
    "name": "dog",
    "description": "Just a normal dog",
    "type": "DOG"
  },
  "status": "200 OK"
}
```

### Get Pet by Short Name

```http
  GET /api/pets/{id}
```

##### Response

```json
{
  "response": [
    {
      "name": "dog",
      "description": "Just a normal dog",
      "type": "DOG"
    }
  ],
  "status": "200 OK"
}
```

### Update Pet

```http
  PUT /api/pets/{id}
```

##### Request

```json
{
  "name": "dog",
  "description": "updated description",
  "type": "DOG"
}
```

##### Response

```json
{
  "response": [
    {
      "name": "dog",
      "description": "updated description",
      "type": "DOG"
    }
  ],
  "status": "200 OK"
}
```

### Delete Pet

```http
  DELETE /api/pets/{id}
```

##### Response

```json
{
  "response": {
    "name": "dog",
    "description": "Just a normal dog",
    "type": "DOG"
  },
  "status": "200 OK"
}
```

### Postman Collection

- Find collection of calls that can be used in Postman in src/main/resources

### Useful links

- [Spring overview](https://spring.io/projects/spring-boot)
- [@Autowire Dependency Injection](https://www.baeldung.com/spring-autowire)
- [Dto Pattern](https://www.baeldung.com/java-dto-pattern)
- [JPA documentation](https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#repositories)




