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
    String searchBar = "//div[@class='%s']//input[contains(@class,'nav-progressive-attribute')]";
    String addToCartButton = "//input[@name='%s']";
    String productQuantity = "//a[@id='%s']";
    By quantityDropdown = By.xpath("(//div[@class='a-row sc-action-links']//span[@class='a-button-inner'])[1]");
    By product = By.linkText("OnePlus 10 Pro 5G (Volcanic Black, 8GB RAM, 128GB Storage)");
    By productDetails = By.xpath("//span[@id='productTitle']");
    By productInformation = By.xpath("//form[@id='twister']");
    By questionAndAnswers = By.xpath("//a[@id='askATFLink']");
    By verifyItemAdded = By.xpath("//div[@id='attachDisplayAddBaseAlert']");
    By cartButton = By.xpath("//span[@id='attach-sidesheet-view-cart-button']");
    By verifyProduct = By.xpath("//div[@class='a-row a-spacing-mini sc-subtotal sc-subtotal-buybox sc-java-remote-feature']");

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
    }

    public void Mobile() {
        driver.findElement(product).click();
        ArrayList<String> childTabWindow = new ArrayList<String>(driver.getWindowHandles());
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
        List<WebElement> ele = driver.findElements(By.xpath("//div[@class='a-fixed-left-grid-col a-col-right' and @style='padding-left:1%;float:left;']"));
        int count = 0;
        for (WebElement que : ele) {
            System.out.println(que.getText());
            count++;
            if (count == 3) {
                break;
            }
        }
    }

    public void AddToCart() {
        driver.findElement(By.xpath(String.format(addToCartButton, ProductBuyEnum.addToCartButton.getResourcesName()))).click();
        wait.until(ExpectedConditions.elementToBeClickable(cartButton));
        String actual = driver.findElement(verifyItemAdded).getText();
        Assert.isTrue(actual.equals("Added to Cart"), "Expected result does not match with actual result");
    }

    public void Cart() {
        driver.findElement(cartButton).click();
        driver.findElement(quantityDropdown).click();
        driver.findElement(By.xpath(String.format(productQuantity, ProductBuyEnum.ProductQuantity.getResourcesName()))).click();
    }

    public void Verify() {
        String ele = driver.findElement(verifyProduct).getText();
        System.out.println(ele);
    }
}