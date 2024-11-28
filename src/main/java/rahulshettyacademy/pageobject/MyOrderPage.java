package rahulshettyacademy.pageobject;

import java.util.List;
import java.util.stream.Collectors;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.abstractclass.AbstractClass;

public class MyOrderPage extends AbstractClass{
	WebDriver driver;
	public MyOrderPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(xpath = "(//tr[@class='ng-star-inserted'])/td[2]")
	List<WebElement> product;
	@FindBy(xpath = "//button[text()='Delete']")
	WebElement deletebutton;
	List<WebElement> collect;
	public String getproductname(String productname)
	{
		//
		collect = product.stream().filter(s->s.getText().equals(productname)).collect(Collectors.toList());
		return collect.get(0).getText();
	}
	public void deleteproduct(String productname) 
	{
		
		//waitvisiblityof(deletebutton);
		deletebutton.click();
	}
	

}
