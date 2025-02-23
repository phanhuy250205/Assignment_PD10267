package selenium;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

public class CRUDTest_Selenium {
    private WebDriver driver;
    private WebDriverWait wait;

    // Thông tin đăng nhập
    private final String LOGIN_URL = "http://localhost:8080/Lap2_war_exploded/login";
    private final String USERNAME = "phanhuy250205@gmail.com";
    private final String PASSWORD = "250205@Huy";
    private final String CRUD_URL = "http://localhost:8080/Lap2_war_exploded/listvideo";

    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        loginToAdmin();
    }

    private void loginToAdmin() {
        driver.get(LOGIN_URL);
        System.out.println("🔹 Đã mở trang đăng nhập.");

        driver.findElement(By.id("username")).sendKeys(USERNAME);
        driver.findElement(By.id("password")).sendKeys(PASSWORD);
        driver.findElement(By.id("loginBtn")).click();
        System.out.println("✔ Đã nhấn nút đăng nhập.");

        // Chờ trang chuyển đến Dashboard
        wait.until(ExpectedConditions.urlContains("/index.jsp"));
        System.out.println("📌 Đã đăng nhập thành công.");

        driver.get(CRUD_URL);
        System.out.println("🔹 Đã vào trang quản lý video.");
    }

    @Test
    public void testCreateRecord() {
        System.out.println("✔ Bắt đầu kiểm tra tạo video mới.");

        // Nhấn vào nút "Add New Video"
        WebElement createButton = waitForElement(By.className("btn-primary"));

        // Cuộn đến nút để đảm bảo Selenium nhìn thấy
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", createButton);

        // Dùng JavaScript click nếu Selenium click không được
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", createButton);

        System.out.println("✔ Đã nhấn nút tạo mới.");

        // Kiểm tra modal hiển thị
        WebElement modal = waitForElement(By.id("addVideoModal"));
        Assert.assertTrue(modal.isDisplayed(), "❌ Modal không hiển thị!");

        // Nhập thông tin video
        driver.findElement(By.name("id")).sendKeys("0977541463");
        driver.findElement(By.name("title")).sendKeys("3 ngày 4 đêm");
        driver.findElement(By.name("poster")).sendKeys("https://www.youtube.com/embed/dQw4w9WgXcQ");
        driver.findElement(By.name("views")).sendKeys("100");
        driver.findElement(By.name("description")).sendKeys("Mô tả video test");

        // Chọn trạng thái hoạt động
        driver.findElement(By.id("active")).sendKeys("Hoạt động");

        // Nhấn "Upload Video"
        WebElement saveButton = waitForElement(By.xpath("//button[contains(text(),'Upload Video')]"));
        saveButton.click();
        System.out.println("✔ Đã nhấn nút lưu video.");

        // Kiểm tra xem video có xuất hiện trong danh sách không
        WebElement newVideo = waitForElement(By.xpath("//h3[contains(text(),'Test Video')]"));
        Assert.assertTrue(newVideo.isDisplayed(), "❌ Video mới không hiển thị!");
        System.out.println("✅ Tạo video thành công.");
    }


    @Test
    public void testReadRecord() throws InterruptedException {
        System.out.println("✔ Bắt đầu kiểm tra tìm kiếm video.");

        // Chờ ô nhập từ khóa xuất hiện
        WebElement searchBox = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("keyword")));
        searchBox.clear();
        searchBox.sendKeys("Test Video");
        System.out.println("✔ Đã nhập từ khóa tìm kiếm.");

        // Nhấn ENTER để tìm kiếm
        searchBox.sendKeys(Keys.RETURN);
        System.out.println("✔ Đã nhấn ENTER để tìm kiếm.");

        // Chờ danh sách cập nhật
        Thread.sleep(3000); // Chờ 3 giây để JavaScript tải dữ liệu mới
        System.out.println("✔ Đợi kết quả tìm kiếm cập nhật...");

        // Kiểm tra xem video có hiển thị không
        WebElement video = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h3[contains(text(),'Test Video')]")));
        Assert.assertTrue(video.isDisplayed(), "❌ Không tìm thấy video!");
        System.out.println("✅ Tìm kiếm video thành công.");
    }



    @Test
    public void testDeleteRecord() throws InterruptedException {
        System.out.println("✔ Bắt đầu kiểm tra xóa video cuối cùng.");

        // Lấy danh sách các video chứa 'Test Video'
        List<WebElement> videoTitles = driver.findElements(By.xpath("//h3[contains(text(),'Test Video')]"));

        if (videoTitles.isEmpty()) {
            System.out.println("❌ Không có video nào để xóa!");
            Assert.fail("Không có video nào để xóa!");
        }

        // Xóa video cuối cùng
        int lastIndex = videoTitles.size() - 1;
        WebElement lastVideoTitle = videoTitles.get(lastIndex);

        // Di chuyển đến video cuối cùng
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", lastVideoTitle);
        Thread.sleep(1000);

        // Tìm nút xóa tương ứng với video cuối cùng
        WebElement deleteButton = driver.findElements(By.xpath("//form[contains(@action, 'listvideo?action=delete')]//button[@type='submit']")).get(lastIndex);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", deleteButton);
        System.out.println("✔ Đã nhấn nút xóa video cuối cùng bằng JavaScript.");

        // Xác nhận hộp thoại alert nếu có
        try {
            Alert alert = wait.until(ExpectedConditions.alertIsPresent());
            alert.accept();
            System.out.println("✔ Đã xác nhận xóa video.");
        } catch (TimeoutException e) {
            System.out.println("⚠ Không có alert xác nhận xóa.");
        }

        // Chờ server xử lý
        Thread.sleep(5000);

        // Làm mới trang để đảm bảo dữ liệu cập nhật
        driver.navigate().refresh();
        Thread.sleep(3000);

        // Kiểm tra danh sách video có còn video 'Test Video' không
        List<WebElement> remainingVideos = driver.findElements(By.xpath("//h3[contains(text(),'Test Video')]"));

        // Chỉ kiểm tra số lượng video đã giảm
        if (remainingVideos.size() < videoTitles.size()) {
            System.out.println("✅ Video cuối cùng đã được xóa thành công.");
        } else {
            System.out.println("❌ Video vẫn còn tồn tại sau khi xóa!");
            Assert.fail("Video vẫn còn tồn tại sau khi xóa!");
        }
    }


    // Phương thức hỗ trợ tìm kiếm phần tử, giúp tránh lỗi TimeoutException
    private WebElement waitForElement(By by) {
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        if (driver != null) {
            if (!result.isSuccess()) {
                takeScreenshot(result.getName());
            }
            System.out.println("🔹 Đóng trình duyệt.");
            driver.quit();
        }
    }

    private void takeScreenshot(String testName) {
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(screenshot, new File("screenshots/" + testName + ".png"));
            System.out.println("📸 Đã chụp màn hình lỗi.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
