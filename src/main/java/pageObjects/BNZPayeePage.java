package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class BNZPayeePage {
	
	WebDriver driver;
	By payeeTitle = By.xpath("//span[contains(text(),'Payees')]");
	By addPayee = By.xpath("//span[contains(text(),\"Add\")]");
	
	By payeeName = By.name("apm-name");
	By payeeBank = By.id("apm-bank");
	By payeeBranch = By.id("apm-branch");
	By payeeAccount = By.id("apm-account");
	By payeeSuffix = By.id("apm-suffix");
	
	By yourParticular = By.id("apm-this-particulars");
	By yourCode = By.id("apm-this-code");
	By yourReference = By.id("apm-this-reference");
	By payeeParticulars = By.id("apm-that-particulars");
	By payeeCode = By.id("apm-that-code");
	By payeeReference = By.id("apm-that-reference");
	By savePayee = By.xpath("//button[text()='Add']");
	By cofirmMessage = By.xpath("//span[contains(text(),\"Payee added\")]");
	By payeeNameError = By.className("field-error");
	By nameList = By.xpath("//span[@class=\"js-payee-name\"]");
	By nameTitle = By.xpath("//span[contains(text(),\"Name\")]");
	public BNZPayeePage(WebDriver driver)
	{
		this.driver = driver;
	}
	public WebElement getPayeeTitle()
	{
			return driver.findElement(payeeTitle);
	}
	public WebElement AddPayee()
	{
			return driver.findElement(addPayee);
	}
	public WebElement getPayeeName()
	{
			return driver.findElement(payeeName);
	}
	public WebElement getPayeeBank()
	{
			return driver.findElement(payeeBank);
	}
	public WebElement getPayeeBranch()
	{
			return driver.findElement(payeeBranch);
	}
	public WebElement getPayeeAccount()
	{
			return driver.findElement(payeeAccount);
	}
	public WebElement getPayeeSuffix()
	{
			return driver.findElement(payeeSuffix);
	}
	public WebElement getYourParticular()
	{
			return driver.findElement(yourParticular);
	}
	public WebElement getYourCode()
	{
			return driver.findElement(yourCode);
	}
	public WebElement getYourReference()
	{
			return driver.findElement(yourReference);
	}
	public WebElement getPayeeParticular()
	{
			return driver.findElement(payeeParticulars);
	}
	public WebElement getPayeeCode()
	{
			return driver.findElement(payeeCode);
	}
	public WebElement getPayeeReference()
	{
			return driver.findElement(payeeReference);
	}
	public WebElement getSavePayee()
	{
			return driver.findElement(savePayee);
	}
	public WebElement getCofirmMessage()
	{
			return driver.findElement(cofirmMessage);
	}
	public List<WebElement> getPayeeNameErrorList()
	{
			return driver.findElements(payeeNameError);
	}
	public List<WebElement> getPayeeNameList()
	{
			return driver.findElements(nameList);
	}
	public WebElement getNameTitle()
	{
		return driver.findElement(nameTitle);
	}
}