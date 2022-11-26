package org.bankTransaction.tests;

import org.bankTransaction.reporting.Reporter;
import org.bankTransaction.utils.tests.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class VerifyDuplicationEmailTest extends BaseTest {

    @Parameters({"endpoint"})
    @Test
    public void verifyDuplicationEmailTest(String endpoint){
        Reporter.info("Validate the users get obtain correctly from endpoint");
        Assert.assertEquals(getAllTheUsersStatus(endpoint), 200, "Users not obtained from endpoint");

        Reporter.info("Validate if there are not email duplicated in the endpoint");
        Assert.assertTrue(verifyEmailIfDuplicated(endpoint), "Duplicate emails exist");
    }
}
