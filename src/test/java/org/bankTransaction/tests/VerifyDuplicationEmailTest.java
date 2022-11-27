package org.bankTransaction.tests;

import org.bankTransaction.reporting.Reporter;
import org.bankTransaction.utils.tests.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

/**
 * Test class Verify Duplication Email Test, extended from {@link org.bankTransaction.utils.tests.BaseTest}
 */
public class VerifyDuplicationEmailTest extends BaseTest {

    /**
     * This test is to check if there's no email duplication in the endpoint and verify the HTTP Response code.
     * @param endpoint String
     */
    @Parameters({"endpoint"})
    @Test
    public void verifyDuplicationEmailTest(String endpoint){
        Reporter.info("Validate the users get obtain correctly from endpoint");
        Assert.assertEquals(getAllTheUsersStatus(endpoint), 200, "Users not obtained from endpoint");

        Reporter.info("Validate if there are not email duplicated in the endpoint");
        Assert.assertTrue(verifyEmailIfDuplicated(endpoint), "Duplicate emails exist");
    }
}
