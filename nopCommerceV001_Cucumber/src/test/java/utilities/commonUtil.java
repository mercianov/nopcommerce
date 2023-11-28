package utilities;

import com.applitools.eyes.MatchLevel;
import com.applitools.eyes.selenium.Eyes;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class commonUtil {
    private static WebDriver driver;
    public static void waitForPageLoad() {
        try {
            Thread.sleep(250);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public static void refreshPage() {

        String currentURL = getCurrentURL();
        driver.navigate().to(currentURL);
    }
    public static String getCurrentURL() {
        return driver.getCurrentUrl();
    }
    public static void enterText(By elementLocator, String text, WebDriver driver) {
        System.out.println(text);
        System.out.println(elementLocator);
        waitForElement(driver,elementLocator);
        driver.findElement(elementLocator).clear();
        driver.findElement(elementLocator).sendKeys(text);
    }
    public static void waitAndClick(By elementLocator, WebDriver driver){
        waitForElement(driver,elementLocator);
        driver.findElement(elementLocator).click();
    }

    public static void waitForElement(WebDriver driver, By elementLocator) {
        FluentWait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(30))
                .pollingEvery(Duration.ofMillis(500))
                .ignoring(NoSuchElementException.class);
        wait.until(ExpectedConditions.visibilityOfElementLocated(elementLocator));
    }
    public static void swipeByElements (WebElement startElement, WebElement endElement, WebDriver driver) {
        int startX = startElement.getLocation().getX() + (startElement.getSize().getWidth() / 2);
        int startY = startElement.getLocation().getY() + (startElement.getSize().getHeight() / 2);

        int endX = endElement.getLocation().getX() + (endElement.getSize().getWidth() / 2);
        int endY = endElement.getLocation().getY() + (endElement.getSize().getHeight() / 2);
        Actions actions = new Actions(driver);
        actions.moveToElement(startElement, startX, startY)
                .pause(1000) // You can use pause to simulate a wait
                .moveToElement(endElement, endX, endY)
                .release()
                .build()
                .perform();
    }

    public static void swipeVertically(WebDriver driver, int pixelsToScroll) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0, arguments[0]);", pixelsToScroll);
    }
    public static void swipeHorizontally(WebDriver driver, int pixelsToScroll) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(arguments[0], 0);", pixelsToScroll);
    }
    public static void scrollUntilElementVisible(WebDriver driver, WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;

        // Scroll until the element is visible or a certain number of attempts is reached
        int attempts = 0;
        while (!isElementVisible(driver, element) && attempts < 3) {
            js.executeScript("window.scrollBy(0, 300);");
            attempts++;
        }
    }

    private static boolean isElementVisible(WebDriver driver, WebElement element) {
        try {
            return element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    public static void zoomIn(WebDriver driver) {
        executeZoomScript(driver, "document.body.style.zoom *= 1.2;");
    }

    public static void zoomOut(WebDriver driver) {
        executeZoomScript(driver, "document.body.style.zoom /= 1.2;");
    }
    private static void executeZoomScript(WebDriver driver, String script) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript(script);
    }

    public static void dragAndDrop(WebDriver driver, WebElement source, WebElement target) {
        Actions actions = new Actions(driver);

        // Perform drag-and-drop
        actions.clickAndHold(source)
                .moveToElement(target)
                .release()
                .build()
                .perform();
    }
    public static void compareScreenshootImage(WebDriver driver, String appName, String testName, String appURL){
        //this eyes applitools will do the job comparing images screenshot
        Eyes eyes = new Eyes();
        eyes.setApiKey("QC99mwcNjuAs1f2Ijq3K4WTrH4g9ZD2OxElCab1040QG3k110");
        eyes.open(driver, appName, testName);

        // Navigate to the page you want to capture
        driver.get(appURL);

        // Set match level to content: it's around 80% same
        eyes.setMatchLevel(MatchLevel.CONTENT);

        // Capture the entire page
        eyes.checkWindow("Full Page");

        // Close Applitools Eyes session
        eyes.close();

        // Close the WebDriver
        driver.quit();

    }
}
