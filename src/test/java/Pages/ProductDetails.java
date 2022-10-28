package Pages;

import net.jodah.failsafe.internal.util.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import Enum.ProductBuyEnum;

public class ProductDetails {
    WebDriver driver;
    WebDriverWait wait;
    List<WebElement> allElementDisplay;
    String searchBar = "//div[@class='%s']//input[contains(@class,'nav-progressive-attribute')]";
    String addToCartButton = "//input[@name='%s']";
    By searchButton = By.xpath("//input[@id='nav-search-submit-button']");
    By product = By.linkText("OnePlus 10 Pro 5G (Volcanic Black, 8GB RAM, 128GB Storage)");
    By productDetails = By.xpath("//span[@id='productTitle']");
    By productInformation = By.xpath("//form[@id='twister']");
    By questionAndAnswers = By.xpath("//a[@id='askATFLink']");
    By verifyItemAdded = By.xpath("//div[@id='attachDisplayAddBaseAlert']");
    By cartButton = By.xpath("//span[@id='attach-sidesheet-view-cart-button']");


    public ProductDetails(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public void URL() throws IOException {
        FileReader reader = new FileReader("src/test/java/PropertiesFile/Config.properties");
        Properties data = new Properties();
        data.load(reader);
        driver.get(data.getProperty("url"));
    }

    public void Search() throws IOException {
        FileReader reader = new FileReader("src/test/java/PropertiesFile/Config.properties");
        Properties data = new Properties();
        data.load(reader);
        driver.findElement(By.xpath(String.format(searchBar, ProductBuyEnum.SearchBarInputBox.getResourcesName()))).sendKeys(data.getProperty("productName"));
        driver.findElement(By.xpath(String.format(searchBar, ProductBuyEnum.SearchButton.getResourcesName()))).click();
        driver.findElement(searchButton).click();
    }

    public void Mobile() {
        driver.findElement(product).click();
        String mainWindow = driver.getWindowHandle();
        ArrayList<String> childTabWindow = new ArrayList<String>(driver.getWindowHandles());
        System.out.println(mainWindow);
        System.out.println(childTabWindow);
        driver.switchTo().window(String.valueOf(childTabWindow.get(1)));
        String actual = driver.findElement(productDetails).getText();
        System.out.println("Name for the product is : " + actual);
    }

    public void ProductOptions() {
        String actual = driver.findElement(productInformation).getText();
        System.out.println("Product options are : \n" + actual);
    }

    public void QuestionAndAnswers() {
        driver.findElement(questionAndAnswers).click();
        List<WebElement> listofItems = driver.findElements(By.xpath("//div[@class='a-fixed-left-grid a-spacing-base']"));

        for (int i = 0; i < listofItems.size(); i++) {
            String text = listofItems.get(i).getText();
            System.out.println(text);
        }
    }

    public void AddToCart() {
        driver.findElement(By.xpath(String.format(addToCartButton, ProductBuyEnum.addToCartButton.getResourcesName()))).click();
        wait.until(ExpectedConditions.visibilityOf((WebElement) cartButton));
        wait.until(ExpectedConditions.visibilityOf((WebElement) verifyItemAdded));
        String actual = driver.findElement(verifyItemAdded).getText();
        System.out.println(actual);
        Assert.isTrue(actual.equals(""), "Expected result does not match with actual result");
    }

    public void Cart() {
        driver.findElement(cartButton).click();
    }

}