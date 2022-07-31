package stepDefinitions;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.BNZLandingPage;
import pageObjects.BNZPayeePage;
import pageObjects.BNZPaymentPage;
import resources.base;

public class stepDefintion extends base {

	public static Logger log = LogManager.getLogger(base.class.getClass());
	BNZLandingPage bnzLandingpage;
	BNZPayeePage bnzPayeePage;
	BNZPaymentPage bnzPaymentPage;

	Double priorEverydayBal, priorBillsBal, afterEverydayBal, afterBillsBal;

	@Given("DemoBNZ Webpage is opened")
	public void demo_bnz_webpage_is_opened() throws IOException {
		driver = initializeDriver();
		log.info("Driver initialized successfully");
		driver.get(prop.getProperty("url"));
		log.info("BNZ Demo Website opened");
	}

	@When("I Select Payees from Menu")
	public void i_select_payees_from_menu() {
		bnzLandingpage = new BNZLandingPage(driver);
		bnzLandingpage.getMenu().click();
		bnzLandingpage.getPayees().click();
		log.info("Payee selected from Menu");
	}

	@Then("Payee page should be loaded")
	public void payee_page_should_be_loaded() {
		bnzPayeePage = new BNZPayeePage(driver);
		Assert.assertEquals(bnzPayeePage.getPayeeTitle().getText(), "Payees");
		log.info("Payee page loaded successfully");
	}

	@And("Close the Browser")
	public void close_the_browser() {
		driver.quit();
	}

	@When("^I Add Payee details$")
	public void i_add_payee_details() throws InterruptedException {
		bnzPayeePage = new BNZPayeePage(driver);
		bnzPayeePage.AddPayee().click();
		bnzPayeePage.getPayeeName().sendKeys("ABCPayee" + Keys.ENTER);

		bnzPayeePage.getPayeeBank().sendKeys("00");
		bnzPayeePage.getPayeeBranch().sendKeys("0000");
		bnzPayeePage.getPayeeAccount().sendKeys("0000000");
		bnzPayeePage.getPayeeSuffix().sendKeys("000");

		bnzPayeePage.getYourParticular().sendKeys("Rent");
		bnzPayeePage.getYourCode().sendKeys("FlatA");
		bnzPayeePage.getYourReference().sendKeys("Mike");

		bnzPayeePage.getPayeeParticular().sendKeys("Rent");
		bnzPayeePage.getPayeeCode().sendKeys("FlatB");
		bnzPayeePage.getPayeeReference().sendKeys("Paul");

		bnzPayeePage.getSavePayee().click();
	}

	@Then("^Payee should get added$")
	public void payee_should_get_added() throws Throwable {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(bnzPayeePage.getCofirmMessage()));
		Assert.assertEquals(bnzPayeePage.getCofirmMessage().getText(), "Payee added");
		log.info("Payee details added successfully");
	}

	@When("I Add Payee details with No values entered")
	public void i_add_payee_details_with_no_values_entered() {
		bnzPayeePage = new BNZPayeePage(driver);
		bnzPayeePage.AddPayee().click();
		bnzPayeePage.getSavePayee().click();
		log.info("Tring to add Payee details added without any mandatory fields");
	}

	@Then("Validation error message displayed for Payee Name")
	public void validation_error_message_displayed_for_payee_name() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(bnzPayeePage.getPayeeName()));
		Assert.assertEquals(bnzPayeePage.getPayeeName().getAttribute("aria-label"),
				"Payee Name is a required field. Please complete to continue.");
		log.info("Payee Name validation error displayed since mandatory fields are missing");
	}

	@When("I Add Payee details with mandatory fields entered")
	public void i_add_payee_details_with_mandatory_fields_entered() {
		bnzPayeePage.getPayeeName().sendKeys("ABCPayee" + Keys.ENTER);
		bnzPayeePage.getPayeeBank().sendKeys("00");
		bnzPayeePage.getPayeeBranch().sendKeys("0000");
		bnzPayeePage.getPayeeAccount().sendKeys("0000000");
		bnzPayeePage.getPayeeSuffix().sendKeys("000");
		bnzPayeePage.getSavePayee().click();
	}

	@Then("Validation error message is not displayed")
	public void validation_error_message_is_not_displayed() {
		List<WebElement> elementList = bnzPayeePage.getPayeeNameErrorList();
		if (elementList.size() > 0)
			Assert.assertTrue(false); // If still error present, then it fails alert
		else
			Assert.assertTrue(true); // If still error doesn't present, then it pass the alert
		log.info("Payee details No validation error message displayed since mandatory fields added");
	}

	@Then("Payees list should be displayed in {string}")
	public void payees_list_should_be_displayed_in(String SortOrder) {
		List<WebElement> elementList = bnzPayeePage.getPayeeNameList();
		ArrayList<String> actualList = new ArrayList<String>();

		for (WebElement we : elementList) {
			actualList.add(we.getText());
		}

		ArrayList<String> expectedSortedList = new ArrayList<String>();
		for (String s : actualList) {
			expectedSortedList.add(s);
		}
		if (SortOrder.equalsIgnoreCase("ascending order")) {
			Collections.sort(expectedSortedList);
			Assert.assertEquals(actualList, expectedSortedList);
		} else if (SortOrder.equalsIgnoreCase("descending order")) {
			Collections.sort(expectedSortedList, Collections.reverseOrder());
			Assert.assertEquals(actualList, expectedSortedList);
		}
		log.info("Payee list should be displayed in " + SortOrder);
	}

	@When("I Click Name Header")
	public void i_click_name_header() {
		bnzPayeePage.getNameTitle().click();
		log.info("Payee Name Clicked to display in reverse order");
	}

	@Given("I Save current balance of Everyday and Bills Account")
	public void i_save_current_balance_of_everyday_and_bills_account() {
		bnzPaymentPage = new BNZPaymentPage(driver);
		String bal = bnzPaymentPage.getEverydayBalance().getText();
		bal = bal.replace(",", "");
		priorEverydayBal = Double.parseDouble(bal);

		bal = bnzPaymentPage.getBillsBalance().getText();
		bal = bal.replace(",", "");
		priorBillsBal = Double.parseDouble(bal);
		log.info("Current balanced for Everyday and Bills Account saved successfully");
	}

	@When("I make {string} Payment Transfer from Everyday to Bills Account")
	public void i_make_payment_transfer_from_everyday_to_bills_account(String amount) {
		bnzLandingpage = new BNZLandingPage(driver);
		bnzLandingpage.getMenu().click();
		bnzLandingpage.getPaymentTransfer().click();
		bnzPaymentPage.getFromAccountList().click();
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].click()", bnzPaymentPage.selectFromAccount());
		
		bnzPaymentPage.getToAccountList().click();
		jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].click()", bnzPaymentPage.selectToAccountTab());
		jse.executeScript("arguments[0].click()", bnzPaymentPage.selectToAccount());

		bnzPaymentPage.Amount().sendKeys(amount);
		bnzPaymentPage.Transfer().click();
	}

	@Then("Transfer should get completed successfully")
	public void transfer_should_get_completed_successfully() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(bnzPaymentPage.getTransferCofirmMessage()));
		Assert.assertEquals(bnzPaymentPage.getTransferCofirmMessage().getText(), "Transfer successful");
		log.info("Payment Transfer triggered successfully");
	}

	@Then("Current balance of Everyday and Bills Account should be correct")
	public void current_balance_of_everyday_and_bills_account_should_be_correct() {
		afterBillsBal = 920.00;
		afterEverydayBal = 2000.00;
		String bal = bnzPaymentPage.getEverydayBalance().getText();
		bal = bal.replace(",", "");
		Assert.assertEquals(afterEverydayBal, Double.parseDouble(bal));

		bal = bnzPaymentPage.getBillsBalance().getText();
		bal = bal.replace(",", "");
		Assert.assertEquals(afterBillsBal, Double.parseDouble(bal));
		log.info("Current balance of everyday and bills account should be correct after Payment Transferred");
	}
}