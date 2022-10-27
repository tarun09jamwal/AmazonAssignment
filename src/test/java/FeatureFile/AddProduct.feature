Feature: to test website functionality
Scenario: add product to the cart
  Given User navigate to the url
  And Navigate to search section and search for mobile
  And  Select the specific mobile capture the Name of the product
  And Check for the Size Name and colors available and print the same on console
  And  Navigate to Customer questions answers and list top three questions and answers
  When  Add the item to the cart
  Then  Navigate to Shopping Cart button increase the order Quantity to four
  And Verify the item quantity inside the cart and list the total amount for the order