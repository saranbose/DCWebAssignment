package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class BNZPaymentPage {
	
	WebDriver driver;
	By everydayBalance = By.xpath("//h3[@title=\"Everyday\"]/parent::span/parent::div/span[3][@class=\"account-balance\"]");
	By billsBalance = By.xpath("//h3[@title=\"Bills \"]/parent::span/parent::div/span[3][@class=\"account-balance\"]");
	By fromAccountChoose = By.xpath("//button[@data-testid='from-account-chooser']");
	By toAccountChoose = By.xpath("//button[@data-testid='to-account-chooser']");
	By fromAccount = By.xpath("//p[contains(text(),'Everyday')]");
	By toAccountTab = By.xpath("//li[@data-testid='to-account-accounts-tab']");
	By toAccount = By.xpath("//p[contains(text(),'Bills ')]");
	By amount = By.xpath("//input[@name='amount']");
	By transfer = By.xpath("//button[@data-monitoring-label='Transfer Form Submit']");
	By transferCofirmMessage = By.xpath("//span[contains(text(),'Transfer successful')]");
	
	public BNZPaymentPage(WebDriver driver)
	{
		this.driver = driver;
	}
	public WebElement getEverydayBalance()
	{
			return driver.findElement(everydayBalance);
	}
	public WebElement getBillsBalance()
	{
		return driver.findElement(billsBalance);
	}
	public WebElement getFromAccountList()
	{
		return driver.findElement(fromAccountChoose);
	}
	public WebElement selectFromAccount()
	{
		return driver.findElement(fromAccount);
	}
	public WebElement getToAccountList()
	{
		return driver.findElement(toAccountChoose);
	}
	
	public WebElement selectToAccountTab()
	{
		return driver.findElement(toAccountTab);
	}
	public WebElement selectToAccount()
	{
		return driver.findElement(toAccount);
	}
	public WebElement Amount()
	{
		return driver.findElement(amount);
	}
	public WebElement Transfer()
	{
		return driver.findElement(transfer);
	}
	public WebElement getTransferCofirmMessage()
	{
			return driver.findElement(transferCofirmMessage);
	}
}
