package rahulshettyacademy.pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrderPage {
	WebDriver driver;
	public OrderPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);


	}
	@FindBy(tagName = "h1")
	WebElement order;
	
	public Boolean checkorder(String expectedordertext)
	{
		String ordertext=order.getText();
		Boolean orderresult=ordertext.equalsIgnoreCase(expectedordertext);
		return orderresult;
		
	}
}
