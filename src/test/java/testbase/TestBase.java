package testbase;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestBase extends Base {
	private static final Logger logger = LoggerFactory.getLogger(TestBase.class);
	private WebDriver driver;
	private Properties prop;

	public WebDriver getDriver() throws IOException {
		logger.info("Load config file");
		String configFile = "./src/test/resources/configs/config.properties";
		FileInputStream inputStream = new FileInputStream(configFile);
		logger.info("Load config file to properties");
		prop = new Properties();
		prop.load(inputStream);
		String browser = prop.getProperty("browser");
		logger.info("browser selected" + browser);
		if (browser.equalsIgnoreCase("chrome")) {
			driver = getChromeBrowser();
		} else if (browser.equalsIgnoreCase("firefox")) {
			driver = getFirefoxBrowser();
		} else if (browser.equalsIgnoreCase("edge")) {
			driver = getEdgeBrowser();
		} else {
			Throwable thr = new Throwable();
			thr.initCause(thr.getCause());
		}
		logger.info("Application url: " + prop.getProperty("url"));
		driver.get(prop.getProperty("url"));
		return driver;
	}

	private WebDriver getChromeBrowser() {
		ChromeOptions opt = new ChromeOptions(); // browser management
		// opt.addArguments("--start-fullscreen");
		opt.addArguments("--disable-popup-blocking");
		opt.addArguments("--disable-infobars");
		opt.setExperimentalOption("excludeSwitches", Arrays.asList("enable-automation"));
		driver = new ChromeDriver(opt);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		driver.manage().window().maximize();
		// driver.manage().window().minimize();
		// driver.manage().window().fullscreen();
		return driver;
	}

	private WebDriver getFirefoxBrowser() {
		FirefoxOptions opt = new FirefoxOptions(); // browser management
		// opt.addArguments("--start-fullscreen");
		opt.addArguments("--disable-popup-blocking");
		opt.addArguments("--disable-infobars");
		// opt.setExperimentalOption("excludeSwitches",
		// Arrays.asList("enable-automation"));
		driver = new FirefoxDriver(opt);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		driver.manage().window().maximize();
		// driver.manage().window().minimize();
		// driver.manage().window().fullscreen();
		return driver;
	}

	private WebDriver getEdgeBrowser() {
		EdgeOptions opt = new EdgeOptions(); // browser management
		// opt.addArguments("--start-fullscreen");
		opt.addArguments("--disable-popup-blocking");
		opt.addArguments("--disable-infobars");
		opt.setExperimentalOption("excludeSwitches", Arrays.asList("enable-automation"));
		driver = new EdgeDriver(opt);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		driver.manage().window().maximize();
		// driver.manage().window().minimize();
		// driver.manage().window().fullscreen();
		return driver;
	}

	public WebDriver getDriverInstance() {
		return this.driver;
	}

	public void closeInstance() {
		logger.info("Driver quit executed!");
		this.driver.quit();
	}

}
