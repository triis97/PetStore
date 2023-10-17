Feature: Pet Store Feature test

  Background:
    * url baseUrl

  Scenario: Add a new pet to the store
    Given path 'pet'
    And request {"id": 10, "name":"otra", "category": {"id":"1", "name": "Dogs"}, "photoUrls": ["www.example.com"], "tags":[{"id":"1", "name": "petTag"}], "status": "available" }
    When method post
    Then status 201
    * print response

  Scenario: Finds Pets by status
    Given path 'pet/findByStatus'
    * param status = 'available'
    When method get
    Then status 200
    * print response
    And match $[0].name == "otra"
#  Scenario: Add a new pet to the store
#    Given path 'pet'
#    And request {"id": 10, "name":"doggie", "category": {"id":"1", "name": "Dogs"}, "photoUrls": ["www.example.com"], "tags":[{"id":"1", "name": "petTag"}], "status": "available" }
#    When method post
#    Then status 201
#    * print response
#
#  Scenario: Update an existing pet
#    Given path 'pet'
#    And request {"id": 10, "name":"doggie", "category": {"id":"1", "name": "Dogs"}, "photoUrls": ["www.example.com"], "tags":[{"id":"1", "name": "petTag"}], "status": "available" }
#    When method put
#    Then status 200
#    * print response
#
#  Scenario: Finds Pets by status
#    Given path 'pet/findByStatus'
#    * param status = 'available'
#    When method get
#    Then status 200
#    * print response
#    And match $[0].name == "doggie"
#
#  Scenario: Finds Pets by tags
#    Given path 'pet/findByTags'
#    * param tag = 'petTag'
#    When method get
#    Then status 200
#    * print response
#    And match $[0].name == "doggie"
#
#  Scenario: Find pet by ID
#    Given path 'pet/' + 10
#    When method get
#    Then status 200
#    * print response
#    And match $.name == "doggie"
#
#  Scenario: Deletes a pet
#    Given path 'pet/' + 10
#    When method delete
#    Then status 200
#    * print response
