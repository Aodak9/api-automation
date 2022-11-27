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

/**
 * Base class which is the father class for the test classes
 *
 */
public class BaseTest {

    protected int getUsersStatus;

    /**
     * method to get all the users that weve given from endpoint and put them on list as objects from the POJO - user {@link org.bankTransaction.pojo.User}.
     * @param endpoint String
     * @return List of {@link org.bankTransaction.pojo.User} from the given endpoint
     */
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

    /**
     * method to get all the user status code from the HTTP Response, all of this after getting {@link org.bankTransaction.pojo.User} from the endpoint
     * @param endpoint String
     * @return Http Response Code for the GET request
     */
    protected int getAllTheUsersStatus(String endpoint){
        getAllTheUsers(endpoint);
        return getUsersStatus;
    }

    /**
     * This method make a Post request to a POJO - user {@link org.bankTransaction.pojo.User} in the given endpoint, adding a new one.
     * @param endpoint String
     * @param user {@link org.bankTransaction.pojo.User}
     * @return HTTP status code Response respectively for Post request
     */
    protected int createTheUser(String endpoint, User user){
        Response response = given().contentType("application/json").body(user).when().post(endpoint);
        return response.getStatusCode();
    }

    /**
     * This method make a Put request to a POJO - user{@link org.bankTransaction.pojo.User} in the given endpoint.
     * @param endpoint String
     * @param user {@link org.bankTransaction.pojo.User}
     * @return HTTP status code Response respectively for Put request
     */
    protected int updateTheUser(String endpoint, User user){
        Response response = given().contentType("application/json").body(user).when().put(endpoint + "/" + user.getId());
        return response.getStatusCode();
    }

    /**
     * this method proceed to make a delete request to a POJO - user {@link org.bankTransaction.pojo.User} in the given endpoint.
     * @param endpoint String
     * @param user {@link org.bankTransaction.pojo.User}
     * @return HTTP status code Response respectively for delete request
     */
    protected int deleteTheUser(String endpoint, User user){
        Response response = given().contentType("application/json").when().delete(endpoint + "/" + user.getId());
        return response.getStatusCode();
    }


    /**
     * This method make an amount of POJO - user {@link org.bankTransaction.pojo.User} with random data created with Faker.
     * @param usersAmount int
     * @return List of created   {@link org.bankTransaction.pojo.User}
     */
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

    /**
     * This method add an amount of random POJO - user {@link org.bankTransaction.pojo.User} to a given endpoint.
     * @param endpoint String
     * @param usersAmount int
     * @return true if all Post request were successfully created (Http status code Response: 200), otherwise false
     */
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

    /**
     * This method make a delete request to all the POJO - user {@link org.bankTransaction.pojo.User} in a given endpoint.
     * @param endpoint String
     * @return true if all Delete request were successfully done (Http Response Code: 200), otherwise false
     */
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

    /**
     * This method makes an update of the info in a specific POJO - user {@link org.bankTransaction.pojo.User} in a given endpoint.
     * @param endpoint String
     * @param userId int
     * @return Http Response Code of the PUT request
     */
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

    /**
     * This method is to verify is there's an Email duplicated by the given endpoint.
     * @param endpoint String
     * @return true if there are no duplicate emails, otherwise false.
     */
    protected boolean verifyEmailIfDuplicated(String endpoint){
        final List<String> EmailsOfUsers = new ArrayList<>();
        getAllTheUsers(endpoint).forEach(user -> {EmailsOfUsers.add(user.getEmail());
        });
        Set<String> userSet = new HashSet<>(EmailsOfUsers);
        return userSet.size() == getAllTheUsers(endpoint).size();
    }
}
