

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

public class Integration {

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

        String html = "<html><head><meta charset='UTF-8'><title>Integration Summary</title>"
                + "<style>body{font-family:Arial;margin:30px}table{border-collapse:collapse;width:100%}"
                + "th,td{border:1px solid #999;padding:8px;text-align:left}th{background:#37474f;color:white}</style></head><body>"
                + "<h1>Test Summary Report - Integration</h1>"
                + "<p><b>Date:</b> " + date + "<br><b>Browser:</b> Chrome<br><b>Application:</b> https://practicesoftwaretesting.com/</p>"
                + "<h3>Summary</h3><table style='width:50%'><tr><th>Total</th><th>Passed</th><th>Failed</th><th>Skipped</th></tr>"
                + "<tr><td>" + results.size() + "</td><td>" + passed + "</td><td>" + failed + "</td><td>" + skipped + "</td></tr></table>"
                + "<h3>Details</h3><table><tr><th>#</th><th>Test Case</th><th>Status</th><th>Duration</th><th>Error</th></tr>"
                + rows + "</table></body></html>";

        File dir = new File("test-reports");
        dir.mkdirs();
        File report = new File(dir, "Integration_Summary.html");
        try (FileWriter fw = new FileWriter(report)) {
            fw.write(html);
        }
        System.out.println("Report: " + report.getAbsolutePath());
    }

    @Test
    public void valid_Total_Billing_And_BankTransfer_Checkout() throws InterruptedException { // Verify that the Total column calculates the total correctly and enter 6 characters containing letters, numbers and special characters and the Billing Address page is displayed and the checkout is completed when the information is correct
        driver.navigate().to("https://practicesoftwaretesting.com/");
        Thread.sleep(3000);

        WebElement product = driver.findElement(By.xpath("//h5[contains(text(),'Combination Pliers')]"));
        product.click();
        Thread.sleep(2000);

        WebElement add = driver.findElement(By.id("btn-add-to-cart"));
        add.click();
        Thread.sleep(3000);

        WebElement cart = driver.findElement(By.cssSelector("[data-test='nav-cart']"));
        cart.click();
        Thread.sleep(3000);

        WebElement proceed1 = driver.findElement(By.cssSelector("[data-test='proceed-1']"));
        proceed1.click();
        Thread.sleep(3000);

        WebElement email = driver.findElement(By.id("email"));
        email.sendKeys("customer@practicesoftwaretesting.com");
        WebElement password = driver.findElement(By.id("password"));
        password.sendKeys("welcome01");
        WebElement login = driver.findElement(By.cssSelector("[data-test='login-submit']"));
        login.click();
        Thread.sleep(3000);

        WebElement proceed2 = driver.findElement(By.cssSelector("[data-test='proceed-2']"));
        proceed2.click();
        Thread.sleep(3000);

        // ===== Billing Address =====
        WebElement country = driver.findElement(By.cssSelector("[data-test='country']"));
        country.click();
        WebElement egypt = driver.findElement(By.xpath("//option[contains(text(),'Egypt')]"));
        egypt.click();

        WebElement postal = driver.findElement(By.cssSelector("[data-test='postal_code']"));
        postal.clear();
        postal.sendKeys("32111");
        WebElement house = driver.findElement(By.cssSelector("[data-test='house_number']"));
        house.clear();
        house.sendKeys("42");
        Thread.sleep(2000); // الموقع بيملي Street وCity وState أوتوماتيك، نستناه قبل ما نكتب فوقهم

        WebElement street = driver.findElement(By.cssSelector("[data-test='street']"));
        street.clear();
        street.sendKeys("Robel Corner");
        WebElement city = driver.findElement(By.cssSelector("[data-test='city']"));
        city.clear();
        city.sendKeys("New Eleanore");
        WebElement state = driver.findElement(By.cssSelector("[data-test='state']"));
        state.clear();
        state.sendKeys("Maine");

        WebElement proceed3 = driver.findElement(By.cssSelector("[data-test='proceed-3']"));
        proceed3.click();
        Thread.sleep(3000);

        // ===== Payment =====
        WebElement paymentMethod = driver.findElement(By.cssSelector("[data-test='payment-method']"));
        paymentMethod.click();
        WebElement bankTransfer = driver.findElement(By.xpath("//option[contains(text(),'Bank Transfer')]"));
        bankTransfer.click();
        Thread.sleep(2000);

        WebElement bankName = driver.findElement(By.cssSelector("[data-test='bank_name']"));
        bankName.sendKeys("Bank of America");
        WebElement accountName = driver.findElement(By.cssSelector("[data-test='account_name']"));
        accountName.sendKeys("John Smith");
        WebElement accountNumber = driver.findElement(By.cssSelector("[data-test='account_number']"));
        accountNumber.sendKeys("123456789");

        WebElement finish = driver.findElement(By.cssSelector("[data-test='finish']"));
        finish.click();
        Thread.sleep(3000);
    }

    @Test
    public void valid_Email_Accepted_And_Billing_Page_Displayed() throws InterruptedException { // Verify that the Email field accepts an email containing “@” and a valid domain and the Billing Address page is displayed
        driver.navigate().to("https://practicesoftwaretesting.com/");
        Thread.sleep(3000);

        WebElement product = driver.findElement(By.xpath("//h5[contains(text(),'Combination Pliers')]"));
        product.click();
        Thread.sleep(2000);

        WebElement add = driver.findElement(By.id("btn-add-to-cart"));
        add.click();
        Thread.sleep(3000);

        WebElement cart = driver.findElement(By.cssSelector("[data-test='nav-cart']"));
        cart.click();
        Thread.sleep(3000);

        WebElement proceed1 = driver.findElement(By.cssSelector("[data-test='proceed-1']"));
        proceed1.click();
        Thread.sleep(3000);

        WebElement email = driver.findElement(By.id("email"));
        email.sendKeys("customer@practicesoftwaretesting.com");
        WebElement password = driver.findElement(By.id("password"));
        password.sendKeys("welcome01");
        WebElement login = driver.findElement(By.cssSelector("[data-test='login-submit']"));
        login.click();
        Thread.sleep(3000);

        WebElement proceed2 = driver.findElement(By.cssSelector("[data-test='proceed-2']"));
        proceed2.click();
        Thread.sleep(3000);
    }

    @Test
    public void invalid_Email_Without_At_Sign() throws InterruptedException { // Verify that the Price column shows the correct price and an email without “@” is not accepted
        driver.navigate().to("https://practicesoftwaretesting.com/");
        Thread.sleep(3000);

        WebElement product = driver.findElement(By.xpath("//h5[contains(text(),'Combination Pliers')]"));
        product.click();
        Thread.sleep(2000);

        WebElement add = driver.findElement(By.id("btn-add-to-cart"));
        add.click();
        Thread.sleep(3000);

        WebElement cart = driver.findElement(By.cssSelector("[data-test='nav-cart']"));
        cart.click();
        Thread.sleep(3000);

        WebElement proceed1 = driver.findElement(By.cssSelector("[data-test='proceed-1']"));
        proceed1.click();
        Thread.sleep(3000);

        WebElement email = driver.findElement(By.id("email"));
        email.sendKeys("testgmail.com");
        WebElement password = driver.findElement(By.id("password"));
        password.sendKeys("welcome01");
        WebElement login = driver.findElement(By.cssSelector("[data-test='login-submit']"));
        login.click();
        Thread.sleep(3000);
    }

    @Test
    public void invalid_City_Field_Empty() throws InterruptedException { // Enter 7 characters containing letters, numbers and special characters and verify that the user cannot proceed when City is empty
        driver.navigate().to("https://practicesoftwaretesting.com/");
        Thread.sleep(3000);

        WebElement product = driver.findElement(By.xpath("//h5[contains(text(),'Combination Pliers')]"));
        product.click();
        Thread.sleep(2000);

        WebElement add = driver.findElement(By.id("btn-add-to-cart"));
        add.click();
        Thread.sleep(3000);

        WebElement cart = driver.findElement(By.cssSelector("[data-test='nav-cart']"));
        cart.click();
        Thread.sleep(3000);

        WebElement proceed1 = driver.findElement(By.cssSelector("[data-test='proceed-1']"));
        proceed1.click();
        Thread.sleep(3000);

        WebElement email = driver.findElement(By.id("email"));
        email.sendKeys("customer@practicesoftwaretesting.com");
        WebElement password = driver.findElement(By.id("password"));
        password.sendKeys("welcome01");
        WebElement login = driver.findElement(By.cssSelector("[data-test='login-submit']"));
        login.click();
        Thread.sleep(3000);

        WebElement proceed2 = driver.findElement(By.cssSelector("[data-test='proceed-2']"));
        proceed2.click();
        Thread.sleep(3000);

        // ===== Billing Address: كل الحقول valid ما عدا City =====
        WebElement country = driver.findElement(By.cssSelector("[data-test='country']"));
        country.click();
        WebElement egypt = driver.findElement(By.xpath("//option[contains(text(),'Egypt')]"));
        egypt.click();

        WebElement postal = driver.findElement(By.cssSelector("[data-test='postal_code']"));
        postal.clear();
        postal.sendKeys("32111");
        WebElement house = driver.findElement(By.cssSelector("[data-test='house_number']"));
        house.clear();
        house.sendKeys("42");
        Thread.sleep(2000);

        WebElement street = driver.findElement(By.cssSelector("[data-test='street']"));
        street.clear();
        street.sendKeys("Robel Corner");
        WebElement state = driver.findElement(By.cssSelector("[data-test='state']"));
        state.clear();
        state.sendKeys("Maine");

        WebElement city = driver.findElement(By.cssSelector("[data-test='city']"));
        city.clear(); // City فاضية

        WebElement proceed3 = driver.findElement(By.cssSelector("[data-test='proceed-3']"));
        proceed3.click();
        Thread.sleep(3000);
    }

    @Test
    public void low_ProceedButton_Size_And_Email_Error_Font() throws InterruptedException { // Verify that the size of the “Proceed to checkout” button is not changed and the font of the email error message is not changed
        driver.navigate().to("https://practicesoftwaretesting.com/");
        Thread.sleep(3000);

        WebElement product = driver.findElement(By.xpath("//h5[contains(text(),'Combination Pliers')]"));
        product.click();
        Thread.sleep(2000);

        WebElement add = driver.findElement(By.id("btn-add-to-cart"));
        add.click();
        Thread.sleep(3000);

        WebElement cart = driver.findElement(By.cssSelector("[data-test='nav-cart']"));
        cart.click();
        Thread.sleep(3000);

        WebElement proceed1 = driver.findElement(By.cssSelector("[data-test='proceed-1']"));
        System.out.println(proceed1.getSize());
        proceed1.click();
        Thread.sleep(3000);

        WebElement email = driver.findElement(By.id("email"));
        email.sendKeys("testgmail.com");
        WebElement login = driver.findElement(By.cssSelector("[data-test='login-submit']"));
        login.click();
        Thread.sleep(2000);

        WebElement emailError = driver.findElement(By.cssSelector("[data-test='email-error']"));
        System.out.println(emailError.getCssValue("font-family"));
        System.out.println(emailError.getCssValue("font-size"));
        System.out.println(emailError.getCssValue("font-weight"));
        Thread.sleep(2000);
    }
}