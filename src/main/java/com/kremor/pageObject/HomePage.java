package com.kremor.pageObject;

import com.kremor.domainentities.User;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/*
 * encapsulates Main page of google search
 *      -
 */

public class HomePage  {
    private static String headerTitle;
    private final WebDriver driver;
    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    @FindBy(id= "lst-ib")
    private static WebElement searchField;

    //@FindBy(xpath = "//a[@class=\"gb_b gb_8a gb_R\"]")
    @FindBy(xpath = "//a[@class=\"gb_ab gb_Ec gb_tf gb_R\"]")
    private WebElement logoIcon;

    @FindBy(xpath = "//a[@class=\"gb_Fa gb_Fe gb_Ne gb_rb\"]")
    private WebElement singOutButton;

    public String getPageHeaderTitle() {
        return driver.getTitle();
    }

    public void logOut(User user) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("document.getElementsByClassName('gb_b gb_8a gb_R')[0].click();");
        String userStr = "//button[@value=\"" + user.getUsername() + "\"]";
        driver.findElement(By.xpath(userStr)).click();
        //singOutButton.click();
    }

}
