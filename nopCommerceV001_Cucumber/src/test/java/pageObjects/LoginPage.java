package pageObjects;

import com.applitools.eyes.MatchLevel;
import com.applitools.eyes.selenium.Eyes;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.commonUtil;

import java.util.List;

import static utilities.commonUtil.enterText;
import static utilities.commonUtil.compareScreenshootImage;

public class LoginPage {
	public WebDriver ldriver;
	
	public LoginPage(WebDriver rdriver) {
		ldriver = rdriver;
		PageFactory.initElements(rdriver, this);
	}
	
	@FindBy(id="Email")
	@CacheLookup
	WebElement txtEmail;
	
	@FindBy(id="Password")
	@CacheLookup
	WebElement txtPassword;
	
	@FindBy(xpath="//button[@type='submit']")
	@CacheLookup
	WebElement btnLogin;
	
	@FindBy(xpath="//a[normalize-space()='Logout']")
	WebElement buttonLogout;
	public static commonUtil cu;
	
	public void setUserEmail(String uname) {
		enterText(By.xpath("//input[@id='Email']"),uname,ldriver);
	}
	public void setPassword(String pwd) {
		txtPassword.clear();
		txtPassword.sendKeys(pwd);
	}
	public void clickLogin() {
		btnLogin.click();
	}
	
	public void clickLogout() {

		JavascriptExecutor js = (JavascriptExecutor) ldriver;
		js.executeScript("arguments[0].click();",ldriver.findElement(By.xpath("//a[normalize-space()='Logout']")));
	}

	public void isOnLoginPage() {
		String appName = "https://admin-demo.nopcommerce.com/login";
		String testName = "Admin area demo";
		String appUrl = "http://admin-demo.nopcommerce.com/login";
		compareScreenshootImage(ldriver, appName, testName, appUrl);
	}
}
