package stepDefinitions;

import org.apache.commons.lang.RandomStringUtils;
import org.openqa.selenium.WebDriver;
import pageObjects.AddCustomerPage;
import pageObjects.LoginPage;
import pageObjects.ProductPage;

public class BaseClass {
    public WebDriver driver;
    public LoginPage lp;
    public AddCustomerPage addCust;
    public ProductPage prod;

    //create random unique email name
    public static String randomestring(){
        String generatedString1 = RandomStringUtils.randomAlphabetic(5);
        return generatedString1;
    }
}
