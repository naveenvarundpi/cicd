package rahulshettyacademy.pageobject;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckOut {
	
		WebDriver driver;
	public CheckOut(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath="//input[@placeholder='Select Country']")
	WebElement country;
	
	@FindBy(xpath="//button[@type='button']")
	List<WebElement>countryelements;
	@FindBy(className = "action__submit")
	WebElement checkout;
	
	
	public void enterandclickcountry(String countryname)
	{
		country.sendKeys(countryname);
		WebElement countryelement=countryelements.stream().filter(s->s.getText().equalsIgnoreCase(countryname)).findFirst().orElse(null); 
		countryelement.click();
	}
	public OrderPage placeorder()
	{
		checkout.click();
		OrderPage orderPage=new OrderPage(driver);
		return orderPage;
	}

}
