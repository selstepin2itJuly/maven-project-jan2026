package testcases;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import testbase.DriverManager;

public class PIMTest {
	WebDriver dr;
	private DriverManager driverManager;

	@Test
	public void f() {
	}

	@BeforeMethod(alwaysRun = true)
	public void beforeMethod() throws IOException, InterruptedException {
		this.driverManager = new DriverManager();
		this.dr = driverManager.getDriverInstance();
		driverManager.loginPage().loginToApp("Admin", "admin123");
		Assert.assertTrue(driverManager.dashboardPage().isDashboardDisplayed());
		driverManager.testbase().attachScreenshotToTestNg(this.dr);
	}

	@AfterMethod(alwaysRun = true)
	public void afterMethod() {
		driverManager.dashboardPage().logout();
		driverManager.testbase().closeInstance();
	}

}
