package org.bankTransaction.tests;


import org.bankTransaction.reporting.Reporter;
import org.bankTransaction.utils.tests.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

/**
 * Test class Update The User Test, extended from {@link org.bankTransaction.utils.tests.BaseTest}
 */
public class UpdateTheUserTest extends BaseTest {
    private int userId = 3;

    /**
     * This test is to check if we can do updates in the POJO - User {@link org.bankTransaction.pojo.User} and verify the HTTP Response code
     * @param endpoint String
     */
    @Parameters({"endpoint"})
    @Test
    public void updateTheUserTest(String endpoint){
        Reporter.info("Validate the user was succesfully updated");
        Assert.assertEquals(updateUserForRandom(endpoint, userId), 200, "User not updated");
    }
}
