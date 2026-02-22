package testcases;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import testbase.DriverManager;

public class MyInfoTest {

	WebDriver dr;
	private DriverManager driverManager;

	@Test(priority = 1, description = "Verify menu item count", groups = { "sanity" })
	public void TC005_Verify_The_Menu_Item_Count_On_MyInfo() throws IOException, InterruptedException {
		int act = driverManager.dashboardPage().clickOnMyInfoTab().getTheCountOfItemsinMenu();
		Assert.assertEquals(act, 10);
		driverManager.testbase().attachScreenshotToTestNg(dr);

	}

	@Test(priority = 2, description = "Verify menu item texts", groups = { "reg" })
	public void TC006_Verify_The_Menu_Item_Text_On_MyInfo() throws IOException, InterruptedException {
		// String[] exp = { "Personal Details", "Contact Details", "Emergency Contacts",
		// "Dependents", "Immigration",
		// "Job", "Salary", "Tax Exemptions", "Report-to", "Qualifications",
		// "Memberships" };
		String[] exp = { "Personal Details", "Contact Details", "Emergency Contacts", "Dependents", "Immigration",
				"Job", "Salary", "Report-to", "Qualifications", "Memberships" };
		String[] act = driverManager.dashboardPage().clickOnMyInfoTab().getTheTextOfItemsinMenu();
		driverManager.softAssertions().assertArrays(act, exp);
		driverManager.testbase().attachScreenshotToTestNg(dr);
		// Assert.assertEquals(act, exp);
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
