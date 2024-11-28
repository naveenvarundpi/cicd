package rahulshettyacademy.pageobject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import rahulshettyacademy.abstractclass.AbstractClass;

public class ProductCatlogue extends AbstractClass{
	WebDriver driver;
	public ProductCatlogue(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(className="card-body")
	List<WebElement> products;
	@FindBy(xpath="button[@style='float: right;']")
	WebElement addtoart;
	
	
	WebElement product;
	By cartele=By.xpath("button[@style='float: right;']");
	
	public List<WebElement> findproducts()
	{
		return products;
		
	}
	public WebElement getproduct(String productname,List<WebElement> elements)
	{
		
		product= elements.stream().filter(s->s.findElement(By.xpath("h5/b")).getText().contains(productname)).findFirst().orElse(null);
		return product;
		
	}
	public void addtocart(WebElement element)
	{
		element.findElement(cartele).click();
	}
	
	
}
