# api-automation
API AUTOMATION EMILIO NAVARRO


This project has been done with this respective endpoint:

https://6380ff43786e112fe1c00f8a.mockapi.io/users



To run all the tests execute the “Suite.xml” file located in resources package-Directory were the endpoint is handled as a parameter.



You can find all the details an user may have in the User class located in the Pojo package.



All the methods we need for the test are located in BaseTest class in utils —>tests.



Java Faker dependency is used on this project to create random data for the users we create.



 Empty Endpoint Test
This test check the endpoint is empty and proceed to delete previous data in case is needed, verifies the status code of the HTTP

We will receive this error if we have more than 30 users to delete: Cant delete user with id 31- Http Response Code: 429

 Initialize Pojo Test
Since one of the users will have a duplicate email in purpose, 11 users are originally created with random data, in order to ensure 10 of them will be created in the endpoint.

 Update User Test
This test is to check if we can do updates in the POJO - User and verify the HTTP Response code

the id #3 is the chosen for this test in case we need to change it, we just need to change the number in the UpdateTheUserTest class

If the endpoint is empty will throw error

 Verify Email Duplication Test
This test is to check if there's no email duplication in the endpoint and verify the HTTP Response code.

