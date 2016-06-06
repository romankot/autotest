package com.kremor.listeners;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverEventListener;

public class EventListener implements WebDriverEventListener {
    private By lastFindBy;
    private static final Logger LOGGER = LogManager.getLogger(EventListener.class);
    private String originalValue;

    @Override
    public final void beforeNavigateTo(String url, WebDriver driver) {
        LOGGER.info("Navigating to " + url);
    }

    @Override
    public void afterNavigateTo(String url, WebDriver driver) {

    }

    @Override
    public void beforeNavigateBack(WebDriver driver) {

    }

    @Override
    public void afterNavigateBack(WebDriver driver) {

    }

    @Override
    public void beforeNavigateForward(WebDriver driver) {

    }

    @Override
    public void afterNavigateForward(WebDriver driver) {

    }

    @Override
    public void beforeNavigateRefresh(WebDriver driver) {

    }

    @Override
    public void afterNavigateRefresh(WebDriver driver) {

    }

    @Override
    public final void beforeFindBy(By by, WebElement element, WebDriver driver) {
        LOGGER.info("Should be element by {}", by);
        lastFindBy = by;
    }

    @Override
    public final void afterFindBy(By by, WebElement element, WebDriver driver) {
        LOGGER.info("Element {} found ", by);
    }

    @Override
    public final void beforeClickOn(WebElement element, WebDriver driver) {
        LOGGER.info("Trying to click on: " + element.getText());
    }

    @Override
    public final void afterClickOn(WebElement element, WebDriver driver) {
       //LOGGER.info("Clicked on: " + element.getText());
    }

    @Override
    public final void beforeChangeValueOf(WebElement element, WebDriver driver) {
        originalValue = element.getText();
    }

    @Override
    public void afterChangeValueOf(WebElement element, WebDriver driver) {

    }

    @Override
    public void beforeScript(String script, WebDriver driver) {

    }

    @Override
    public void afterScript(String script, WebDriver driver) {

    }

    @Override
    public final void onException(Throwable throwable, WebDriver driver) {
        if (throwable.getClass().equals(NoSuchElementException.class)) {
            LOGGER.error("WebDriver error: Element not found " + lastFindBy);
        } else {
            LOGGER.info("Error occurred: " + throwable);
        }
    }
}
