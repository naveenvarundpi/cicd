package rahulshettyacademy.tests;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import rahulshettyacademy.pageobject.Cart;
import rahulshettyacademy.pageobject.ProductCatlogue;
import rahulshettyacademy.testcomponent.BaseClass;

public class ErrorValidation extends BaseClass{
	
	@Test
	public void incorrectlogin() throws IOException {
		// TODO Auto-generated method stub
		//WebDriver driver=new ChromeDriver();
		String username="naveen@gmailyahoo.com";
		String password="Varun@12345";	
		loginpage.login(username, password);
		String geterrormessage = loginpage.geterrormessage();
		Assert.assertEquals(geterrormessage,"Incorrect email1 or password.");		
		}
	@Test
		public void incorrectproduct() throws InterruptedException
		{
		String username="naveen@gmailyahoo.com";
		String password="Varun@1234";
			ProductCatlogue productcatlogue=loginpage.login(username, password);
			String productname="ZARA COAT 3";
			List<WebElement> products=productcatlogue.findproducts();
			WebElement product=productcatlogue.getproduct(productname,products);
			productcatlogue.addtocart(product);
			Cart cart=productcatlogue.clickcart();
			Boolean status=cart.checkproductadded("ZARA COAT 33");
			Assert.assertFalse(status);
		}
		

}
