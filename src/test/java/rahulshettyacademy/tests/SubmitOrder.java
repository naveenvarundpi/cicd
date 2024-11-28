package rahulshettyacademy.tests;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import rahulshettyacademy.pageobject.Cart;
import rahulshettyacademy.pageobject.CheckOut;
import rahulshettyacademy.pageobject.MyOrderPage;
import rahulshettyacademy.pageobject.OrderPage;
import rahulshettyacademy.pageobject.ProductCatlogue;
import rahulshettyacademy.testcomponent.BaseClass;
import rahulshettyacademy.testcomponent.RetryListeners;

public class SubmitOrder extends BaseClass{
	@Test(dataProvider = "getdata",groups = "Addproduct",retryAnalyzer =RetryListeners.class)
	public void submitorder(HashMap<String, String> input) throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		//WebDriver driver=new ChromeDriver();
		String email=input.get("email");
		String password=input.get("password");
		String productname=input.get("product");
		String country="india";
		String expectedordertext="Thankyou for the order."; 

		ProductCatlogue productcatlogue=loginpage.login(email,password );

		List<WebElement> products=productcatlogue.findproducts();
		WebElement product=productcatlogue.getproduct(productname,products);
		productcatlogue.addtocart(product);
		Cart cart=productcatlogue.clickcart();
		Boolean status=cart.checkproductadded(productname);
		Assert.assertTrue(status);
		CheckOut checkout=cart.clickcheckout();

		checkout.enterandclickcountry(country);
		OrderPage orderPage=checkout.placeorder();

		Boolean orderresult=orderPage.checkorder(expectedordertext);
		Assert.assertTrue(orderresult);

	}
	@Test(dataProvider = "getdata",dependsOnMethods = "submitorder")
	public void verifyorder(HashMap<String, String> input)
	{
		String email=input.get("email");
		String password=input.get("password");
		String productname=input.get("product");
		ProductCatlogue productcatlogue=loginpage.login(email, password);
		MyOrderPage myorderpage= productcatlogue.clickorder();
		String getproductname = myorderpage.getproductname(productname);
		Assert.assertEquals(getproductname, productname);
		myorderpage.deleteproduct(productname);
	}
	@DataProvider
	public Object[][] getdata() throws IOException
	{

		Properties prop=new Properties();

		FileInputStream stream=new FileInputStream(System.getProperty("user.dir")+
				"\\src\\main\\java\\rahulshettyacademy\\resources\\Globaldata.properties");
		prop.load(stream); String
		jsonfilepath=System.getProperty("user.dir").concat(prop.getProperty(
				"jsonfilepath")); List<HashMap<String,String>> getjsontohashmap =
				getjsontohashmap(jsonfilepath);

				HashMap<String , String> map=getjsontohashmap.get(0);
				HashMap<String , String> map1=getjsontohashmap.get(1);


				/*
				 * HashMap< String, String> map=new HashMap<String,String>(); map.put("email",
				 * "naveen@gmailyahoo.com"); map.put("password", "Varun@1234");
				 * map.put("product", "ZARA COAT 3"); HashMap< String, String> map1=new
				 * HashMap<String,String>(); map1.put("email", "naveen1@gmailyahoo.com");
				 * map1.put("password", "Varun@1234"); map1.put("product", "ADIDAS ORIGINAL");
				 */


				// Object Object[][] = {{"naveen@gmailyahoo.com","Varun@1234","ZARA COAT 3"},
				//{"naveen@gmailyahoo.com","Varun@1234","ADIDAS ORIGINAL"}};

				return new Object[][] {{map},{map1}};
	}
	

}
