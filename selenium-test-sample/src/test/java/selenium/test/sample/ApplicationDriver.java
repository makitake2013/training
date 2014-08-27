package selenium.test.sample;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import cucumber.api.java.After;
import cucumber.api.java.Before;

public class ApplicationDriver {

	private final static long DEFAULT_TIMEOUT = 1000;

	private WebDriver driver = new ChromeDriver();

	@Before
	public void init() throws Exception {
	}

	@After
	public void destroy() {
		driver.close();
	}

	public void open(String location) {
		driver.get(location);
		driver.manage().timeouts()
				.implicitlyWait(DEFAULT_TIMEOUT, TimeUnit.MILLISECONDS);
	}

	public void click(String selector) {
		driver.findElement(By.linkText(selector)).click();
		driver.manage().timeouts()
				.implicitlyWait(DEFAULT_TIMEOUT, TimeUnit.MILLISECONDS);
	}

	public void inputText(String name, String value) {
		driver.findElement(By.name(name)).sendKeys(value);
		driver.manage().timeouts()
				.implicitlyWait(DEFAULT_TIMEOUT, TimeUnit.MILLISECONDS);
	}

	public void submit() {
		driver.findElement(By.tagName("form")).submit();
	}

	public boolean isShown(String text) {
		WebElement content = driver.findElement(By.tagName("body"));
		return content.getText().contains(text);
	}

	public void capture(String scenario, String name) {
		try {
			FileUtils
					.copyFile(((TakesScreenshot) driver)
							.getScreenshotAs(OutputType.FILE), new File(
							"target/" + scenario + "/" + name + ".png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
