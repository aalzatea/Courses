Feature: Find customers

  Background:
    * url baseUrl

  Scenario: Find all customers

    Given path 'customers'
    When method GET
    Then status 200
      And match $ == '#[3]'
      And match each $ contains {idType: '#notnull'}
      And match each $ contains {identification: '#string'}
      And match each $ contains {name: '#string'}
      And match each $ contains {birthDate: '#string'}
      And match each $ contains {hasDriverLicense: '#boolean'}

    * def customer = response[0]

    Given path 'customers', customer.idType, customer.identification
    When method GET
    Then status 200
     And match response == customer

  Scenario: Find customer with id type 'SOCIAL_SECURITY' and identification 'test-1234567891' should return data

    Given path 'customers', 'SOCIAL_SECURITY', 'test-1234567891'
    When method GET
    Then status 200
      And match $.idType == 'SOCIAL_SECURITY'
      And match response.identification == 'test-1234567891'

  Scenario: Find customer with id type 'DRIVER_LICENSE' and identification 'test' should return an error message
    * def errorJson = { status: '404', date: '#notnull', message: 'Customer not found.', description: '#string'}

    Given path 'customers', 'DRIVER_LICENSE', 'test'
    When method GET
    Then status 404
      And match $ == errorJson