package lib.resources;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class custFns {

	public static WebDriver driver = null;
	public WebElement element;
	public int counter;
	public static WebDriverWait wait;
	public Properties prop = new Properties();

	/* initializing web driver and navigating to given URL */
	public void startApp(String url) throws IOException {

		File f = new File("./src/main/resources/drivers/chromedriver.exe");
		System.setProperty("webdriver.chrome.driver", f.getCanonicalPath());

		driver = new ChromeDriver();
		driver.get(url);
	}

	/*function to access properties file*/
	public Properties getData() throws IOException {

		FileInputStream fis = new FileInputStream("./src/main/resources/env.properties");
		prop.load(fis);
		return prop;
	}

	/* method to send coundDown timer value */
	public void sendValue() throws IOException {

		element = driver.findElement(By.xpath("//input[@id='EggTimer-start-time-input-text']"));
		element.sendKeys(prop.getProperty("value"));

		try {
			custFns.takeSnapShot();
		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	/* returning contDown timer value in int datatype after casting */
	public int counterValue() {
		
		String val = driver.findElement(By.xpath("//input[@id='EggTimer-start-time-input-text']"))
				.getAttribute("value");

		counter = Integer.parseInt(val);
		return counter;
	}

	public void submit() {
		driver.findElement(By.xpath("//button[contains(text(),'Start')]")).click();
	}

	/*function to check if timer is equal to and return value in int datatype after casting*/
	public int countDownToZero() {

		wait = new WebDriverWait(driver, counter + 1);

		String wb = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[starts-with(text(),'0')]")))
				.getText();

		String subStrVal = wb.substring(0, 1);
		int zeroDigit = Integer.parseInt(subStrVal);

		return zeroDigit;
	}

	/*function to check alert is present*/
	public void alertCheck() {

		wait = new WebDriverWait(driver, counter + 2);

		if (wait.until(ExpectedConditions.alertIsPresent()) == null) {
			System.out.println("Alert was not present");
		} else {

			System.out.println();
			System.out.println("=================== Alert was present...=======================");
		}

		driver.switchTo().alert().accept();
	}

	public String verifyTimeExpried() {

		wait = new WebDriverWait(driver, counter);

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'Time Expired!')]")));

		String TimeExpired = driver.findElement(By.xpath("//span[contains(text(),'Time Expired!')]")).getText();
		Assert.assertEquals(TimeExpired, "Time Expired!");

		return TimeExpired;
	}

	public static void takeSnapShot() throws Exception {

		// Convert web driver object to TakeScreenshot
		TakesScreenshot scrShot = ((TakesScreenshot) driver);

		// Call getScreenshotAs method to create image file
		File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);

		// store image file to new destination
		try {
			FileUtils.copyFile(SrcFile, new File("./src/main/resources/logs/timehome.png"));
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

	}

}
