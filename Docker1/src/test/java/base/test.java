package base;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class test {
	
	@BeforeSuite
	public void startContainer() throws InterruptedException
	{
		commonMethods.runTerminalCommand("docker-compose up","Docker Container is UP");
		Thread.sleep(10000);
		System.out.println("Docker Container is UP....");
	}

	@Test(priority=1)
	public void firstTest() throws InterruptedException, MalformedURLException {

		WebDriver driver;
		
		//Adding desired capabilities
		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setBrowserName("firefox");
		cap.setPlatform(Platform.LINUX);
		driver= new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),cap);

		// Launch Website  
		driver.navigate().to("http://www.google.com");  

		Thread.sleep(5000);
		//Maximize the browser  
		driver.manage().window().maximize();  
		Thread.sleep(3000);

		// Click on the Search button  
		driver.findElement(By.className("gLFyf")).sendKeys("iphone 14"+Keys.ENTER);
		Thread.sleep(30000);
		Assert.assertTrue(driver.getCurrentUrl().contains("iphone"));

		//Quiting the driver
		driver.quit();

		//Console printing logs
		System.out.println("Program exited....");

	}
	
	
	@AfterSuite
	public void stopContainer() throws InterruptedException
	{
		commonMethods.runTerminalCommand("docker-compose down","Docker Container is DOWN");
		Thread.sleep(10000);
		System.out.println("Docker Container is DOWN....");
	}

}
