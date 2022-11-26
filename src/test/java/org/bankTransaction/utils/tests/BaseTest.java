package org.bankTransaction.utils.tests;

import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.bankTransaction.pojo.User;
import org.bankTransaction.reporting.Reporter;

import java.util.*;

import static io.restassured.RestAssured.given;

public class BaseTest {

    protected int getUsersStatus;

    protected List<User> getAllTheUsers(String endpoint){
        RestAssured.baseURI = endpoint;
        RequestSpecification requestSpecification = given();
        Response response = requestSpecification.get("");
        JsonPath jsonPathEvaluator = response.jsonPath();
        List<User> allUsers = new ArrayList<>();

        try {
            allUsers = jsonPathEvaluator.getList(".",User.class);
        } catch (Exception e) {
            Reporter.error(String.valueOf(e));
        }

        getUsersStatus = response.getStatusCode();
        return allUsers;
    }

    protected int getAllTheUsersStatus(String endpoint){
        getAllTheUsers(endpoint);
        return getUsersStatus;
    }

    protected int createTheUser(String endpoint, User user){
        Response response = given().contentType("application/json").body(user).when().post(endpoint);
        return response.getStatusCode();
    }

    protected int updateTheUser(String endpoint, User user){
        Response response = given().contentType("application/json").body(user).when().put(endpoint + "/" + user.getId());
        return response.getStatusCode();
    }

    protected int deleteTheUser(String endpoint, User user){
        Response response = given().contentType("application/json").when().delete(endpoint + "/" + user.getId());
        return response.getStatusCode();
    }



    protected List<User> creationOfUsersRandomly(int usersAmount){
        List<User> users = new ArrayList<>();
        Faker faker = Faker.instance(new Locale("en-US"));

        for (double i = 0; i < usersAmount; i++) {
            users.add(new User(
               faker.name().firstName(),
               faker.name().lastName(),
               faker.number().numberBetween(0, 10000),
               faker.number().randomDouble(2, 100000, 100000000) ,
               faker.options().option("withdrawal", "payment", "invoice", "deposit"),
               faker.internet().emailAddress(),
               faker.random().nextBoolean(),
               faker.country().name(),
               faker.phoneNumber().cellPhone()
            ));
        }
        String emailDuplication = faker.internet().emailAddress();
        users.get(0).setEmail(emailDuplication);
        users.get(usersAmount - 1).setEmail(emailDuplication);
        return users;
    }

    protected boolean createUsersForRandomUsers(String endpoint, int usersAmount) {
        List<User> users = creationOfUsersRandomly(usersAmount);
        List<Boolean> theCreatedUsersForRandomStatus = new ArrayList<>();

        for (int i = 0; i < users.size(); i++) {
            boolean emailExist = false;
            for (int j = 0; j < users.size() && !emailExist; j++) {
                if (users.get(i).getEmail().equals(users.get(j).getEmail()) && i < j) {
                    Reporter.info("This email " + users.get(j).getEmail() + "is duplicated. The User wont be created");
                    emailExist = true;
                }
            }
            if (!emailExist) {
                theCreatedUsersForRandomStatus.add(createTheUser(endpoint, users.get(i)) == 201);
            }
        }

        return !theCreatedUsersForRandomStatus.contains(false);
    }

    protected boolean deleteAllTheUsers(String endpoint){
        List<Boolean> deletedUserStatus = new ArrayList<>();
        List<Integer> status = new ArrayList<>();
        List<User> users = getAllTheUsers(endpoint);
        if (users.size() > 0){
            for (int i = 0; i < users.size(); i++){
                status.add(deleteTheUser(endpoint, users.get(i)));
                deletedUserStatus.add(status.get(i) == 200);
                if (status.get(i) != 200){
                    Reporter.error("Cant delete user with id " + users.get(i).getId() + "- Http Response Code: " + status.get(i));

                }
            }
        }
        return !deletedUserStatus.contains(false);
    }

    protected int updateUserForRandom(String endpoint, int userId){
        User user = getAllTheUsers(endpoint).get(userId - 1);
        Faker faker = Faker.instance(new Locale("en-US"));

        user.setName(faker.name().firstName());
        user.setLastName(faker.name().lastName());
        user.setAccountNumber(faker.number().numberBetween(0, 10000));
        user.setAmount(faker.number().randomDouble(2, 100000, 100000000));
        user.setTransactionType(faker.options().option("withdrawal", "payment", "invoice", "deposit"));
        user.setEmail(faker.internet().emailAddress());
        user.setActive(faker.random().nextBoolean());
        user.setCountry(faker.country().name());
        user.setTelephone(faker.phoneNumber().cellPhone());

        return updateTheUser(endpoint, user);
    }

    protected boolean verifyEmailIfDuplicated(String endpoint){
        final List<String> EmailsOfUsers = new ArrayList<>();
        getAllTheUsers(endpoint).forEach(user -> {EmailsOfUsers.add(user.getEmail());
        });
        Set<String> userSet = new HashSet<>(EmailsOfUsers);
        return userSet.size() == getAllTheUsers(endpoint).size();
    }
}
