package StepDefinition;

import Pages.BaseClass;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.io.IOException;

public class AddProductToCart extends BaseClass {
    @Given("User navigate to the url")
    public void user_navigate_to_the_url() throws IOException {
        Setup();
        pageFactory.getProductDetails().URL();

    }

    @Given("Navigate to search section and search for mobile")
    public void navigate_to_search_section_and_search_for_mobile() throws IOException {
        pageFactory.getProductDetails().Search();

    }

    @Given("Select the specific mobile capture the Name of the product")
    public void select_the_specific_mobile_capture_the_name_of_the_product() {
        pageFactory.getProductDetails().Mobile();

    }

    @Given("Check for the Size Name and colors available and print the same on console")
    public void check_for_the_size_name_and_colors_available_and_print_the_same_on_console() {
        pageFactory.getProductDetails().ProductOptions();

    }

    @Given("Navigate to Customer questions answers and list top three questions and answers")
    public void navigate_to_customer_questions_answers_and_list_top_three_questions_and_answers() {
        pageFactory.getProductDetails().QuestionAndAnswers();

    }

    @When("Add the item to the cart")
    public void add_the_item_to_the_cart()  {
        pageFactory.getProductDetails().AddToCart();

    }

    @Then("Navigate to Shopping Cart button increase the order Quantity to four")
    public void navigate_to_shopping_cart_button_increase_the_order_quantity_to_four() {
        pageFactory.getProductDetails().Cart();

    }

    @Then("Verify the item quantity inside the cart and list the total amount for the order")
    public void verify_the_item_quantity_inside_the_cart_and_list_the_total_amount_for_the_order() {
        pageFactory.getProductDetails().Verify();

    }

}
