package com.kremor.pageObject;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class LoginPage {

    private final WebDriver driver;

    public LoginPage(WebDriver driver){
        this.driver = driver;
    }
    private final Logger log = LogManager.getLogger(LoginPage.class);

    @FindBy(xpath = "//a[@class=\"gb_Me gb_Ha gb_rb\"]")
    private static WebElement sighInButton;

    @FindBy(id = "Email")
    private static WebElement usernameTextField;

    @FindBy(id = "Passwd")
    private static WebElement passwordTextField;

    //@FindBy(xpath = "//input[@value = 'Log in' and @type='submit']")
    @FindBy(id = "signIn")
    private static WebElement loginButton;

    @FindBy(xpath = "//*[@class=\"error-msg\"]")
    private static WebElement errorMsg;

    public WebElement getErrorMsg() {
        return errorMsg;
    }

    public final void setUsername(String username){
        //Enter Username.
        usernameTextField.sendKeys(username);
        usernameTextField.submit();
        log.info("Input Username \"" + username + "\".");
    }

    public final void setPassword(String password){
        //Enter password.
        passwordTextField.sendKeys(password);
        passwordTextField.submit();
        log.info("Input Password \"" + password + "\".");
    }

    public final void clickLoginButton(){
        //Click "Login" button.
        loginButton.click();
        log.info("Click \"Login\" button.");
    }

    public final void login(String username, String password) {
        sighInButton.click();
        setUsername(username);
        setPassword(password);
    }
}
