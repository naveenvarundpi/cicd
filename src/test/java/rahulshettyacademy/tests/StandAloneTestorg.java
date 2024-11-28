package rahulshettyacademy.tests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class StandAloneTestorg {
	public static void main(String[]  args) {
		// TODO Auto-generated method stub
		WebDriver driver=new ChromeDriver();
		String productname="ZARA COAT 3";
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		driver.get("https://rahulshettyacademy.com/client");
		//Loginpage loginpage=new Loginpage(driver);
		
		driver.findElement(By.id("userEmail")).sendKeys("naveen@gmailyahoo.com");
		driver.findElement(By.id("userPassword")).sendKeys("Varun@1234");
		driver.findElement(By.id("login")).click();
		List<WebElement> findElements = driver.findElements(By.className("card-body"));
		WebElement product=findElements.stream().filter(s->s.findElement(By.xpath("h5/b")).getText().contains(productname)).findFirst().orElse(null);
		product.findElement(By.xpath("button[@style='float: right;']")).click();
		/*
		 * String alerttext=driver.findElement(By.id("//div[@role='alert']")).getText();
		 */
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("toast-container")));
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		driver.findElement(By.xpath("//button[@routerlink='/dashboard/cart']")).click();
		//System.out.println(alerttext).;
		List<WebElement> productincart=driver.findElements(By.xpath("//div[@class='cartSection']/h3"));
		Boolean status=productincart.stream().anyMatch(s->s.getText().contains(productname));
		Assert.assertTrue(status);
		driver.findElement(By.xpath("//li[@class='totalRow']/button")).click();
		driver.findElement(By.xpath("//input[@placeholder='Select Country']")).sendKeys("ind");
		List<WebElement> countryelements=driver.findElements(By.xpath("//button[@type='button']"));
		WebElement countryelement= countryelements.stream().filter(s->s.getText().equals("India")).findFirst().orElse(null);
		countryelement.click();
		driver.findElement(By.className("action__submit")).click();
		String ordertext=driver.findElement(By.tagName("h1")).getText();
		String expectedordertext="Thankyou for the order.";
		Boolean orderresult=ordertext.equalsIgnoreCase(expectedordertext);
		Assert.assertTrue(orderresult);
		
	}

}
