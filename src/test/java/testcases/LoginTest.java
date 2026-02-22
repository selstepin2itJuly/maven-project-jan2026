package testcases;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import pages.MyInfoPage;
import testbase.DriverManager;
import utitities.ExcelUtilities;

public class LoginTest {
	private static final Logger logger = LoggerFactory.getLogger(MyInfoPage.class);
	DriverManager driverManager;
	WebDriver dr;

	@Test(priority = 1, description = "Verify login is successful", groups = { "sanity", "reg" })
	public void TC001_loginTest_success() throws IOException, InterruptedException {
		logger.info("TC001_Login_Success Test Started");
		String file = ExcelUtilities.getFilePath("testdata.xlsx");
		String user = ExcelUtilities.readCellData(file, 1, 0);
		String pass = ExcelUtilities.readCellData(file, 1, 1);
		driverManager.loginPage().loginToApp(user, pass);
		Assert.assertTrue(driverManager.dashboardPage().isDashboardDisplayed());
		driverManager.testbase().attachScreenshotToTestNg(dr);
		driverManager.dashboardPage().logout();
		Assert.assertTrue(driverManager.loginPage().isLoginPageDisplayed());
		logger.info("TC001_Login_Success Test Ended");
		driverManager.testbase().attachScreenshotToTestNg(dr);
	}

	@Test(description = "Verify Login failed", groups = { "reg" })
	public void TC002_loginTest_failed() throws IOException, InterruptedException {
		logger.info("TC002_Login_Failed Test Started");
		driverManager.loginPage().loginToApp("Admin1", "admin123");
		// Assert.assertTrue(driverManager.loginPage().isErrorDisplayed());
		new SoftAssert().assertTrue(driverManager.loginPage().isErrorDisplayed());
		driverManager.testbase().attachScreenshotToTestNg(dr);
		Assert.assertEquals(driverManager.loginPage().getErrorText(), "Invalid credentials");
		logger.info("TC002_Login_failed Test Ended");
	}

	@Test(dataProvider = "UserData", dependsOnMethods = "TC002_loginTest_failed", enabled = false, priority = 2, description = "Verify multiple users")
	public void TC012_Verify_Multiple_Login_Users(String user, String pass) {
		logger.info("TC012_Verify_Multiple_Login_Users Test Started");
		driverManager.loginPage().loginToApp(user, pass);
		Assert.assertTrue(driverManager.dashboardPage().isDashboardDisplayed());
		driverManager.dashboardPage().logout();
		Assert.assertTrue(driverManager.loginPage().isLoginPageDisplayed());
		logger.info("TC012_Verify_Multiple_Login_Users Test Ended");
	}

	@BeforeMethod(alwaysRun = true, description = "Before method")
	public void beforeMethod() throws IOException {
		this.driverManager = new DriverManager();
		this.dr = this.driverManager.getDriverInstance();
	}

	@AfterMethod(alwaysRun = true, description = "After Method")
	public void afterMethod() {
		driverManager.testbase().closeInstance();
	}

	@DataProvider(name = "UserData")
	public String[][] getUserData() {
		return data();
	}

	private String[][] data() {
		String x1[] = { getUsername(1), getPassword(1) };
		String x2[] = { getUsername(2), getPassword(1) };
		String x3[] = { getUsername(3), getPassword(1) };
		String x4[] = { getUsername(4), getPassword(1) };
		String[][] tmp = { x1, x2, x3, x4 };
		return tmp;
	}

	public String getUsername(int i) {
		String user[] = { "Admin", "Admin", "Admin12", "Admin12" };
		return user[i];
	}

	public String getPassword(int i) {
		String pass[] = { "admin123", "Admin123", "admin123", "admin124" };
		return pass[i];
	}
}
