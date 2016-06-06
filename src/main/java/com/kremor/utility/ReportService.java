package com.kremor.utility;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.testng.Assert;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public final class ReportService {

    public static final int SIZE = 18;

    private ReportService() {
        super();
    }

    public static void takeScreenshot(String testCaseName, String message, WebDriver driver, Logger logger) throws IOException {

        String screenshotName = testCaseName + "ScreenShot.png";
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        final BufferedImage image = ImageIO.read(scrFile);

        File f = new File("target/screenshots/");
        if (!(f.exists() && f.isDirectory())) {
            File dir = new File("target/screenshots");
            dir.mkdir();
        }

        Graphics g = image.getGraphics();
        g.setFont(new Font("Arial Black", Font.PLAIN, SIZE));
        g.setColor(Color.GRAY);
        g.drawString("URL: " + message, 20, 20);
        g.dispose();

        ImageIO.write(image, "png", new File("target/screenshots/" + screenshotName));

        logger.info("");
        logger.warn("Screenshot captured.");
        logger.warn("Screenshot name: \"" + screenshotName + "\".");

    }

    public static void assertTrue(Boolean condition, String errorMessage, Logger logger) {
        if (!condition){
            logger.info("");
            logger.error("ACTUAL RESULT:");
            logger.error(errorMessage);
        }
        Assert.assertTrue(condition);
    }

    public static void assertFalse(Boolean condition, String errorMessage, Logger logger) {

        if (condition){
            logger.info("");
            logger.error("ACTUAL RESULT:");
            logger.error(errorMessage);
        }

        Assert.assertFalse(condition);

    }

    public static void assertEquals(String condition1, String condition2, String errorMessage, Logger logger) {

        if (!condition1.equalsIgnoreCase(condition2)){
            logger.info("");
            logger.error("ACTUAL RESULT:");
            logger.error(errorMessage);
            logger.error("Expected: \"" + condition2 + "\", but found: \"" + condition1 + "\".");
        }

        Assert.assertEquals(condition1, condition2);

    }

    public static void assertEquals(Integer condition1, Integer condition2, String errorMessage, Logger logger) {

        if (!condition1.equals(condition2)){
            logger.info("");
            logger.error("ACTUAL RESULT:");
            logger.error(errorMessage);
            logger.error("Expected: \"" + condition2 + "\", but found: \"" + condition1 + "\".");
        }

        Assert.assertEquals(condition1, condition2);

    }

    public static void testCaseURL(String id, Logger logger){

        logger.info("");
        logger.info("Test Case Confluence URL:");
        logger.info("https://edunav.atlassian.net/wiki/pages/viewpage.action?pageId=" + id);
        logger.info("");
    }

    public static boolean elementIsDisplayed(WebElement element, String elementName) {

        try {
            element.isDisplayed();
            return true;
        } catch (NoSuchElementException e) {
            return false;
        } catch (ElementNotVisibleException e) {
            Assert.assertTrue(false, "\"" + elementName + "\" was not visible.");
            return false;
        } catch (StaleElementReferenceException e) {
            return false;
        }

    }
}
