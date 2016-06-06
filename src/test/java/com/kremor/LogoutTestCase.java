package com.kremor;

import com.kremor.dataprovider.DataProviders;
import com.kremor.domainentities.User;
import com.kremor.pageObject.HomePage;
import com.kremor.pageObject.LoginPage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LogoutTestCase extends LoginTestCase {

    private HomePage homePage;
    @Test(dataProvider = "DefaultUser", dataProviderClass = DataProviders.class, dependsOnMethods = "loginTestCase")
    public final void logoutTestCase(User user) {
        homePage = PageFactory.initElements(driver, HomePage.class);
        homePage.logOut(user);

        Assert.assertEquals(homePage.getPageHeaderTitle(), "Google",
                "Could log out with credential: " + user.getUsername()+ " pass:" + user.getPassword());
    }
}
