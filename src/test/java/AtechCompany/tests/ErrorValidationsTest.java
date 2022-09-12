package AtechCompany.tests;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import AtechCompany.TestComponents.Retry;
import AtechCompany.TestComponents.BaseTest;
import AtechCompany.pageObjects.CartPage;
import AtechCompany.pageObjects.ProductCatalogue;

public class ErrorValidationsTest extends BaseTest{
	
	@Test(groups = {"ErrorHandling"},retryAnalyzer=Retry.class)
	public void LoginErrorValidation() {
    landingPage.loginApplication("ram.kumar@gmail.com", "RAMk123");
    Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());
	}
	
	@Test
	public void ProductErrorValidation() throws IOException, InterruptedException {	
		String productName = "ZARA COAT 3";
        ProductCatalogue productCatalogue = landingPage.loginApplication("pankaj.kumar@gmail.com", "PANKAJ@k123");
        List<WebElement> products = productCatalogue.getProductList();
        productCatalogue.addProductToCart(productName);
        
        CartPage cartPage = productCatalogue.goToCartPage();
        boolean match = cartPage.VerifyProductDisplay("ZARA COAT 33");
	    Assert.assertFalse(match);	    
	    
	}
	
}
