package com.components;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class BasePage {
    private static final Logger LOG = LoggerFactory.getLogger(BasePage.class);

    WebDriver driver;

    public BasePage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(this.driver,this);
    }

    public WebElement waitForElement(WebElement element, String reason, int timeOut) {
        if (reason == null) {
            throw new IllegalArgumentException("The reason to wait must be specified");
        } else {
            Wait<WebDriver> wait = new FluentWait(this.driver)
                    .withTimeout(timeOut, TimeUnit.SECONDS)
                    .pollingEvery(1L,TimeUnit.SECONDS)
                    .ignoring(NoSuchElementException.class);
            long tStart = System.currentTimeMillis();
            WebElement el = (WebElement)wait.until(ExpectedConditions.visibilityOf(element));
            long tElapsed = System.currentTimeMillis() - tStart;
            LOG.info("the reason for wait: " + reason + " waited " + tElapsed + "ms");
            return el;
        }
    }

    public void getScreenShot(String screenshotName) throws IOException{
        String dayDate = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
        File source = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        String destination = System.getProperty("user.dir")+"/target/results"+screenshotName+"_"+dayDate+".png";
        FileUtils.copyFile(source,new File(destination));
        LOG.info("Captured screenshot has been placed at: "+destination);
    }

    public void mouseHoverToElement(WebElement element){
        Actions action = new Actions(driver) ;
        action.moveToElement(element).build().perform();
    }

}
