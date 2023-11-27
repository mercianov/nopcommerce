package pageObjects;

import com.applitools.eyes.MatchLevel;
import com.applitools.eyes.selenium.Eyes;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import utilities.commonUtil;

import static utilities.commonUtil.enterText;
import static utilities.commonUtil.waitAndClick;


public class ProductPage {
	public WebDriver ldriver;
	@FindBy(xpath="//p[normalize-space()='Catalog']")
	WebElement catalogMenu;

	@FindBy(xpath="//p[normalize-space()='Products']")
	WebElement productMenuList;

	@FindBy(xpath="//input[@id='SearchIncludeSubCategories']")
	WebElement searchSubCategoriesCheckBox;
	@FindBy(xpath="//select[@id='SearchWarehouseId']")
	WebElement searchWareHouse;
	public ProductPage(WebDriver rdriver) {
		ldriver = rdriver;
		PageFactory.initElements(rdriver, this);
	}

	public void userClickCatalogMenu() {
		catalogMenu.click();
	}
	public void userClickProductMenuItem(){
		waitAndClick(By.xpath("//p[normalize-space()='Products']"),ldriver);

	}
	public void userIsOnProductPage() {
		Assert.assertEquals("Products / nopCommerce administration",ldriver.getTitle());

		//here I tick box the sub categories, to demonstrate handling filter for checkbox type
		searchSubCategoriesCheckBox.click();
		//select dropdown
		Select select = new Select(searchWareHouse);
		select.selectByIndex(1);
	}
}
