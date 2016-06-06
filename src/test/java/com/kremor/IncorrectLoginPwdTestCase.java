package com.kremor;

import com.kremor.dataprovider.DataProviders;
import com.kremor.domainentities.User;
import com.kremor.pageObject.HomePage;
import com.kremor.pageObject.LoginPage;
import com.kremor.utility.ReportService;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

public class IncorrectLoginPwdTestCase extends BaseTestCase{
    private HomePage homePage;
    @Test(dataProvider = "DefaultUser", dataProviderClass = DataProviders.class)
    public final void incorrectLoginTestCase(User user) {
        LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
        String wrongPwd = user.getPassword() + "wrong";
        loginPage.login(user.getUsername(), wrongPwd);

        Assert.assertEquals(ReportService.elementIsDisplayed(loginPage.getErrorMsg(), "Error message"),
                true, "There are no error messages");
    }

}
