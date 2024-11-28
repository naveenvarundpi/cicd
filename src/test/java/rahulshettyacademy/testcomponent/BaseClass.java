package rahulshettyacademy.testcomponent;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import rahulshettyacademy.pageobject.Loginpage;

public class BaseClass 
{
	public WebDriver driver;
	String browsername;
	public String url;
	public Loginpage loginpage;
	String jsonfilepath;



	public WebDriver initializeDriver() throws IOException
	{
		Properties prop=new Properties();

		FileInputStream stream=new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\rahulshettyacademy\\resources\\Globaldata.properties");
		prop.load(stream);
		browsername=prop.getProperty("browser");
		url=prop.getProperty("url");

		if(browsername.equalsIgnoreCase("chrome"))
		{
			//ChromeOptions options =new ChromeOptions();
			//options.addArguments("headless");
			driver=new ChromeDriver();
		}
		else if(browsername.equalsIgnoreCase("firefox"))
		{//firefox driver code
		}

		else if(browsername.equalsIgnoreCase("edge"))
		{
			//edgedriver code
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		return driver;


	}
	@BeforeMethod(alwaysRun = true)
	public Loginpage driverlaunch() throws IOException
	{
		driver=initializeDriver();
		loginpage=new Loginpage(driver);
		loginpage.launchbrowser(url);
		return loginpage;
	}
	@AfterMethod(alwaysRun = true)
	public void closebrowser()
	{
		driver.close();
	}
	public List<HashMap<String, String>> getjsontohashmap(String jsonfilepath) throws IOException
	{
		String jsoncontent=FileUtils.readFileToString
				(new File(jsonfilepath),StandardCharsets.UTF_8);
		ObjectMapper mapper=new ObjectMapper();
		List<HashMap<String,String>> datas=
				mapper.readValue(jsoncontent, new TypeReference<List<HashMap<String,String>>>() {});
		return datas;
	}
	public String getscreenshot(String testcase,WebDriver driver) throws IOException
	{
		TakesScreenshot st=(TakesScreenshot)driver;
		File source=st.getScreenshotAs(OutputType.FILE);
		File dest=new File(System.getProperty("user.dir")+"//Reports//"+testcase+".png");
		FileUtils.copyFile(source, dest);
		return System.getProperty("user.dir")+"//Reports//"+testcase+".png";

	}
}
