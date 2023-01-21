package Domaci22;

import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class LoginTest {
    private WebDriver driver;
    private Faker faker;


    @BeforeClass
    public void beforeClass() {
        System.setProperty("webdiver.chrome.driver", "C:\\ITBootCamp\\chromedriver");
        driver = new ChromeDriver();

    }

    @BeforeMethod
    public void beforeTest() {
        driver.get("https://vue-demo.daniel-avellaneda.com/");
        driver.manage().window().maximize();
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }


    @Test
    public void test1() {

        WebElement loginButton = driver.findElement(By.xpath("//*[@id=\"app\"]/div/div/header/div/div[3]/a[3]"));
        loginButton.click();
        String actualUrl = driver.getCurrentUrl();
        String expectedUrl = "https://vue-demo.daniel-avellaneda.com/login";

        Assert.assertTrue(actualUrl.contains("/login"));


        WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(20));
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));

        WebElement email = driver.findElement(By.id("email"));
        String actualEmail = email.getAttribute("type");
        String expectedTitle = "email";


        Assert.assertEquals(actualEmail, expectedTitle);


        WebElement passwordField = driver.findElement(By.id("password"));
        String actualPassword = passwordField.getAttribute("type");
        String expectedPassword = "password";

        Assert.assertEquals(actualPassword, expectedPassword);
    }


    @Test
    public void test2() {
        WebElement loginButton = driver.findElement(By.xpath("//*[@id=\"app\"]/div/div/header/div/div[3]/a[3]"));
        loginButton.click();
        WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(20));
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
        faker = new Faker();

        WebElement email = driver.findElement(By.id("email"));
        email.sendKeys(faker.internet().emailAddress());
        WebElement passwordField = driver.findElement(By.id("password"));
        passwordField.sendKeys(faker.internet().password());

        WebElement login = driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/main/div/div[2]/div/div/div[3]/span/form/div/div[3]/button"));
        login.click();

        WebDriverWait webDriverWait1 = new WebDriverWait(driver, Duration.ofSeconds(20));
        webDriverWait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"app\"]/div[1]/main/div/div[2]/div/div/div[4]/div/div/div/div")));

        String expectedMessage = "User does not exists";
        WebElement message = driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/main/div/div[2]/div/div/div[4]/div/div/div/div/div[1]/ul/li"));
        String actualMessage = message.getText();

        Assert.assertEquals(actualMessage, expectedMessage);

    }
    @Test
    public void test3() {
        WebElement loginButton = driver.findElement(By.xpath("//*[@id=\"app\"]/div/div/header/div/div[3]/a[3]"));
        loginButton.click();
        WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(20));
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
        faker = new Faker();

        WebElement email = driver.findElement(By.id("email"));
        email.sendKeys("admin@admin.com");
        WebElement passwordField = driver.findElement(By.id("password"));
        passwordField.sendKeys(faker.internet().password());

        WebElement login = driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/main/div/div[2]/div/div/div[3]/span/form/div/div[3]/button"));
        login.click();

        WebDriverWait webDriverWait1 = new WebDriverWait(driver, Duration.ofSeconds(20));
        webDriverWait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"app\"]/div[1]/main/div/div[2]/div/div/div[4]/div/div/div/div")));

        String expectedMessage = "Wrong password";
        WebElement message = driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/main/div/div[2]/div/div/div[4]/div/div/div/div/div[1]/ul/li"));
        String actualMessage = message.getText();

        Assert.assertEquals(actualMessage, expectedMessage);

    }
}
