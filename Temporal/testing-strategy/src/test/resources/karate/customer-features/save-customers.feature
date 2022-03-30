Feature: Save customers

  Background:
    * url baseUrl

  Scenario Outline: Save customer with id type '<idType>' and identification '<identification>'
    * def customer =
        """
          {
            "idType": '<idType>',
            "identification": '<identification>',
            "name": '<name>',
            "birthDate": '<birthDate>',
            "hasDriverLicense": <hasDriverLicense>
          }
        """
    * print customer

    Given path 'customers'
      And request customer
      And print customer
    When method POST
    Then status <status-code>

    * def expectedResponse = responseStatus == 200 ? '' : { status: '<status-code>', date: '#string', message: "<error-message>", description: '#string'}
    * match response == expectedResponse

    Examples:
      | idType          | identification  | name   | birthDate                     | hasDriverLicense | status-code | error-message                                      |
      | IDENTIFICATION  | test-1234567890 | Test-0 | 2002-03-28T00:00:00.000+00:00 | true             | 200         |                                                    |
      | SOCIAL_SECURITY | test-1234567891 | Test-1 | 2002-04-20T00:00:00.000+00:00 | true             | 200         |                                                    |
      | DRIVER_LICENSE  | test-1234567892 | Test-2 | 2002-05-10T00:00:00.000+00:00 | false            | 200         |                                                    |
      | IDENTIFICATION  | test-1234567893 | Test-3 | 2012-01-15T00:00:00.000+00:00 | true             | 409         | The customer age is not between age range allowed. |
      | SOCIAL_SECURITY | test-1234567894 | Test-4 | 1999-08-30T00:00:00.000+00:00 | false            | 409         | The customer does not have driver license.         |
      | IDENTIFICATION  | test-1234567890 | Test-0 | 2002-03-28T00:00:00.000+00:00 | true             | 409         | Customer with ID type 'IDENTIFICATION' and identification number 'test-1234567890' already exist. |