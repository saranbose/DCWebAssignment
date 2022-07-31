package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class BNZLandingPage {
	
	WebDriver driver;
	By Menu = By.xpath("//span[contains(text(),'Menu')]");
	By Payees = By.xpath("//span[contains(text(),'Payees')]");
	By paymentTransfer = By.xpath("//span[contains(text(),\"Pay or transfer\")]");

	public BNZLandingPage(WebDriver driver)
	{
		this.driver = driver;
	}
	public WebElement getMenu()
	{
		return driver.findElement(Menu);
	}
	public WebElement getPayees()
	{
		return driver.findElement(Payees);
	}
	public WebElement getPaymentTransfer()
	{
		return driver.findElement(paymentTransfer);
	}
}
