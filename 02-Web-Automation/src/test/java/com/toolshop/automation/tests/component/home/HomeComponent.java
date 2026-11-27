package com.toolshop.automation.tests.component.home;

import com.toolshop.automation.base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class HomeComponent extends BaseTest {

    @Test
    public void verifyProductPricesDisplayedInDollars() {
        // TC-001: Verify that the product price is displayed in dollars ($)
        List<WebElement> prices = driver.findElements(
                By.cssSelector("app-overview .container a.card[data-test^='product-'] [data-test='product-price']")
        );
        Assert.assertFalse(prices.isEmpty(), "Expected product prices to be displayed on the home page");
        for (WebElement price : prices) {
            Assert.assertTrue(price.getText().trim().startsWith("$"),
                    "Expected price in dollars, but found: " + price.getText());
        }
    }

    @Test
    public void verifyNextPagePaginationFunctionality() {
        // TC-004: Verify next page arrow functionality in pagination
        WebElement nextBtn = driver.findElement(
                By.cssSelector("app-pagination ul.pagination button[data-test='pagination-next']")
        );
        nextBtn.click();

        WebElement activePageBtn = driver.findElement(
                By.cssSelector("app-pagination ul.pagination li.page-item.active button")
        );
        Assert.assertEquals(activePageBtn.getText().trim(), "2",
                "Expected active page to move from Page 1 to Page 2 after clicking Next");
    }

    @Test
    public void verifyOutOfStockLabelDisplayedOnOutOfStockProduct() {
        // TC-007: Verify "Out of stock" badge appears on out-of-stock product cards
        WebElement combinationPliersCard = driver.findElement(
                By.cssSelector("app-overview .container a.card[data-test='product-01M8E3R0S2P3PHXYZEKM9TXJQJ']")
        );
        WebElement outOfStockBadge = combinationPliersCard.findElement(
                By.cssSelector("[data-test='out-of-stock']")
        );
        Assert.assertTrue(outOfStockBadge.isDisplayed(),
                "Expected 'Out of stock' label to be visible on Combination Pliers card");
        Assert.assertEquals(outOfStockBadge.getText().trim(), "Out of stock");
    }

    @Test
    public void verifyCategoryAndBrandFiltersOnHomePage() {
        // Covers category filter (Hammer) & brand filter (ForgeFlex Tools)
        WebElement hammerFilter = driver.findElement(
                By.cssSelector("input[name='category_id'][data-test='category-01M8E3R0Q7EEH93CKSD9MQY3PQ']")
        );
        hammerFilter.click();
        Assert.assertTrue(hammerFilter.isSelected(), "Hammer category checkbox should be selected");

        WebElement forgeFlexFilter = driver.findElement(
                By.cssSelector("input[name='brand_id'][data-test='brand-01M8E3R0BTFNYBVQ68ZKGWVMJ6']")
        );
        forgeFlexFilter.click();
        Assert.assertTrue(forgeFlexFilter.isSelected(), "ForgeFlex Tools brand checkbox should be selected");
    }

    @Test
    public void verifySortProductsByPriceLowToHigh() {
        // Covers sorting by Price (Low - High)
        Select sortDropdown = new Select(driver.findElement(By.cssSelector("select[data-test='sort']")));
        sortDropdown.selectByValue("price,asc");

        List<WebElement> priceEls = driver.findElements(
                By.cssSelector("app-overview .container a.card[data-test^='product-'] [data-test='product-price']")
        );
        Assert.assertTrue(priceEls.size() > 1, "Expected multiple products to verify ascending price order");

        double prev = -1.0;
        for (WebElement el : priceEls) {
            double current = Double.parseDouble(el.getText().replace("$", "").trim());
            Assert.assertTrue(current >= prev,
                    "Expected ascending price order, but found " + current + " after " + prev);
            prev = current;
        }
    }
}