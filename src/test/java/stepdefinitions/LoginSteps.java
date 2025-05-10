package stepdefinitions;  // <-- match your TestRunner glue

import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.time.Duration;

public class LoginSteps {
    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Given("User launches SauceDemo site")
    public void user_launches_site() {
        driver.get("https://www.saucedemo.com/");
    }

    @When("User logs in with {string} and {string}")
    public void user_logs_in(String user, String pass) {
        driver.findElement(By.id("user-name")).sendKeys(user);
        driver.findElement(By.id("password")).sendKeys(pass);
        driver.findElement(By.id("login-button")).click();

        // if an alert pops up, click OK
        try {
            Alert alert = wait.until(ExpectedConditions.alertIsPresent());
            alert.accept();
        } catch (TimeoutException e) {
            // no alert displayed—proceed normally
        }
    }

    @Then("User adds items to the cart")
    public void user_adds_items_to_cart() {
        wait.until(ExpectedConditions.elementToBeClickable(By.id("add-to-cart-sauce-labs-backpack")))
                .click();
        driver.findElement(By.id("add-to-cart-sauce-labs-bike-light")).click();
    }

    @And("User proceeds to checkout and fills information")
    public void user_proceeds_to_checkout_and_fills_information() {
        driver.findElement(By.className("shopping_cart_link")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.id("checkout"))).click();
        driver.findElement(By.id("first-name")).sendKeys("John");
        driver.findElement(By.id("last-name")).sendKeys("Doe");
        driver.findElement(By.id("postal-code")).sendKeys("12345");
        driver.findElement(By.id("continue")).click();
    }

    @And("User finishes the order")
    public void user_finishes_the_order() {
        wait.until(ExpectedConditions.elementToBeClickable(By.id("finish"))).click();
    }

    @Then("User should see the message {string}")
    public void user_should_see_the_message(String expected) {
        String actual = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.className("complete-header"))
        ).getText();
        Assert.assertEquals(actual, expected);
        driver.quit();
    }
}
