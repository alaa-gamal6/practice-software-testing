package com.toolshop.automation.tests.integration.home;

import com.toolshop.automation.base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class HomeIntegration extends BaseTest {

    @Test
    public void verifyCategoriesFilterAndSortIntegration() {
        driver.findElement(By.cssSelector("[data-test='nav-categories']")).click();
        driver.findElement(By.cssSelector("[data-test='nav-hand-tools']")).click();

        Select sortDropdown = new Select(driver.findElement(By.cssSelector("[data-test='sort']")));
        sortDropdown.selectByValue("name,asc");

        WebElement handSawCheckbox = driver.findElement(
                By.cssSelector("[data-test='category-01M8E3R0Q7EEH93CKSD9MQY3PR']")
        );
        handSawCheckbox.click();

        Assert.assertTrue(handSawCheckbox.isSelected(), "Hand Saw filter should be selected");
    }

    @Test
    public void verifySortAndPriceRangeIntegration() {
        Select sortDropdown = new Select(driver.findElement(By.cssSelector("[data-test='sort']")));
        sortDropdown.selectByValue("price,desc");

        WebElement priceSlider = driver.findElement(By.cssSelector("[aria-label='ngx-slider']"));
        Assert.assertTrue(priceSlider.isDisplayed(), "Price range slider should be displayed");

        List<WebElement> prices = driver.findElements(By.cssSelector("[data-test='product-price']"));
        Assert.assertFalse(prices.isEmpty(), "Expected products after sorting by Price (High - Low)");
    }

    @Test
    public void verifySearchAndBrandFilterIntegration() {
        WebElement searchInput = driver.findElement(By.cssSelector("[data-test='search-query']"));
        searchInput.clear();
        searchInput.sendKeys("Pliers");
        driver.findElement(By.cssSelector("[data-test='search-submit']")).click();

        WebElement forgeFlexCheckbox = driver.findElement(
                By.cssSelector("[data-test='brand-01M8E3R0BTFNYBVQ68ZKGWVMJ6']")
        );
        forgeFlexCheckbox.click();

        Assert.assertTrue(forgeFlexCheckbox.isSelected(), "ForgeFlex Tools filter should be selected");
    }

    @Test
    public void verifySearchAndSortIntegration() {
        WebElement searchInput = driver.findElement(By.cssSelector("[data-test='search-query']"));
        searchInput.clear();
        searchInput.sendKeys("Hammers");
        driver.findElement(By.cssSelector("[data-test='search-submit']")).click();

        Select sortDropdown = new Select(driver.findElement(By.cssSelector("[data-test='sort']")));
        sortDropdown.selectByValue("name,desc");

        List<WebElement> productNames = driver.findElements(By.cssSelector("[data-test='product-name']"));
        Assert.assertFalse(productNames.isEmpty(), "Expected products for 'Hammers' sorted Z-A");
    }

    @Test
    public void verifyHomePageAndProductDetailsIntegration() {
        driver.findElement(By.cssSelector("[data-test='product-01M8E3R0SRN3H5MY8JEZYN5K4P']")).click();

        Assert.assertTrue(
                driver.getCurrentUrl().contains("/product/01M8E3R0SRN3H5MY8JEZYN5K4P"),
                "Expected URL to contain product details path, actual URL: " + driver.getCurrentUrl()
        );
    }
}