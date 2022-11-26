package org.bankTransaction.tests;


import org.bankTransaction.reporting.Reporter;
import org.bankTransaction.utils.tests.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class UpdateTheUserTest extends BaseTest {
    private int userId = 1;

    @Parameters({"endpoint"})
    @Test
    public void updateTheUserTest(String endpoint){
        Reporter.info("Validate the user was succesfully updated");
        Assert.assertEquals(updateUserForRandom(endpoint, userId), 200, "User not updated");
    }
}
