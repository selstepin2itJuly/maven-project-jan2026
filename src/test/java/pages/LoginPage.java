package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import testbase.Base;

public class LoginPage extends Base {
	private static final Logger logger = LoggerFactory.getLogger(LoginPage.class);
	private WebDriver dr;

	public LoginPage(WebDriver driver) {
		this.dr = driver;
		logger.info("Page Factory intialized!");
		PageFactory.initElements(driver, this);
	}

	// locators @FindBy, @FindBys, @FindAll
	@FindBy(name = "username")
	private WebElement username;

	@FindBy(name = "password")
	private WebElement password;

	@FindBy(css = "[type=submit]")
	private WebElement loginbt;

	@FindBy(css = "[class$=oxd-alert-content-text]")
	private WebElement error;
	// functions

	public void loginToApp(String user, String pass) {
		logger.info("Login to Application-" + user + "-" + pass);
		username.sendKeys(user);
		password.sendKeys(pass);
		loginbt.click();

	}

	/*
	 * public DashboardPage loginToApp(String user, String pass) {
	 * logger.info("Login to Application-" + user + "-" + pass);
	 * username.sendKeys(user); password.sendKeys(pass); loginbt.click(); return new
	 * DashboardPage(dr); }
	 */

	public boolean isErrorDisplayed() {
		logger.info("check for the Error!");
		boolean flag = false;
		try {
			flag = error.isDisplayed();
		} catch (Exception e) {
			e.getMessage();
		}
		logger.info("check the Error! -->" + flag);
		return flag;
	}

	public String getErrorText() {
		logger.info("Get Error Text-" + error.getText());
		return error.getText();
	}

	public boolean isLoginPageDisplayed() {
		logger.info("check for the Login Page!");
		boolean flag = false;
		try {
			flag = username.isDisplayed();
		} catch (Exception e) {
			e.getMessage();
		}
		logger.info("check the Error! -->" + flag);
		return flag;
	}
}
