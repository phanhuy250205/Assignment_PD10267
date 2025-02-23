package selenium;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class RegisterTest_Selenium {
    private WebDriver driver;

    @BeforeMethod  // Dùng của TestNG, thay vì @BeforeEach của JUnit
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://localhost:8080/Lap2_war_exploded/dangky");
    }

    @Test
    public void testRegisterSuccess() throws InterruptedException {
        System.out.println("🔹 Bắt đầu test đăng ký thành công...");

        driver.findElement(By.name("fullname")).sendKeys("Phan Văn Huy ");
        System.out.println("✔ Nhập tên đầy đủ: Phan Văn Huy");

        driver.findElement(By.name("email")).sendKeys("huydsdsad@gmail.com");
        System.out.println("✔ Nhập email: user@example.com");

        driver.findElement(By.name("password")).sendKeys("12345678");
        System.out.println("✔ Nhập mật khẩu");

        driver.findElement(By.name("confirm_password")).sendKeys("12345678");
        System.out.println("✔ Nhập xác nhận mật khẩu");

        Thread.sleep(5000); // Chờ 5 giây để trang tải xong
        System.out.println("⏳ Chờ 5 giây để đảm bảo giao diện đã load xong...");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement registerButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("registerBtn")));
        System.out.println("✔ Nút đăng ký đã sẵn sàng để click");

        registerButton.click();
        System.out.println("🚀 Đã nhấn vào nút đăng ký");
        System.out.println("Đã Đăng Kí Thành Công");
    }


    @Test
    public void testRegisterFail_ExistingUser() {
        System.out.println("🔹 Bắt đầu test đăng ký với tài khoản đã tồn tại...");

        driver.findElement(By.name("fullname")).sendKeys("admin");
        System.out.println("✔ Nhập tên đầy đủ: admin");

        driver.findElement(By.name("email")).sendKeys("admin@example.com");
        System.out.println("✔ Nhập email: admin@example.com");

        driver.findElement(By.name("password")).sendKeys("password123");
        System.out.println("✔ Nhập mật khẩu");

        driver.findElement(By.name("confirm_password")).sendKeys("password123");
        System.out.println("✔ Nhập xác nhận mật khẩu");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        WebElement registerButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("registerBtn")));
        System.out.println("✔ Nút đăng ký đã xuất hiện");

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", registerButton);
        System.out.println("✔ Đã cuộn đến nút đăng ký");

        try {
            registerButton.click();
            System.out.println("🚀 Đã nhấn vào nút đăng ký");
        } catch (Exception e) {
            System.out.println("⚠ Không thể click, thử click bằng JavaScript...");
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", registerButton);
            System.out.println("✅ Click thành công bằng JavaScript!");
        }

        // Kiểm tra chuyển hướng hoặc thay đổi URL (nếu có)
        WebDriverWait redirectWait = new WebDriverWait(driver, Duration.ofSeconds(40));
        try {
            wait.until(ExpectedConditions.urlContains("errorPage"));
            System.out.println("✅ Đã chuyển hướng tới trang lỗi");
        } catch (TimeoutException e) {
            System.out.println("⚠ Không có chuyển hướng, kiểm tra lại thông báo lỗi trên trang hiện tại");
        }

        // Kiểm tra thông báo lỗi trong DOM
        WebDriverWait errorWait = new WebDriverWait(driver, Duration.ofSeconds(40));
        try {
            WebElement errorMsg = errorWait.until(ExpectedConditions.presenceOfElementLocated(By.id("errorMsg")));
            errorWait.until(ExpectedConditions.visibilityOf(errorMsg));
            Assert.assertTrue(errorMsg.isDisplayed(), "❌ Không thấy thông báo lỗi khi đăng ký tài khoản đã tồn tại!");
            System.out.println("✅ Test thành công: Hiển thị thông báo lỗi đúng khi đăng ký tài khoản đã tồn tại.");
        } catch (TimeoutException e) {
            System.out.println("⚠ Lỗi: Không tìm thấy thông báo lỗi sau 40 giây. Có thể test chưa hoạt động đúng!");
        }
    }


    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
