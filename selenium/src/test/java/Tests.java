import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class Tests {

    public WebDriver driver;

    static boolean isElementPresent(WebDriver driver, By by){
        try{
            driver.findElement(by);
            return true;
        }
        catch(NoSuchElementException e){
            return false;
        }
    }

    @BeforeAll
    public static void setDriver(){
        System.setProperty("webdriver.chrome.driver","C:\\Users\\koca\\Downloads\\chromedriver_win32\\chromedriver.exe");
    }

    @BeforeEach
    public void setup() {
        driver = new ChromeDriver();
    }

    @AfterEach
    public void quit() {
        driver.quit();
    }

    //TEST CASE 1
    @Test
    public void test1(){
        //Open url https://www.saucedemo.com/
        driver.get("https://www.saucedemo.com/");

        //Login with valid user and password (standard_user/secret_sauce)
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();

        //Verify following web elements are present on the products home page
        //"PRODUCTS" header
        Assert.assertEquals(true, isElementPresent(driver,By.className("header_secondary_container")));
        //Shopping cart
        Assert.assertEquals(true, isElementPresent(driver,By.className("shopping_cart_link")));
        //Burger menu in the uper left corner
        Assert.assertEquals(true, isElementPresent(driver,By.id("react-burger-menu-btn")));
        //Twitter, Facebook, Linkedin links
        Assert.assertEquals(true, isElementPresent(driver,By.xpath("//*/li[contains(@class,'twitter')]/a")));
        Assert.assertEquals(true, isElementPresent(driver,By.xpath("//*/li[contains(@class,'facebook')]/a")));
        Assert.assertEquals(true, isElementPresent(driver,By.xpath("//*/li[contains(@class,'linkedin')]/a")));

        //Logout
        driver.findElement(By.id("react-burger-menu-btn")).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(500));
        wait.until(ExpectedConditions.elementToBeClickable(By.id("logout_sidebar_link"))).click();
    }

    //TEST CASE 2:
    @Test
    public void test2(){
        //Open url https://www.saucedemo.com/
        driver.get("https://www.saucedemo.com/");

        //Login with valid user and password (standard_user/secret_sauce)
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();

        //Click on the "Sauce Labs Backpack" item
        driver.findElement(By.id("item_4_img_link")).click();

        //Verify title, description and price of this item
        Assert.assertEquals(driver.findElement(By.xpath("//*/div[@class='inventory_details_desc_container']/div[1]")).getText(),"Sauce Labs Backpack");
        Assert.assertEquals(driver.findElement(By.xpath("//*/div[@class='inventory_details_desc_container']/div[2]")).getText(),"carry.allTheThings() with the sleek, streamlined Sly Pack that melds uncompromising style with unequaled laptop and tablet protection.");
        Assert.assertEquals(driver.findElement(By.xpath("//*/div[@class='inventory_details_desc_container']/div[3]")).getText(),"$29.99");

        //Click on the button "ADD TO CART"
        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
        //Click on the button "BACK TO PRODUCTS"
        driver.findElement(By.id("back-to-products")).click();
        //From product home page click "ADD TO CART" button for "Sauce Labs Fleece Jacket" item
        driver.findElement(By.id("add-to-cart-sauce-labs-fleece-jacket")).click();
        //Click on the "Shopping Cart" button to open Shopping Cart page
        driver.findElement(By.className("shopping_cart_link")).click();
        //Click on the "Chechout" button to continue with order
        driver.findElement(By.id("checkout")).click();

        //Enter Firstname, Lastname, Zipcode and click on Finish button
        driver.findElement(By.id("first-name")).sendKeys("Filip");
        driver.findElement(By.id("last-name")).sendKeys("Kocovic");
        driver.findElement(By.id("postal-code")).sendKeys("34000");
        driver.findElement(By.id("continue")).click();
        driver.findElement(By.id("finish")).click();

        //Verify "THANK YOU FOR YOUR ORDER" is displayed
        Assert.assertEquals(driver.findElement(By.className("complete-header")).getText(), "THANK YOU FOR YOUR ORDER");

        //Logout
        driver.findElement(By.id("react-burger-menu-btn")).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(500));
        wait.until(ExpectedConditions.elementToBeClickable(By.id("logout_sidebar_link"))).click();
    }
}