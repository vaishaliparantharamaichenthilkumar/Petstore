# Petstore
Automated Tests for PetStore

## Summary
This project contains the Automated tests for Petstore which create, update and delete a Pet information.

## Swagger Document
http://petstore.swagger.io/#/pet

## Test Case Details
| S.No | TestCase Id | Scenario | Methods involved |
| ------ | ------ | ------ | ------ |
| 1. | TestCase_001 | To create a Pet for PetStore and validate the Pet details are created correctly. | POST |
| 2. | TestCase_002 | To update a Pet for PetStore and validate the Pet details are updated correctly. | PUT |
| 3. | TestCase_003 | To Delete a Pet for PetStore and validate the Pet details are deleted correctly. | DELETE, GET |

## Run Command
* Git Clone the Automated Test Scripts from https://github.com/vaishaliparantharamaichenthilkumar/Petstore.git
* Import the test scripts to IntelliJ and go the Terminal
* Use the following command to execute the Automated test scripts from Termianl

`mvn clean -Dtest=com.petstore.tests.functionaltests.PetStoreTests test`

### Detailed Explanation on the Syntax:

mvn clean -Dtest=<module/class path where the tests are available> test


