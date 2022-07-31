package resources;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class base {
	public static WebDriver driver;
	public Properties prop;
	public WebDriver initializeDriver() throws IOException
	{	
		prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\resources\\data.properties");
		prop.load(fis);
		//String browser = prop.getProperty("browser");
		String browser = System.getProperty("browser");
		
		if (browser.contains("Chrome"))
		{
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\src\\main\\java\\resources\\chromedriver1.exe");
			ChromeOptions options = new ChromeOptions();
			if (browser.contains("headless"))
				options.addArguments("--headless"); // OR options.setHeadless(true);
			
			driver = new ChromeDriver(options);
			
		} else if (browser.equals("Firefox"))
		{
			System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+"\\src\\main\\java\\resources\\geckodriver.exe");
			FirefoxOptions options = new FirefoxOptions();
			options.addArguments("headless"); // OR options.setHeadless(true);
			driver = new FirefoxDriver(options);
		
		}else if (browser.equals("Edge"))
		{
			System.setProperty("webdriver.edge.driver", System.getProperty("user.dir")+"\\src\\main\\java\\resources\\edgedriver.exe");
			driver = new EdgeDriver();
		
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.manage().window().maximize();
		return driver;
	}
	public String getScreenshot(WebDriver driver, String testcaseName) throws IOException
	{
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		String destination = System.getProperty("user.dir")+"\\reports\\"+testcaseName+".png";
		FileUtils.copyFile(source,new File(destination));
		return destination;
	}
}
