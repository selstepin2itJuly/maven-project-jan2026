package testbase;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pages.DashboardPage;
import pages.LoginPage;
import pages.MyInfoPage;
import pages.admin.UserManagementPage;
import utitities.SoftAssertions;

public class DriverManager {
	private static final Logger logger = LoggerFactory.getLogger(DriverManager.class);
	private TestBase testbase;
	private LoginPage loginpage;
	private MyInfoPage myInfoPage;
	private UserManagementPage userManagementPage;
	private DashboardPage dashbboard;
	WebDriver driver;

	public DriverManager() throws IOException {
		logger.info("Driver Manager constructor intialized!");
		this.testbase = new TestBase();
		this.driver = testbase.getDriver();
		this.loginpage = new LoginPage(driver);
		this.myInfoPage = new MyInfoPage(driver);
		this.userManagementPage = new UserManagementPage(driver);
		this.dashbboard = new DashboardPage(driver);
	}

	public WebDriver getDriverInstance() {
		return testbase.getDriverInstance();
	}

	public TestBase testbase() {
		logger.info("Testbase intialized!");
		return testbase;
	}

	public LoginPage loginPage() {
		logger.info("loginPage intialized!");
		return loginpage;
	}

//	public MyInfoPage myInfoPage()
//	{
//		logger.info("MyInfoPage intialized!");
//		return myInfoPage;
//	}

	public UserManagementPage userManagementPage() {
		logger.info("userManagementPage intialized!");
		return userManagementPage;
	}

	public DashboardPage dashboardPage() {
		logger.info("DashbboardPage intialized!");
		return dashbboard;
	}

	public SoftAssertions softAssertions() {
		logger.info("Soft Assertions");
		return new SoftAssertions();
	}
}
