package AtechCompany.tests;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import AtechCompany.TestComponents.BaseTest;
import AtechCompany.pageObjects.CartPage;
import AtechCompany.pageObjects.CheckoutPage;
import AtechCompany.pageObjects.ConfirmationPage;
import AtechCompany.pageObjects.OrderPage;
import AtechCompany.pageObjects.ProductCatalogue;

public class SubmitOrderTest extends BaseTest{
	String productName = "ZARA COAT 3";

	@Test(dataProvider = "getData", groups = {"Purchase"})
	public void submitOrder(HashMap<String, String> input) throws IOException, InterruptedException {	
        ProductCatalogue productCatalogue = landingPage.loginApplication(input.get("email"),input.get("password"));
        List<WebElement> products = productCatalogue.getProductList();
        productCatalogue.addProductToCart(input.get("product"));
        
        CartPage cartPage = productCatalogue.goToCartPage();
        boolean match = cartPage.VerifyProductDisplay(input.get("product"));
	    Assert.assertTrue(match);
	    
	    CheckoutPage checkoutPage = cartPage.goToCheckout();
	    checkoutPage.selectCountry("India");
	    ConfirmationPage confirmationPage = checkoutPage.submitOrder();
	    
	    
	    String confirmMessage = confirmationPage.getConfirmationMessage();
	    Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
	    }
	
        @Test(dependsOnMethods = {"submitOrder"})
        public void OrderHistoryTest() {
            ProductCatalogue productCatalogue = landingPage.loginApplication("ram.kumar@gmail.com", "RAM@k123");
            OrderPage ordersPage = productCatalogue.goToOrdersPage();
            Assert.assertTrue(ordersPage.verifyOrderDisplay(productName));

            
        }
        
  
          
          @DataProvider
          public Object[][] getData() throws IOException{

        	  
        	  List<HashMap<String, String>> data = getJsonDataToMap(System.getProperty("user.dir")+"\\src\\test\\java\\AtechCompany\\data\\PurchaseOrder.json");
        	  return new Object[][] {{data.get(0)},{data.get(1)}};
          }
//    	  HashMap<String, String> map  = new HashMap<String, String>();
//    	  map.put("email", "ram.kumar@gmail.com");
//    	  map.put("password","RAM@k123");
//    	  map.put("product","ZARA COAT 3");
//    	  
//    	  HashMap<String, String> map1  = new HashMap<String, String>();
//    	  map1.put("email", "pankaj.kumar@gmail.com");
//    	  map1.put("password","PANKAJ@k123");
//    	  map1.put("product","ADIDAS ORIGINAL");
//        return new Object[][] {{map},{map1}};
        
//        @DataProvider
//        public Object[][] getData(){
//			return new Object[][] {{"ram.kumar@gmail.com", "RAM@k123","ZARA COAT 3"},{"pankaj.kumar@gmail.com", "PANKAJ@k123","ADIDAS ORIGINAL"}};
//       	 
//        }
}
