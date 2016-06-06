package com.kremor;

import com.kremor.dataprovider.DataProviders;
import com.kremor.domainentities.User;
import com.kremor.pageObject.HomePage;
import com.kremor.pageObject.LoginPage;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTestCase extends BaseTestCase {
    private HomePage homePage;
    @Test(dataProvider = "DefaultUser", dataProviderClass = DataProviders.class)
    public final void loginTestCase(User user) {
        LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
        loginPage.login(user.getUsername(), user.getPassword());
        //driver.findElement(By.xpath("//*[@class=\"error-msg\"]"));
        homePage = PageFactory.initElements(driver, HomePage.class);
        Assert.assertEquals(homePage.getPageHeaderTitle(), "Google",
                "Could not log in with credential: " + user.getUsername() + " pass:" + user.getPassword());
        //homePage.logOut(user);
        String str = "1";
    }
}
