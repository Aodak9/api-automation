package org.bankTransaction.tests;

import org.bankTransaction.reporting.Reporter;
import org.bankTransaction.utils.tests.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

/**
 * Test class to check Empty Endpoint Test, extended from {@link org.bankTransaction.utils.tests.BaseTest}
 */
public class EmptyEndpointTest extends BaseTest {

    /**
     * This test check the endpoint is empty and proceed to delete previous data in case is needed, verifies the status code of the HTTP
     * @param endpoint String
     */
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
