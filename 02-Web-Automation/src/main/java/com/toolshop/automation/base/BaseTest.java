package com.toolshop.automation.base;

import com.toolshop.automation.driver.DriverFactory;
import com.toolshop.automation.utils.WaitUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

public class BaseTest {

    protected static final String BASE_URL = "https://practicesoftwaretesting.com/";

    protected WebDriver driver;

    @BeforeClass(alwaysRun = true)
    public void beforeClass() {
        driver = DriverFactory.initDriver();
    }

    @BeforeMethod(alwaysRun = true)
    public void beforeMethod() {
        driver.manage().deleteAllCookies();
        driver.navigate().to(BASE_URL);
        ((JavascriptExecutor) driver)
                .executeScript("window.localStorage.clear(); window.sessionStorage.clear();");
        driver.navigate().to(BASE_URL);
        WaitUtils.sleep(2000);
    }

    @AfterMethod(alwaysRun = true)
    public void afterMethod() {
        WaitUtils.sleep(1000);
    }

    @AfterClass(alwaysRun = true)
    public void afterClass() {
        DriverFactory.quitDriver();
    }
}