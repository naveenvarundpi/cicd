package rahulshettyacademy.pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.abstractclass.AbstractClass;

public class Loginpage extends AbstractClass{
	WebDriver driver;
	public Loginpage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(id="userEmail")
	WebElement useremailbox;
	@FindBy(id="userPassword")
	WebElement userpasswordbox;
	@FindBy(id="login")
	WebElement loginbutton;
	@FindBy(xpath="//div[@role='alert']")
	WebElement error;
	
	public ProductCatlogue login(String username,String userpassword)
	{
		useremailbox.sendKeys(username);
		userpasswordbox.sendKeys(userpassword);
		loginbutton.click();
		ProductCatlogue productcatlogue=new ProductCatlogue(driver);
		return productcatlogue;
	}
	public void launchbrowser(String url)
	{
		driver.get(url);
		
	}
	public String geterrormessage() 
	{
		
		//waitvisiblityof(error);
		return error.getText();	
		
	}
}
