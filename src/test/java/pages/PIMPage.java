package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PIMPage {

	private static final Logger logger = LoggerFactory.getLogger(PIMPage.class);
	private WebDriver dr;

	public PIMPage(WebDriver driver) {
		this.dr = driver;
		PageFactory.initElements(driver, this);
	}
}
