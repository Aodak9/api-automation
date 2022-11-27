package org.bankTransaction.tests;

import org.bankTransaction.reporting.Reporter;
import org.bankTransaction.utils.tests.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

/**
 * Test class to Initialize Pojo Test, extended from {@link org.bankTransaction.utils.tests.BaseTest}
 */
public class InitializeThePojoTest extends BaseTest {
    private int usersToCreate = 15;

    /**
     * POJO - User class is added to the endopoint and confirm the HTTP response code
     * {@link org.bankTransaction.pojo.User}
     * @param endpoint String
     */

    @Parameters({"endpoint"})
    @Test
    public void initializeThePojoTest(String endpoint){
        Reporter.info("Validate all the users were created succesfully");
        Assert.assertTrue(createUsersForRandomUsers(endpoint, usersToCreate), "Users not created");
    }
}
