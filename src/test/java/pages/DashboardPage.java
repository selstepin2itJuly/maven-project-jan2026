package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DashboardPage {
	private static final Logger logger = LoggerFactory.getLogger(DashboardPage.class);
	private WebDriver dr;

	public DashboardPage(WebDriver driver) {
		this.dr = driver;
		PageFactory.initElements(driver, this);
	}

	// locator

	@FindBy(xpath = "//*[contains(@class,'header-breadcrumb-module')]")
	private WebElement headerBreadcrum;

	@FindBy(css = "[class$='caret-down-fill oxd-userdropdown-icon']")
	private WebElement logoutIcon;

	@FindBy(linkText = "Logout")
	private WebElement logout;

	@FindBy(xpath = "//span[text()='My Info']")
	private WebElement myInfotab;

	//
	public boolean isDashboardDisplayed() {
		logger.info("check the Error!");
		boolean flag = false;
		try {
			flag = headerBreadcrum.isDisplayed();
		} catch (Exception e) {
			e.getMessage();
		}
		logger.info("check the Error! -->" + flag);
		return flag;
	}

	public void logout() {
		logger.info("Logout of Application!");
		logoutIcon.click();
		logout.click();

	}

	public MyInfoPage clickOnMyInfoTab() {
		myInfotab.click();
		return new MyInfoPage(dr);
	}
}
