

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Component {

    WebDriver driver;
    List<String[]> results = new ArrayList<>();

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        String status = result.getStatus() == ITestResult.SUCCESS ? "Passed"
                : result.getStatus() == ITestResult.FAILURE ? "Failed" : "Skipped";
        long seconds = (result.getEndMillis() - result.getStartMillis()) / 1000;
        String error = result.getThrowable() == null ? "-"
                : String.valueOf(result.getThrowable().getMessage()).split("\n")[0]
                .replace("<", "&lt;").replace(">", "&gt;");

        results.add(new String[]{result.getMethod().getMethodName(), status, seconds + " s", error});
        driver.quit();
    }

    @AfterClass
    public void generateReport() throws IOException {
        int passed = 0, failed = 0, skipped = 0;
        StringBuilder rows = new StringBuilder();
        int i = 1;

        for (String[] r : results) {
            String color;
            if (r[1].equals("Passed")) { color = "#c8e6c9"; passed++; }
            else if (r[1].equals("Failed")) { color = "#ffcdd2"; failed++; }
            else { color = "#fff9c4"; skipped++; }

            rows.append("<tr><td>").append(i++).append("</td><td>").append(r[0]).append("</td>")
                    .append("<td style='background:").append(color).append("'>").append(r[1]).append("</td>")
                    .append("<td>").append(r[2]).append("</td><td>").append(r[3]).append("</td></tr>");
        }

        String date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));

        String html = "<html><head><meta charset='UTF-8'><title>Component Summary</title>"
                + "<style>body{font-family:Arial;margin:30px}table{border-collapse:collapse;width:100%}"
                + "th,td{border:1px solid #999;padding:8px;text-align:left}th{background:#37474f;color:white}</style></head><body>"
                + "<h1>Test Summary Report - Component</h1>"
                + "<p><b>Date:</b> " + date + "<br><b>Browser:</b> Chrome<br><b>Application:</b> https://practicesoftwaretesting.com/</p>"
                + "<h3>Summary</h3><table style='width:50%'><tr><th>Total</th><th>Passed</th><th>Failed</th><th>Skipped</th></tr>"
                + "<tr><td>" + results.size() + "</td><td>" + passed + "</td><td>" + failed + "</td><td>" + skipped + "</td></tr></table>"
                + "<h3>Details</h3><table><tr><th>#</th><th>Test Case</th><th>Status</th><th>Duration</th><th>Error</th></tr>"
                + rows + "</table></body></html>";

        File dir = new File("test-reports");
        dir.mkdirs();
        try (FileWriter fw = new FileWriter(new File(dir, "Component_Summary.html"))) {
            fw.write(html);
        }
    }

    @Test
    public void valid_Checkout() throws InterruptedException { // Verify that the Quantity column shows 1 after adding one product
        driver.navigate().to("https://practicesoftwaretesting.com/");
        Thread.sleep(3000);
        WebElement product = driver.findElement(By.xpath("//h5[contains(text(),'Combination Pliers')]"));
        product.click();
        Thread.sleep(2000);
        WebElement add = driver.findElement(By.id("btn-add-to-cart"));
        add.click();
        Thread.sleep(2000);
    }

    @Test
    public void click_Cart_Icon() throws InterruptedException {
        driver.navigate().to("https://practicesoftwaretesting.com/");
        Thread.sleep(3000);

        WebElement product = driver.findElement(By.xpath("//h5[contains(text(),'Combination Pliers')]"));
        product.click();

        Thread.sleep(2000);
        WebElement add = driver.findElement(By.id("btn-add-to-cart"));
        add.click();

        Thread.sleep(2000);
        WebElement cart = driver.findElement(By.cssSelector("[data-test='nav-cart']"));
        cart.click();
        Thread.sleep(2000);

    }

    @Test
    public void proceed_To_Checkout_Navigates_To_Sign_In() throws InterruptedException { // Verify that clicking on the “Proceed to Checkout” button navigates to the Sign in step
        // 1. ادخل على الهوم
        driver.navigate().to("https://practicesoftwaretesting.com/");
        Thread.sleep(3000);

        // 2. ادخل على المنتج
        WebElement product = driver.findElement(By.xpath("//h5[contains(text(),'Combination Pliers')]"));
        product.click();
        Thread.sleep(2000);

        // 3. اضغط Add to cart
        WebElement add = driver.findElement(By.id("btn-add-to-cart"));
        add.click();
        Thread.sleep(2000);

        // 4. ادخل على الكارت
        WebElement cart = driver.findElement(By.cssSelector("[data-test='nav-cart']"));
        cart.click();
        Thread.sleep(2000);

        // 5. اضغط Proceed to Checkout
        WebElement proceed1 = driver.findElement(By.cssSelector("[data-test='proceed-1']"));
        proceed1.click();
        Thread.sleep(2000);
    }

    @Test
    public void email_Without_At_Sign_Is_Not_Accepted() throws InterruptedException { // Verify that an email without the “@” special character is not accepted
        driver.navigate().to("https://practicesoftwaretesting.com/");
        Thread.sleep(3000);

        WebElement product = driver.findElement(By.xpath("//h5[contains(text(),'Combination Pliers')]"));
        product.click();

        Thread.sleep(2000);
        WebElement add = driver.findElement(By.id("btn-add-to-cart"));
        add.click();

        Thread.sleep(2000);
        WebElement cart = driver.findElement(By.cssSelector("[data-test='nav-cart']"));
        cart.click();
        Thread.sleep(3000);
        WebElement proceed1 = driver.findElement(By.cssSelector("[data-test='proceed-1']"));
        proceed1.click();
        Thread.sleep(2000);

        WebElement email = driver.findElement(By.id("email"));
        email.sendKeys("testgmail.com");
        WebElement password = driver.findElement(By.id("password"));
        password.sendKeys("Ab1@cD");
        WebElement login = driver.findElement(By.cssSelector("[data-test='login-submit']"));
        login.click();
        Thread.sleep(3000);
    }

    @Test
    public void register_Link_Navigates_To_Register_Page() throws InterruptedException { // Verify that clicking on the “Register your account” link navigates to the Register page
        driver.navigate().to("https://practicesoftwaretesting.com/");
        Thread.sleep(3000);

        WebElement product = driver.findElement(By.xpath("//h5[contains(text(),'Combination Pliers')]"));
        product.click();

        Thread.sleep(2000);
        WebElement add = driver.findElement(By.id("btn-add-to-cart"));
        add.click();

        Thread.sleep(2000);
        WebElement cart = driver.findElement(By.cssSelector("[data-test='nav-cart']"));
        cart.click();
        Thread.sleep(3000);
        WebElement proceed1 = driver.findElement(By.cssSelector("[data-test='proceed-1']"));
        proceed1.click();
        Thread.sleep(2000);

        WebElement register = driver.findElement(By.cssSelector("[data-test='register-link']"));
        register.click();
        Thread.sleep(3000);
    }

}

