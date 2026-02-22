package testbase;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Reporter;

public class Base {
	private static final Logger logger = LoggerFactory.getLogger(Base.class);

	/// Fluent Wait Time
	public void fluentWaitForElementToBeClickable(By ele, WebDriver driver) {
		logger.info("fluentWaitForElementToBeClickable");
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(30L))
				.pollingEvery(Duration.ofSeconds(5L)).ignoring(NoSuchElementException.class);

		WebElement foo = wait.until(new Function<WebDriver, WebElement>() {
			@Override
			public WebElement apply(WebDriver driver) {
				return driver.findElement(ele);
			}
		});
	}

	/// Fluent Wait Time StaleElement added new comment
	public void WaitForStaleElement(By ele, WebDriver driver) {
		logger.info("WaitForStaleElement");
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(30L))
				.pollingEvery(Duration.ofSeconds(5L)).ignoring(StaleElementReferenceException.class);

		WebElement foo = wait.until(new Function<WebDriver, WebElement>() {
			@Override
			public WebElement apply(WebDriver driver) {
				return driver.findElement(ele);
			}
		});
	}

//////Fluent Wait Time
	public void fluentWaitForElementToBeClickable(WebElement ele, WebDriver driver) {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(30L))
				.pollingEvery(Duration.ofSeconds(5L)).ignoring(NoSuchElementException.class);

		WebElement foo = wait.until(new Function<WebDriver, WebElement>() {
			@Override
			public WebElement apply(WebDriver driver) {
				return ele;
			}
		});
	}

	/// Explicit Wait Time
	public void waitForElementToBeClickable(WebElement ele, WebDriver driver) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(ele));
	}

	/// Explicit Wait Time
	public void waitForStaleElementToBeClickable(WebElement ele, WebDriver driver) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.stalenessOf(ele));
	}

	/// Explicit Wait Time
	public void waitForElementToBeRefreshed(WebElement ele, WebDriver driver) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.refreshed(ExpectedConditions.elementToBeClickable(ele)));
	}

	/// Explicit Wait Time
	public void waitForElementToBeVisible(WebElement ele, WebDriver driver) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(ele));
	}

	public void waitForAlertToBeVisible(WebElement ele, WebDriver driver) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.alertIsPresent());
	}

	public void scrollElementIntoView(WebElement ele, WebDriver driver) {
		JavascriptExecutor je = (JavascriptExecutor) driver;
		je.executeScript("arguments[0].scrollIntoView(false);", ele);
		je.executeScript("window.scrollBy(0,400)", "");
	}

	public void scrollToElementByActions(WebElement ele, WebDriver driver) {
		Actions ac = new Actions(driver);
		ac.scrollToElement(ele).perform();
		((JavascriptExecutor) driver).executeScript("window.scrollBy(600,0)", "");
	}

	public void attachScreenshotToTestNg(WebDriver driver) throws InterruptedException {
		Thread.sleep(3000);
		TakesScreenshot tc = (TakesScreenshot) driver;
		String src = tc.getScreenshotAs(OutputType.BASE64);
		// Embed screenshot in report
		String htmlImage = "<img src=\"data:image/png;base64, " + src + "\" height=\"550\" width=\"700\" />";
		Reporter.log(htmlImage);
	}

	public void captureScreen(WebDriver driver) throws IOException {
		String folder = "screenshots" + folerWithDate();
		File screenshot = new File(folder);
		if (!screenshot.isDirectory()) {
			screenshot.mkdirs();
		} else {
			System.out.println("Folder available");
		}
		// screen capture
		TakesScreenshot tc = (TakesScreenshot) driver;
		File file = tc.getScreenshotAs(OutputType.FILE);
		FileHandler.copy(file, new File("./" + folder + "/" + dateTime() + "-image.jpg"));// png and jpg
	}

	private static String dateTime() {
		Date dt = new Date();
		System.out.println(dt);
		SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MMM-dd-hh-mm-ss-SSS");
		String date = sdf.format(dt);
		System.out.println(date);
		return date;
	}

	private static String folerWithDate() {
		Date dt = new Date();
		System.out.println(dt);
		SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MMM-dd");
		String date = sdf.format(dt);
		System.out.println(date);
		return date;
	}
}
