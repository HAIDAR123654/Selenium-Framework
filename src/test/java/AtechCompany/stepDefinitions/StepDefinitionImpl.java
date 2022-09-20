package AtechCompany.stepDefinitions;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import AtechCompany.TestComponents.BaseTest;
import AtechCompany.pageObjects.CartPage;
import AtechCompany.pageObjects.CheckoutPage;
import AtechCompany.pageObjects.ConfirmationPage;
import AtechCompany.pageObjects.LandingPage;
import AtechCompany.pageObjects.ProductCatalogue;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinitionImpl extends BaseTest{
	    public LandingPage landingPage; 
	    public ProductCatalogue productCatalogue;
	    public ConfirmationPage confirmationPage;
        @Given ("I landed on Ecommerce Page")
        public void I_landed_on_Ecommerce_Page() throws IOException {
        	landingPage = launchApplication();
        }
        
        @Given ("^Logged in with username (.+)  and password (.+)$")
        public void logged_in_with_username_and_password(String username, String password) {
            productCatalogue = landingPage.loginApplication(username,password);
            
        }
        
        @When ("^I add product (.+) to cart$")
        public void i_add_product_to_cart(String productName) throws InterruptedException {
        	List<WebElement> products = productCatalogue.getProductList();
            productCatalogue.addProductToCart(productName);
        }
        
        @And("^checkout (.+) and submit the order$")
        public void checkout_submit_order(String productName) {
        	CartPage cartPage = productCatalogue.goToCartPage();
            boolean match = cartPage.VerifyProductDisplay(productName);
    	    Assert.assertTrue(match);
    	    
    	    CheckoutPage checkoutPage = cartPage.goToCheckout();
    	    checkoutPage.selectCountry("India");
    	    confirmationPage = checkoutPage.submitOrder();
        }
        
        @Then("{string} message is displayed on ConfirmationPage")
        public void message_displayed_confirmationPage(String string) {
        	String confirmMessage = confirmationPage.getConfirmationMessage();
    	    Assert.assertTrue(confirmMessage.equalsIgnoreCase(string));
            driver.close();
        }

        @Then("^\"([^\"]*)\" message is displayed$")
        public void something_message_is_displayed(String strArg1) {
            Assert.assertEquals(strArg1, landingPage.getErrorMessage());
            driver.close();
        }

        		


}
