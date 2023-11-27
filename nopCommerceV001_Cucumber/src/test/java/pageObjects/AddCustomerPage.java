package pageObjects;

import io.cucumber.java.eo.Se;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

import static utilities.commonUtil.*;

public class AddCustomerPage {
    public WebDriver ldriver;
    public AddCustomerPage(WebDriver rdriver)
    {
        ldriver=rdriver;
        PageFactory.initElements(rdriver,this);
    }
    By lnkCustomers_menu = By.xpath("//a[@href='#']//p[contains(text(),'Customers')]");
    By lnkCustomers_menuitem = By.xpath("//a[@href='/Admin/Customer/List']//p[contains(text(),'Customers')]");
    By addNewCustomer = By.xpath("//a[@class='btn btn-primary']");
    By txtEmail = By.id("Email");
    By txtPassword = By.id("Password");
    By txtFirstName = By.id("FirstName");
    By txtLastName = By.id("LastName");
    By radioMale = By.id("Gender_Male");
    By radioFemale = By.id("Gender_Female");
    By dateOfBirth = By.id("DateOfBirth");
    By company = By.id("Company");
    By txtcustomerRoles = By.xpath("//div[@class='input-group-append input-group-required']//input[@role='listbox']");
    //By txtcustomerRoles = By.xpath("//div[@class='k-multiselect-wrap k-floatwrap']");
    By lstitemAdmin = By.xpath("//li[contains(text(),'Administrators')]");
    By lstitemRegist = By.xpath("//li[contains(text(),'Registered')]");
    By lstitemGuest = By.xpath("//li[contains(text(),'Guests')]");
    By lstitemVendors = By.xpath("//li[contains(text(),'Vendors')]");
    By drpmgrOfVendor = By.xpath("//*[@id='VendorId']");
    By adminComment = By.xpath("//textarea[@id='AdminComment']");
    By btnSave = By.xpath("//button[@name='save']");

    public String getPageTitle(){
        return ldriver.getTitle();
    }
    public void clickOnCustomersMenu(){
        ldriver.findElement(lnkCustomers_menu).click();
    }
    public void clickOnCustomersMenuItem(){
        ldriver.findElement(lnkCustomers_menuitem).click();
    }
    public void clickOnAddNew(){
        ldriver.findElement(addNewCustomer).click();
    }
    public void setEmail(String email){
        ldriver.findElement(txtEmail).sendKeys(email);
    }
    public void setPassword(String password){
        ldriver.findElement(txtPassword).sendKeys(password);
    }
    public void setCustomerRoles(String role) throws InterruptedException{
        if(!role.equals("Vendors")){
            ldriver.findElement(By.xpath("//span[@title='delete']")).click();
        }
        ldriver.findElement(txtcustomerRoles).click();
        WebElement listitem;
        Thread.sleep(3000);
        if(role.equals("Administrators"))
        {
            listitem=ldriver.findElement(lstitemAdmin);
        } else if (role.equals("Guests")) {
            listitem=ldriver.findElement(lstitemGuest);
        } else if (role.equals("Registered")) {
            listitem=ldriver.findElement(lstitemRegist);
        } else if (role.equals("Vendors")) {
            listitem=ldriver.findElement(lstitemVendors);
        } else {
            listitem=ldriver.findElement(lstitemGuest);
        }
        JavascriptExecutor js = (JavascriptExecutor) ldriver;
        js.executeScript("arguments[0].click();",listitem);
    }
    public void setGender(String gender){
        if(gender.equals("Female"))
        {
            ldriver.findElement(radioFemale).click();
        } else{
            ldriver.findElement(radioMale).click();//also default
        }
    }
    public void setManagerOfVendor(String value)
    {
        Select drp=new Select(ldriver.findElement(drpmgrOfVendor));
        drp.selectByVisibleText(value);
    }
    public void setFirstName(String fname){
        ldriver.findElement(txtFirstName).sendKeys(fname);
    }
    public void setLastName(String lname){
        ldriver.findElement(txtLastName).sendKeys(lname);
    }

    public void setDob(String dob){
        ldriver.findElement(dateOfBirth).sendKeys(dob);
    }
    public void setCompanyName(String comname){
        ldriver.findElement(company).sendKeys(comname);
    }
    public void setAdminComment(String comment){
        ldriver.findElement(adminComment).sendKeys(comment);
        JavascriptExecutor js = (JavascriptExecutor) ldriver;
        js.executeScript("window.scrollBy(0,350)", "");
    }
    public void clickOnSave()
    {
        ldriver.findElement(btnSave).click();
    }

    public void isOnCustomerPage(){
        //verify element txtEmail not found
        List<WebElement> li = ldriver.findElements(txtEmail);
        Assert.assertEquals(0,li.size());

        //verify element SearchEmail is found
        ldriver.findElement(By.id("SearchEmail")).isDisplayed();

        //scroll until element
        scrollUntilElementVisible(ldriver,ldriver.findElement(By.xpath("//th[contains(text(),'Email')]")));

        //swipe
        swipeVertically(ldriver,-300); //swipe up
        swipeHorizontally(ldriver,200);

        //zoom in and out
        zoomIn(ldriver);
        zoomOut(ldriver);

        //drag and drop, but this is just example of syntax. no function on this web can be operated as drag n drop
        //dragAndDrop(ldriver,ldriver.findElement(By.xpath("//p[normalize-space()='Manufacturers']")),ldriver.findElement(By.id("AdminComment")));

    }
}
