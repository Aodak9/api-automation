package org.bankTransaction.tests;

import org.bankTransaction.reporting.Reporter;
import org.bankTransaction.utils.tests.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class EmptyEndpointTest extends BaseTest {

    @Parameters({"endpoint"})
    @Test
    public void emptyEndpointTest(String endpoint){
        Reporter.info("Validate the users were obtained from the endpoint");
        Assert.assertEquals(getAllTheUsersStatus(endpoint), 200, "Users not obtained from the endpoint");
        Reporter.info("Validate endpoint is empty");
        Reporter.info("Records amount in the endpoint " + getAllTheUsers(endpoint).size());
        Reporter.info("Validate all the previous data was deleted if needed");
        Assert.assertTrue(deleteAllTheUsers(endpoint), "Users not deleted succesfully");
    }
}
