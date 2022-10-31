package StepDefinition;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.jodah.failsafe.internal.util.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;
import Enum.ProductBuyEnum;

public class AddProductToCart extends BaseClass {
    String searchBar = "//div[@class='%s']//input[contains(@class,'nav-progressive-attribute')]";
    String addToCartButton = "//input[@name='%s']";
    String productQuantity = "//a[@id='%s']";
    By quantityDropdown = By.xpath("(//div[@class='a-row sc-action-links']//span[@class='a-button-inner'])[1]");
    By product = By.xpath("//span[contains(@class, 'a-color-base a-text-normal') and text() = 'OnePlus 10 Pro 5G (Volcanic Black, 8GB RAM, 128GB Storage)']");
    By productDetails = By.xpath("//span[contains(@class, 'product-title-word-break') and text() = '        OnePlus 10 Pro 5G (Volcanic Black, 8GB RAM, 128GB Storage)       ']");
    By productInformation = By.xpath("//div[contains(@class, 'addTwisterMargin')]");
    By questionAndAnswers = By.xpath("//a[contains(@class, 'askATFLink')]");
    By verifyItemAdded = By.xpath("//span[contains(@class, 'a-size-medium-plus')]");
    By cartButton = By.xpath("//span[contains(@class, 'a-button-text') and text() =' Cart '][1]");
    By verifyProduct = By.xpath("//div[@class='a-row a-spacing-mini sc-subtotal sc-subtotal-buybox sc-java-remote-feature']");


    @Given("User navigate to the url")
    public void user_navigate_to_the_url() throws IOException {
        Setup();
        FileReader reader = new FileReader("src/test/java/PropertiesFile/Config.properties");
        Properties data = new Properties();
        data.load(reader);
        driver.get(data.getProperty("url"));
    }

    @Given("Navigate to search section and search for mobile")
    public void navigate_to_search_section_and_search_for_mobile(DataTable mobileSearch) throws IOException {
        FileReader reader = new FileReader("src/test/java/PropertiesFile/Config.properties");
        Properties data1 = new Properties();
        data1.load(reader);
        List<List<String>> data = Collections.singletonList(mobileSearch.values());
        driver.findElement(By.xpath(String.format(searchBar, ProductBuyEnum.SearchBarInputBox.getResourcesName()))).sendKeys(data.get(0).get(0));
        driver.findElement(By.xpath(String.format(searchBar, ProductBuyEnum.SearchButton.getResourcesName()))).click();
        driver.navigate().back();
        driver.findElement(By.xpath(String.format(searchBar, ProductBuyEnum.SearchBarInputBox.getResourcesName()))).sendKeys(data.get(0).get(1));
        driver.findElement(By.xpath(String.format(searchBar, ProductBuyEnum.SearchButton.getResourcesName()))).click();


    }

    @Given("Select the specific mobile capture the Name of the product")
    public void select_the_specific_mobile_capture_the_name_of_the_product() {
        driver.findElement(product).click();
        ArrayList<String> childTabWindow = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(String.valueOf(childTabWindow.get(1)));
        String actual = driver.findElement(productDetails).getText();
        System.out.println("Name for the product is : " + actual);

    }

    @Given("Check for the Size Name and colors available and print the same on console")
    public void check_for_the_size_name_and_colors_available_and_print_the_same_on_console() {
        String actual = driver.findElement(productInformation).getText();
        System.out.println("Product options are : \n" + actual);

    }

    @Given("Navigate to Customer questions answers and list top three questions and answers")
    public void navigate_to_customer_questions_answers_and_list_top_three_questions_and_answers() {
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

    @When("Add the item to the cart")
    public void add_the_item_to_the_cart() {
        driver.findElement(By.xpath(String.format(addToCartButton, ProductBuyEnum.addToCartButton.getResourcesName()))).click();
        wait.until(ExpectedConditions.elementToBeClickable(cartButton));
        String actual = driver.findElement(verifyItemAdded).getText();
        Assert.isTrue(actual.equals("Added to Cart"), "Expected result does not match with actual result");

    }

    @Then("Navigate to Shopping Cart button increase the order Quantity to four")
    public void navigate_to_shopping_cart_button_increase_the_order_quantity_to_four() {
        driver.findElement(cartButton).click();
        driver.findElement(quantityDropdown).click();
        driver.findElement(By.xpath(String.format(productQuantity, ProductBuyEnum.ProductQuantity.getResourcesName()))).click();

    }

    @Then("Verify the item quantity inside the cart and list the total amount for the order")
    public void verify_the_item_quantity_inside_the_cart_and_list_the_total_amount_for_the_order() {
        String ele = driver.findElement(verifyProduct).getText();
        System.out.println(ele);

    }

}
