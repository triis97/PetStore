#Feature: Pet Store Feature test
#
#  Background:
#    * url baseUrl
#    * def petBody = read('classpath:integration/resources/createPet.json')
#
#  Scenario: Pet Store integration
#    #Create a Pet
#    Given path 'api/pets'
#    And request petBody
#    When method post
#    Then status 201
#    * print response
#    * print responseHeaders
#    * def petId = responseHeaders['PetId'][0]
#
#    Given path 'api/pets'
#    * petBody.name = "Second Pet"
#    And request petBody
#    When method post
#    Then status 201
#    * print response
#
#    #Get all Pet
#    Given path 'api/pets'
#    When method get
#    Then status 200
#    * json jsonResponse = response
#    * print response
#    And match $.response == '#[2]'
#
#    # Get Pet by Id
#    Given path 'api/pets/' + petId
#    When method get
#    Then status 200
#    * print response
#    And match $.response.name == "normal dog"
#
