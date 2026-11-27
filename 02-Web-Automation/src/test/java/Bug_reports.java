

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class Bug_reports {
    @Test
    public void Bug1() throws InterruptedException { //Valid email and password are rejected when logging in during Checkout
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

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

        // 6. صفحة Sign in: اكتب الايميل
        WebElement email = driver.findElement(By.id("email"));
        email.sendKeys("ahrt76@gmail.com");
        Thread.sleep(1000);

        // 7. اكتب الباسورد
        WebElement password = driver.findElement(By.id("password"));
        password.sendKeys("Ahrt65183773@");
        Thread.sleep(1000);

        // 8. اضغط Login
        WebElement login = driver.findElement(By.cssSelector("[data-test='login-submit']"));
        login.click();
        Thread.sleep(3000);

    }

    @Test
    public void billingAddressAutoFillBug() throws InterruptedException { //wrong autofill
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

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

        // 6. Sign in: الايميل
        WebElement email = driver.findElement(By.id("email"));
        email.sendKeys("customer@practicesoftwaretesting.com");
        Thread.sleep(1000);

        // 7. الباسورد
        WebElement password = driver.findElement(By.id("password"));
        password.sendKeys("welcome01");
        Thread.sleep(1000);

        // 8. اضغط Login
        WebElement login = driver.findElement(By.cssSelector("[data-test='login-submit']"));
        login.click();
        Thread.sleep(3000);

        // 9. اضغط Proceed to Checkout بعد اللوجن (دخول على Billing Address)
        WebElement proceed2 = driver.findElement(By.cssSelector("[data-test='proceed-2']"));
        proceed2.click();
        Thread.sleep(2000);

        // 10. اختار الكانتري Egypt
        WebElement country = driver.findElement(By.cssSelector("[data-test='country']"));
        country.sendKeys("Egypt");
        Thread.sleep(1000);

        // 11. اكتب البوستال كود
        WebElement postcode = driver.findElement(By.cssSelector("[data-test='postal_code']"));
        postcode.clear();
        postcode.sendKeys("11511");
        Thread.sleep(1000);

        // 12. اكتب رقم البيت
        WebElement houseNumber = driver.findElement(By.cssSelector("[data-test='house_number']"));
        houseNumber.clear();
        houseNumber.sendKeys("10");
        Thread.sleep(3000); // استنى الاوتو فيل

        // 13. اقرا اللي اتعبّى لوحده
        String street = driver.findElement(By.cssSelector("[data-test='street']")).getAttribute("value");
        String city = driver.findElement(By.cssSelector("[data-test='city']")).getAttribute("value");
        String state = driver.findElement(By.cssSelector("[data-test='state']")).getAttribute("value");

        System.out.println("Street: " + street);
        System.out.println("City: " + city);
        System.out.println("State: " + state);
    }


    @Test
    public void creditCardPaymentBug() throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        // 1. ادخل على الهوم
        driver.navigate().to("https://practicesoftwaretesting.com/");
        Thread.sleep(3000);

        // 2. ادخل على المنتج
        WebElement product = driver.findElement(By.xpath("//h5[contains(text(),'Claw Hammer')]"));
        product.click();
        Thread.sleep(2000);

        // 3. اضغط Add to cart
        WebElement add = driver.findElement(By.id("btn-add-to-cart"));
        add.click();
        Thread.sleep(5000); // استنى الـ toast يختفي

// 4. ادخل على الكارت
        WebElement cart = driver.findElement(By.cssSelector("[data-test='nav-cart']"));
        cart.click();
        Thread.sleep(2000);

        // 5. اضغط Proceed to Checkout
        WebElement proceed1 = driver.findElement(By.cssSelector("[data-test='proceed-1']"));
        proceed1.click();
        Thread.sleep(2000);

        // 6. Sign in: الايميل
        WebElement email = driver.findElement(By.id("email"));
        email.sendKeys("customer@practicesoftwaretesting.com");
        Thread.sleep(1000);

        // 7. الباسورد
        WebElement password = driver.findElement(By.id("password"));
        password.sendKeys("welcome01");
        Thread.sleep(1000);

        // 8. اضغط Login
        WebElement login = driver.findElement(By.cssSelector("[data-test='login-submit']"));
        login.click();
        Thread.sleep(3000);

        // 9. اضغط Proceed to Checkout بعد اللوجن
        WebElement proceed2 = driver.findElement(By.cssSelector("[data-test='proceed-2']"));
        proceed2.click();
        Thread.sleep(2000);

        // 10. اختار الكانتري Egypt
        WebElement country = driver.findElement(By.cssSelector("[data-test='country']"));
        country.sendKeys("Egypt");
        Thread.sleep(1000);

        // 11. اكتب البوستال كود
        WebElement postcode = driver.findElement(By.cssSelector("[data-test='postal_code']"));
        postcode.clear();
        postcode.sendKeys("11511");
        Thread.sleep(1000);

        // 12. اكتب رقم البيت
        WebElement houseNumber = driver.findElement(By.cssSelector("[data-test='house_number']"));
        houseNumber.clear();
        houseNumber.sendKeys("10");
        Thread.sleep(3000); // استنى الاوتو فيل

        // 13. اقرا اللي اتعبّى لوحده
        String street = driver.findElement(By.cssSelector("[data-test='street']")).getAttribute("value");
        String city = driver.findElement(By.cssSelector("[data-test='city']")).getAttribute("value");
        String state = driver.findElement(By.cssSelector("[data-test='state']")).getAttribute("value");

        System.out.println("Street: " + street);
        System.out.println("City: " + city);
        System.out.println("State: " + state);


        // 15. اضغط Proceed to Checkout (على البيمنت)
        WebElement proceed3 = driver.findElement(By.cssSelector("[data-test='proceed-3']"));
        proceed3.click();
        Thread.sleep(2000);

        // 16. اختار طريقة الدفع Credit Card
        WebElement paymentMethod = driver.findElement(By.cssSelector("[data-test='payment-method']"));
        paymentMethod.sendKeys("Credit Card");
        Thread.sleep(1000);

        // 17. رقم الكارت
        WebElement cardNumber = driver.findElement(By.cssSelector("[data-test='credit_card_number']"));
        cardNumber.sendKeys("0000-0000-0000-0000");
        Thread.sleep(1000);


    }
    @Test
    public void creditCardNumberNotSeparated() throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        // 1. ادخل على الهوم
        driver.navigate().to("https://practicesoftwaretesting.com/");
        Thread.sleep(3000);

        // 2. ادخل على المنتج
        WebElement product = driver.findElement(By.xpath("//h5[contains(text(),'Bolt Cutters')]"));
        product.click();
        Thread.sleep(2000);

        // 3. اضغط Add to cart
        WebElement add = driver.findElement(By.id("btn-add-to-cart"));
        add.click();
        Thread.sleep(5000);

        // 4. ادخل على الكارت
        WebElement cart = driver.findElement(By.cssSelector("[data-test='nav-cart']"));
        cart.click();
        Thread.sleep(2000);

        // 5. اضغط Proceed to Checkout
        WebElement proceed1 = driver.findElement(By.cssSelector("[data-test='proceed-1']"));
        proceed1.click();
        Thread.sleep(2000);

        // 6. Sign in: الايميل
        WebElement email = driver.findElement(By.id("email"));
        email.sendKeys("customer@practicesoftwaretesting.com");
        Thread.sleep(1000);

        // 7. الباسورد
        WebElement password = driver.findElement(By.id("password"));
        password.sendKeys("welcome01");
        Thread.sleep(1000);

        // 8. اضغط Login
        WebElement login = driver.findElement(By.cssSelector("[data-test='login-submit']"));
        login.click();
        Thread.sleep(3000);

        // 9. اضغط Proceed to Checkout بعد اللوجن
        WebElement proceed2 = driver.findElement(By.cssSelector("[data-test='proceed-2']"));
        proceed2.click();
        Thread.sleep(2000);

        // 10. اختار الكانتري Egypt
        WebElement country = driver.findElement(By.cssSelector("[data-test='country']"));
        country.sendKeys("Egypt");
        Thread.sleep(1000);

        // 11. اكتب البوستال كود
        WebElement postcode = driver.findElement(By.cssSelector("[data-test='postal_code']"));
        postcode.clear();
        postcode.sendKeys("11511");
        Thread.sleep(1000);

        // 12. اكتب رقم البيت
        WebElement houseNumber = driver.findElement(By.cssSelector("[data-test='house_number']"));
        houseNumber.clear();
        houseNumber.sendKeys("10");
        Thread.sleep(3000); // استنى الاوتو فيل

        // 13. اضغط Proceed to Checkout (على البيمنت)
        WebElement proceed3 = driver.findElement(By.cssSelector("[data-test='proceed-3']"));
        proceed3.click();
        Thread.sleep(2000);

        // 14. اختار طريقة الدفع Credit Card
        WebElement paymentMethod = driver.findElement(By.cssSelector("[data-test='payment-method']"));
        paymentMethod.sendKeys("Credit Card");
        Thread.sleep(1000);

        // 15. اكتب رقم الكارت (من غير فواصل)
        WebElement cardNumber = driver.findElement(By.cssSelector("[data-test='credit_card_number']"));
        cardNumber.sendKeys("1111222233334444");
        Thread.sleep(1000);

        // 16. اقرا الرقم بعد ما اتكتب
        String value = cardNumber.getAttribute("value");
        System.out.println("Card number: " + value);
    }
    @Test
    public void invalidCvvAccepted() throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        // 1. ادخل على الهوم
        driver.navigate().to("https://practicesoftwaretesting.com/");
        Thread.sleep(3000);

        // 2. ادخل على المنتج
        WebElement product = driver.findElement(By.xpath("//h5[contains(text(),'Pliers')]"));
        product.click();
        Thread.sleep(2000);

        // 3. اضغط Add to cart
        WebElement add = driver.findElement(By.id("btn-add-to-cart"));
        add.click();
        Thread.sleep(5000);

        // 4. ادخل على الكارت
        WebElement cart = driver.findElement(By.cssSelector("[data-test='nav-cart']"));
        cart.click();
        Thread.sleep(2000);

        // 5. اضغط Proceed to Checkout
        WebElement proceed1 = driver.findElement(By.cssSelector("[data-test='proceed-1']"));
        proceed1.click();
        Thread.sleep(2000);

        // 6. Sign in: الايميل
        WebElement email = driver.findElement(By.id("email"));
        email.sendKeys("customer@practicesoftwaretesting.com");
        Thread.sleep(1000);

        // 7. الباسورد
        WebElement password = driver.findElement(By.id("password"));
        password.sendKeys("welcome01");
        Thread.sleep(1000);

        // 8. اضغط Login
        WebElement login = driver.findElement(By.cssSelector("[data-test='login-submit']"));
        login.click();
        Thread.sleep(3000);

        // 9. اضغط Proceed to Checkout بعد اللوجن
        WebElement proceed2 = driver.findElement(By.cssSelector("[data-test='proceed-2']"));
        proceed2.click();
        Thread.sleep(2000);

        // 10. اختار الكانتري Egypt
        WebElement country = driver.findElement(By.cssSelector("[data-test='country']"));
        country.sendKeys("Egypt");
        Thread.sleep(1000);

        // 11. اكتب البوستال كود
        WebElement postcode = driver.findElement(By.cssSelector("[data-test='postal_code']"));
        postcode.clear();
        postcode.sendKeys("11511");
        Thread.sleep(1000);

        // 12. اكتب رقم البيت
        WebElement houseNumber = driver.findElement(By.cssSelector("[data-test='house_number']"));
        houseNumber.clear();
        houseNumber.sendKeys("10");
        Thread.sleep(3000); // استنى الاوتو فيل

        // 13. اضغط Proceed to Checkout (على البيمنت)
        WebElement proceed3 = driver.findElement(By.cssSelector("[data-test='proceed-3']"));
        proceed3.click();
        Thread.sleep(2000);

        // 14. اختار طريقة الدفع Credit Card
        WebElement paymentMethod = driver.findElement(By.cssSelector("[data-test='payment-method']"));
        paymentMethod.sendKeys("Credit Card");
        Thread.sleep(1000);

        // 15. اكتب الـ CVV
        WebElement cvv = driver.findElement(By.cssSelector("[data-test='cvv']"));
        cvv.sendKeys("123");
        cvv.sendKeys(Keys.TAB); // عشان الفاليديشن يشتغل
        Thread.sleep(2000);

        // 16. شوف ظهر ايرور ولا لا
        if (driver.findElements(By.cssSelector("[data-test='cvv-error'], .invalid-feedback")).size() > 0) {
            System.out.println("ظهر ايرور على الـ CVV");
        } else {
            System.out.println("BUG: الـ CVV 123 اتقبل ومفيش ايرور");
        }
    }
}
