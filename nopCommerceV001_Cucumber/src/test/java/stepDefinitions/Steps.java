package stepDefinitions;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.cucumber.java.en.*;
import pageObjects.AddCustomerPage;
import pageObjects.LoginPage;
import pageObjects.ProductPage;
import utilities.commonUtil;


public class Steps extends BaseClass {

	@Given("User launch Chrome browser")
	public void user_launch_chrome_browser() {
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"//Drivers/chromedriver");
		driver = new ChromeDriver();
		lp = new LoginPage(driver);
		commonUtil.waitForPageLoad();
	}

	@When("User open URL {string}")
	public void user_open_url(String url) {
		driver.get(url);
	}

	@When("User enters email as {string} and password as {string}")
	public void user_enters_email_as_and_password_as(String email, String password) {
		lp.setUserEmail(email);
		lp.setPassword(password);
	}

	@Then("User is on login page")
	public void user_is_on_login_page(){
		lp.isOnLoginPage();
	}


	@When("Click on login")
	public void click_on_login() {
		lp.clickLogin();
	}

	@Then("Page Title should be {string}")
	public void page_title_should_be(String title) {

		System.out.println("apa   "+ driver.getTitle());
		Assert.assertEquals(title, driver.getTitle());
		if(driver.getPageSource().contains("Login was unsuccessful.")) {
			driver.close();
		}
	}

	@When("User click on Log Out button")
	public void user_click_on_log_out_button() throws InterruptedException {
		lp.clickLogout();
		Thread.sleep(3000);
	}

	@Then("close browser")
	public void close_browser() {
		driver.close();
	}

	//-------- customers
	@And("User can view dashboard")
	public void user_can_view_dashboard(){
		addCust=new AddCustomerPage(driver);
		Assert.assertEquals("Dashboard / nopCommerce administration",addCust.getPageTitle());

	}
	@When("User click on customers menu")
	public void user_click_on_customers_menu() throws InterruptedException{
		Thread.sleep(3000);
		addCust.clickOnCustomersMenu();
	}

	@When("click on customers menu item")
	public void click_on_customers_menu_item() throws InterruptedException{
		Thread.sleep(2000);
		addCust.clickOnCustomersMenuItem();
	}

	@When("click on add new button")
	public void click_on_add_new_button() throws InterruptedException{
		addCust.clickOnAddNew();
		Thread.sleep(2000);
	}

	@Then("user can view add new customer page")
	public void user_can_view_add_new_customer_page() {
		Assert.assertEquals("Add a new customer / nopCommerce administration",addCust.getPageTitle());
	}

	@When("user enter customer info")
	public void user_enter_customer_info() throws InterruptedException{
		String email=randomestring()+"@gmail.com";
		addCust.setEmail(email);
		addCust.setPassword("test123");
		addCust.setGender("Male");
		addCust.setFirstName("Anto");
		addCust.setLastName("Widodo");
		addCust.setDob("11/22/2023");
		addCust.setCompanyName("busyQA");
		addCust.setCustomerRoles("Guest");
		addCust.setManagerOfVendor("Vendor 2");
		addCust.setAdminComment("This is data testing........");
		Thread.sleep(2000);
	}

	@When("click on save button")
	public void click_on_save_button() throws InterruptedException{
		addCust.clickOnSave();
		Thread.sleep(2000);
	}

	@Then("user can view confirmation message {string}")
	public void user_can_view_confirmation_message(String string) {
		Assert.assertTrue(driver.findElement(By.tagName("body")).getText().contains("The new customer has been added successfully"));
	}

	@Then("user can view customer page")
	public void user_view_customer_page(){
		addCust.isOnCustomerPage();
	}

	@When("User click on catalog menu")
	public void user_click_on_catalog_menu(){
		prod=new ProductPage(driver);
		prod.userClickCatalogMenu();
	}
	@And("User click on products menu item")
	public void user_click_on_product_menu_item() {
		prod.userClickProductMenuItem();
	}

	@Then("User is on product page")
	public void user_is_on_product_page(){
		prod.userIsOnProductPage();
	}
	
}
