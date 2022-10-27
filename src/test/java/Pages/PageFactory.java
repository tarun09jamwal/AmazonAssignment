package Pages;

import org.openqa.selenium.WebDriver;

public class PageFactory {
    WebDriver driver;
    private ProductDetails productDetails;

    public PageFactory(WebDriver driver) {
        this.driver = driver;
    }

    public ProductDetails getProductDetails() {
        if (productDetails == null) {
            productDetails = new ProductDetails(driver);
        }
        return productDetails;
    }
}

