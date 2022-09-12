package AtechCompany.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AtechCompany.AbstractComponents.AbstractComponents;

public class LandingPage extends AbstractComponents{
		WebDriver driver;
		
		public LandingPage(WebDriver driver) {
			// initialization of driver
			super(driver);
			this.driver = driver;
			PageFactory.initElements(driver, this);
		}
		
		//WebElement userEmail = driver.findElement(By.id("userEmail"));
		
		//using pageFactory method
		@FindBy(id="userEmail")
		WebElement userEmail;
		
		@FindBy(id="userPassword")
		WebElement passwordEle;
		
		@FindBy(id="login")
		WebElement submit;
		
		@FindBy(css = "[class*='flyInOut']")
		WebElement errorMessage;
		//Action methods...
		
		public ProductCatalogue loginApplication(String email, String password) {
			userEmail.sendKeys(email);
			passwordEle.sendKeys(password);
			submit.click();
			ProductCatalogue productCatalogue = new ProductCatalogue(driver);
			return productCatalogue;
		}
        
		public String getErrorMessage() {
			waitForWebElementToAppear(errorMessage);
			return errorMessage.getText();
		}
		public void goTo() {
	        driver.get("https://rahulshettyacademy.com/client/");

		}
	

}
