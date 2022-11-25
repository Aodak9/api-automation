package org.bankTransaction.utils.tests;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.bankTransaction.pojo.User;
import org.bankTransaction.reporting.Reporter;

import java.util.ArrayList;
import java.util.List;

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
        Response response = given().contentType("application/json").when().put(endpoint + "" + user.getId());
        return response.getStatusCode();
    }

    protected int deleteTheUser(String endpoint, User user){
        Response response = given().contentType("application/json").when().delete(endpoint + "/" + user.getId());
        return response.getStatusCode();
    }

    



}
