package rahulshettyacademy.abstractclass;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import rahulshettyacademy.pageobject.Cart;
import rahulshettyacademy.pageobject.MyOrderPage;

public class AbstractClass {
	WebDriver driver;
	WebDriverWait wait;
	public AbstractClass(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(xpath ="//button[@routerlink='/dashboard/cart']")
	WebElement  cart;
	By toast=By.id("toast-container");
	By animate=By.cssSelector(".ng-animating");
	@FindBy(xpath="//button[@routerlink='/dashboard/myorders']")
	WebElement order;
	public void waitvisiblityofelement(By by)
	{
		wait=new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(by));	
	}
	public void waitinvisiblityofelement(By by)
	{
		wait=new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(by));	
	}
	public void waitvisiblityof(WebElement by)
	{
		wait=new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.invisibilityOf(by));	
	}
	public Cart clickcart() throws InterruptedException
	{
		
		waitvisiblityofelement(toast);
		Thread.sleep(2000);
		//waitinvisiblityofelement(animate);
		cart.click();
		Cart cart=new Cart(driver);
		return cart;
	}
	public MyOrderPage clickorder()
	{
		
		order.click();
		MyOrderPage myorderpage=new MyOrderPage(driver);
		return myorderpage;
	}
}
