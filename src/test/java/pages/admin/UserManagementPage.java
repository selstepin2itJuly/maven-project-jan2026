package pages.admin;

import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pages.LoginPage;
import testbase.Base;

public class UserManagementPage extends Base{
	private static final Logger logger = LoggerFactory.getLogger(UserManagementPage.class);
	private WebDriver dr;
	public UserManagementPage(WebDriver driver)
	{
		this.dr=driver;
	}
}
