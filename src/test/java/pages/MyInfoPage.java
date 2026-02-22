package pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import testbase.Base;

public class MyInfoPage extends Base {
	private static final Logger logger = LoggerFactory.getLogger(MyInfoPage.class);
	private WebDriver dr;

	public MyInfoPage(WebDriver driver) {
		logger.info("MyInfor Constructor");
		this.dr = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "a[class^='orangehrm-tabs-item']")
	private List<WebElement> infoMenu;

	public int getTheCountOfItemsinMenu() {
		return infoMenu.size();
	}

	public String[] getTheTextOfItemsinMenu() {

		String[] temp = new String[infoMenu.size()];
		int i = 0;
		for (WebElement e : infoMenu) {
			temp[i] = e.getText();
			i++;
		}
		return temp;
	}

}
