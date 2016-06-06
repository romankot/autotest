package com.kremor;

import com.kremor.listeners.EventListener;
import com.kremor.utility.Constants;
import com.kremor.utility.ReportService;
import com.kremor.utility.WebDriverHolder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public abstract class BaseTestCase {

    private String baseUrl = Constants.LOGIN_URL;
    private String testCaseName = this.getClass().toString().substring(this.getClass().toString().lastIndexOf(".") + 1);
    private final long  timeout = 5;
    @SuppressWarnings("CheckStyle")
    public static WebDriver driver;
    private static final Logger LOGGER = LogManager.getLogger(BaseTestCase.class);

    @BeforeSuite
    public final void startTestSuite(ITestContext context) {
        LOGGER.info("=================================================================");
        LOGGER.info("Test suite started.");
    }

    @BeforeTest
    public final void launchBrowser() {
        ProfilesIni profile = new ProfilesIni();
        FirefoxProfile ffprofile = profile.getProfile("");
        driver = new FirefoxDriver(ffprofile);
        EventFiringWebDriver eventFiringWebDriver = new EventFiringWebDriver(driver);
        eventFiringWebDriver.register(new EventListener());
        WebDriverHolder.setDriver(eventFiringWebDriver);
        driver = WebDriverHolder.getDriver();
        driver.manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);
        driver.get(baseUrl);
    }

    @AfterMethod
    public final void screenshotOnTestFailure(ITestResult testResult) throws IOException {
        if (testResult.getStatus() == ITestResult.FAILURE) {
            ReportService.takeScreenshot(testCaseName, driver.getCurrentUrl(), driver, LOGGER);
            LOGGER.info("");
            LOGGER.error("TestCase: \"" + testCaseName + "\" FAILED.");
            LOGGER.info("");
            // MultithreadedConsoleAppender.printThreadEvents();
        }
    }
    @AfterSuite
    public final void terminateBrowser() {
        driver.quit();
        LOGGER.info("Close browser.");
    }

}
