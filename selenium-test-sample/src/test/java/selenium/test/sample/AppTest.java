package selenium.test.sample;

import java.io.File;

import junit.framework.TestCase;

import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AppTest extends TestCase {

	@Test
	public void testEcho() throws Exception {
		WebDriver driver = new ChromeDriver();
		try {
			driver.get("http://localhost:8080/spring-mvc-sample/echo/");
			Wait<WebDriver> wait = new WebDriverWait(driver, 30);

			WebElement button = wait.until(ExpectedConditions
					.elementToBeClickable(By.tagName("input")));
			driver.findElement(By.id("name")).sendKeys("test input");
			button.submit();
			
			wait.until(ExpectedConditions.titleIs("Echo Application"));
			assertTrue(driver.getPageSource().contains("Hello test input!"));

			FileUtils
					.copyFile(((TakesScreenshot) driver)
							.getScreenshotAs(OutputType.FILE), new File(
							"target/" + this.getClass().getPackage().getName()
									+ this.getClass().getSimpleName()
									+ "#testEcho.png"));
		} finally {
			driver.quit();
		}
	}

}
