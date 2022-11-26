package org.bankTransaction.tests;

import org.bankTransaction.reporting.Reporter;
import org.bankTransaction.utils.tests.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class InitializeThePojoTest extends BaseTest {
    private int usersToCreate = 15;

    @Parameters({"endpoint"})
    @Test
    public void initializeThePojoTest(String endpoint){
        Reporter.info("Validate all the users were created succesfully");
        Assert.assertTrue(createUsersForRandomUsers(endpoint, usersToCreate), "Users not created");
    }
}
