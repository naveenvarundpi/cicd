package rahulshettyacademy.pageobject;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.abstractclass.AbstractClass;

public class Cart extends AbstractClass {

	WebDriver driver;
	public Cart(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath="//div[@class='cartSection']/h3")
	List<WebElement> productincart;
	@FindBy(xpath="//li[@class='totalRow']/button")
	WebElement checkout;
	
	public Boolean checkproductadded(String productname) 
	{
		Boolean	status=productincart.stream().anyMatch(s->s.getText().contains(productname));
		return status;
	}
	public CheckOut clickcheckout()
	{
		checkout.click();
		CheckOut checkout=new CheckOut(driver);
		return checkout;
	}

}
